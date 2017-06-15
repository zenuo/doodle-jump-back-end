package yz.doodlejump.entity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户
 */
public class Player {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String bio;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private Integer record;

    public Player() {
    }

    public Player(String name, String bio, String email, String password) {
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.password = password;
        this.record = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", record=" + record +
                '}';
    }
}
