package com.aira.services.authorization.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank
    @Size(min = 1, max = 40, message = "{firstname.not.empty}")
    private String firstName;

    @Size(min = 0, max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    @Email(message = "{email.not.valid}")
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "{password.length}")
    private String password;

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
}
