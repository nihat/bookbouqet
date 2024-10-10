package com.bookbouqet.user;


import jakarta.persistence.*;
import lombok.*;
import org.aspectj.apache.bcel.classfile.LocalVariable;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Token {

    @Id
    @Generated
    @GeneratedValue
    private Integer id;

    private String token;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private User user;

}
