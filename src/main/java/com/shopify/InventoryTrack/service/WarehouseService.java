package com.shopify.InventoryTrack.service;

import com.shopify.InventoryTrack.dto.AssignItemRequest;
import com.shopify.InventoryTrack.dto.CreateWarehouseRequest;
import com.shopify.InventoryTrack.exception.ObjectNotFoundException;
import com.shopify.InventoryTrack.model.Warehouse;
import com.shopify.InventoryTrack.repository.ItemRepository;
import com.shopify.InventoryTrack.repository.WarehouseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarehouseService
{
  @Autowired
  private WarehouseRepository warehouseRepository;

  @Autowired
  private ItemRepository itemRepository;

  public List<Warehouse> getAllWarehouses(){
    return warehouseRepository.findAll();
  }

  public Warehouse getWarehouseById(long id)
  {
    return warehouseRepository.getWarehouseById(id).orElseThrow(()->
        new ObjectNotFoundException("Warehouse can not be found by this Id: " + id));
  }

  @Transactional
  public Warehouse createWarehouse(CreateWarehouseRequest createWarehouseRequest)
  {
    var warehouse = Warehouse.builder()
        .address(createWarehouseRequest.address())
        .phoneNumber(createWarehouseRequest.phoneNumber())
        .build();
    return warehouseRepository.save(warehouse);
  }

  @Transactional
  public Warehouse assignInventoryToWarehouse(long warehouseId, AssignItemRequest assignInventoryRequest)
  {
    var warehouse = warehouseRepository.getWarehouseById(warehouseId).orElseThrow(()->
        new ObjectNotFoundException("Warehouse can not be found by this Id: " + warehouseId));

    assignInventoryRequest.itemIds().forEach( itemId ->
    {
      var item = itemRepository.getItemById(itemId).orElseThrow(()->
          new ObjectNotFoundException("Item can not be found by this Id: " + itemId));
      item.getWarehouses().add(warehouse);
      itemRepository.save(item);
    });
    return warehouse;
  }
}
