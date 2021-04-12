package com.yoyo.mapper;

import com.yoyo.entity.Dog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DogMapper {
    @Select("select * from Dog where name like #{name}")
    public List<Dog> likeName(String name);

    @Insert("insert into Dog(name) values(#{name})")
    public boolean insertDog(String name);
    // public Dog getDogById(int id);

    //public String getNameById(int id);
}