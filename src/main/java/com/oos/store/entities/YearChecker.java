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
public class YearChecker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "yeaOfSell")
    private String yeaOfSell;

    public YearChecker() {
    }

    public YearChecker(Long id, String yeaOfSell) {
        this.id = id;
        this.yeaOfSell = yeaOfSell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYeaOfSell() {
        return yeaOfSell;
    }

    public void setYeaOfSell(String yeaOfSell) {
        this.yeaOfSell = yeaOfSell;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.yeaOfSell);
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
        final YearChecker other = (YearChecker) obj;
        if (!Objects.equals(this.yeaOfSell, other.yeaOfSell)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "YearChecker{" + "id=" + id + ", yeaOfSell=" + yeaOfSell + '}';
    }

}
