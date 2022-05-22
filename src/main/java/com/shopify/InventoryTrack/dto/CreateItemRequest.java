package com.shopify.InventoryTrack.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateItemRequest(
    @NotBlank
    @Size(max = 500)
    String itemName,

    @NotBlank
    @Min(value = 0)
    Long availableQuantity,

    @NotBlank
    @Min(value = 0)
    Long maxQuantity,

    @NotBlank
    @Min(value = 0)
    Double cost,

    @NotBlank
    @Min(value = 0)
    Double price,

    @NotBlank
    @Size(max = 500)
    String vendor
)
{
}
