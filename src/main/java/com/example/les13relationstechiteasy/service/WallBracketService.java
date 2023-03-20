package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }


}
