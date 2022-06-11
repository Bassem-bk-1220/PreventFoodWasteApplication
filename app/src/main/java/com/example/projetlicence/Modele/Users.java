package com.example.projetlicence.Modele;

public class Users {
    public String fullname,occupation,phone,email,password, profileimage,lastMessage, id_user;
    private int unseemMessages;

    public Users() {
    }

    public Users(String fullname, String occupation, String phone, String email, String password, String profileimage) {
        this.fullname = fullname;
        this.occupation = occupation;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.profileimage = profileimage;
    }

    public Users(String fullname, String occupation, String phone, String email, String password) {
        this.fullname = fullname;
        this.occupation = occupation;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public Users(String fullname, String lastMessage, String profileimage,  int unseemMessages, String id_user) {
        this.fullname = fullname;
        this.lastMessage = lastMessage;
        this.profileimage=profileimage;
        this.unseemMessages = unseemMessages;
        this.id_user=id_user;
    }

    public Users(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getUnseemMessages() {
        return unseemMessages;
    }

    public void setUnseemMessages(int unseemMessages) {
        this.unseemMessages = unseemMessages;
    }
}
