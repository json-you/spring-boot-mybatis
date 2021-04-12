package com.yoyo.service;

import com.yoyo.entity.Dog;
import com.yoyo.mapper.DogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DogServiceImpl implements DogService {
    @Autowired
    private DogMapper dogMapper;

    @Override
    public List<Dog> likeName(String name) {
        return dogMapper.likeName("%" + name + "%");
    }

    @Override
    public boolean insertDog(String name) {
        return dogMapper.insertDog(name);
    }
}
