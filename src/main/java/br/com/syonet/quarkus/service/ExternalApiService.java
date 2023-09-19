package br.com.syonet.quarkus.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.syonet.quarkus.entity.Auth;
import br.com.syonet.quarkus.entity.BrandResponse;
import br.com.syonet.quarkus.entity.ModelResponse;
import br.com.syonet.quarkus.entity.ModelByYear;
import br.com.syonet.quarkus.entity.ReferenceTable;
import br.com.syonet.quarkus.entity.VehicleResponse;
import br.com.syonet.quarkus.entity.Year;
import jakarta.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "external-api-service")
public interface ExternalApiService {

    @POST
    @Path("/login")
    Auth login(JsonObject body);

    @POST
    @Path("/vehicles/ConsultarTabelaDeReferencia")
    ReferenceTable getReferenceTable();

    @POST
    @Path("/vehicles/ConsultarMarcas")
    BrandResponse getBrands(JsonObject body);

    @POST
    @Path("/vehicles/ConsultarModelos")
    ModelResponse getModels(JsonObject body);

    @POST
    @Path("/vehicles/ConsultarAnoModelo")
    Year getYearByModel(JsonObject body);

    @POST
    @Path("/vehicles/ConsultarModeloAtravesDoAno")
    ModelByYear getModelByYear(JsonObject body);

    @POST
    @Path("/vehicles/ConsultarValorComTodosParametros")
    VehicleResponse getValueByParameter(JsonObject body);
}
