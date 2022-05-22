package com.shopify.InventoryTrack.controller;


import com.shopify.InventoryTrack.model.Warehouse;
import com.shopify.InventoryTrack.service.WarehouseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouses")
@ResponseStatus(HttpStatus.OK)
public class WareHouseController
{

  @Autowired
  private WarehouseService warehouseService;

  @GetMapping
  public List<Warehouse> getAll(){
    return warehouseService.getAllWarehouses();
  }
}
