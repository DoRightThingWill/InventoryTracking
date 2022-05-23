package com.shopify.InventoryTrack.controller;


import com.shopify.InventoryTrack.dto.CreateWarehouseRequest;
import com.shopify.InventoryTrack.model.Warehouse;
import com.shopify.InventoryTrack.service.WarehouseService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Warehouse createWarehouse(@RequestBody @Valid CreateWarehouseRequest createWarehouseRequest){
    return warehouseService.createWarehouse(createWarehouseRequest);
  }

  @GetMapping
  public List<Warehouse> getAll(){
    return warehouseService.getAllWarehouses();
  }

  @GetMapping("/{id}")
  public Warehouse getWarehouseById(@PathVariable @Valid long id){
    return warehouseService.getWarehouseById(id);
  }

  @PutMapping("/{warehouseId}/items/{itemId}")
  public Warehouse assignInventoryToWarehouse(@PathVariable @Valid long warehouseId, @PathVariable @Valid long itemId){
    return warehouseService.assignInventoryToWarehouse(warehouseId, itemId);
  }

}
