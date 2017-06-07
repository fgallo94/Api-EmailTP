package com.utn.api.email;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
public class Message {

    private User user;
    private DateTime fecha;
    private User recipient;
    private String subject;
    private String body;

    public Message() {
        user = new User();
        fecha = DateTime.now();
        subject = "";
        recipient = new User();
        body = "";
    }

    public Message(User user, DateTime fecha, User recipient, String subject, String Body) {
        this.user = user;
        this.fecha = fecha;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (user != null ? !user.equals(message.user) : message.user != null) return false;
        if (fecha != null ? !fecha.equals(message.fecha) : message.fecha != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        return body != null ? body.equals(message.body) : message.body == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getBody(){return body;}
    public void setBody(String body) {
        this.body = body;
    }
}
