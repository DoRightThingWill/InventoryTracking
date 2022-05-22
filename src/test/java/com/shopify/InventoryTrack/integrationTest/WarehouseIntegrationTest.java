package com.shopify.InventoryTrack.integrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class WarehouseIntegrationTest extends IntegrationTest
{

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

}
