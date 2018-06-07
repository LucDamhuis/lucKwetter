/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ivo
 */
@Entity
@NamedQueries({})
@XmlRootElement
public class Achievement implements Serializable {

    @Id
    @TableGenerator(name = "AchievementSEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AchievementSEQ")
    private Long id;

    @Column(unique = false, nullable = false, length = 255)
    private String name;
    @Column(unique = false, nullable = false)
    private Integer points;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "AchiementObjectives",
            joinColumns = @JoinColumn(name = "achievement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "objective_id", referencedColumnName = "id"))
    private Set<Objective> objectives;

    public Achievement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setObjectives(Set<Objective> objectives) {
        this.objectives = objectives;
    }

    public String getName() {
        return name;
    }

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public Integer getPoints() {
        return points;
    }
}
