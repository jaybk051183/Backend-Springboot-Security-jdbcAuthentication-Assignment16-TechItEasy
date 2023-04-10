package com.example.les13relationstechiteasy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//Dit is een Java-klasse genaamd TelevisionWallBracketKey, die een ingebedde sleutel (embedded key) of samengestelde sleutel (composite key) voor de TelevisionWallBracket entiteit vertegenwoordigt. Dit wordt gebruikt wanneer een entiteit meerdere kolommen als primaire sleutel heeft
//In het algemeen wordt deze ingebedde sleutelklasse (TelevisionWallBracketKey) gebruikt in de TelevisionWallBracket entiteit om een relatie tussen de Television en WallBracket entiteiten te vertegenwoordigen via een samengestelde sleutel bestaande uit hun respectievelijke ID's.

//Deze embeddable class wordt gebruikt als Embedded Id in de TelevisionWallBracket class
//@Embeddable: Deze annotatie geeft aan dat deze klasse een ingebedd object is en zal worden gebruikt als een samengestelde sleutel in een andere entiteit (in dit geval TelevisionWallBracket).
@Embeddable
public class TelevisionWallBracketKey implements Serializable {
    //De klasse wordt gedefinieerd met de naam TelevisionWallBracketKey en implementeert de Serializable interface. Dit is vereist voor ingebedde sleutels om ze te kunnen opslaan en ophalen uit de database.

    //Deze annotaties geven aan dat de velden moeten worden opgeslagen in kolommen met de gespecificeerde namen in de database.
    @Column(name = "television_id")
    private Long televisionId;

    @Column(name = "wall_bracket_id")
    private Long wallBracketId;

    //Constructor
    public TelevisionWallBracketKey() {}
    public TelevisionWallBracketKey(Long televisionId, Long wallBracketId) {
        this.televisionId = televisionId;
        this.wallBracketId = wallBracketId;
    }

    //Getters & Setters
    public Long getTelevisionId() {
        return televisionId;
    }

    public Long getWallBracketId() {
        return wallBracketId;
    }

    public void setTelevisionId(Long televisionId) {
        this.televisionId = televisionId;
    }

    public void setWallBracketId(Long wallBracketId) {
        this.wallBracketId = wallBracketId;
    }

    //De equals() methode is overschreven om te controleren of twee TelevisionWallBracketKey objecten gelijk zijn. Deze methode vergelijkt de televisionId en wallBracketId eigenschappen van beide objecten. Als beide eigenschappen in de twee objecten gelijk zijn, wordt true geretourneerd, anders false.
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        TelevisionWallBracketKey that = (TelevisionWallBracketKey) o;
        return televisionId.equals(that.televisionId)&& wallBracketId.equals(that.wallBracketId);
    }

    //De hashCode() methode is ook overschreven om een hashcode voor het object te genereren. Dit is belangrijk voor het correct functioneren van de equals() methode in verschillende datastructuren zoals HashSet en HashMap. De methode gebruikt de Objects.hash() methode om een hashcode te genereren op basis van de televisionId en wallBracketId eigenschappen.
    @Override
    public int hashCode() {return Objects.hash(televisionId, wallBracketId);}
}

//equals() & hascode()

//De equals() en hashCode() methoden komen oorspronkelijk uit de java.lang.Object klasse, de hoofdklasse van alle Java-objecten. Ze zijn belangrijk voor het correct functioneren van Java-objecten in verschillende datastructuren, zoals HashSet en HashMap, en worden gebruikt om objecten te vergelijken op basis van hun inhoud in plaats van hun geheugenadressen. In deze specifieke klasse, TelevisionWallBracketKey, worden deze twee methoden overschreven om ervoor te zorgen dat de objecten correct worden vergeleken en gehasht op basis van hun televisionId en wallBracketId eigenschappen.

//equals(): Deze methode wordt overschreven om te controleren of twee TelevisionWallBracketKey objecten gelijk zijn. Het vergelijkt de televisionId en wallBracketId eigenschappen van beide objecten. Als beide eigenschappen in de twee objecten gelijk zijn, wordt true geretourneerd, anders false. Het overschrijven van deze methode zorgt ervoor dat de vergelijking van twee TelevisionWallBracketKey objecten op de juiste manier gebeurt op basis van hun eigenschappen in plaats van hun geheugenadressen.
//hashCode(): Deze methode wordt overschreven om een hashcode voor het TelevisionWallBracketKey object te genereren. Dit is belangrijk voor het correct functioneren van de equals() methode in verschillende datastructuren, zoals HashSet en HashMap. De methode gebruikt de Objects.hash() methode om een hashcode

//In de TelevisionWallBracketKey klasse worden de equals() en hashCode() methoden overschreven om te zorgen voor een correcte vergelijking en hashing van de objecten op basis van hun televisionId en wallBracketId eigenschappen, omdat dit de relevante velden zijn die uniek de combinatie van een televisie en een muurbeugel identificeren.
//Standaard implementeren de equals() en hashCode() methoden in de java.lang.Object klasse een vergelijking en hashing op basis van objectreferenties (geheugenadressen). Dit is meestal niet voldoende voor complexe objecten die op basis van hun inhoud moeten worden vergeleken.

//In het geval van de TelevisionWallBracketKey klasse is het belangrijk om de objecten te vergelijken op basis van hun televisionId en wallBracketId eigenschappen, omdat deze de werkelijke relatie tussen een televisie en een muurbeugel representeren. Door de equals() en hashCode() methoden te overschrijven, zorg je ervoor dat de objecten correct worden vergeleken en gehasht op basis van deze relevante eigenschappen. Dit is vooral belangrijk bij het gebruik van datastructuren zoals HashSet en HashMap, die afhankelijk zijn van de correcte implementatie van deze methoden voor het opslaan en ophalen van objecten.

//In de TelevisionWallBracketKey klasse is het belangrijk om objecten te vergelijken om de volgende redenen:
//Uniciteit: In de context van een many-to-many relatie tussen de Television en WallBracket entiteiten, is de TelevisionWallBracketKey klasse bedoeld om unieke combinaties van televisies en muurbeugels te identificeren. Door objecten te vergelijken op basis van hun televisionId en wallBracketId eigenschappen, kunt u ervoor zorgen dat er geen duplicaten zijn in de relatie.
//Datastructuren: Zoals eerder vermeld, zijn er verschillende datastructuren in Java, zoals HashSet en HashMap, die afhankelijk zijn van de correcte implementatie van de equals() en hashCode() methoden voor het opslaan en ophalen van objecten. Wanneer u objecten van de TelevisionWallBracketKey klasse opslaat in dergelijke datastructuren, is het belangrijk om een correcte vergelijking en hashing van de objecten te garanderen op basis van hun relevante eigenschappen.
//Consistentie: Het is een goede programmeerpraktijk om de equals() en hashCode() methoden te overschrijven in aangepaste klassen die als sleutels dienen voor relaties tussen entiteiten. Dit zorgt voor consistentie bij het vergelijken en hashen van objecten in uw toepassing en voorkomt onverwachte gedragingen