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
public class LoginController extends Support{

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
        User u = userService.load(user);
        if(u != null) {
            session.setAttribute("user", u);
            session.setAttribute("diskInfo", myDiskInfoService.load(u.getId()));
            session.setAttribute("homeId", myFileService.getHomeId(u.getId()));
            return "redirect:/home/disk";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/login_confirm", method=RequestMethod.POST)
    @ResponseBody
    public String loginConfirm(User user) {
    	User u = userService.load(user);
    	if(u == null) return "0";
    	session.setAttribute("user", u);
    	return "1";
    }
}
