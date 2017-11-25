package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.MyFile;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MyFileDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.mapper.MyFileMapper.";

    @Autowired
    private SqlSessionTemplate sqlSession;

	public MyFile getMyFileById(int id) {
        String statement = BASEPATHOFMAPPER + "getMyFileByID";
        return (MyFile)sqlSession.selectOne(statement, id);
    }

    public List<MyFile> getFilesByFolderId(int id) {
    
        String statement = BASEPATHOFMAPPER + "listFilesByFolderId";
        List<MyFile> files = sqlSession.selectList(statement, id);
        return files;
    }
    
    public MyFile getMyFileByName(String name) {
        String statement = BASEPATHOFMAPPER + "getMyFileByName";
        return sqlSession.selectOne(statement, name);
    }

    public void insertMyFile(MyFile myFile) {
        sqlSession.insert(BASEPATHOFMAPPER + "insertMyFile",myFile);
    }
}
