/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Luc
 */
import enums.ObjectiveType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import models.Achievement;
import models.Objective;

@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.getByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    ,
    @NamedQuery(name = "User.getByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.getLogin", query = "SELECT u FROM User u WHERE u.password = :password AND u.email = :email")
        })
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role Role;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    private String location;
    private String weburl;

    @Column(length = 160)
    private String bio;

    @ManyToMany
    private List<User> followedUsers;

    @ManyToMany
    private List<User> followingUsers;

    @OneToMany
    private List<Kweet> kweets;
    
    @ManyToMany
    private List<Achievement> achievements;
    
    public User() {
    }
    
    public void addFollowingUser(User user){
        followedUsers.add(user);
    }
    public void removeFollowingUser(User user){
        followedUsers.remove(user);
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFollowedUsers(List<User> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @XmlTransient
    public List<User> getFollowedUsers() {
        return followedUsers;
    }

    @XmlTransient
    public List<User> getFollowingUsers() {
        return followingUsers;
    }

    @XmlTransient
    public List<Kweet> getKweets() {
        return kweets;
    }

    public Role getRole() {
        return Role;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getWeburl() {
        return weburl;
    }

    public String getBio() {
        return bio;
    }

    public void addKweet(Kweet k) {
        kweets.add(k);
    }

    public void setRole(Role role) {
        this.Role = role;
    }
    
    public void sendObjectives(User u){
        Objective o = new Objective();
        o.setId(1l);
        o.setObjectivePointsNeeded(5.0);
        o.setObjectiveType(ObjectiveType.TWEETS_MADE);
        
    } 

}
