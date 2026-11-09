package com.example.emailservice.service;

import com.example.emailservice.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderInvoice(OrderCreatedEvent event) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.getCustomerEmail());
            message.setSubject("Xác nhận đặt hàng thành công - Mã: " + event.getOrderId());

            String emailContent = String.format(
                    "Xin chào quý khách,\n\n" +
                            "Đơn hàng %s của bạn đã được tiếp nhận thành công:\n" +
                            "- Tên sản phẩm: %s\n" +
                            "- Số lượng: %d\n" +
                            "- Tổng thanh toán: %,.0f VNĐ\n" +
                            "- Thời gian đặt: %s\n\n" +
                            "Cảm ơn bạn đã tin tưởng mua sắm!",
                    event.getOrderId(), event.getProductName(), event.getQuantity(), event.getTotalPrice(), event.getTimestamp()
            );

            message.setText(emailContent);
            mailSender.send(message);
            log.info(">>> [Email-Service] Đã gửi email xác nhận cho khách hàng [{}] thành công!", event.getCustomerEmail());
        } catch (Exception ex) {
            log.error(">>> [Email-Service] Lỗi khi gửi email xác nhận: {}", ex.getMessage());
        }
    }
}