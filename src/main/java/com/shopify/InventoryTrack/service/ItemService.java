package com.shopify.InventoryTrack.service;

import com.shopify.InventoryTrack.exception.ObjectNotFoundException;
import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.repository.ItemRepository;
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

  public List<Item> getAllItems()
  {
    System.out.println("aaa");
    List<Item> result = itemRepository.findAll();
    return itemRepository.findAll();
  }

  public Item getItemById(long id)
  {
    return itemRepository.getItemById(id).orElseThrow(()->
        new ObjectNotFoundException("Item can not be found by this Id: " + id));
  }

  public void deleteItemById(int id)
  {
    if(!itemRepository.existsById(id)){
      throw new ObjectNotFoundException("Item can not be found by this Id: " + id);
    }

    itemRepository.deleteById(id);
  }

  @Transactional
  public void creatItem(Item item)
  {
    itemRepository.save(item);
  }
}
