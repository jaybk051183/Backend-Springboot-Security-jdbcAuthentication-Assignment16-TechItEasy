package com.example.les13relationstechiteasy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class RemoteControllerInputDto {
    @NotBlank(message = "CompatibleWith is verplicht")
    public String compatibleWith;

    @NotBlank(message = "BatteryType is verplicht")
    public String batteryType;

    @NotBlank(message = "Naam is verplicht")
    public String name;

    @NotBlank(message = "Merk is verplicht")
    public String brand;

    @Positive(message = "Prijs moet een positief getal zijn")
    public Double price;

    @PositiveOrZero(message = "OriginalStock moet een positief getal of nul zijn")
    private Integer originalStock;

}
