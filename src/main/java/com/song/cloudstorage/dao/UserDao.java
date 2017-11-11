package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class UserDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.MyDiskInfoMapper.";

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    public User load(User user) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "loadUser";
        User u = session.selectOne(statement, user);
        session.close();
        return u;
    }

    public int insert(User user) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "insertUser";
        int uID = session.insert(statement, user);
        session.close();
        return uID;
    }
}
