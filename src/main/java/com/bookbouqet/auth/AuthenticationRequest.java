package com.bookbouqet.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not well formatted")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8 ,message = "Password must be 8 characters long minimum")
    private String password;
}
