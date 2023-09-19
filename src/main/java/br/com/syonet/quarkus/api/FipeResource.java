package br.com.syonet.quarkus.api;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import br.com.syonet.quarkus.business.ExternalApiBusiness;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/fipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FipeResource {

    @Inject
    ExternalApiBusiness externalApiBusiness;

    @GET
    public Response getAll(@QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok(externalApiBusiness.getBrands()).build();
    }

    @GET
    @Path("/brand/{idBrand}")
    public Response getBrands(@PathParam("idBrand") int idBrand) {
        return Response.ok(externalApiBusiness.getBrandById(idBrand)).build();
    }

    @PATCH
    @Path("/reprocessar")
    public Response updateFipeTable() {
        externalApiBusiness.externalApiTrigger();
        return Response.ok().build();
    }
}