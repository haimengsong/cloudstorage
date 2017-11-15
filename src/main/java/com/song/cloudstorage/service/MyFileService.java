package com.song.cloudstorage.service;


import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.model.MyFile;
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
}
