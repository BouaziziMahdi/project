package com.example.contact.Controller;

import com.example.contact.dto.MailDto;
import com.example.contact.entity.Mail;
import com.example.contact.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
@Tag(name = "Mail", description = "Mail API")
@CrossOrigin("*")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public long sendMail(@RequestBody MailDto mailDto) {
        return mailService.sendMail(mailDto);
    }

    @PostMapping("/sendWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(@RequestBody MailDto mailDto) throws MessagingException {
        mailService.sendMailWithAttachment(mailDto);
        return ResponseEntity.ok("Mail sent successfully.");
    }
    @GetMapping("/getAll")
    public List<MailDto> getAllMails() {
        return mailService.getAllMail();
    }
  @DeleteMapping("/delete/{id}")
    public MailDto deleteMail(@PathVariable Integer id) {
        return mailService.deleteMail(id);
    }


}
