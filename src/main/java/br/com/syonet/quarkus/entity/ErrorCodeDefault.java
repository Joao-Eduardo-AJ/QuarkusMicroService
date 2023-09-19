package br.com.syonet.quarkus.entity;

import java.util.Optional;

import jakarta.json.bind.annotation.JsonbProperty;

public class ErrorCodeDefault {

    @JsonbProperty( "codigo" )
    private Optional<String> code;

    @JsonbProperty( "erro" )
    private Optional<String> error;

    public Optional<String> getCode() {
        return code;
    }

    public Optional<String> getError() {
        return error;
    }
}
