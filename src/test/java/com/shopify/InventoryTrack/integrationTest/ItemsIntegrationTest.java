package com.shopify.InventoryTrack.integrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.shopify.InventoryTrack.dto.CreateItemRequest;
import com.shopify.InventoryTrack.dto.UpdateItemRequest;
import com.shopify.InventoryTrack.repository.ItemRepository;
import com.shopify.InventoryTrack.service.ItemService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

public class ItemsIntegrationTest extends IntegrationTest
{
  @Autowired
  private ItemService itemService;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ItemsTestData itemsTestData;


  @DisplayName("return item response when successfully create an item")
  @Test
  public void returnItemResponseWhenCreateAnItemSuccess()
  {
    var createItemRequest = new CreateItemRequest(
        "SAMSUNG PHONE",
        100L,
        300L,
        1495.5,
        2155.5,
        "SAMSUNG US");


    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .body(createItemRequest)
        .when()
        .post("items")
        .then()
        .body(
            "id", equalTo(5),
            "itemName", equalTo("SAMSUNG PHONE"),
            "availableQuantity", equalTo(100),
            "maxQuantity", equalTo(300),
            "cost", equalTo(1495.5f),
            "price", equalTo(2155.5f),
            "vendor", equalTo("SAMSUNG US"),
            "warehouses", hasSize(0)
        );
  }

  @Transactional
  @DisplayName("should return the item with Id = 1 when get an item by ID")
  @Test
  public void getItemById()
  {
    given()
        .baseUri(uri)
        .pathParam("id", 1)
        .contentType(ContentType.JSON)
        .when()
        .get("/items/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(
            "id", equalTo(1),
            "itemName", equalTo("Tire"),
            "availableQuantity", equalTo(200),
            "maxQuantity", equalTo(2000),
            "cost", equalTo((float) 215.2),
            "price", equalTo((float) 310.2),
            "vendor", equalTo("Blue Tire"),
            "warehouses", hasSize(1)
        );
  }

  @DisplayName("should return not found when get an item by an wrong ID")
  @Test
  public void shouldNotFoundWhenGetAnItemByWrongId()
  {
    given()
        .baseUri(uri)
        .pathParam("id", 100)
        .contentType(ContentType.JSON)
        .when()
        .get("/items/{id}")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }


  @Transactional
  @DisplayName("successfully update information of an item")
  @Test
  public void updateAnItem()
  {

    var updateItemRequest = new UpdateItemRequest(1000L, "APPLE WATCH", 2000L, "APPLE-US");

    given()
        .baseUri(uri)
        .pathParam("id", 4)
        .contentType(ContentType.JSON)
        .body(updateItemRequest)
        .when()
        .put("/items/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(
            "id", equalTo(4),
            "itemName", equalTo("APPLE WATCH"),
            "availableQuantity", equalTo(1000),
            "maxQuantity", equalTo(2000),
            "cost", equalTo((float) 1500),
            "price", equalTo((float) 2500),
            "vendor", equalTo("APPLE-US"),
            "warehouses", hasSize(2)
        );

  }

  @DisplayName("successfully update the warehouse of an item")
  @Test
  public void updateWarehouseOfAnItem()
  {


  }

  @Transactional
  @DisplayName("fail in updating the the available quantity of an item")
  @Test
  public void failInUpdatingTheAvailableQuantity()
  {

    var updateItemRequest = new UpdateItemRequest(10000l, "APPLE IPAD", 2000l, "APPLE UK");

    given()
        .baseUri(uri)
        .pathParam("id", 4)
        .contentType(ContentType.JSON)
        .body(updateItemRequest)
        .when()
        .put("/items/{id}")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
    ;
  }

  @Transactional
  @DisplayName("successfully delete an item")
  @Test
  public void deleteAnItem()
  {

    given()
        .baseUri(uri)
        .pathParam("id", 2L)
        .contentType(ContentType.JSON)
        .when()
        .delete("/items/{id}")
        .then()
        .statusCode(HttpStatus.OK.value());
    assertFalse(itemRepository.getItemById(2).isPresent());
  }

  @DisplayName("should return all items when get all items")
  @Test
  public void getAllItems()
  {

    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .when()
        .get("/items")
        .then()
        .statusCode(HttpStatus.OK.value())
        .log()
        .all()
        .body("", hasSize(4));
  }
}
