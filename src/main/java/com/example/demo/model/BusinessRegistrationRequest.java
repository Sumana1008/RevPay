/*The process of registering a new business involves sending data to the server via an API.
The payload of this registration request typically includes fields like username and password.
Rather than exposing all the fields of the Business entity,this seperation is made to focus on registration process
During registration, you may want to perform validation on the provided data, such as ensuring that the username is unique and the
password meets certain criteria.By using the BusinessRegistrationRequest class, you can add validation annotations
(e.g., @NotBlank, @Size, etc.) to the fields.
The BusinessRegistrationRequest class acts as an intermediary for transforming and mapping the request data to the fields of the
Business entity before it's actually created and saved.
 */
package com.example.demo.model;
public class BusinessRegistrationRequest {
    private String username;
    private String password;

    public BusinessRegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

