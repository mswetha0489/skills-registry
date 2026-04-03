package com.metabyte.skillregistry.dto;

import jakarta.validation.constraints.NotBlank;

public class AliasRequest {
    @NotBlank
    public String alias;
}
