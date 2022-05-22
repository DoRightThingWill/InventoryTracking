package com.shopify.InventoryTrack.repository;

import com.shopify.InventoryTrack.model.Warehouse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>
{
  @Query("select w from Warehouse w where w.id = :id")
  Optional<Warehouse> getWarehouseById(@Param("id") Long warehouseId);


  @Query("select w from Warehouse w")
  List<Warehouse> findAll();
}
