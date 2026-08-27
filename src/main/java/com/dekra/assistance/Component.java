package com.dekra.assistance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.*;

@Entity
@Table(name = "componenti_inventario")
@XmlRootElement(name = "component", namespace = "http://www.greyshield.com/schema/inventory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Component {

    @Id // Assuming 'id' is the primary key
    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "category")
    private String category;

    // 2. Se l'XML usa un default namespace, spesso è necessario indicarlo 
    // anche sui singoli elementi figli affinché vengano mappati correttamente.

    @XmlElement(name = "name", namespace = "http://www.greyshield.com/schema/inventory")
    private String name;

    @XmlElement(name = "qualityClass", namespace = "http://www.greyshield.com/schema/inventory")
    private String qualityClass;

    @XmlElement(name = "manufacturer", namespace = "http://www.greyshield.com/schema/inventory")
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