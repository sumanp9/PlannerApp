package com.pleasedo.dbClass;

public class Login {

    private  String col_id;
    private  String  col_username;
    private  String col_fName;
    private  String col_lName;
    private  String col_email;
    private  String col_password;

    public Login(){}


    //Little suspecious about column ID. I dont know if it should be a part of the class or not since
    //login id or col id is autoincreamented in sqlite.
    public Login(String username, String fName, String lName, String email, String password){

        this.col_username = username;
        this.col_fName = fName;
        this.col_lName = lName;
        this.col_email = email;
        this.col_password = password;
    }

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public String getCol_username() {
        return col_username;
    }

    public void setCol_username(String col_username) {
        this.col_username = col_username;
    }

    public String getCol_fName() {
        return col_fName;
    }

    public void setCol_fName(String col_fName) {
        this.col_fName = col_fName;
    }

    public String getCol_lName() {
        return col_lName;
    }

    public void setCol_lName(String col_lName) {
        this.col_lName = col_lName;
    }

    public String getCol_email() {
        return col_email;
    }

    public void setCol_email(String col_email) {
        this.col_email = col_email;
    }

    public String getCol_password() {
        return col_password;
    }

    public void setCol_password(String col_password) {
        this.col_password = col_password;
    }
}
