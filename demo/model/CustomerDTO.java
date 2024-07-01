package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Username should not be blank")
    private String username;

    @NotBlank(message = "Password should not be blank")
    private String password;

    @NotBlank(message = "First name should not be blank")
    private String firstname;

    @NotBlank(message = "Last name should not be blank")
    private String lastname;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Mobile number should not be blank")
    private String mobile;

    @NotBlank(message = "Aadhar number should not be blank")
    private String aadhar;

    // Constructor
    public CustomerDTO() {
        super();
    }

    public CustomerDTO(Long id, @NotBlank String username, @NotBlank String password, @NotBlank String firstname,
                       @NotBlank String lastname, @Email String email, @NotBlank String mobile, @NotBlank String aadhar) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.aadhar = aadhar;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    @Override
    public String toString() {
        return "CustomerDTO [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname + ", email=" + email + ", mobile=" + mobile + ", aadhar=" + aadhar + "]";
    }
}
