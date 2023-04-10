package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.dto.TelevisionDto;
import com.example.les13relationstechiteasy.dto.TelevisionInputDto;
import com.example.les13relationstechiteasy.exceptions.RecordNotFoundException;
import com.example.les13relationstechiteasy.model.Television;
import com.example.les13relationstechiteasy.repository.CIModuleRepository;
import com.example.les13relationstechiteasy.repository.RemoteControllerRepository;
import com.example.les13relationstechiteasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

//(@Service) markeert de klasse als een Spring-service, wat betekent dat het een component is dat geïnjecteerd kan worden in andere Spring-componenten en geannoteerd is om transacties af te handelen.
@Service
public class TelevisionService {
    //Privé-instanties van verschillende repositories en services die nodig zijn voor het uitvoeren van de functionaliteit van deze serviceklasse.

    private final TelevisionRepository televisionRepository;

    private final RemoteControllerRepository remoteControllerRepository;

    private final RemoteControllerService remoteControllerService;

    private final CIModuleRepository ciModuleRepository;

    private final CIModuleService ciModuleService;

    //Dit is de constructor van de klasse, die vijf argumenten accepteert: televisionRepository, remoteControllerRepository, remoteControllerService, ciModuleRepository en ciModuleService. De constructor initialiseert de instanties van de repositories en services met de argumenten die zijn doorgegeven aan de constructor. De afhankelijkheden van de klasse worden via de constructor geïnjecteerd en worden gebruikt om de functies van de serviceklasse uit te voeren.
    public TelevisionService(TelevisionRepository televisionRepository,
                             RemoteControllerRepository remoteControllerRepository,
                             RemoteControllerService remoteControllerService,
                             CIModuleRepository ciModuleRepository,
                             CIModuleService ciModuleService
    ){
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerService = remoteControllerService;
        this.ciModuleRepository = ciModuleRepository;
        this.ciModuleService = ciModuleService;
    }


//Deze code toont een methode genaamd getAllTelevisions() die een lijst met televisies in DTO-formaat teruggeeft.
    //Dit is de signatuur van de methode, die aangeeft dat deze een lijst met televisies in DTO-formaat zal retourneren en geen argumenten zal accepteren.
    public List<TelevisionDto> getAllTelevisions() {
        //Hier wordt een lijst van alle televisies opgehaald met behulp van de findAll()-methode van de televisionRepository. Deze methode retourneert een lijst van Television objecten.
        List<Television> tvList = televisionRepository.findAll();
        //Hier wordt de lijst met Television objecten omgezet in een lijst van TelevisionDto objecten door de transferTvListToDtoList() methode aan te roepen. De retourwaarde van deze methode is de uiteindelijke lijst van televisies in DTO-formaat.
        return transferTvListToDtoList(tvList);
    }


