package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.MyDiskInfo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyDiskInfoDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.mapper.MyDiskInfoMapper.";

    @Autowired
    private  SqlSessionTemplate session;


    public MyDiskInfo load(int id) {

        String statement = BASEPATHOFMAPPER + "loadDiskInfo";
        MyDiskInfo diskInfo = session.selectOne(statement, id);
        return diskInfo;
    }

    public int insert(MyDiskInfo myDiskInfo) {
        int id = session.insert(BASEPATHOFMAPPER + "insertDiskInfo",myDiskInfo);
        return id;
    }
}
