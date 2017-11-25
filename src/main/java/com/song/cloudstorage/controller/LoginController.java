package com.song.cloudstorage.controller;

import com.song.cloudstorage.model.User;
import com.song.cloudstorage.service.MyDiskInfoService;
import com.song.cloudstorage.service.MyFileService;
import com.song.cloudstorage.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController{

    @Autowired
    private UserService userService;
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private MyDiskInfoService myDiskInfoService;
    /**
     * user login
     * @param user
     * @return
     */
    @RequestMapping("/welcome")
    public String login(User user) {
    	boolean isSuccess = userService.login(user);
        if(isSuccess) {
            return "redirect:/home/disk";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/login_confirm", method=RequestMethod.POST)
    @ResponseBody
    public String loginConfirm(User user) {
    	if(userService.loginConfirm(user)) {
    		return "1";
    	}
    	return "0";
    }
    
    @RequestMapping("/logout")
    public String logout(User user) {
    	userService.logout(user);
    	return "redirect:/";
    }
}
