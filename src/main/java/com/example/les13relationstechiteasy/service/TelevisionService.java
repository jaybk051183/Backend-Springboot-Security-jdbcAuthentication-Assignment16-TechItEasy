package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.repository.RemoteControllerRepository;
import com.example.les13relationstechiteasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

@Service
public class TelevisionService {

    private final TelevisionRepository repos;
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;

    public TelevisionService(TelevisionRepository repos, TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
        this.repos = repos;
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }

}
