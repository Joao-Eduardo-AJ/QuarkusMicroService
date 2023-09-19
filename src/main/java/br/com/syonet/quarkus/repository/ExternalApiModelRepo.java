package br.com.syonet.quarkus.repository;

import java.util.List;

import br.com.syonet.quarkus.entity.ModelProperties;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

public class ExternalApiModelRepo implements PanacheRepository<ModelProperties> {

    @Transactional
    public List<ModelProperties> getModels() {
        PanacheQuery<ModelProperties> modelQuery = this
                .find("select mp from ModalProperties mp");
        return modelQuery.list();
    }

    @Transactional
    public Response saveModel(ModelProperties model) {
        this.persist(model);
        return Response.ok(model).build();
    }
}