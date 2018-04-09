package com.giorgione.nazzaro.countershock.share;

/**
 * Created by 91juv on 19/09/2016.
 */
public class User {

    private String email,name,surname,password;

    public User (String email,String name,String surname,String password){
        this.email=email;
        this.name=name;
        this.surname=surname;
        this.password=password;
    }
    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPassword(){
        return password;
    }
}
