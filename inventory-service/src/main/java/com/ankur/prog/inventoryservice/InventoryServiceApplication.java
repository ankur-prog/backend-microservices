package com.ankur.prog.inventoryservice;

import com.ankur.prog.inventoryservice.model.Inventory;
import com.ankur.prog.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner loadData(InventoryRepository inventoryRepository)
    {
        return args -> {
            if (inventoryRepository.count() == 0) {
                Inventory inventory1 = new Inventory();
                inventory1.setQuantity(100);
                inventory1.setSkuCode("iphone14");

                Inventory inventory2 = new Inventory();
                inventory2.setQuantity(100);
                inventory2.setSkuCode("iphone14 Pro");

                Inventory inventory3 = new Inventory();
                inventory3.setQuantity(0);
                inventory3.setSkuCode("iphone14 Pro Max");
                inventoryRepository.saveAll(Arrays.asList(inventory1, inventory2, inventory3));
            }


        };

    }

}
