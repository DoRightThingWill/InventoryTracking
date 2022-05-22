package com.shopify.InventoryTrack.service;

import com.shopify.InventoryTrack.model.Warehouse;
import com.shopify.InventoryTrack.repository.WarehouseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService
{
  @Autowired
  private WarehouseRepository warehouseRepository;

  public List<Warehouse> getAllWarehouses(){
    return warehouseRepository.findAll();
  }

}
