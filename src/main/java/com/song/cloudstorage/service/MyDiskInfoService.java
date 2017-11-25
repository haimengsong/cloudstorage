package com.song.cloudstorage.service;

import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.model.MyDiskInfo;
import com.song.cloudstorage.model.MyFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class MyDiskInfoService extends BaseService{

    @Autowired
    private MyDiskInfoDao myDiskInfoDao;

    
    public boolean isEnoughSpace(MyFile myFile) {
    	MyDiskInfo myDiskInfo = myDiskInfoDao.getMyDiskInfoById(myFile.getUser_id());
    	if(myDiskInfo.getUsedSize() + myFile.getSize() < myDiskInfo.getTotalSize()) {
    		return true;
    	}
    	return false;
    }
}
