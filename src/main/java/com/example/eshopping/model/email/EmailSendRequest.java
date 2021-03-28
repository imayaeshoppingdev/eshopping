package com.example.eshopping.model.email;

public class EmailSendRequest {

    private String from;
    private String fromName;
    private String to;
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }

    public String getFromName() {
        return fromName;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
