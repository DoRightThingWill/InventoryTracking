package com.shopify.InventoryTrack.service;

import com.shopify.InventoryTrack.dto.CreateItemRequest;
import com.shopify.InventoryTrack.dto.UpdateItemRequest;
import com.shopify.InventoryTrack.exception.BusinessException;
import com.shopify.InventoryTrack.exception.ObjectNotFoundException;
import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.repository.ItemRepository;
import com.shopify.InventoryTrack.repository.WarehouseRepository;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService
{
  @Autowired
  private final ItemRepository itemRepository;

  @Autowired
  private final WarehouseRepository warehouseRepository;

  public List<Item> getAllItems()
  {
    System.out.println("aaa");
    List<Item> result = itemRepository.findAll();
    return itemRepository.findAll();
  }

  public Item getItemById(long id)
  {
    return itemRepository.getItemById(id).orElseThrow(() ->
        new ObjectNotFoundException("Item can not be found by this Id: " + id));
  }

  @Transactional
  public void deleteItemById(long id)
  {
    itemRepository.getItemById(id).orElseThrow(() ->
        new ObjectNotFoundException("Item can not be found by this Id: " + id));
    itemRepository.deleteById(id);
    System.out.println("aaa");
  }

  @Transactional
  public Item creatItemFromRequest(CreateItemRequest createItemRequest)
  {
    var item = Item.builder()
        .itemName(createItemRequest.itemName())
        .availableQuantity(createItemRequest.availableQuantity())
        .maxQuantity(createItemRequest.maxQuantity())
        .cost(createItemRequest.cost())
        .price(createItemRequest.price())
        .vendor(createItemRequest.vendor())
        .warehouses(new HashSet<>())
        .build();
    return itemRepository.save(item);
  }

  @Transactional
  public Item createItemFromItem(Item item){
    return itemRepository.save(item);
  }


  @Transactional
  public Item updateItemById(long id, UpdateItemRequest updateItemRequest)
  {
    var item = itemRepository.getItemById(id).orElseThrow(() ->
        new ObjectNotFoundException("Item can not be found by this Id: " + id));
    if (updateItemRequest.maxAvailableQuantity() < updateItemRequest.availableQuantity())
    {
      throw new BusinessException(
          "available quantity " + updateItemRequest.availableQuantity() + " can not exceed max available quantity " + item.getMaxQuantity());
    }

    item.setItemName(updateItemRequest.name());
    item.setAvailableQuantity(updateItemRequest.availableQuantity());
    item.setVendor(updateItemRequest.vendor());

    return itemRepository.save(item);
  }
}
