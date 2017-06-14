package yz.doodlejump.entity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户
 */
public class User {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String userName;

    @JsonProperty
    private String bio;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    public User() {
    }

    public User(Integer id, String userName, String bio, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.bio = bio;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
