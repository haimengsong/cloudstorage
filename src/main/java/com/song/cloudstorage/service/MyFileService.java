package com.song.cloudstorage.service;


import com.alibaba.fastjson.JSON;
import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.model.MyDiskInfo;
import com.song.cloudstorage.model.MyFile;
import com.song.cloudstorage.model.User;
import com.song.cloudstorage.util.FileStorage;
import com.song.cloudstorage.util.UploadHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class MyFileService extends BaseService{
	
	private static final String FILEBASEPATH = FileStorage.getFilePath();

    @Autowired
    private MyFileDao myFileDao;

    @Autowired
    private MyDiskInfoDao myDiskInfoDao;
    
	private String getPath(int folderid) {
		// TODO Auto-generated method stub
		return "";
	}
	
	@Transactional(readOnly = false)
    public void saveMyFile(MyFile myFile) {
		myFileDao.insertMyFile(myFile);
		MyDiskInfo myDiskInfo = myDiskInfoDao.getMyDiskInfoById(myFile.getUser_id());
		myDiskInfo.setUsedSize(myDiskInfo.getUsedSize() + myFile.getSize());
		myDiskInfo.setFileNumber(myDiskInfo.getFileNumber() + 1);
		myDiskInfoDao.updateMyDiskInfo(myDiskInfo);
	}
	
	
	/**
     * upload files
     * @param request
     * @param folderid
     * @return
     */
	public String upload(HttpServletRequest request, int folderid) {
		// TODO Auto-generated method stub
		UploadHelper uploadHelper = new UploadHelper();
    	MultipartFile file = uploadHelper.getFiles(request).get(0);
    	
    	String result = "fail";
    	String fileName = file.getOriginalFilename();
    	String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
    	
    	User user = (User) session.getAttribute("user");
    	
    	MyFile myFile = new MyFile();
    	myFile.setUser_id(user.getId());
    	myFile.setSize(file.getSize());
    	
    	if(isEnoughSpace(myFile)) {
    		String filePath = request.getServletContext().getRealPath(FILEBASEPATH) + new Date().getTime() + "." + suffix;
    		System.out.println(filePath);
    		try {
    			uploadHelper.upload(file,filePath);
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			
    			myFile.setCreateDate(sdf.format(new Date()));
    			myFile.setName(fileName);
    			myFile.setParent_id(folderid);
    			myFile.setType(suffix.toLowerCase());
    			myFile.setPath(getPath(folderid) + folderid + "/");
    			myFile.setLocation(filePath);
    			myFile.setIsShare(0);
    			myFile.setDescription("");
    			
    			saveMyFile(myFile);
    			
    			MyDiskInfo diskInfo = myDiskInfoDao.getMyDiskInfoById(user.getId());
    			session.setAttribute("diskInfo", diskInfo);
    			
    			Map<String,Object> data = new HashMap<String, Object>();
    			data.put("file", myFile);
    			data.put("usedSize", diskInfo.getUsedSize());
    			
    			result = JSON.toJSONString(data);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    	}
    	return result;
	}
	/**
	 * check if there is enough space
	 * @param myFile
	 * @return
	 */
	private boolean isEnoughSpace(MyFile myFile) {
		// TODO Auto-generated method stub
		MyDiskInfo myDiskInfo = myDiskInfoDao.getMyDiskInfoById(myFile.getUser_id());
		if(myDiskInfo.getUsedSize() + myFile.getSize() < myDiskInfo.getTotalSize()) {
			return true;
		}
		return false;
	}

	public String listFiles(int id, String pwd) {
		// TODO Auto-generated method stub
		MyFile folder = myFileDao.getMyFileById(id);
        List<MyFile> myFiles = null;

        if(folder.getIsLock() == 1) {
            if(folder.getPassword().equals(pwd)) {
                myFiles = myFileDao.getFilesByFolderId(id);
            }else {
                return "fail";
            }
        }else {
            myFiles = myFileDao.getFilesByFolderId(id);
        }

        return JSON.toJSONString(myFiles);
	}
}
