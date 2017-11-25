package com.song.cloudstorage.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
	@Autowired
    protected HttpSession session;
}
