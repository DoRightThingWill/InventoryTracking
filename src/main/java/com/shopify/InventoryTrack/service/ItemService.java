package com.shopify.InventoryTrack.service;

import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService
{
  @Autowired
  private final ItemRepository itemRepository;

  public List<Item> getAllItems()
  {
    return itemRepository.findAll();
  }

  public Item getItemById(long id)
  {
    return itemRepository.findById(id).orElseThrow();
  }
}
