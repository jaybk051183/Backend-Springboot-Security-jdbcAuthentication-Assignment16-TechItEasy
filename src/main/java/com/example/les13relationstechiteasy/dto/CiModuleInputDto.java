package com.example.les13relationstechiteasy.dto;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CiModuleInputDto {

    @NotBlank(message = "Naam is verplicht")
    public String name;

    @NotBlank(message = "Type is verplicht")
    public String type;

    @Positive(message = "Prijs moet een positief getal zijn")
    public Double price;

}
