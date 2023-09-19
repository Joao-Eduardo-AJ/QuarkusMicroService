package br.com.syonet.quarkus.business;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.syonet.quarkus.api.ExternalApiResource;
import br.com.syonet.quarkus.entity.Brand;
import br.com.syonet.quarkus.entity.BrandResponse;
import br.com.syonet.quarkus.entity.ModelResponse;
import br.com.syonet.quarkus.entity.ReferenceTable;
import br.com.syonet.quarkus.entity.ReferenceTable.TableProperties;
import br.com.syonet.quarkus.repository.ExternalApiBrandRepo;
import br.com.syonet.quarkus.repository.ExternalApiModelRepo;
import jakarta.inject.Inject;

public class ExternalApiBusiness {
    private int tableCode;

    @Inject
    ExternalApiResource api;

    @Inject
    ExternalApiModelRepo modelRepo;

    @Inject
    ExternalApiBrandRepo brandRepo;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void beginSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long timeUntilExecute = calendar.getTimeInMillis() - System.currentTimeMillis();

        scheduler.scheduleAtFixedRate(this::externalApiTrigger, timeUntilExecute, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
    }

    public void externalApiTrigger() {
        if (brandRepo.getBrands().size() > 0) {
            brandRepo.deleteAll();
        }
        handleDbData();
    }

    public void handleDbData() {
        boolean isAuth = api.getAuth();
        if (!isAuth) {
            return;
        }

        ReferenceTable tableResponse = api.getReferenceTable();
        if (tableResponse.getError()) {
            System.out.println(tableResponse.getMessage());
            return;
        }

        final int vehicleTypeCode = 1;
        List<TableProperties> tableProperties = tableResponse.getResponse();

        tableCode = 0;
        for (TableProperties element : tableProperties) {
            if (tableCode < element.getCode()) {
                tableCode = element.getCode();
            }
        }

        BrandResponse brandResponse = api.getBrands(tableCode, vehicleTypeCode);
        if (brandResponse.getError()) {
            System.out.println(brandResponse.getMessage());
            return;
        }

        // List<Brand> brandsByTable = brandResponse.getResponse();
        // Para não estourar as requisicoes, vamos de Volks (id 59) Mazda (id 38) e
        // Dodge (id 17);

        Brand brand1 = new Brand("VolksWagen", "59");
        Brand brand2 = new Brand("Mazda", "38");
        Brand brand3 = new Brand("Dodge", "17");
        List<Brand> brands = Arrays.asList(brand1, brand2, brand3);

        brands.stream().forEach(element -> {
            ModelResponse modelResponse = api.getModels(tableCode, vehicleTypeCode, element.getValue());
            if (modelResponse.getError()) {
                System.out.println(modelResponse.getMessage());
                return;
            }
            element.setModels(modelResponse.getResponse().getModels());
            element.getModels().stream().forEach(item -> {
                item.setBrand(element);
                modelRepo.saveModel(item);
            });
            brandRepo.savebrand(element);
        });
    }

    public List<Brand> getBrands() {
        return brandRepo.getBrands();
    }

    public Brand getBrandById(int idBrand) {
        return brandRepo.getBrandById(idBrand);
    }
}
