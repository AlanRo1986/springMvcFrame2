package com.mymvc.service.impl;

import com.mymvc.model.ConditionModel;
import com.mymvc.model.TestModel;
import com.mymvc.service.resource.ITestService;
import com.mymvc.system.basic.BasicServiceImpl;
import com.mymvc.system.basic.IComponent;
import com.mymvc.system.exception.IllegalComponentException;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alan.luo on 2017/9/18.
 */
@Transactional
@Service
public class TestServiceImpl extends BasicServiceImpl implements ITestService,IComponent<TestModel> {

    private TestModel model = new TestModel();

    public TestServiceImpl() {
        super();
    }

    @Override
    public String getName() throws IllegalServiceException {
        logger("TestServiceImpl>>");
        return "TestServiceImpl->name:"+model.getName();
    }

    @Override
    public void setName(String name) {
        this.model.setName(name);
    }

    @Override
    public TestModel getOne(ConditionModel model) throws IllegalComponentException {
        return null;
    }

    @Override
    public TestModel getRow(ConditionModel model) throws IllegalComponentException {

        return null;
    }

    @Override
    public List<TestModel> getAll(ConditionModel model) throws IllegalComponentException {
        return null;
    }

    @Override
    public Integer count(ConditionModel model) throws IllegalComponentException {
        return null;
    }

    @Override
    public Integer insert(TestModel model) throws IllegalComponentException {
        return null;
    }

    @Override
    public Integer update(TestModel model) throws IllegalComponentException {
        return null;
    }

    @Override
    public Integer delete(ConditionModel model) throws IllegalComponentException {
        return null;
    }
}
