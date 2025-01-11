package org.service.HfhServer.configuration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "org.hfh.sendgrid")
public class SendGridConfigProperties {

    @NotBlank
    private String apiKey;

    @Email
    @NotBlank
    private String senderMail;

    @NotBlank
    private String senderName;

    @NotBlank
    private String templateId;

    public void setTemplateId(@NotBlank String templateId) {
        this.templateId = templateId;
    }

    public @NotBlank String getTemplateId() {
        return templateId;
    }

    public void setApiKey(@NotBlank String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSenderMail(@Email @NotBlank String senderMail) {
        this.senderMail = senderMail;
    }

    public void setSenderName(@NotBlank String senderName) {
        this.senderName = senderName;
    }

    public @NotBlank String getApiKey() {
        return apiKey;
    }

    public @Email @NotBlank String getSenderMail() {
        return senderMail;
    }

    public @NotBlank String getSenderName() {
        return senderName;
    }
}
