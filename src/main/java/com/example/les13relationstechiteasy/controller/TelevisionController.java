package com.example.les13relationstechiteasy.controller;

import com.example.les13relationstechiteasy.dto.IdInputDto;
import com.example.les13relationstechiteasy.dto.TelevisionDto;
import com.example.les13relationstechiteasy.dto.TelevisionInputDto;
import com.example.les13relationstechiteasy.dto.WallBracketDto;
import com.example.les13relationstechiteasy.service.TelevisionService;
import com.example.les13relationstechiteasy.service.TelevisionWallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("televisions")
public class TelevisionController {

    //Constructor: De controller heeft twee serviceklassen geïnjecteerd via de constructor: TelevisionService en TelevisionWallBracketService.
    // Deze services worden gebruikt om CRUD-operaties (Create, Read, Update, Delete) op de televisie-entiteiten en hun gerelateerde entiteiten uit te voeren.
    private final TelevisionService televisionService;
    private final TelevisionWallBracketService televisionWallBracketService;

    public TelevisionController(TelevisionService televisionService,
                                TelevisionWallBracketService televisionWallBracketService){
        this.televisionService = televisionService;
        this.televisionWallBracketService = televisionWallBracketService;
    }


    //Endpoints met methodes definieren

    @GetMapping
    //De methode retourneert een ResponseEntity object met een lijst van TelevisionDto objecten. Het accepteert een enkele parameter: een optioneel brand query parameter van het type String.
    // De @RequestParam annotatie zorgt ervoor dat het brand parameter wordt opgehaald uit de query parameters van het HTTP-verzoek.
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {
        //Hier wordt een variabele dtos gedeclareerd, die een lijst van TelevisionDto objecten zal bevatten.
        List<TelevisionDto> dtos;
//Deze regel controleert of het brand parameter leeg is, wat betekent dat er geen merk is opgegeven in het verzoek
        if (brand.isEmpty()){
            //Als er geen merk is opgegeven roept deze regel de getAllTelevisions methode aan op het televisionService object. Deze methode haalt alle televisies op en slaat ze op in de dtos variabele.
            dtos = televisionService.getAllTelevisions();

        } else {
            //In de else-tak roepen we de getAllTelevisionsByBrand methode aan op het televisionService object en geven het merkparameter als argument door. Deze methode haalt alle televisies op van het opgegeven merk en slaat ze op in de dtos variabele.
            dtos = televisionService.getAllTelevisionsByBrand(brand.get());
        }
        //Tot slot retourneert de methode een ResponseEntity object met een HTTP-statuscode van 200 (OK) en het dtos object als de body van het antwoord. Hiermee wordt de lijst van TelevisionDto objecten naar de client gestuurd als het resultaat van het GET-verzoek.
        return ResponseEntity.ok().body(dtos);
    }


    //getTelevision: Haalt een specifieke televisie op basis van het meegegeven ID.
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id")Long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);
        return ResponseEntity.ok().body(television);
    }


    @PostMapping
    //De methode neemt één parameter, televisionInputDto, die het request body bevat.
    // De @Valid annotatie zorgt ervoor dat de invoer wordt gevalideerd op basis van de beperkingen die zijn gedefinieerd in de TelevisionInputDto klasse.
    // De @RequestBody annotatie geeft aan dat de parameter uit de request body moet worden gelezen.
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {
        // Deze regel roept de addTelevision methode van de televisionService aan, die de logica bevat om een nieuwe televisie toe te voegen aan de database. De televisionInputDto wordt doorgegeven als een parameter en de methode retourneert een nieuw TelevisionDto object, dat de gecreëerde televisie vertegenwoordigt. Dit object wordt toegewezen aan de variabele dto.
        TelevisionDto dto = televisionService.addTelevision(televisionInputDto);
        //Deze regel retourneert een ResponseEntity object met de gecreëerde TelevisionDto. De created methode wordt gebruikt om een nieuwe ResponseEntity met een HTTP-statuscode van 201 (Created) te maken. De null parameter betekent dat er geen specifieke URI voor de gecreëerde resource wordt verstrekt, maar dit kan worden vervangen door een URI als dat nodig is. De .body(dto) methode voegt de TelevisionDto toe aan de body van het response.
        return ResponseEntity.created(null).body(dto);
    }


    //@DeleteMapping("/{id}"): Dit is een annotatie die aangeeft dat deze methode een HTTP DELETE-verzoek moet verwerken. De URL voor dit verzoek bevat een padvariabele id, die het ID van de te verwijderen televisie vertegenwoordigt.
    @DeleteMapping("/{id}")
    //De methode retourneert een ResponseEntity object van het type Object. Het accepteert een enkele parameter: een id padvariabele van het type Long.
    // De @PathVariable annotatie zorgt ervoor dat het id parameter wordt opgehaald uit de padvariabelen van het HTTP-verzoek.
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
//Deze regel roept de deleteTelevision methode aan op het televisionService object en geeft het id parameter als argument door. Deze methode verwijdert de televisie met het opgegeven ID uit de onderliggende gegevensopslag.
        televisionService.deleteTelevision(id);