    //Deze code toont een methode genaamd getAllTelevisionsByBrand(String brand) die een lijst met televisies in DTO-formaat retourneert op basis van een opgegeven merk.
    //Dit is de signatuur van de methode, die aangeeft dat deze een lijst met televisies in DTO-formaat zal retourneren en een argument brand van het type String zal accepteren.
    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        //Hier wordt een lijst van alle televisies opgehaald die behoren tot het opgegeven merk. De findAllTelevisionsByBrandEqualsIgnoreCase() methode wordt aangeroepen op de televisionRepository, die deze functionaliteit implementeert. Het argument brand wordt gebruikt om het merk op te zoeken en het "IgnoreCase" deel zorgt ervoor dat de zoekopdracht niet hoofdlettergevoelig is.
        List<Television> tvList = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        //Hier wordt de lijst met Television objecten omgezet in een lijst van TelevisionDto objecten door de transferTvListToDtoList() methode aan te roepen. De retourwaarde van deze methode is de uiteindelijke lijst van televisies in DTO-formaat.
        return transferTvListToDtoList(tvList);
    }


    //Deze code toont een methode genaamd transferTvListToDtoList(List<Television> televisions) die een lijst met televisies omzet in een lijst met televisies in DTO-formaat.
    //Dit is een typische DTO-mapping methode, waarbij de functionaliteit is geïmplementeerd om de gegevens van een Television object om te zetten in een TelevisionDto object. Het heeft extra logica om ook de eigenschappen van de CI-module en afstandsbediening in de DTO van de televisie te zetten, als deze aanwezig zijn
    //Dit is de signatuur van de methode, die aangeeft dat deze een lijst van Television objecten zal omzetten in een lijst van TelevisionDto objecten en een argument televisions van het type List<Television> zal accepteren.
    public List<TelevisionDto> transferTvListToDtoList(List<Television> televisions){
        //Hier wordt een lege lijst van TelevisionDto objecten aangemaakt.
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        //Dit is een for-loop die over alle televisies in de opgegeven lijst televisions loopt.
        for(Television tv : televisions) {
            //Hier wordt de transferToDto() methode aangeroepen om een DTO-object te maken van de huidige televisie in de for-loop.
            TelevisionDto dto = transferToDto(tv);
            //Als de huidige televisie een CI-module heeft, wordt de transferToDto() methode van de ciModuleService aangeroepen om een DTO-object te maken van de CI-module en dit DTO-object wordt ingesteld in de overeenkomstige eigenschap in de DTO van de televisie.
            if(tv.getCIModules() != null){
                dto.setCiModuleDto(ciModuleService.transferToDto(tv.getCIModules()));
            }
            //Als de huidige televisie een afstandsbediening heeft, wordt de transferToDto() methode van de remoteControllerService aangeroepen om een DTO-object te maken van de afstandsbediening en dit DTO-object wordt ingesteld in de overeenkomstige eigenschap in de DTO van de televisie.
            if(tv.getRemotecontroller() != null){
                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemotecontroller()));
            }
            //Hier wordt de DTO van de huidige televisie toegevoegd aan de lijst van TelevisionDto objecten.
            tvDtoList.add(dto);
        }
        //Hier wordt de lijst van TelevisionDto objecten geretourneerd.
        return tvDtoList;
    }


    //De getTelevisionById methode heeft als doel het ophalen van een televisie-object op basis van het opgegeven ID en het omzetten van dit object naar een TelevisionDto object.
    //Dit is de definitie van een methode genaamd getTelevisionById die een Long parameter id accepteert en een object van het type TelevisionDto retourneert.
    public TelevisionDto getTelevisionById(Long id) {

        //Deze regel controleert of er een Television object is gevonden met het opgegeven id door de findById methode van het televisionRepository aan te roepen. De isPresent() methode wordt gebruikt om te controleren of er een waarde aanwezig is.
        if (televisionRepository.findById(id).isPresent()){
            // Als er een waarde aanwezig is, wordt het Television object opgehaald met behulp van de get() methode en toegewezen aan de variabele tv.
            Television tv = televisionRepository.findById(id).get();
            //De methode transferToDto wordt aangeroepen met het Television object tv als argument om het object om te zetten naar een TelevisionDto object. Dit object wordt toegewezen aan de variabele dto.
            TelevisionDto dto =transferToDto(tv);
            //Controleert of het Television object tv een waarde heeft voor de CIModules eigenschap.
            if(tv.getCIModules() != null){
                //Als de CIModules eigenschap een waarde heeft, wordt de transferToDto methode van de ciModuleService aangeroepen om de CIModules om te zetten naar een CiModuleDto object. Vervolgens wordt de setCiModuleDto methode van het dto object aangeroepen om de omgezette CiModuleDto waarde in te stellen.
                dto.setCiModuleDto(ciModuleService.transferToDto(tv.getCIModules()));
            }
            //Controleert of het Television object tv een waarde heeft voor de Remotecontroller eigenschap.
            if(tv.getRemotecontroller() != null){
                //Als de Remotecontroller eigenschap een waarde heeft, wordt de transferToDto methode van de remoteControllerService aangeroepen om de Remotecontroller om te zetten naar een RemoteControllerDto object. Vervolgens wordt de setRemoteControllerDto methode van het dto object aangeroepen om de omgezette RemoteControllerDto waarde in te stellen.
                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemotecontroller()));
            }
            //Het Television object tv wordt omgezet naar een TelevisionDto object met behulp van de transferToDto methode en het resultaat wordt geretourneerd.
            return transferToDto(tv);
            //ls er geen Television object is gevonden met het opgegeven id, gaat de code naar het else gedeelte.
        } else {
            //Er wordt een nieuwe RecordNotFoundException aangemaakt met het bericht "geen televisie gevonden" en deze wordt gegooid. Dit geeft aan dat er geen televisie is gevonden met het opgegeven id.
            throw new RecordNotFoundException("geen televisie gevonden");
        }
    }

