package br.com.syonet.quarkus.api;

import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import br.com.syonet.quarkus.entity.Auth;
import br.com.syonet.quarkus.entity.BrandResponse;
import br.com.syonet.quarkus.entity.ModelResponse;
import br.com.syonet.quarkus.entity.ModelByYear;
import br.com.syonet.quarkus.entity.ReferenceTable;
import br.com.syonet.quarkus.entity.VehicleResponse;
import br.com.syonet.quarkus.entity.Year;
import br.com.syonet.quarkus.service.ExternalApiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.core.Context;

@ApplicationScoped
public class ExternalApiResource {

    Cache<String, String> authCache = Caffeine.newBuilder()
            .expireAfterWrite(7, TimeUnit.DAYS)
            .build();

    @RestClient
    @Inject
    ExternalApiService service;

    @Context
    @HeaderParam("Content-Type")
    String contentType = "application/json";

    @Context
    @HeaderParam("DeviceToken")
    String deviceToken = "11f43b96-b004-415b-a58c-c9a43d99e1a0";

    @Context
    @HeaderParam("Authorization")
    private String authorization = "";

    public boolean getAuth() {
        String email = System.getenv("quarkus.email");
        String password = System.getenv("quarkus.password");
        String cachedAuthorization = authCache.getIfPresent(email);

        if (cachedAuthorization != null) {
            this.authorization = cachedAuthorization;
            return true;
        }
        JsonObject body = Json.createObjectBuilder()
                .add("email", email)
                .add("password", password)
                .build();
        Auth auth = service.login(body);
        if (auth.getError()) {
            System.out.println("Erro inesperado ocorreu ao autenticar a sessão, verifique suas credenciais.");
            return false;
        }
        this.authorization = auth.getAuthCredentials().getType() + " " + auth.getAuthCredentials().getToken();
        authCache.put(email, this.authorization);
        return true;
    }

    public ReferenceTable getReferenceTable() {
        return service.getReferenceTable();
    }

    public BrandResponse getBrands(
            Integer referenceTableCode,
            Integer vehicleTypeCode) {
        JsonObject body = Json.createObjectBuilder()
                .add("codigoTabelaReferencia", referenceTableCode)
                .add("codigoTipoVeiculo", vehicleTypeCode)
                .build();
        return service.getBrands(body);
    }

    public ModelResponse getModels(
            Integer referenceTableCode,
            Integer vehicleTypeCode,
            String brandCode) {
        JsonObject body = Json.createObjectBuilder()
                .add("codigoTabelaReferencia", referenceTableCode)
                .add("codigoTipoVeiculo", vehicleTypeCode)
                .add("codigoMarca", brandCode)
                .build();
        return service.getModels(body);
    }

    public Year getYearByModel(
            Integer referenceTableCode,
            Integer vehicleTypeCode,
            Integer brandCode,
            Integer modelCode) {
        JsonObject body = Json.createObjectBuilder()
                .add("codigoTabelaReferencia", referenceTableCode)
                .add("codigoTipoVeiculo", vehicleTypeCode)
                .add("codigoMarca", brandCode)
                .add("codigoModelo", modelCode)
                .build();
        return service.getYearByModel(body);
    }

    public ModelByYear getModelByYear(
            Integer referenceTableCode,
            Integer vehicleTypeCode,
            String year,
            Integer fuelTypeCode,
            Integer yearModel) {
        JsonObject body = Json.createObjectBuilder()
                .add("codigoTabelaReferencia", referenceTableCode)
                .add("codigoTipoVeiculo", vehicleTypeCode)
                .add("ano", year)
                .add("codigoTipoCombustivel", fuelTypeCode)
                .add("yearModel", yearModel)
                .build();
        return service.getModelByYear(body);
    }

    public VehicleResponse getValueByParameter(
            Integer referenceTableCode,
            Integer vehicleTypeCode,
            Integer brandCode,
            String year,
            Integer fuelTypeCode,
            Integer yearModel,
            Integer modelCode) {
        JsonObject body = Json.createObjectBuilder()
                .add("codigoTabelaReferencia", referenceTableCode)
                .add("codigoTipoVeiculo", vehicleTypeCode)
                .add("codigoMarca", brandCode)
                .add("codigoModelo", modelCode)
                .add("ano", year)
                .add("codigoTipoCombustivel", fuelTypeCode)
                .add("yearModel", yearModel)
                .build();
        return service.getValueByParameter(body);
    }
}
