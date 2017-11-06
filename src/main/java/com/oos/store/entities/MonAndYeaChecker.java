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
public class MonAndYeaChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "monAndYeaOfSell")
    private String monAndYeaOfSell;

    public MonAndYeaChecker() {
    }

    public MonAndYeaChecker(Long id, String monAndYeaOfSell) {
        this.id = id;
        this.monAndYeaOfSell = monAndYeaOfSell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonAndYeaOfSell() {
        return monAndYeaOfSell;
    }

    public void setMonAndYeaOfSell(String monAndYeaOfSell) {
        this.monAndYeaOfSell = monAndYeaOfSell;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.monAndYeaOfSell);
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
        final MonAndYeaChecker other = (MonAndYeaChecker) obj;
        if (!Objects.equals(this.monAndYeaOfSell, other.monAndYeaOfSell)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonAndYeaChecker{" + "id=" + id + ", monAndYeaOfSell=" + monAndYeaOfSell + '}';
    }

}
