package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.dto.TelevisionDto;
import com.example.les13relationstechiteasy.dto.WallBracketDto;
import com.example.les13relationstechiteasy.exceptions.RecordNotFoundException;
import com.example.les13relationstechiteasy.model.Television;
import com.example.les13relationstechiteasy.model.TelevisionWallBracket;
import com.example.les13relationstechiteasy.model.TelevisionWallBracketKey;
import com.example.les13relationstechiteasy.model.WallBracket;
import com.example.les13relationstechiteasy.repository.TelevisionRepository;
import com.example.les13relationstechiteasy.repository.TelevisionWallBracketRepository;
import com.example.les13relationstechiteasy.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.
//De TelevisionWallBracketService klasse is een serviceklasse die afhankelijk is van drie repositories (TelevisionRepository, WallBracketRepository, en TelevisionWallBracketRepository) om de logica en interacties tussen Television, WallBracket en TelevisionWallBracket objecten te beheren
@Service
public class TelevisionWallBracketService{
    //Drie privé instantievariabelen worden gedefinieerd voor de klasse:
    //a. televisionRepository: Een object van het type TelevisionRepository dat wordt gebruikt voor het beheren van de toegang tot Television objecten in de onderliggende opslag (bijvoorbeeld een database).
    private TelevisionRepository televisionRepository;
    //b. wallBracketRepository: Een object van het type WallBracketRepository dat wordt gebruikt voor het beheren van de toegang tot WallBracket objecten in de onderliggende opslag.
    private WallBracketRepository wallBracketRepository;
    //c. televisionWallBracketRepository: Een object van het type TelevisionWallBracketRepository dat wordt gebruikt voor het beheren van de toegang tot TelevisionWallBracket objecten in de onderliggende opslag.
    private TelevisionWallBracketRepository televisionWallBracketRepository;

    //Een constructor voor de klasse wordt gedefinieerd met drie parameters:
    //a. TelevisionRepository televisionRepository: Een object van het type TelevisionRepository.
    //b. WallBracketRepository wallBracketRepository: Een object van het type WallBracketRepository.
    //c. TelevisionWallBracketRepository televisionWallBracketRepository: Een object van het type TelevisionWallBracketRepository.
    //Deze constructor initialiseert de instantievariabelen met de meegegeven waarden.
    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionWallBracketRepository televisionWallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionWallBracketRepository = televisionWallBracketRepository;
    }


//De getTelevisionsByWallBracketId(Long wallBracketId) methode haalt een verzameling van Television objecten op die geassocieerd zijn met een bepaald WallBracket object, converteert deze objecten naar TelevisionDto objecten en retourneert de verzameling.
    public Collection<TelevisionDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        //Een nieuwe lege HashSet van TelevisionDto objecten wordt gecreëerd en toegewezen aan de variabele dtos.
        Collection<TelevisionDto> dtos = new HashSet<>();
        //De methode findAllByWallBracketId(wallBracketId) van het televisionWallBracketRepository object wordt aangeroepen met het argument wallBracketId. Dit retourneert een verzameling van TelevisionWallBracket objecten die geassocieerd zijn met het opgegeven WallBracket object. De verzameling wordt toegewezen aan de variabele televisionWallbrackets.
        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        //Er wordt een for-each loop uitgevoerd voor elk TelevisionWallBracket object in de televisionWallbrackets verzameling.
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            //a. Het Television object geassocieerd met het huidige TelevisionWallBracket object wordt opgehaald met de getTelevision() methode en toegewezen aan de variabele television.
            Television television = televisionWallbracket.getTelevision();
            //b. Een nieuw TelevisionDto object wordt gecreëerd en toegewezen aan de variabele televisionDto.
            TelevisionDto televisionDto = new TelevisionDto();
            //c. De velden van het televisionDto object worden ingesteld op basis van de waarden van de velden van het television object.
            televisionDto.setId(television.getId());
            televisionDto.setType(television.getType());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setName(television.getName());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setAvailableSize(television.getAvailableSize());
            televisionDto.setRefreshRate(television.getRefreshRate());
            televisionDto.setScreenType(television.getScreenType());
            televisionDto.setScreenQuality(television.getScreenQuality());
            televisionDto.setSmartTv(television.getSmartTv());
            televisionDto.setWifi(television.getWifi());
            televisionDto.setVoiceControl(television.getVoiceControl());
            televisionDto.setHdr(television.getHdr());
            televisionDto.setBluetooth(television.getBluetooth());
            televisionDto.setAmbiLight(television.getAmbiLight());
            televisionDto.setOriginalStock(television.getOriginalStock());
            televisionDto.setSold(television.getSold());
