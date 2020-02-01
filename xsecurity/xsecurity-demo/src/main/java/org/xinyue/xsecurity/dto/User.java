package org.xinyue.xsecurity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.xinyue.xsecurity.validator.CustomizedConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    private int id;
    @CustomizedConstraint(message = "Name validator")
    private String name;
    @NotBlank(message = "Password cannot be null")
    private String password;
    private String firstName;
    private String lastName;
    @Past(message = "Date cannot be the future")
    private Date date;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonView(UserSimpleView.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserDetailView.class)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonView(UserDetailView.class)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonView(UserDetailView.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
