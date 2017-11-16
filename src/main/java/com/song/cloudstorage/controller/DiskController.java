package com.song.cloudstorage.controller;


import com.alibaba.fastjson.JSON;
import com.song.cloudstorage.model.MyFile;
import com.song.cloudstorage.service.MyFileService;
import com.song.cloudstorage.util.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home")
public class DiskController extends Support{


    private static final String FILEBASEPATH = FileStorage.getFilePath();

    @Autowired
    private MyFileService myFileService;

    /**
     * list all files in the corresponding directory
     */
    @RequestMapping(value="/list_myfile", method=RequestMethod.POST)
    @ResponseBody
    public String listFiles(int id, String pwd) {
    	System.out.println(id);
    	System.out.println(pwd);
        MyFile folder = myFileService.getMyFile(id);
        List<MyFile> myFiles = null;

        if(folder.getIsLock() == 1) {
            if(folder.getPassword().equals(pwd)) {
                myFiles = myFileService.getFilesByFolderId(id);
            }else {
                return "fail";
            }
        }else {
            myFiles = myFileService.getFilesByFolderId(id);
        }

        return JSON.toJSONString(myFiles);
    }
    
    
    /**
     * visit home page with path '/disk'
     * @return
     */
    @RequestMapping("/disk")
    public String index(){
    	return "disk";
    }
    
    /**
     * visit home page with path '/'
     */
    @RequestMapping("/")
    public String index1(){
 
    	return "redirect:/home/disk";
    }
    
    /**
     * visit home page with path ''
     */
    @RequestMapping("")
    public String index2(){
    	return "redirect:/home/disk";
    }
}
