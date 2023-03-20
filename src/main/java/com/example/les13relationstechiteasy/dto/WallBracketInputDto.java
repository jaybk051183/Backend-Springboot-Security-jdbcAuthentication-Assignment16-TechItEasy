package com.example.les13relationstechiteasy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class WallBracketInputDto {
    @NotBlank(message = "Size is verplicht")
    public String size;
    public Boolean adjustable;

    @NotBlank(message = "Naam is verplicht")
    public String name;

    @PositiveOrZero(message = "Price moet een positief getal of nul zijn")
    public Double price;

}
