package br.com.syonet.quarkus.entity;

import java.util.List;

public class BrandResponse extends ErrorMessageDefault {
    private List<Brand> response;
    
    public List<Brand> getResponse() {
        return response;
    }
}
