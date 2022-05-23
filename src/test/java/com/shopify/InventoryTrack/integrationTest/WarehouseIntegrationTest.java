package com.shopify.InventoryTrack.integrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

import com.shopify.InventoryTrack.dto.CreateWarehouseRequest;
import com.shopify.InventoryTrack.model.Item;
import com.shopify.InventoryTrack.repository.ItemRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class WarehouseIntegrationTest extends IntegrationTest
{

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void createWarehouse(){
    var createWarehouseRequest = new CreateWarehouseRequest("1437 Big Oak Rd Macon, Georgia(GA), 31217", "(478) 745-7411");
    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .body(createWarehouseRequest)
        .when()
        .post("/warehouses")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .log()
        .all()
        .body(
            "address", equalTo("1437 Big Oak Rd Macon, Georgia(GA), 31217"),
            "phoneNumber", equalTo("(478) 745-7411"));
  }

  @Test
  public void assignInventoryToWarehouse(){

    Item item = Item.builder()
        .itemName("SHOPIFY SWAG")
        .availableQuantity(100L)
        .maxQuantity(2000L)
        .cost(10.5d)
        .price(25.5d)
        .vendor("SHOPIFY")
        .build();

    var savedItem = itemRepository.save(item);
    var itemId = savedItem.getId();

    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .pathParam("warehouseId", 1)
        .pathParam("itemId", 1)
        .when()
        .put("/warehouses/{warehouseId}/items/{itemId}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .log()
        .all()
        .body(
            "items.id", hasSize(2));

  }

  @Test
  public void removeInventoryFromWarehouse(){

  }

  @Test
  public void getAllWarehouse(){
    given()
        .baseUri(uri)
        .contentType(ContentType.JSON)
        .when()
        .get("/warehouses")
        .then()
        .statusCode(HttpStatus.OK.value())
        .log()
        .all()
        .body("", hasSize(4));
  }

  @Test
  public void getWarehouseById(){
    given()
        .baseUri(uri)
        .pathParam("id", 1)
        .contentType(ContentType.JSON)
        .when()
        .get("/warehouses/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .log()
        .all()
        .body(
            "id", equalTo(1),
            "address", equalTo("10201 Tanner Bridge Rd Henley, Missouri(MO), 65040"),
            "phoneNumber", equalTo("(573) 496-3106"),
            "items", hasSize(2)
            );
  }

  @Test
  public void failGetWarehouseById(){
    given()
        .baseUri(uri)
        .pathParam("id", 100)
        .contentType(ContentType.JSON)
        .when()
        .get("/warehouses/{id}")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

}
