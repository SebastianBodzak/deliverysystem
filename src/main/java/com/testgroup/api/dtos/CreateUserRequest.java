package com.testgroup.api.dtos;

/**
 * @author beata.ilowiecka@impaqgroup.com on 09.12.16.
 */
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String address;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
