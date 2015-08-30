package com.goree.api.sample;

import com.goree.api.mapper.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MybatisSampleService {
    @Autowired
    private SampleMapper mapper;
    
    public FooBar findAllFooBar() {
        return mapper.selectFooBar();
    }
}
