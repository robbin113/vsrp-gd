package com.zxit.tools;

import com.zxit.model.FileBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class UtilCopy {
    public int CopyFiles(FileBean fileBean) {
        int flag = 0;

        File s = new File(fileBean.getSrcPath());
        if (!s.exists()) {
            System.out.println("文件在物理磁盘上不存在！");
            return flag;
        }

        File newFile = new File(fileBean.getTarPath());

        flag = fileChannelCopy(s, newFile);
        return flag;
    }

    public int fileChannelCopy(File srcFile, File newFile) {
        int flag = 1;
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(srcFile);
            fo = new FileOutputStream(newFile);

            in = fi.getChannel();
            out = fo.getChannel();

            if (in.transferTo(0L, in.size(), out) <= 0L)
                flag = 0;
        } catch (IOException e) {
            e.printStackTrace();
            flag = 0;
            try {
                if (fi != null)
                    fi.close();
                if (in != null)
                    in.close();
                if (fo != null)
                    fo.close();
                if (out != null)
                    out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}