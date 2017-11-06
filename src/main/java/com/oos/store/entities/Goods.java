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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gadgetName", length = 20)
    private String gadgetName;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "model", length = 20)
    private String model;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "curdate")
    private String curdate;

    @Column(name = "showgoods")
    private boolean showGoods;

    public Goods() {
    }

    public Goods(Long id, String gadgetName, Double price, String description, String model, int quatity, String curdate, boolean showGoods) {
        this.id = id;
        this.gadgetName = gadgetName;
        this.price = price;
        this.description = description;
        this.model = model;
        this.quatity = quatity;
        this.curdate = curdate;
        this.showGoods = showGoods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGadgetName() {
        return gadgetName;
    }

    public void setGadgetName(String gadgetName) {
        this.gadgetName = gadgetName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getCurdate() {
        return curdate;
    }

    public void setCurdate(String curdate) {
        this.curdate = curdate;
    }

    public boolean isShowGoods() {
        return showGoods;
    }

    public void setShowGoods(boolean showGoods) {
        this.showGoods = showGoods;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.gadgetName);
        hash = 17 * hash + Objects.hashCode(this.price);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.model);
        hash = 17 * hash + this.quatity;
        hash = 17 * hash + Objects.hashCode(this.curdate);
        hash = 17 * hash + (this.showGoods ? 1 : 0);
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
        final Goods other = (Goods) obj;
        if (!Objects.equals(this.gadgetName, other.gadgetName)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (this.quatity != other.quatity) {
            return false;
        }
        if (!Objects.equals(this.curdate, other.curdate)) {
            return false;
        }
        if (this.showGoods != other.showGoods) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Goods{" + "id=" + id + ", gadgetName=" + gadgetName + ", price=" + price + ", description=" + description + ", model=" + model + ", quatity=" + quatity + ", curdate=" + curdate + ", showGoods=" + showGoods + '}';
    }

}
