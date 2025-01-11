package org.service.HfhServer.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.service.HfhServer.configuration.SendGridConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {

    private static final String EMAIL_ENDPOINT = "mail/send";

    private final SendGrid sendGrid;

    private final SendGridConfigProperties configProperties;

    @Autowired
    public MailService(SendGrid sendGrid, SendGridConfigProperties configProperties) {
        this.sendGrid = sendGrid;
        this.configProperties = configProperties;
    }

    public void dispatchMail(String emailId) throws IOException {
        // fetching sender mail, sender name and template ID
        String fromMail = this.configProperties.getSenderMail();
        Mail mail = getMail(emailId, fromMail);

        // preparing mail request
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(EMAIL_ENDPOINT);
        request.setBody(mail.build());

        // sending the mail
        sendGrid.api(request);
    }

    private Mail getMail(String emailId, String fromMail) {
        String templateId = this.configProperties.getTemplateId();
        String senderName = this.configProperties.getSenderName();

        // preparing mail structure
        Email receiverMail = new Email(emailId);
        Email senderMail = new Email(fromMail, senderName);
        Content content = new Content("text/html", "placeholder");
        Mail mail = new Mail(senderMail, "placeholder", receiverMail, content);

        // adding sendgrid html template
        mail.setTemplateId(templateId);
        return mail;
    }
}
