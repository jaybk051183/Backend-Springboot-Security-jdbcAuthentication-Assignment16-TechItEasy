package com.example.les13relationstechiteasy.repository;

import com.example.les13relationstechiteasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Deze methode is een afgeleide querymethode die door Spring Data JPA automatisch wordt geïmplementeerd op basis van de naamgevingsconventie. Het doel van deze methode is om een lijst met Television-objecten op te halen waarvan het merk (brand) overeenkomt met de opgegeven merkparameter, ongeacht de hoofdletters/kleine letters.
public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);
}
