package com.shopify.InventoryTrack.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long warehouseId;

  @NotNull
  @ManyToMany(mappedBy = "warehouses")
  private List<Item> items;

}