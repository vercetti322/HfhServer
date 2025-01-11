package org.service.HfhServer.controller;

import org.service.HfhServer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestParam String receiverMail) {
        try {
            this.mailService.dispatchMail(receiverMail);
            return ResponseEntity.ok("Mail sent successfully to " + receiverMail);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to send the mail: "
                    + e.getMessage());
        }
    }
}
