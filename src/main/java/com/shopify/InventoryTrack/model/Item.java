package com.shopify.InventoryTrack.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
@Table(name = "items")
public class Item implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "items_warehouses", joinColumns = {
      @JoinColumn(name = "item_id", referencedColumnName = "id")
  }, inverseJoinColumns = {
      @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
  }
  )
  private Set<Warehouse> warehouses;

}
