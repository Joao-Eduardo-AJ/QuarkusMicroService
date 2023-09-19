package br.com.syonet.quarkus.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "ModelProperties")
@Table(name = "model_properties")
public class ModelProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonbProperty("Label")
    private String label;

    @JsonbProperty("Value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id", nullable = false)
    private Brand brand;

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
