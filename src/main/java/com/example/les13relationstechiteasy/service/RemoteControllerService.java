package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.dto.RemoteControllerDto;
import com.example.les13relationstechiteasy.exceptions.RecordNotFoundException;
import com.example.les13relationstechiteasy.model.RemoteController;
import com.example.les13relationstechiteasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// De RemoteControllerService klasse is een Spring servicecomponent die verantwoordelijk is voor het uitvoeren van de logica met betrekking tot afstandsbediening objecten. De klasse maakt gebruik van een RemoteControllerRepository om de noodzakelijke databasetaken uit te voeren. De afhankelijkheid wordt geïnjecteerd via de constructor.

    @Service
    public class RemoteControllerService  {
//Hier wordt een instantievariabele van het type RemoteControllerRepository gedefinieerd. RemoteControllerRepository is verantwoordelijk voor de communicatie met de databaselaag voor afstandsbediening objecten.
        private RemoteControllerRepository remoteControllerRepository;
//Dit is de constructor van de RemoteControllerService klasse. Deze constructor neemt een enkel argument van het type RemoteControllerRepository.
        public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
            // In de constructor wordt de meegegeven RemoteControllerRepository toegewezen aan de instantievariabele remoteControllerRepository. Op deze manier wordt de afhankelijkheid van RemoteControllerRepository geïnjecteerd in de RemoteControllerService klasse, waardoor het de mogelijkheid krijgt om databasetaken met betrekking tot afstandsbediening objecten uit te voeren.
            this.remoteControllerRepository = remoteControllerRepository;
        }

    //De getAllRemoteControllers() methode haalt alle afstandsbediening objecten op uit de repository, converteert elk object naar een RemoteControllerDto, en retourneert de geconverteerde DTO's als een lijst.
    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteControllerDto> dtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        for (RemoteController rc : remoteControllers) {
            dtos.add(transferToDto(rc));
        }
        return dtos;
    }

//De getRemoteController(long id) methode haalt een afstandsbediening object op uit de repository op basis van het opgegeven ID, converteert het naar een RemoteControllerDto en retourneert het. Als het object niet wordt gevonden, wordt er een uitzondering gegooid.
    public RemoteControllerDto getRemoteController(long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);
        if(remoteController.isPresent()) {
            return transferToDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remotecontroller found");
        }
    }


    //De addRemoteController(RemoteControllerDto remoteControllerDto) methode converteert een RemoteControllerDto naar een RemoteController object, voegt het object toe aan de repository en retourneert het oorspronkelijke DTO
    public RemoteControllerDto addRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController rc =  transferToRemoteController(remoteControllerDto);
        remoteControllerRepository.save(rc);
        return remoteControllerDto;
    }


    public void deleteRemoteController(Long id) {
        remoteControllerRepository.deleteById(id);
    }

//De updateRemoteController(Long id, RemoteControllerDto remoteControllerDto) methode controleert of het afstandsbediening object met het opgegeven ID bestaat, werkt de velden bij met de waarden van het bijgewerkte DTO en slaat de wijzigingen op in de repository. Als het object niet wordt gevonden, wordt er een uitzondering gegooid.
    public void updateRemoteController(Long id, RemoteControllerDto remoteControllerDto) {
        if(!remoteControllerRepository.existsById(id)) {
            throw new RecordNotFoundException("No remotecontroller found");
        }
        RemoteController storedRemoteController = remoteControllerRepository.findById(id).orElse(null);
        storedRemoteController.setId(remoteControllerDto.getId());
        storedRemoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        storedRemoteController.setBatteryType(remoteControllerDto.getBatteryType());
        storedRemoteController.setName(remoteControllerDto.getName());
        storedRemoteController.setPrice(remoteControllerDto.getPrice());
        storedRemoteController.setBrand(remoteControllerDto.getBrand());
        storedRemoteController.setOriginalStock(remoteControllerDto.getOriginalStock());
        remoteControllerRepository.save(storedRemoteController);
    }


//De transferToDto(RemoteController remoteController) methode converteert een RemoteController object naar een RemoteControllerDto object door de velden van het DTO-object in te stellen op basis van de waarden van de velden van het RemoteController object.
    public RemoteControllerDto transferToDto(RemoteController remoteController){
        var dto = new RemoteControllerDto();

        dto.id = remoteController.getId();
        dto.compatibleWith = remoteController.getCompatibleWith();
        dto.batteryType = remoteController.getBatteryType();
        dto.name = remoteController.getName();
        dto.brand = remoteController.getBrand();
        dto.price = remoteController.getPrice();
        dto.originalStock = remoteController.getOriginalStock();

        return dto;
    }


    //De transferToRemoteController(RemoteControllerDto dto) methode converteert een RemoteControllerDto object naar een RemoteController object door de velden van het RemoteController object in te stellen op basis van de waarden van de velden van het RemoteControllerDto object.
    public RemoteController transferToRemoteController(RemoteControllerDto dto){
        var remoteController = new RemoteController();

        remoteController.setId(dto.getId());
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }

}
