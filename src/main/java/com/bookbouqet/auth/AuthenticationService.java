package com.bookbouqet.auth;

import com.bookbouqet.email.EmailService;
import com.bookbouqet.email.EmailTemplateName;
import com.bookbouqet.role.RoleRepository;
import com.bookbouqet.security.JWTService;
import com.bookbouqet.user.Token;
import com.bookbouqet.user.TokenRepository;
import com.bookbouqet.user.User;
import com.bookbouqet.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

private final UserRepository userRepository;
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;
private final TokenRepository tokenRepository;
private final EmailService emailService;
private final AuthenticationManager authenticationManager;
private final JWTService jwtService;

@Value("${application.mailing.frontend.activation-url}")
private String activationURL;

@Transactional
    public void register(RegistrationRequest registrationRequest) throws MessagingException {

        var userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new RuntimeException("User Role Not Found")
        );

        var user = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationURL,
                newToken,
                "Account Activation"
                );
    }

    public String generateAndSaveActivationToken(User user) {
        //generate a new token
        String generatedToken = generateActivationToken(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;

    }

    private String generateActivationToken(int length) {
        String characters = "0123456789";
        StringBuilder builder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var claims = new HashMap<String ,Object>();
        User user = (User) auth.getPrincipal();
        claims.put("fullName", user.fullName());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    @Transactional
    public void activateAccount(String token) throws MessagingException {
        var savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token Not Found"));
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Token Expired , a fresh token is sent to same email address");
        }

        var user = userRepository.findByEmail(savedToken.getUser().getEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setEnabled(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
