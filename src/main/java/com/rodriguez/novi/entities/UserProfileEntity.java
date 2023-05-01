package com.rodriguez.novi.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="users Profile")
@ApiModel("It contains the main personal information of the users")
public class UserProfileEntity {

    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("PK autoincrement")
    private Long id;
    private String name;
    private String lastname;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String email;
    private String languages;
    @ApiModelProperty("It show if the user is active o not active")
    private Boolean activeUser;
    private int telephone;
    private String ocupation;


    //Default constructor: It is because the JPA generated empty object to be use.

    public UserProfileEntity() {
    }

    //Constructor with access to all the attributes

    public UserProfileEntity(Long id, String name, String lastname, String gender, LocalDate birthday, String address, String email, String languages, Boolean activeUser, int telephone, String profession) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.languages = languages;
        this.activeUser = activeUser;
        this.telephone = telephone;
        this.ocupation = profession;
    }

    //Getter and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Boolean getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Boolean activeUser) {
        this.activeUser = activeUser;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

}
