package br.com.syonet.quarkus.entity;

import java.util.List;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Model")
@Table(name = "tb_model")
public class ModelResponse extends ErrorMessageDefault {
    private Model response;

    public static class Model extends ErrorCodeDefault {

        @JsonbProperty( "Modelos" )
        private List<ModelProperties> models;

        @JsonbProperty( "Anos" )
        private List<ModelProperties> years;

        public List<ModelProperties> getModels() {
            return models;
        }

        public List<ModelProperties> getYears() {
            return years;
        }
    }

    public Model getResponse() {
        return response;
    }
}
