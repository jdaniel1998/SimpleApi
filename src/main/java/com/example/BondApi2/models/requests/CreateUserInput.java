package com.example.BondApi2.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserInput {

    @NotBlank(message = "Name (name) is required")
    @Size(max = 100, message = "Name max length is 100 characters")
    private String name;

    @Size(max = 254, message = "Description max length is 254 characters")
    private String description;

    @NotBlank(message = "Public key (public_key) is required")
    private String public_key;

    @NotBlank(message = "Api key (api_key) is required")
    private String api_key;

    @NotBlank(message = "Purchase id (purchase_id) is required")
    private String purchase_id;

}
