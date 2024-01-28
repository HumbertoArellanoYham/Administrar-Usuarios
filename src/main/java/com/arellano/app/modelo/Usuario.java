package com.arellano.app.modelo;

public class Usuario {
    private Integer id;
    private String username;
    private String password;
    private String email;

    public Usuario(){}
    public Usuario(Integer id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return this.id + " | " + this.username + " | " + this.password + " | " + this.email;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Usuario)) return false;

        if(this.getUsername() == null || this.getPassword() == null || this.email == null) return false;

        Usuario userEquals = (Usuario) obj;

        return this.getId().equals(userEquals.id) && this.getUsername().equals(userEquals.username)
                && this.password.equals(userEquals.password) && this.email.equals(userEquals.getEmail());
    }
}
