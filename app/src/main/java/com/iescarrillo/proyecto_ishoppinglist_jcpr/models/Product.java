package com.iescarrillo.proyecto_ishoppinglist_jcpr.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String productName;
    private String infoNote;
    private boolean pendStatus;
    private boolean lactosa;
    private boolean gluten;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInfoNote() {
        return infoNote;
    }

    public void setInfoNote(String infoNote) {
        this.infoNote = infoNote;
    }

    public boolean isPendStatus() {
        return pendStatus;
    }

    public void setPendStatus(boolean pendStatus) {
        this.pendStatus = pendStatus;
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", infoNote='" + infoNote + '\'' +
                ", estadoPendiente=" + pendStatus +
                '}';
    }
}
