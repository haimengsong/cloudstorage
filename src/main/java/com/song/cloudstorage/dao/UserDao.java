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


    public User getUserByEmailAndPassword(User user) {
        String statement = BASEPATHOFMAPPER + "getUser";
        User u = session.selectOne(statement, user);
        return u;
    }

    public int insertUser(User user) {
        String statement = BASEPATHOFMAPPER + "insertUser";
        int uID = session.insert(statement, user);
        return uID;
    }

	public int countUsersByEmail(String email) {
		// TODO Auto-generated method stub
        String statement = BASEPATHOFMAPPER + "countUsersByEmail";
        int numOfUser = session.selectOne(statement, email);
        return numOfUser;
	}
	
	public int countUsersByUsername(String username) {
        String statement = BASEPATHOFMAPPER + "countUsersByUsername";
        int numOfUser = session.insert(statement, username);
        return numOfUser;
	}
}
