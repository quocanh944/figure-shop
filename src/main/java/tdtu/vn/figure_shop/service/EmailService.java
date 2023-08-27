package tdtu.vn.figure_shop.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendOrderConfirmationEmail(String recipientEmail, String orderDetails) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true); // True indicates multipart message
            helper.setTo(recipientEmail);
            helper.setSubject("Order Confirmation");
            Resource resource = new ClassPathResource("orderMail.html");
            String emailContent = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            emailContent = emailContent.replace("{ORDER_DETAILS}", orderDetails);
            helper.setText(emailContent,true);
            javaMailSender.send(message);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
