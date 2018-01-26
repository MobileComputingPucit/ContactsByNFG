package com.example.uzair.contact.Models;



public class ContactInfo {
    private String name;
    private String email;
    private String phone;
    private int id;

    public ContactInfo(int id, String name, String phone, String email) {
        setId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
