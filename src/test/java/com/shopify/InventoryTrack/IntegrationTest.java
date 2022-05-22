package com.shopify.InventoryTrack;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTest
{
  @LocalServerPort
  protected int port;
  protected String uri;


//  protected WebTestClient webTestClient;

  @BeforeEach
  public void initialiseRestAssuredMockMvcStandalone(){
    uri = "http://localhost:" + port;
//    webTestClient = WebTestClient.bindToServer()
//        .baseUrl(uri)
//        .build();
  }

}
