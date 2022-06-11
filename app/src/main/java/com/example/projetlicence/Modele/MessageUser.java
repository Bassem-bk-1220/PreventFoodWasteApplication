package com.example.projetlicence.Modele;

public class MessageUser {
    String sender, recevier, message_user,time;

    public MessageUser() {
    }

    public MessageUser(String sender, String recevier, String message_user, String time) {
        this.sender = sender;
        this.recevier = recevier;
        this.message_user = message_user;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecevier() {
        return recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier;
    }

    public String getMessage_user() {
        return message_user;
    }

    public void setMessage_user(String message_user) {
        this.message_user = message_user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
