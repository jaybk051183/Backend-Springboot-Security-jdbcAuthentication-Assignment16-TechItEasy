package com.example.les13relationstechiteasy.controller;

import com.example.les13relationstechiteasy.dto.RemoteControllerDto;
import com.example.les13relationstechiteasy.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Dit is de Controller klasse van de RemoteController entiteit en heeft vergelijkbare methodes als de TelevisionController.
@RestController
@RequestMapping("remotecontrollers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemotecontrollers() {
        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllers();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemotecontroller(@PathVariable("id") Long id) {
        RemoteControllerDto dto = remoteControllerService.getRemoteController(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@RequestBody RemoteControllerDto dto) {
        RemoteControllerDto dto1 = remoteControllerService.addRemoteController(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateTelevision(@PathVariable("id") Long id, @RequestBody RemoteControllerDto dto) {
        remoteControllerService.updateRemoteController(id, dto);
        return ResponseEntity.ok(dto);
    }

}

