/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runefist.serversiteplus.shared.models;

import com.runefist.serversiteplus.shared.enums.ObjectiveType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivo
 */
@Entity
@NamedQueries({})
@XmlRootElement
public class Objective implements Serializable {

    @Id
    @TableGenerator(name = "ObjectiveSEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ObjectiveSEQ")
    private Long id;

    @Column(unique = false, nullable = false)
    private ObjectiveType objectiveType;
    
    @Column(unique = false, nullable = false)
    private Double objectivePoints;

    public Objective() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectiveType getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(ObjectiveType objectiveType) {
        this.objectiveType = objectiveType;
    }

    public Double getObjectivePoints() {
        return objectivePoints;
    }

    public void setObjectivePoints(Double objectivePoints) {
        this.objectivePoints = objectivePoints;
    }
}
