package br.com.syonet.quarkus.entity;

import java.util.List;

import jakarta.json.bind.annotation.JsonbProperty;

public class ReferenceTable extends ErrorMessageDefault {
    private List<TableProperties> response;

    public static class TableProperties {

        @JsonbProperty("Codigo")
        private int code;

        @JsonbProperty("Mes")
        private String month;

        public int getCode() {
            return code;
        }

        public String getMonth() {
            return month;
        }
    }

    public List<TableProperties> getResponse() {
        return response;
    }
}
