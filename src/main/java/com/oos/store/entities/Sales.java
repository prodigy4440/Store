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
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "gadgetName")
    private String gadgetName;
    @Column(name = "priceSold")
    private double priceSold;
    @Column(name = "price")
    private double price;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "gadgetImei")
    private String gadgetImei;
    @Column(name = "customerAddress")
    private String customerAddress;
    @Column(name = "seller")
    private String seller;
    @Column(name = "cur")
    private String cur;
    @Column(name = "model")
    private String model;
    @Column(name = "gadgetId")
    private long gadgetId;
    @Column(name = "mon")
    private String monOfSell;
    @Column(name = "yea")
    private String yeaOfSells;
    @Column(name = "monAndYea")
    private String monAndYea;
    @Column(name = "profit")
    private double profit;
    @Column(name = "loss")
    private double loss;
    @Column(name = "dateOfSells")
    private String dateOfSells;

    public Sales() {
    }

    public Sales(Long id, String gadgetName, double priceSold, double price, String customerName, String gadgetImei, String customerAddress, String seller, String cur, String model, long gadgetId, String monOfSell, String yeaOfSells, String monAndYea, double profit, double loss, String dateOfSells) {
        this.id = id;
        this.gadgetName = gadgetName;
        this.priceSold = priceSold;
        this.price = price;
        this.customerName = customerName;
        this.gadgetImei = gadgetImei;
        this.customerAddress = customerAddress;
        this.seller = seller;
        this.cur = cur;
        this.model = model;
        this.gadgetId = gadgetId;
        this.monOfSell = monOfSell;
        this.yeaOfSells = yeaOfSells;
        this.monAndYea = monAndYea;
        this.profit = profit;
        this.loss = loss;
        this.dateOfSells = dateOfSells;
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

    public double getPriceSold() {
        return priceSold;
    }

    public void setPriceSold(double priceSold) {
        this.priceSold = priceSold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGadgetImei() {
        return gadgetImei;
    }

    public void setGadgetImei(String gadgetImei) {
        this.gadgetImei = gadgetImei;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getGadgetId() {
        return gadgetId;
    }

    public void setGadgetId(long gadgetId) {
        this.gadgetId = gadgetId;
    }

    public String getMonOfSell() {
        return monOfSell;
    }

    public void setMonOfSell(String monOfSell) {
        this.monOfSell = monOfSell;
    }

    public String getYeaOfSells() {
        return yeaOfSells;
    }

    public void setYeaOfSells(String yeaOfSells) {
        this.yeaOfSells = yeaOfSells;
    }

    public String getMonAndYea() {
        return monAndYea;
    }

    public void setMonAndYea(String monAndYea) {
        this.monAndYea = monAndYea;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public String getDateOfSells() {
        return dateOfSells;
    }

    public void setDateOfSells(String dateOfSells) {
        this.dateOfSells = dateOfSells;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.gadgetName);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.priceSold) ^ (Double.doubleToLongBits(this.priceSold) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.customerName);
        hash = 73 * hash + Objects.hashCode(this.gadgetImei);
        hash = 73 * hash + Objects.hashCode(this.customerAddress);
        hash = 73 * hash + Objects.hashCode(this.seller);
        hash = 73 * hash + Objects.hashCode(this.cur);
        hash = 73 * hash + Objects.hashCode(this.model);
        hash = 73 * hash + (int) (this.gadgetId ^ (this.gadgetId >>> 32));
        hash = 73 * hash + Objects.hashCode(this.monOfSell);
        hash = 73 * hash + Objects.hashCode(this.yeaOfSells);
        hash = 73 * hash + Objects.hashCode(this.monAndYea);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.profit) ^ (Double.doubleToLongBits(this.profit) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.loss) ^ (Double.doubleToLongBits(this.loss) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.dateOfSells);
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
        final Sales other = (Sales) obj;
        if (!Objects.equals(this.gadgetName, other.gadgetName)) {
            return false;
        }
        if (Double.doubleToLongBits(this.priceSold) != Double.doubleToLongBits(other.priceSold)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.gadgetImei, other.gadgetImei)) {
            return false;
        }
        if (!Objects.equals(this.customerAddress, other.customerAddress)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        if (!Objects.equals(this.cur, other.cur)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (this.gadgetId != other.gadgetId) {
            return false;
        }
        if (!Objects.equals(this.monOfSell, other.monOfSell)) {
            return false;
        }
        if (!Objects.equals(this.yeaOfSells, other.yeaOfSells)) {
            return false;
        }
        if (!Objects.equals(this.monAndYea, other.monAndYea)) {
            return false;
        }
        if (Double.doubleToLongBits(this.profit) != Double.doubleToLongBits(other.profit)) {
            return false;
        }
        if (Double.doubleToLongBits(this.loss) != Double.doubleToLongBits(other.loss)) {
            return false;
        }
        if (!Objects.equals(this.dateOfSells, other.dateOfSells)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", gadgetName=" + gadgetName + ", priceSold=" + priceSold + ", price=" + price + ", customerName=" + customerName + ", gadgetImei=" + gadgetImei + ", customerAddress=" + customerAddress + ", seller=" + seller + ", cur=" + cur + ", model=" + model + ", gadgetId=" + gadgetId + ", monOfSell=" + monOfSell + ", yeaOfSells=" + yeaOfSells + ", monAndYea=" + monAndYea + ", profit=" + profit + ", loss=" + loss + ", dateOfSells=" + dateOfSells + '}';
    }

}
