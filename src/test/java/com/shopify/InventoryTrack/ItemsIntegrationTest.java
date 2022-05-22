package com.shopify.InventoryTrack;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

import com.shopify.InventoryTrack.service.ItemService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ItemsIntegrationTest extends IntegrationTest
{
  @Autowired
  private ItemService itemService;

  @Autowired
  private ItemsTestData itemsTestData;


  @DisplayName("should return all items when get all items")
  @Test
  public void getAllItems(){
    itemsTestData.addOneItem();

    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .when()
        .get("/items")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("", hasSize(4));


  }


  @DisplayName("should return the item with Id = 1 when get an item by ID")
  @Test
  public void getItemById(){

    itemsTestData.addOneItem();

    given()
        .baseUri(uri)
        .pathParam("id", 4)
        .contentType(ContentType.JSON)
        .when()
        .get("/items/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(
            "id", equalTo(4),
            "itemName", equalTo("Mac Pro"),
            "availableQuantity", equalTo(200),
            "maxQuantity", equalTo(2000),
            "cost", equalTo((float)1500),
            "price", equalTo((float)2500),
            "vendor", equalTo("APPLE"),
            "warehouses", hasSize(2)
            );
  }
}
