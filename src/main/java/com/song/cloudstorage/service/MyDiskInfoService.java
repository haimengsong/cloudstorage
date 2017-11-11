package com.song.cloudstorage.service;

import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.model.MyDiskInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDiskInfoService {

    @Autowired
    private MyDiskInfoDao myDiskInfoDao;

    public MyDiskInfo load(int id) {
        return myDiskInfoDao.load(id);
    }
}
