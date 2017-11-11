package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.MyDiskInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class MyDiskInfoDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.MyDiskInfoMapper.";

    @Autowired
    private  SqlSessionFactory sqlSessionFactory;


    public MyDiskInfo load(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "loadDiskInfo";
        MyDiskInfo diskInfo = session.selectOne(statement, id);
        session.close();
        return diskInfo;
    }

    public int insert(MyDiskInfo myDiskInfo) {
        SqlSession session = sqlSessionFactory.openSession();
        int id = session.insert(BASEPATHOFMAPPER + "insertDiskInfo",myDiskInfo);
        session.close();
        return id;
    }
}
