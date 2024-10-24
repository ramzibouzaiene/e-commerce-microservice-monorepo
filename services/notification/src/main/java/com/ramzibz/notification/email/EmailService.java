package com.ramzibz.notification.email;

import com.ramzibz.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ramzibz.notification.email.EmailTemplate.ORDER_CONFIRMATION;
import static com.ramzibz.notification.email.EmailTemplate.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public void emailSendHandler(
            Map<String, Object> variables,
            String destinationEmail,
            EmailTemplate emailTemplate
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom("ramzibouzaiene00@gmail.com");

        final String templateName = emailTemplate.getTemplate();

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(emailTemplate.getSubject());

        try {
            String htmlTemplate = springTemplateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException exception) {
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        try {
            this.emailSendHandler(variables, destinationEmail, ORDER_CONFIRMATION);
        } catch (MessagingException e) {
            log.error("ERROR - Failed to send order confirmation email to {}: {}", destinationEmail, e.getMessage());
        }
    }

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        try {
            this.emailSendHandler(variables, destinationEmail, PAYMENT_CONFIRMATION);
        } catch (MessagingException e) {
            log.error("ERROR - Failed to send payment success email to {}: {}", destinationEmail, e.getMessage());
        }
    }
}
