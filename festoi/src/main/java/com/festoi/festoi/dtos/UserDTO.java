package com.festoi.festoi.dtos;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    //other attribute might come here
    private boolean isArtist;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String username, String password, String email, boolean isArtist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isArtist = isArtist;
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

    public boolean isArtist() {
        return isArtist;
    }

    public void setIsArtist(boolean isArtist) {
        this.isArtist = isArtist;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isArtist=" + isArtist +
                '}';
    }
}
