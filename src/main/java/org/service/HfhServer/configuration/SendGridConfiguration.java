package org.service.HfhServer.configuration;

import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SendGridConfigProperties.class)
public class SendGridConfiguration {

    private final SendGridConfigProperties configProperties;

    public SendGridConfiguration(SendGridConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Bean
    public SendGrid sendGrid() {
        String apiKey = configProperties.getApiKey();
        return new SendGrid(apiKey);
    }

    @Bean
    public Email senderMail() {
        String senderMail = configProperties.getSenderMail();
        String senderName = configProperties.getSenderName();
        return new Email(senderMail, senderName);
    }
}
