package com.example.eshopping.client.aws.request;

public class EmailRequest {

    private final String from;
    private final String fromName;
    private final String to;
    private final String subject;
    private final String body;

    public EmailRequest(Builder builder) {
        this.from = builder.from;
        this.fromName = builder.fromName;
        this.to = builder.to;
        this.subject = builder.subject;
        this.body = builder.body;
    }

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

    public static class Builder {
        private String from;
        private String fromName;
        private String to;
        private String subject;
        private String body;

        public Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder withFromName(String fromName) {
            this.fromName = fromName;
            return this;
        }

        public Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public EmailRequest build() {
            return new EmailRequest(this);
        }
    }
}
