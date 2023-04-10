package com.example.les13relationstechiteasy.dto;
//TelevisionDto klasse is een DTO-klasse die is ontworpen om informatie over een televisie-object op te slaan en over te dragen tussen verschillende lagen van een applicatie

public class TelevisionDto {

    //De klasse bevat een reeks openbare velden die de eigenschappen van een televisie-object vertegenwoordigen.
    // Deze velden zijn van verschillende gegevenstypen zoals Long, String, Double, Boolean en Integer.
    // Er zijn ook twee extra velden van het type CIModuleDto en RemoteControllerDto om informatie over een CI-module en een afstandsbediening bij de televisie op te slaan.
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;

    private CIModuleDto ciModuleDto;
    private RemoteControllerDto remoteControllerDto;

    //Standaard constructor:
    //De klasse bevat een standaard, parameterloze constructor (TelevisionDto()). Deze constructor is nodig voor het de serialiseren van gegevens uit bijvoorbeeld JSON-formaat.
    public TelevisionDto() {
    }

    //Overbelaste constructor:
    //Een overbelaste constructor (TelevisionDto(Long id, String type, ...) is gedefinieerd om het eenvoudiger te maken om een nieuw TelevisionDto object te maken met alle vereiste velden als argumenten.
    public TelevisionDto(Long id, String type, String brand, String name, Double price, Double availableSize, Double refreshRate, String screenType, String screenQuality, Boolean smartTv, Boolean wifi, Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight, Integer originalStock, Integer sold) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
    }

    //Getters:
    //Getters zijn methoden die de waarde van de velden in de klasse retourneren
    //Setters:
    //Setters zijn methoden die de waarde van de velden in de klasse instellen.
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getAvailableSize() {
        return availableSize;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public Boolean getSmartTv() {
        return smartTv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public Boolean getVoiceControl() {
        return voiceControl;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public Boolean getAmbiLight() {
        return ambiLight;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public Integer getSold() {
        return sold;
    }

    public CIModuleDto getCiModuleDto() {
        return ciModuleDto;
    }

    public RemoteControllerDto getRemoteControllerDto() {
        return remoteControllerDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailableSize(Double availableSize) {
        this.availableSize = availableSize;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public void setSmartTv(Boolean smartTv) {
        this.smartTv = smartTv;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public void setVoiceControl(Boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setAmbiLight(Boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public void setCiModuleDto(CIModuleDto ciModuleDto) {
        this.ciModuleDto = ciModuleDto;
    }

    public void setRemoteControllerDto(RemoteControllerDto remoteControllerDto) {
        this.remoteControllerDto = remoteControllerDto;
    }
}

