package com.example.passsecuritys;

public class PassWord {
    private String APPName;
    private String email;
    private String Password;

    public PassWord(String APPName, String email, String Password){
        this.APPName = APPName;
        this.email = email;
        this.Password = Password;
    }

    public String getAPPName() {
        return APPName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public String toString(){
        return "AppName = " + APPName + "\t UserName = " + email + " \tPassWord" + "";
    }
}
