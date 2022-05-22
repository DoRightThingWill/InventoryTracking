package com.shopify.InventoryTrack.unitTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.model.Warehouse;
import com.shopify.InventoryTrack.repository.ItemRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemRepositoryUnitTest
{
  @Autowired
  private ItemRepository itemRepository;

  @BeforeEach
  @Transactional
  void addOneItem()
  {
    var warehouse1 = Warehouse.builder()
        .address("2340 Riva Ridge Rd Montgomery, Illinois(IL), 60538")
        .phoneNumber("123456")
        .build();

    var warehouse2 = Warehouse.builder()
        .address("Fort George G Meade, Maryland(MD), 20755")
        .phoneNumber("(410) 874-8489")
        .build();

    Set<Warehouse> warehouseSet = new HashSet<>();
    warehouseSet.addAll(Arrays.asList(warehouse1, warehouse2));

    Item item1 = Item.builder()
        .itemName("Mac Pro")
        .availableQuantity(200L)
        .maxQuantity(2000L)
        .cost(1500d)
        .price(2500d)
        .vendor("APPLE")
        .build();

    Item item2 = Item.builder()
        .itemName("LG TABLET")
        .availableQuantity(200L)
        .maxQuantity(2000L)
        .cost(1500d)
        .price(2500d)
        .vendor("LG")
        .build();


    item1.setWarehouses(warehouseSet);
    item2.setWarehouses(warehouseSet);
    itemRepository.save(item1);
  }

  @Test
  void findAll(){
    var result2 = itemRepository.getItemById(1);
    assertEquals(result2, 4);
  }


}
