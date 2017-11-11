package com.song.cloudstorage.controller;


import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.dao.UserDao;
import com.song.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends Support{

    @Autowired
    private UserDao userDao;
    @Autowired
    private MyFileDao myFileDao;
    @Autowired
    private MyDiskInfoDao myDiskInfoDao;
    /**
     * user login
     * @param user
     * @return
     */
    @RequestMapping("/welcome")
    public String login(User user) {
        User u = userDao.load(user);
        if(u != null) {
            session.setAttribute("user", u);
            session.setAttribute("diskInfo", myDiskInfoDao.load(u.getId()));
            session.setAttribute("homeId", myFileDao.getHomeId(u.getId()));
            return "redirect:/home/disk";
        }
        return "redirect:/";
    }

}
