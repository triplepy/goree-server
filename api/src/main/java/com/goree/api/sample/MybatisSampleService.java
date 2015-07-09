package com.goree.api.sample;

import org.springframework.stereotype.Service;

@Service
public class MybatisSampleService {

    public FooBar selectFooBar() {
        return new FooBar();
    }
    
}
