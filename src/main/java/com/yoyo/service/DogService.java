package com.yoyo.service;

import com.yoyo.entity.Dog;
import com.yoyo.mapper.DogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DogService {


    public List<Dog> likeName(String name);

    public boolean insertDog(String name);
}
