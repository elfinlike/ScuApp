package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Bulletin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BulletinMapper {
    void add(Bulletin bulletin);
}
