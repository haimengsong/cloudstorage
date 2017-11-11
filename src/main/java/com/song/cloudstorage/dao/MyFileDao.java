package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.MyFile;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyFileDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.MyFileMapper.";

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public MyFile getMyFile(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "getMyFileByID";
        MyFile file = session.selectOne(statement, id);
        session.close();
        return file;
    }

    public List<MyFile> getFilesByFolderId(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "getMyFilesByFolderID";
        List<MyFile> files = session.selectList(statement, id);
        session.close();
        return files;
    }

    public int getHomeId(int userId) {
        SqlSession session = sqlSessionFactory.openSession();
        String statement = BASEPATHOFMAPPER + "getHomeID";
        int id  = session.selectOne(statement, userId);
        session.close();
        return id;
    }

    public void insert(MyFile myFile) {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert(BASEPATHOFMAPPER + "insertMyFile",myFile);
        session.close();
    }
}