//d. Het televisionDto object wordt toegevoegd aan de dtos verzameling.
            dtos.add(televisionDto);
        }
        //De dtos verzameling met TelevisionDto objecten wordt geretourneerd.
        return dtos;
    }


    //De getWallBracketsByTelevisionId(Long televisionId) methode haalt een verzameling van WallBracket objecten op die geassocieerd zijn met een bepaald Television object, converteert deze objecten naar WallBracketDto objecten en retourneert de verzameling.
    // Collection is de super klasse van zowel List als Set.
    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        //We gebruiken hier Set om te voorkomen dat er dubbele entries in staan.
        //Een nieuwe lege HashSet van WallBracketDto objecten wordt gecreëerd en toegewezen aan de variabele dtos. HashSet wordt gebruikt om ervoor te zorgen dat er geen dubbele items in de verzameling voorkomen.
        Set<WallBracketDto> dtos = new HashSet<>();
        //De methode findAllByTelevisionId(televisionId) van het televisionWallBracketRepository object wordt aangeroepen met het argument televisionId. Dit retourneert een lijst van TelevisionWallBracket objecten die geassocieerd zijn met het opgegeven Television object. De lijst wordt toegewezen aan de variabele televisionWallbrackets.
        List<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        //Er wordt een for-each loop uitgevoerd voor elk TelevisionWallBracket object in de televisionWallbrackets lijst.
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            //a. Het WallBracket object geassocieerd met het huidige TelevisionWallBracket object wordt opgehaald met de getWallBracket() methode en toegewezen aan de variabele wallBracket.
            WallBracket wallBracket = televisionWallbracket.getWallBracket();
            //b. Een nieuw WallBracketDto object wordt gecreëerd en toegewezen aan de variabele dto.
            var dto = new WallBracketDto();
            //c. De velden van het dto object worden ingesteld op basis van de waarden van de velden van het wallBracket object.
            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());
            //d. Het dto object wordt toegevoegd aan de dtos verzameling.
            dtos.add(dto);
        }
        //De dtos verzameling met WallBracketDto objecten wordt geretourneerd.
        return dtos;
    }


//De addTelevisionWallBracket(Long televisionId, Long wallBracketId) methode koppelt een Television object, geïdentificeerd door televisionId, aan een WallBracket object, geïdentificeerd door wallBracketId, en slaat deze koppeling op in de vorm van een TelevisionWallBracket object. De methode retourneert een TelevisionWallBracketKey object dat de unieke sleutel van de opgeslagen koppeling vertegenwoordigt.
    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        //Een nieuw TelevisionWallBracket object wordt gecreëerd en toegewezen aan de variabele televisionWallBracket.
        var televisionWallBracket = new TelevisionWallBracket();
        //Er wordt gecontroleerd of het Television object met het opgegeven televisionId bestaat. Als dit niet het geval is, wordt een RecordNotFoundException gegenereerd.
        if (!televisionRepository.existsById(televisionId)) {throw new RecordNotFoundException();}
        //Het Television object wordt opgehaald uit de televisionRepository met behulp van de findById(televisionId) methode en toegewezen aan de variabele television.
        Television television = televisionRepository.findById(televisionId).orElse(null);
        //Er wordt gecontroleerd of het WallBracket object met het opgegeven wallBracketId bestaat. Als dit niet het geval is, wordt een RecordNotFoundException gegenereerd.
        if (!wallBracketRepository.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        //Het WallBracket object wordt opgehaald uit de wallBracketRepository met behulp van de findById(wallBracketId) methode en toegewezen aan de variabele wallBracket.
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        //De setTelevision(television) methode van het televisionWallBracket object wordt aangeroepen met het television object als argument om de koppeling tussen beide objecten te maken.
        televisionWallBracket.setTelevision(television);
        //De setWallBracket(wallBracket) methode van het televisionWallBracket object wordt aangeroepen met het wallBracket object als argument om de koppeling tussen beide objecten te maken.
        televisionWallBracket.setWallBracket(wallBracket);
        //Een nieuw TelevisionWallBracketKey object wordt gecreëerd met de opgegeven televisionId en wallBracketId en toegewezen aan de variabele id.
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        //De setId(id) methode van het televisionWallBracket object wordt aangeroepen met het id object als argument om de unieke sleutel van de koppeling in te stellen.
        televisionWallBracket.setId(id);
        //De save(televisionWallBracket) methode van het televisionWallBracketRepository object wordt aangeroepen met het televisionWallBracket object als argument om de koppeling op te slaan.
        televisionWallBracketRepository.save(televisionWallBracket);
        //Het id object wordt geretourneerd.
        return id;
    }
}
