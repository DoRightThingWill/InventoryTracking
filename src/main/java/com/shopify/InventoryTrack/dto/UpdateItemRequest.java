package com.shopify.InventoryTrack.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UpdateItemRequest(
    @NotBlank
    @Min(value = 0)
    Long availableQuantity,

    @NotBlank
    @Size(max = 500)
    String name,

    @NotBlank
    @Min(value = 0)
    Long maxAvailableQuantity,

    @NotBlank
    @Size(max = 500)
    String vendor
    )
{
}
