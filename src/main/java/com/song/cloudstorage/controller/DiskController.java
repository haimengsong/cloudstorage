package com.song.cloudstorage.controller;


import com.song.cloudstorage.service.MyDiskInfoService;
import com.song.cloudstorage.service.MyFileService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class DiskController{
    
    @Autowired
    private MyFileService myFileService;

    
    @Autowired
    private MyDiskInfoService myDiskInfoService;
    
    /**
     * list all files in the corresponding directory
     */
    @RequestMapping(value="/list_myfile", method=RequestMethod.POST)
    @ResponseBody
    public String listFiles(int id, String pwd) {
    	return myFileService.listFiles(id, pwd);
    }
    
    
    /**
     * upload files
     * @param request
     * @param folderid
     * @return
     */
    @RequestMapping("/upload/{folderid}")
    @ResponseBody
    public String upload(HttpServletRequest request, @PathVariable int folderid) throws Exception{
    	return myFileService.upload(request, folderid);
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