// Tot slot retourneert de methode een ResponseEntity object met een HTTP-statuscode van 204 (No Content) zonder een body. Dit geeft aan dat het DELETE-verzoek succesvol is uitgevoerd en dat er geen aanvullende informatie wordt verstrekt in de body van het antwoord.
// De noContent() methode creëert een ResponseEntity.Builder met de statuscode 204, en de build() methode bouwt vervolgens het uiteindelijke ResponseEntity object om terug te sturen naar de client.
        return ResponseEntity.noContent().build();
    }


    //Dit is een annotatie die aangeeft dat deze methode een HTTP PUT-verzoek moet verwerken. De URL voor dit verzoek bevat een padvariabele id, die het ID van de bij te werken televisie vertegenwoordigt.
    @PutMapping("/televisions/{id}")
    //De methode retourneert een ResponseEntity object van het type Object.
    // Het accepteert twee parameters: een id pad variabele van het type Long en een newTelevision object van het type TelevisionInputDto.
    // De @PathVariable annotatie zorgt ervoor dat het id parameter wordt opgehaald uit de pad variabelen van het HTTP-verzoek, en de
    // @RequestBody annotatie zorgt ervoor dat het newTelevision object wordt opgehaald uit de body van het HTTP-verzoek.
    // De @Valid annotatie zorgt ervoor dat het newTelevision object wordt gevalideerd op basis van de beperkingen die zijn gedefinieerd in de TelevisionInputDto klasse.
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto newTelevision) {
      // Deze regel roept de updateTelevision methode aan op het televisionServiceobject en geeft de id en newTelevision parameters als argumenten door.
        // Deze methode werkt de televisie met het opgegeven ID bij in de onderliggende gegevensopslag op basis van de gegevens in het newTelevision object.
        //Het resultaat van deze methode is eenTelevisionDto` object dat de bijgewerkte televisiegegevens bevat.
        TelevisionDto dto = televisionService.updateTelevision(id, newTelevision);
//Tot slot retourneert de methode een ResponseEntity object met een HTTP-statuscode van 200 (OK) en het bijgewerkte TelevisionDto object als de body van het antwoord. Hiermee wordt de bijgewerkte televisiegegevens naar de client gestuurd als het resultaat van het PUT-verzoek.
        return ResponseEntity.ok().body(dto);
    }

    // Onderstaande 2 methodes zijn endpoints om andere entiteiten toe te voegen aan de Television.
    // Dit is één manier om dit te doen, met één PathVariable en één RequestBody.

    //Dit is een annotatie die aangeeft dat deze methode een HTTP PUT-verzoek moet verwerken. De URL voor dit verzoek bevat een pad variabele id, die het ID van de televisie vertegenwoordigt waaraan de afstandsbediening moet worden toegewezen.
    @PutMapping("/{id}/remotecontroller")
    //De methode retourneert een ResponseEntity object van het type Object. Het accepteert twee parameters: een id pad variabele van het type Long en een input object van het type IdInputDto.
    // De @PathVariable("id") annotatie zorgt ervoor dat het id parameter wordt opgehaald uit de pad variabelen van het HTTP-verzoek, en de @RequestBody annotatie zorgt ervoor dat het input object wordt opgehaald uit de body van het HTTP-verzoek.
    //De @Valid annotatie zorgt ervoor dat het input object wordt gevalideerd op basis van de beperkingen die zijn gedefinieerd in de IdInputDto klasse.
    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id,@Valid @RequestBody IdInputDto input) {

        //Deze regel roept de assignRemoteControllerToTelevision methode aan op het televisionService object en geeft de id en input.id parameters als argumenten door. Deze methode wijst de afstandsbediening met het ID input.id toe aan de televisie met het opgegeven ID in de onderliggende gegevensopslag.
        televisionService.assignRemoteControllerToTelevision(id, input.id);
        //Tot slot retourneert de methode een ResponseEntity object met een HTTP-statuscode van 204 (No Content) zonder een body.
        // Dit geeft aan dat het PUT-verzoek succesvol is uitgevoerd en dat er geen aanvullende informatie wordt verstrekt in de body van het antwoord.
        // De noContent() methode creëert een ResponseEntity.Builder met de statuscode 204, en de build() methode bouwt vervolgens het uiteindelijke ResponseEntity object om terug te sturen naar de client.
        return ResponseEntity.noContent().build();
    }


    //Dit is een andere manier om het te doen, met twee Path variables, maar het kan uiteraard ook anders.
    @PutMapping("/{id}/{ciModuleId}")
    public ResponseEntity<Object> assignCIModuleToTelevision(@PathVariable("id") Long id, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assignCIModuleToTelevision(id, ciModuleId);
        return ResponseEntity.noContent().build();
    }



    // Deze methode is om alle wallbrackets op te halen die aan een bepaalde television gekoppeld zijn.
    // Deze methode maakt gebruik van de televisionWallBracketService.
    @GetMapping("/wallBrackets/{televisionId}")
    public ResponseEntity<Collection<WallBracketDto>> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
        return ResponseEntity.ok(televisionWallBracketService.getWallBracketsByTelevisionId(televisionId));
    }

}
