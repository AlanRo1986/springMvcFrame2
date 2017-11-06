package com.mymvc.service.resource;

import com.mymvc.model.UploadModel;
import com.mymvc.system.callback.IFilePutCallback;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by alan.luo on 2017/9/21.
 */
public interface IUploadService {

    UploadModel doSave(MultipartFile file, IFilePutCallback callBack);

    UploadModel doSave(String base64File, IFilePutCallback callBack);

    String getDirectoryPath();

    String getFileName();

    String getExt(String fileName, String fileType);

    boolean isLegalFile(String fileName);

}
