package br.com.syonet.quarkus.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.syonet.quarkus.entity.Brand;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ExternalApiBrandRepo implements PanacheRepository<Brand> {

    @Transactional
    public Brand getBrandById(int idBrand) {
        Map<String, Object> params = new HashMap<>();
        params.put("idBrand", idBrand);
        PanacheQuery<Brand> brandQuery = this
                .find("select b from Brand b where b.value = :idBrand", params);
        return brandQuery.firstResult();
    }

    @Transactional
    public List<Brand> getBrands() {
        PanacheQuery<Brand> brandQuery = this
                .find("select b from Brand b");
        return brandQuery.list();
    }

    @Transactional
    public Response savebrand(Brand brand) {
        this.persist(brand);
        return Response.ok(brand).build();
    }
}
