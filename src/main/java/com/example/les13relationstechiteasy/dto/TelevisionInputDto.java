package com.example.les13relationstechiteasy.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class TelevisionInputDto {

    @NotBlank(message = "Type is verplicht")
    private String type;

    @NotBlank(message = "Merk is verplicht")
    private String brand;

    @NotBlank(message = "Naam is verplicht")
    private String name;

    @Positive(message = "Prijs moet een positief getal zijn")
    private Double price;

    @Positive(message = "AvailableSize moet een positief getal zijn")
    private Double availableSize;

    @Positive(message = "RefreshRate moet een positief getal zijn")
    private Double refreshRate;

    @NotBlank(message = "ScreenType is verplicht")
    private String screenType;

    @NotBlank(message = "ScreenQuality is verplicht")
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;

    @PositiveOrZero(message = "OriginalStock moet een positief getal of nul zijn")
    private Integer originalStock;

    @PositiveOrZero(message = "Sold moet een positief getal of nul zijn")
    private Integer sold;

}