//Dit is de definitie van een methode genaamd addTelevision, die een TelevisionInputDto object dto accepteert en een object van het type TelevisionDto retourneert.
    public TelevisionDto addTelevision(TelevisionInputDto dto) {
//De methode transferToTelevision wordt aangeroepen met het TelevisionInputDto object dto als argument om het object om te zetten naar een Television object. Dit object wordt toegewezen aan de variabele tv.
        Television tv = transferToTelevision(dto);
//Het Television object tv wordt opgeslagen in de televisionRepository.
        televisionRepository.save(tv);
//Het opgeslagen Television object tv wordt omgezet naar een TelevisionDto object met behulp van de transferToDto methode en het resultaat wordt geretourneerd.
        return transferToDto(tv);
    }


    // Dit is de definitie van een methode genaamd deleteTelevision, die een Long parameter id accepteert en niets retourneert (void).
    public void deleteTelevision(@RequestBody Long id) {
//Het Television object met het opgegeven id wordt verwijderd uit de televisionRepository.
        televisionRepository.deleteById(id);
    }

    // Dit is de definitie van een methode genaamd updateTelevision, die een Long parameter id en een TelevisionInputDto object inputDto accepteert, en een object van het type TelevisionDto retourneert.
    public TelevisionDto updateTelevision(Long id, TelevisionInputDto inputDto) {
//Deze regel controleert of er een Television object is gevonden met het opgegeven id door de findById methode van het televisionRepository aan te roepen. De isPresent() methode wordt gebruikt om te controleren of er een waarde aanwezig is.
        if (televisionRepository.findById(id).isPresent()){
// Als er een waarde aanwezig is, wordt het Television object opgehaald met behulp van de get() methode en toegewezen aan de variabele tv.
            Television tv = televisionRepository.findById(id).get();
//De methode transferToTelevision wordt aangeroepen met het TelevisionInputDto object inputDto als argument om het object om te zetten naar een Television object. Dit object wordt toegewezen aan de variabele tv1
            Television tv1 = transferToTelevision(inputDto);
            // De setId methode van het tv1 object wordt aangeroepen om het ID van het bestaande Television object tv in te stellen.
            tv1.setId(tv.getId());
//Het bijgewerkte Television object tv1 wordt opgeslagen in de televisionRepository.
            televisionRepository.save(tv1);
//Het bijgewerkte Television object tv1 wordt omgezet naar een TelevisionDto object met behulp van de transferToDto methode en het resultaat wordt geretourneerd.
            return transferToDto(tv1);
//Als er geen Television object is gevonden met het opgegeven id, gaat de code naar het else gedeelte.
        } else {
//Er wordt een nieuwe RecordNotFoundException aangemaakt met het bericht "geen televisie gevonden" en deze wordt gegooid. Dit geeft aan dat er geen televisie is gevonden met het opgegeven id.
            throw new  RecordNotFoundException("geen televisie gevonden");
        }
    }

    //Deze functie zet de informatie van een TelevisionInputDto-object om naar een Television-object door de waarden van de eigenschappen één voor één over te zetten.
    //De functie transferToTelevision wordt gedefinieerd met een parameter dto van het type TelevisionInputDto.
    public Television transferToTelevision(TelevisionInputDto dto){
        //Er wordt een nieuw Television-object gecreëerd met de naam television
        var television = new Television();
//De verschillende eigenschappen van het dto-object (type, merk, naam, prijs, etc.) worden gekopieerd naar het television-object met behulp van de bijbehorende 'setter'-methoden (bijv. setType, setBrand, setName, etc.).
        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());
