package com.moutamid.sra.models;

public class UserModel {
    String username, email, password, whatsapp, referralCode, invitationCode;

    public UserModel() {
    }

    public UserModel(String username, String email, String password, String whatsapp, String referralCode, String invitationCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.whatsapp = whatsapp;
        this.referralCode = referralCode;
        this.invitationCode = invitationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
