package com.example.contact.dto;

import com.example.contact.entity.Mail;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
     private int id;
     @NotNull(message = "Destination cannot be null")
    private String destination;
    @NotNull(message = "Object cannot be null")
    private String object;
    @NotNull(message = "Message cannot be null")
    private String message;

    public static MailDto fromEntity(Mail mail){
        return MailDto.builder()
                .id(mail.getId())
                .destination(mail.getDestination())
                .object(mail.getObject())
                .message(mail.getMessage())
                .build();

    }
    public static Mail toEntity(MailDto mailDto){
        return Mail.builder()
                .id(mailDto.getId())
                .destination(mailDto.getDestination())
                .object(mailDto.getObject())
                .message(mailDto.getMessage())
                .build();
    }

}
