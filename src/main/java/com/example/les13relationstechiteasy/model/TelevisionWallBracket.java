package com.example.les13relationstechiteasy.model;

import jakarta.persistence.*;

@Entity
public class TelevisionWallBracket {

    // EmbeddedId zorgt dat er geen nieuwe Id wordt aangemaakt, maar dat de variabelen met de @MapsId annotatie tot key worden gecombineerd.
    //@EmbeddedId: Deze annotatie wordt gebruikt om aan te geven dat het id veld een samengestelde sleutel is voor deze entiteit en dat het een ingebed object zal bevatten (TelevisionWallBracketKey).
    @EmbeddedId
    private TelevisionWallBracketKey id;

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    //Deze annotaties geven aan dat er een "many-to-one" relatie bestaat tussen de TelevisionWallBracket entiteit en de Television entiteit. De TelevisionWallBracket entiteit is de eigenaar van deze relatie.
    @ManyToOne(fetch = FetchType.LAZY)
    //De @MapsId annotatie geeft aan dat het televisionId veld van de samengestelde sleutel (TelevisionWallBracketKey) gemapt wordt op deze relatie.
    @MapsId("televisionId")
    @JoinColumn(name = "television_id")
    private Television television;


    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    //Deze annotaties geven aan dat er een "many-to-one" relatie bestaat tussen de TelevisionWallBracket entiteit en de WallBracket entiteit. De TelevisionWallBracket entiteit is de eigenaar van deze relatie.
    @ManyToOne
    //De @MapsId annotatie geeft aan dat het wallBracketId veld van de samengestelde sleutel (TelevisionWallBracketKey) gemapt wordt op deze relatie.
    @MapsId("wallBracketId")
    @JoinColumn(name = "wall_bracket_id")
    private WallBracket wallBracket;

    //Getters & Setters
    public TelevisionWallBracketKey getId() {
        return id;
    }

    public Television getTelevision() {
        return television;
    }

    public WallBracket getWallBracket() {
        return wallBracket;
    }

    public void setId(TelevisionWallBracketKey id) {
        this.id = id;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }

    public void setWallBracket(WallBracket wallBracket) {
        this.wallBracket = wallBracket;
    }
}
