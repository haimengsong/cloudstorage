package com.song.cloudstorage.model;


public class MyDiskInfo {
    private int id;
    private int user_id;
    private long totalSize;
    private long usedSize;
    private int fileNumber;
    private int shareNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(long usedSize) {
        this.usedSize = usedSize;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public int getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(int shareNumber) {
        this.shareNumber = shareNumber;
    }
    
   
}
