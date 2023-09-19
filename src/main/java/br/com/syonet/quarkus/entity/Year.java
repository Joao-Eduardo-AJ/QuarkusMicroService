package br.com.syonet.quarkus.entity;

import java.util.List;

import jakarta.json.bind.annotation.JsonbProperty;

public class Year {
    private YearProperties response;

    public static class YearProperties extends ErrorCodeDefault {

        @JsonbProperty( "Modelos" )
        private List<Util> models;

        @JsonbProperty( "Anos" )
        private List<Util> years;

        public List<Util> getModels() {
            return models;
        }

        public List<Util> getYears() {
            return years;
        }
    }

    public static class Util {
        
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

    public YearProperties getResponse() {
        return response;
    }
}
