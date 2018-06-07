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


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kweet.findByText",query = "SELECT k FROM Kweet k WHERE k.text = :text"),
    @NamedQuery(name = "Kweet.findByUId",query = "SELECT k FROM Kweet k WHERE k.owner.id =:id")
})
public class Kweet implements Serializable{
    @Id
    @TableGenerator(name ="KweetSEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User owner;
    
    private String username;
    
    @OneToMany
    private List<User> rekweets;
    
    private String text;

    public Kweet() {
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getUsername() {
        return username;
    }

    @XmlTransient
    public List<User> getRekweets() {
        return rekweets;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRekweets(List<User> rekweets) {
        this.rekweets = rekweets;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
    
    
}
