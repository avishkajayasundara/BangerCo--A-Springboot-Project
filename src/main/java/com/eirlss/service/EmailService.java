package com.eirlss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
public class EmailService {
    String filepath = "C:\\Users\\User\\Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\webapp\\images\\licences\\";

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String subject, String messageBody, String from, String to, String fileName) {
        try {
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(messageBody);
//            BodyPart attachment = new MimeBodyPart();
//            attachment.setContent(new File(filepath + fileName),"licence");
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,MULTIPART_MODE_MIXED);
//            mimeMessageHelper.getMimeMultipart().addBodyPart(messageBodyPart);
//            mimeMessageHelper.getMimeMultipart().addBodyPart(attachment);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(new InternetAddress(from, from));
            mimeMessageHelper.setTo(to.split("[,;]"));
            mimeMessageHelper.setText(messageBody);
            mimeMessageHelper.addAttachment(fileName, new File(filepath + fileName));
            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MailException | MessagingException | UnsupportedEncodingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
