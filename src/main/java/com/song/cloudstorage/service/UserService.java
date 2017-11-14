package com.song.cloudstorage.service;


import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.dao.UserDao;
import com.song.cloudstorage.model.MyDiskInfo;
import com.song.cloudstorage.model.MyFile;
import com.song.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class UserService {

    private static final long DEFAULT_TOTAL_SIZE = 1024 * 1024 * 5;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MyDiskInfoDao myDiskInfoDao;

    @Autowired
    private MyFileDao myFileDao;

    @Transactional(readOnly = false)
    public void save(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd(E),yyyy");
        user.setJoindate(sdf.format(new Date()));
        int uID = userDao.insert(user);
        MyDiskInfo myDiskInfo = new MyDiskInfo();
        myDiskInfo.setUser_id(uID);
        myDiskInfo.setTotalSize(DEFAULT_TOTAL_SIZE);;
        int homeID = myDiskInfoDao.insert(myDiskInfo);
        MyFile myFile = new MyFile();
        myFile.setUser_id(uID);
        myFile.setParent_id(homeID);
        myFile.setPath("/"+homeID+"/");
        myFile.setType("adir");
        String [] folders = {"Documents", "Music", "Photos", "Books"};
        for(int i = 0; i < 4; i++) {
            myFile.setName(folders[i]);
            myFileDao.insert(myFile);
        }
        user.setId(uID);
        user.setPortrait("portrait");
    }

	public String confirmEmail(String email) {
		// TODO Auto-generated method stub
		if(userDao.UserByEmailExists(email)){
			return "0";
		}
		return "1";
	}

	public String confirmUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
