package com.example.whatsapp.models;

public class Users {
String profilepic,username,email,password,userid,lastamessege,status;

    public Users(String profilepic, String username, String email, String password, String userid, String lastamessege, String status) {
        this.profilepic = profilepic;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userid = userid;
        this.lastamessege = lastamessege;
        this.status = status;
    }

    //empty constructor
    public Users() {   }
//SignUp constructor

    public Users( String username, String email, String password ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
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

//    public String getUserid(String key) {
//        return userid;
//    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastamessege() {
        return lastamessege;
    }

    public void setLastamessege(String lastamessege) {
        this.lastamessege = lastamessege;
    }
}
