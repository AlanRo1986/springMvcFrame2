package com.mymvc.system.callback;

import com.mymvc.model.UploadModel;

/**
 * Created by alan.luo on 2017/9/21.
 */
public interface IFilePutCallback {

    /**
     * save success callback
     * @param code
     * @param info
     * @param model
     */
    void success(int code,String info,UploadModel model);

    /**
     * error callback
     * @param code
     * @param info
     */
    void error(int code,String info);

}
