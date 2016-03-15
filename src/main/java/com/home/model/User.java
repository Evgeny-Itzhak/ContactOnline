package com.home.model;

import java.util.Date;

/**
 * @author  Itzhak-Miryam on 14.03.2016.
 */
public class User {

    private int userid;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    public int getUserId() {
        return userid;
    }
    public void setUserId(int userid) {
        this.userid = userid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User [userId=" + userid + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dob=" + dob + ", email="
                + email + "]";
    }

}
