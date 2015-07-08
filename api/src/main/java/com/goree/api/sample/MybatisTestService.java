package com.goree.api.sample;

import org.springframework.stereotype.Service;

@Service
public class MybatisTestService {

    public FooBar selectFooBar() {
        return new FooBar();
    }
    
}
