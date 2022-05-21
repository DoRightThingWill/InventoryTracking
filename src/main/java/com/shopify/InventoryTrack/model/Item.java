package com.shopify.InventoryTrack.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long itemId;

  @NotNull
  @Column(length = 500)
  private String itemName;

  @NotNull
  private Long availableQuantity;
  
  @NotNull
  private Long maxQuantity;
  
  @NotNull
  private Double cost;

  @NotNull
  private Double price;

  @NotNull
  @Column(length = 500)
  private String vendor;
  
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "items_warehouses")
  @NotNull
  private List<Warehouse> warehouses;

}