//De functie retourneert het television-object met de gekopieerde eigenschappen.
        return television;
    }


    //Deze functie zet de informatie van een Television-object om naar een TelevisionDto-object door de waarden van de eigenschappen één voor één over te zetten, inclusief de gerelateerde CIModules
    //De functie transferToDto wordt gedefinieerd met een parameter television van het type Television.
    public TelevisionDto transferToDto(Television television){
        //Er wordt een nieuw TelevisionDto-object gecreëerd met de naam dto
        TelevisionDto dto = new TelevisionDto();
//De verschillende eigenschappen van het television-object (ID, type, merk, naam, prijs, etc.) worden gekopieerd naar het dto-object met behulp van de bijbehorende 'setter'-methoden (bijv. setId, setType, setBrand, etc.).
        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getWifi());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        // Als extra op deze transfer methode, voegen we ook de relaties toe.
        // Hier moeten we eerst een null check voor doen,
        // omdat we anders in CIModule.transferToDto de get-methodes van "null" aanroepen en dat kan niet.
        //Er wordt een null-check uitgevoerd op de CIModules eigenschap van het television-object om te voorkomen dat een NullPointerException optreedt.
        if(television.getCIModules() != null){
            //Als de CIModules eigenschap niet null is, wordt de CIModuleService.transferToDto-functie aangeroepen om de gerelateerde CIModule-objecten om te zetten naar bijbehorende CiModuleDto-objecten. Vervolgens worden deze aan het dto-object toegevoegd met behulp van de setCiModuleDto-methode.
            dto.setCiModuleDto(CIModuleService.transferToDto(television.getCIModules()));
        }
        //De functie retourneert het dto-object met de gekopieerde eigenschappen en de gerelateerde CiModuleDto-objecten.
        return dto;
    }


    //De methode assignRemoteControllerToTelevision wordt aangeroepen met twee argumenten: id (het ID van de televisie) en remoteControllerId (het ID van de afstandsbediening).
    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        //De methode zoekt de televisie en afstandsbediening met behulp van hun respectievelijke ID's door middel van de findById() methode van televisionRepository en remoteControllerRepository
        //De findById() methode retourneert een Optional object, dat het gevonden object kan bevatten of leeg kan zijn als het object niet is gevonden.
        var optionalTelevision = televisionRepository.findById(id);
        var optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);

        //De code controleert of zowel de televisie als de afstandsbediening zijn gevonden met behulp van de isPresent() methode op de Optional objecten.
        if(optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            //Als zowel de televisie als de afstandsbediening zijn gevonden:
            //a. Haal de daadwerkelijke televisie- en afstandsbedieningsobjecten op met de get() methode op de Optional objecten.
            var television = optionalTelevision.get();
            var remoteController = optionalRemoteController.get();
            //b. Koppel de afstandsbediening aan de televisie met de setRemotecontroller() methode op het televisieobject.
            television.setRemotecontroller(remoteController);
            //c. Sla het bijgewerkte televisieobject op met behulp van de save() methode van televisionRepository.
            televisionRepository.save(television);
        } else {
            //Als de televisie of de afstandsbediening niet is gevonden, wordt er een RecordNotFoundException gegooid om aan te geven dat de opgegeven records niet zijn gevonden.
            throw new RecordNotFoundException();
        }
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if(optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            var television = optionalTelevision.get();
            var ciModule = optionalCIModule.get();

            television.setCIModules(ciModule);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }
}