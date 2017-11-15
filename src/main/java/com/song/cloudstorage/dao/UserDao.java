package com.song.cloudstorage.dao;


import com.song.cloudstorage.model.User;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private static final String BASEPATHOFMAPPER = "com.song.cloudstorage.mapper.UserMapper.";

    @Autowired
    private SqlSessionTemplate session;


    public User load(User user) {
        String statement = BASEPATHOFMAPPER + "loadUser";
        User u = session.selectOne(statement, user);
        return u;
    }

    public int insert(User user) {
        String statement = BASEPATHOFMAPPER + "insertUser";
        int uID = session.insert(statement, user);
        return uID;
    }

	public boolean userByEmailExists(String email) {
		// TODO Auto-generated method stub
        String statement = BASEPATHOFMAPPER + "selectNumOfUsersByEmail";
        int numOfUser = session.selectOne(statement, email);
        if(numOfUser > 0) return true;
		return false;
	}
	
	public boolean userByUsernameExists(String username) {
        String statement = BASEPATHOFMAPPER + "selectNumOfUsersByUsername";
        int numOfUser = session.insert(statement, username);
        if(numOfUser > 0) return true;
		return false;
	}
}
