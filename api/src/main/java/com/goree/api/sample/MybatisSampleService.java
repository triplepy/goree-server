package com.goree.api.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goree.api.mapper.SampleMapper;

@Service
public class MybatisSampleService {
    @Autowired
    private SampleMapper mapper;
    
    public FooBar findAllFooBar() {
        return mapper.selectFooBar();
    }
}
