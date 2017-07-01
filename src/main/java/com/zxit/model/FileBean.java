package com.zxit.model;

/**
 * 拷贝文件用
 * 如果文件是放在远程服务器
 * 而且在不允许或者没有ftp等文件服务的时候
 * 把远程服务器的文件拷贝到应用下输出
 *
 * @author Administrator
 */
public class FileBean {

    private String srcPath;
    private String tarPath;

    public FileBean() {

    }

    public FileBean(String srcPath, String tarPath) {
        this.srcPath = srcPath;
        this.tarPath = tarPath;
    }


    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getTarPath() {
        return tarPath;
    }

    public void setTarPath(String tarPath) {
        this.tarPath = tarPath;
    }

}