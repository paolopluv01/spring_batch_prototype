package com.dekra.assistance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.*;

@Entity
@Table(name = "componenti_inventario")
@XmlRootElement(name = "component")
@XmlAccessorType(XmlAccessType.FIELD)
public class Component {

    @Id // Assuming 'id' is the primary key
    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "category")
    private String category;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "qualityClass")
    private String qualityClass;

    @XmlElement(name = "manufacturer")
    private String manufacturer;

    // Aggiungi i getter, i setter e il metodo toString()
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getQualityClass() {
        return qualityClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualityClass(String qualityClass) {
        this.qualityClass = qualityClass;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String toString() {
        return "Component{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", qualityClass='" + qualityClass + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}