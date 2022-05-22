package com.shopify.InventoryTrack.repository;

import com.shopify.InventoryTrack.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>
{

  @Query("select i from Item i where i.id = :id")
  Optional<Item> getItemById(@Param("id") long id);

  @Query("update Item set availableQuantity = :newQuantity")
  void updateAvailableQuantity(@Param("newQuantity") long newQuantity);

  @Query("select i from Item i")
  List<Item> findAll();

}
