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


    public MyDiskInfo getMyDiskInfoById(int id) {

        String statement = BASEPATHOFMAPPER + "getDiskInfo";
        MyDiskInfo diskInfo = session.selectOne(statement, id);
        return diskInfo;
    }

    public void insertMyDiskInfo(MyDiskInfo myDiskInfo) {
        session.insert(BASEPATHOFMAPPER + "insertDiskInfo",myDiskInfo);
    }
    
    public void updateMyDiskInfo(MyDiskInfo myDiskInfo) throws Exception{
    	session.update(BASEPATHOFMAPPER + "updateDiskInfo", myDiskInfo);
    }
}
