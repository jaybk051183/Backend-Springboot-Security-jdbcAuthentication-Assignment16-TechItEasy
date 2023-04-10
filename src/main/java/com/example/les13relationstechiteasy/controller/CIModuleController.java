package com.example.les13relationstechiteasy.controller;

import com.example.les13relationstechiteasy.dto.CIModuleDto;
import com.example.les13relationstechiteasy.service.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Dit is de Controller klasse van de CIModule entiteit en heeft vergelijkbare methodes als de TelevisionController
@RestController
@RequestMapping("cimodules")
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService){
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> dtos = ciModuleService.getAllCIModules();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable("id") Long id) {
        CIModuleDto ciModuleDto = ciModuleService.getCIModule(id);
        return ResponseEntity.ok(ciModuleDto);
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleDto dto) {
        CIModuleDto ciModuleDto = ciModuleService.addCIModule(dto);
        return ResponseEntity.created(null).body(ciModuleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") Long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable("id") Long id, @RequestBody CIModuleDto ciModuleDto) {
        ciModuleService.updateCIModule(id, ciModuleDto);
        return ResponseEntity.ok(ciModuleDto);
    }

}
