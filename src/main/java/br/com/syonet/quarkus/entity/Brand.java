package br.com.syonet.quarkus.entity;

import java.util.List;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Brand")
@Table(name = "tb_brand")
public class Brand extends ErrorCodeDefault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "ds_brand")
    @JsonbProperty("Label")
    private String label;
    
    @Column(name = "id_brand")
    @JsonbProperty("Value")
    private String value;
    
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<ModelProperties> models;
    
    public Brand(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setModels(List<ModelProperties> models) {
        this.models = models;
    }

    public List<ModelProperties> getModels() {
        return models;
    }
}