package com.song.cloudstorage.service;


import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.model.MyFile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyFileService {

    @Autowired
    private MyFileDao myFileDao;

    public int getHomeId(int id) {
        return myFileDao.getHomeId(id);
    }
    
    public MyFile getMyFile(int id) {
    	return myFileDao.getMyFile(id);
    }

	public List<MyFile> getFilesByFolderId(int id) {
		// TODO Auto-generated method stub
		return myFileDao.getFilesByFolderId(id);
	}
}
