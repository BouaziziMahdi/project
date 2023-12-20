package com.projectIntegration.authentification.dto;

import com.projectIntegration.authentification.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class Userdto {
    private Integer id ;
    @NotNull(message = "First name is required")
    private String firstName ;
    @NotNull(message = "Last name is required")
    private String lastName ;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email ;
    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password ;
    private boolean active ;

    public  static Userdto fromEntity(User user){
        return Userdto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .build();
    }
    public static User toEntity(Userdto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
