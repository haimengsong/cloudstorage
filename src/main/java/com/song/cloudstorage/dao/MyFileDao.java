package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.MyFile;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MyFileDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.MyFileMapper.";

    @Autowired
    private SqlSessionTemplate sqlSession;

	public MyFile getMyFile(int id) {
        String statement = BASEPATHOFMAPPER + "getMyFileByID";
        return (MyFile)sqlSession.selectOne(statement, id);
    }

    public List<MyFile> getFilesByFolderId(int id) {
    
        String statement = BASEPATHOFMAPPER + "getMyFilesByFolderID";
        List<MyFile> files = sqlSession.selectList(statement, id);
        return files;
    }

    public int getHomeId(int userId) {
        String statement = BASEPATHOFMAPPER + "getHomeID";
        return sqlSession.selectOne(statement, userId);
    }

    public void insert(MyFile myFile) {
        sqlSession.insert(BASEPATHOFMAPPER + "insertMyFile",myFile);
    }
}
