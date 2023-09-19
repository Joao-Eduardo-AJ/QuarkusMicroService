package br.com.syonet.quarkus.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Vehicle")
@Table(name = "tb_vehicle")
public class Vehicle {

    @Column(name = "value")
    @JsonbProperty("Valor")
    private String value;

    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id", nullable = false)
    @JsonbProperty("Marca")
    private String brand;

    @Column(name = "model")
    @JsonbProperty("Modelo")
    private String model;

    @Column(name = "model_year")
    @JsonbProperty("AnoModelo")
    private int modelYear;

    @Column(name = "fuel_year")
    @JsonbProperty("Combustivel")
    private int fuelYear;

    @Column(name = "fipe_code")
    @JsonbProperty("CodigoFipe")
    private String fipeCode;

    @Column(name = "reference_month")
    @JsonbProperty("MesReferencia")
    private String referenceMonth;

    @Column(name = "vehicle_type")
    @JsonbProperty("TipoVeiculo")
    private int vehicleType;

    @Column(name = "fuel_acronym")
    @JsonbProperty("SiglaCombustivel")
    private String fuelAcronym;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setFuelYear(int fuelYear) {
        this.fuelYear = fuelYear;
    }

    public int getFuelYear() {
        return fuelYear;
    }

    public void setFipeCode(String fipeCode) {
        this.fipeCode = fipeCode;
    }

    public String getFipeCode() {
        return fipeCode;
    }

    public void setReferenceMonth(String referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public String getReferenceMonth() {
        return referenceMonth;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setFuelAcronym(String fuelAcronym) {
        this.fuelAcronym = fuelAcronym;
    }

    public String getFuelAcronym() {
        return fuelAcronym;
    }
}
