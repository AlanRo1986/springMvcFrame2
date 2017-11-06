package com.mymvc.system.provider;

import com.mymvc.system.basic.BasicProvider;
import com.mymvc.system.provider.basic.IStoreFile;
import com.mymvc.system.provider.handler.FileHandler;
import com.mymvc.system.annotation.Provider;

/**
 * Created by alan.luo on 2017/8/4.
 *
 *  fileProvider.save("/tmp/123.log",texts....);
 *  fileProvider.get("/tmp/123.log")
 *
 */
@Provider
public class FileProvider extends BasicProvider implements IStoreFile {

    protected FileHandler fileHandler;

    public FileProvider(){
        super();
        fileHandler = new FileHandler();
    }

    public static FileProvider getInstance(){
        return new FileProvider();
    }

    @Override
    public String get(String path) {
        return fileHandler.get(path);
    }

    @Override
    public void put(String path, String content) {
        fileHandler.put(path,content);
    }

    @Override
    public void save(String path, String content) {
        fileHandler.save(path,content);
    }

    @Override
    public boolean remove(String path) {
        return fileHandler.remove(path);
    }

    @Override
    public boolean exist(String path) {
        return fileHandler.exist(path);
    }

}
