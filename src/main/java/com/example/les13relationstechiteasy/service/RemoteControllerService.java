package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }


}
