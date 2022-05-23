package com.shopify.InventoryTrack.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;

public record AssignItemRequest(
    @NotBlank
    List<Long> itemIds
)
{

}
