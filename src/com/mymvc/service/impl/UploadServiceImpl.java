package com.mymvc.service.impl;

import com.mymvc.constant.Constant;
import com.mymvc.model.UploadModel;
import com.mymvc.service.resource.IUploadService;
import com.mymvc.system.basic.CompactService;
import com.mymvc.system.callback.IFilePutCallback;
import com.mymvc.system.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by alan.luo on 2017/9/21.
 */
@Service
public class UploadServiceImpl extends CompactService implements IUploadService {

    private int maxSize = 10240;

    public UploadServiceImpl() {
        super(UploadServiceImpl.class);
    }

    @Override
    public UploadModel doSave(MultipartFile file, IFilePutCallback callBack) {

        if (!this.isLegalFile(file.getOriginalFilename())) {
            callBack.error(0,"文件格式不正确");return null;
        }
        if (file.getSize() > (this.maxSize * 1024)){
            callBack.error(0,"文件尺寸过大");return null;
        }

        //如果目录不存在，则创建
        String path = getDirectoryPath() + getFileName() + getExt(file.getOriginalFilename(),null);


        UploadModel model = new UploadModel();

        model.setContentType(file.getContentType());
        model.setOriginalFilename(file.getOriginalFilename());
        model.setName(file.getName());
        model.setSize(file.getSize());
        model.setUrl(path.replace(Constant.uploadDirectory,"/"));

        try {
            File f = new File(path);
            file.transferTo(f);
            model.setAbsolutePath(f.getAbsolutePath().replace("\\","/"));

            callBack.success(1,"保存成功!",model);
        } catch (IOException e) {
            e.printStackTrace();
            callBack.error(0,e.getMessage());
        }

        return model;
    }

    @Override
    public UploadModel doSave(String base64File, IFilePutCallback callBack) {

        String[] arr = base64File.split(",");
        if (arr.length != 2){
            callBack.error(0,"文件信息错误");
            return null;
        }

        if (base64File.length() > (this.maxSize * 1024)){
            callBack.error(0,"文件尺寸过大");return null;
        }

        String fileType = arr[0].substring(arr[0].indexOf(":")+1,arr[0].indexOf(";"));
        String fileName = getFileName() + getExt(null,fileType);

        if (!isLegalFile(fileName)){
            callBack.error(0,"文件格式不正确");
            return null;
        }

        UploadModel model = new UploadModel();
        try {

            BASE64Decoder decoder = new BASE64Decoder();

            String path = getDirectoryPath() + fileName;

            File outFile = new File(path);

            OutputStream ro = new FileOutputStream(outFile);
            //byte[] b = decoder.decodeBuffer(base64File);
            byte[] b = decoder.decodeBuffer(arr[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            ro.write(b);
            ro.flush();
            ro.close();

            model.setContentType(fileType);
            model.setName(fileName);
            model.setSize(Long.valueOf(base64File.length()));
            model.setUrl(path.replace(Constant.uploadDirectory,"/"));
            model.setAbsolutePath(outFile.getAbsolutePath().replace("\\","/"));
            callBack.success(1,"保存成功!",model);

        } catch (Exception e) {
            e.printStackTrace();
            callBack.error(0,e.getMessage());
        }

        return model;

    }

    @Override
    public String getDirectoryPath() {
        String path = Constant.uploadDirectory;

        if (!path.substring(path.length() - 1).equals("/")) {
            path += "/";
        }
        path += "images/" + DateUtil.getFullDateQ(null) + "/";

        return getFolder(path);
    }

    @Override
    public String getFileName() {
        return String.valueOf(DateUtil.getTime());
    }

    @Override
    public String getExt(String fileName, String fileType) {

        String ext = ".jpg";
        if (fileType != null) {
            switch (fileType) {
                case "image/png":
                    ext = ".png";
                    break;
                case "image/bmp":
                    ext = ".bmp";
                    break;
                case "image/gif":
                    ext = ".gif";
                    break;
            }

        } else {
            ext = fileName.substring(fileName.lastIndexOf("."));
        }

        return ext;
    }

    @Override
    public boolean isLegalFile(String fileName) {
        Iterator<String> type = Arrays.asList(Constant.uploadAllowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * if the directory is exists,when created it.
     *
     * @param path
     */
    private String getFolder(String path) {
        String[] paths = path.split("/");
        File file = null;
        String tmp = "";
        for (String str : paths) {
            tmp += str + "/";
            file = new File(tmp);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return path;
    }
}
