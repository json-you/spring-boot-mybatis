package com.yoyo.controller;

import com.yoyo.service.DogService;
import com.yoyo.entity.Dog;
import com.yoyo.service.DogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    @Autowired
    DogService dogService;

    @RequestMapping("/likename")
    public List<Dog> likeName(String name) {
        return dogService.likeName(name);
    }

    @RequestMapping("/insert")
    public boolean insert(String name) {
        return dogService.insertDog(name);
    }
}
