/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Oluwaseun_Smart
 */
@Entity
public class DateChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "datOfSell")
    private String dateOfSell;

    public DateChecker() {
    }

    public DateChecker(Long id, String dateOfSell) {
        this.id = id;
        this.dateOfSell = dateOfSell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(String dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.dateOfSell);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DateChecker other = (DateChecker) obj;
        if (!Objects.equals(this.dateOfSell, other.dateOfSell)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DateChecker{" + "id=" + id + ", dateOfSell=" + dateOfSell + '}';
    }

}
