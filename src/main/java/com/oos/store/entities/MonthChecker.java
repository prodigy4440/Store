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
public class MonthChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "monOfSell")
    private String monOfSell;

    public MonthChecker() {
    }

    public MonthChecker(Long id, String monOfSell) {
        this.id = id;
        this.monOfSell = monOfSell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonOfSell() {
        return monOfSell;
    }

    public void setMonOfSell(String monOfSell) {
        this.monOfSell = monOfSell;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.monOfSell);
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
        final MonthChecker other = (MonthChecker) obj;
        if (!Objects.equals(this.monOfSell, other.monOfSell)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonthChecker{" + "id=" + id + ", monOfSell=" + monOfSell + '}';
    }

}
