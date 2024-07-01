package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    // Getters and setters
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Owner() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Owner(Long id, @NotBlank String username, @NotBlank String password, @NotBlank String firstname,
                 @NotBlank String lastname, @Email String email, @NotBlank String mobile) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Owner [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname + ", email=" + email + ", mobile=" + mobile + "]";
    }

    // Getters and setters
    // Same as already defined
}
