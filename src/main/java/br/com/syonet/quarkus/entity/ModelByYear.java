package br.com.syonet.quarkus.entity;

import java.util.List;

import jakarta.json.bind.annotation.JsonbProperty;

public class ModelByYear extends ErrorMessageDefault {
    private List<Util> response;

    public static class Util extends ErrorCodeDefault {

        @JsonbProperty( "Label" )
        private String label;

        @JsonbProperty( "Value" )
        private String value;

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }
    }

    public List<Util> getResponse() {
        return response;
    }
}
