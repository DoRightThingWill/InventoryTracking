package com.shopify.InventoryTrack.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateWarehouseRequest (
    @NotBlank
    @Size(max = 500)
    String address,

    @NotBlank
    @Size(max = 30)
    String phoneNumber
)
{
}
