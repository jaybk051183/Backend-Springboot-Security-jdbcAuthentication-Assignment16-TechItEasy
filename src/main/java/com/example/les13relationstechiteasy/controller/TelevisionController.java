package com.example.les13relationstechiteasy.controller;

import com.example.les13relationstechiteasy.dto.IdInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("televisions")
public class TelevisionController<televisionId, remoteControllerId> {

    public TelevisionController() {
    }

    @PutMapping("/{id}/remotecontroller")
    public ResponseEntity<Object>assignRemoteControllerToTelevision(@PathVariable String id, @RequestBody remoteControllerId IdInputDto){

        return null;
    }

    @GetMapping
    public ResponseEntity<Object>getAllTelevisions(){

    }

}
