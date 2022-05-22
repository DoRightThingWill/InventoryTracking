package com.shopify.InventoryTrack.controller;

import com.shopify.InventoryTrack.dto.CreateItemRequest;
import com.shopify.InventoryTrack.dto.UpdateItemRequest;
import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.service.ItemService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class ItemController
{

  @Autowired
  private final ItemService itemService;

  @GetMapping
  public List<Item> getAllItems(){
    return itemService.getAllItems();
  }

  @GetMapping("/{id}")
  public Item getItemById(@PathVariable @Valid long id){
    return itemService.getItemById(id);
  }

  @Transactional
  @DeleteMapping("/{id}")
  public void deleteItemById(@PathVariable @Valid long id){
    itemService.deleteItemById(id);
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Item createItem(@RequestBody CreateItemRequest createItemRequest){
    return itemService.creatItemFromRequest(createItemRequest);
  }

  @PutMapping("/{id}")
  public Item updateItemById(@PathVariable long id,
                             @RequestBody @Valid UpdateItemRequest updateItemRequest){
    return itemService.updateItemById(id, updateItemRequest);
  }
}
