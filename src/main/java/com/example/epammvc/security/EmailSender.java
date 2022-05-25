package com.example.epammvc.security;

public class EmailSender {
    //нужно добавить в бд ключ
    //и осталось получать емэйл
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("a3310010752@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.printf("Mail Sent success");
    }
}
