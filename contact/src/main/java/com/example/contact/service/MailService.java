package com.example.contact.service;

import com.example.contact.dto.MailDto;
import com.example.contact.entity.Mail;
import com.example.contact.repository.MailRepository;
import com.example.contact.validator.ObjectsValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailRepository mailRepository;
    private final JavaMailSender javaMailSender;
    private final ObjectsValidator<MailDto> validator;

    public long sendMail(MailDto mailDto) throws MailException {
        validator.validate(mailDto);
        System.out.println("Sending email...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("mahdibouazizi10@gamil.com");
        mail.setTo(mailDto.getDestination());
        mail.setSubject(mailDto.getObject());
        mail.setText(mailDto.getMessage());
        javaMailSender.send(mail);
        return mailRepository.save(MailDto.toEntity(mailDto)).getId();
    }

    public void sendMailWithAttachment(MailDto mailDto) throws MailException, MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(mailDto.getDestination());
        helper.setSubject(mailDto.getObject());
        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(mail);
    }

    public List<MailDto> getAllMail() {
        return mailRepository.findAll().stream()
                .map(MailDto::fromEntity)
                .collect(Collectors.toList());

    }

   public MailDto deleteMail(Integer id) {
        var mail = mailRepository.findById(id).orElseThrow();
        mailRepository.deleteById(id);
        return MailDto.fromEntity(mail);
    }


}
