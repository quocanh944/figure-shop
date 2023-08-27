package tdtu.vn.figure_shop.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


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
            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".header { background-color: #f2f2f2; padding: 10px; }"
                    + ".content { padding: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='header'><h1>Order Confirmation</h1></div>"
                    + "<div class='content'>"
                    + "<p>Thank you for your order! Here are your order details:</p>"
                    + "<pre>" + orderDetails + "</pre>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            helper.setText(emailContent,true);
            javaMailSender.send(message);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
