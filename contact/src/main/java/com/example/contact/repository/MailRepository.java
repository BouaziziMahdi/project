package com.example.contact.repository;

import com.example.contact.dto.MailDto;
import com.example.contact.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {


}
