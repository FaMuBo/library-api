package com.library.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ModelUtil {
    private ModelMapper modelMapper;

    @PostConstruct
    public void init(){
        modelMapper = new ModelMapper();
    }

    public  <S, T> T map(S src, Class<T> dest){
        return modelMapper.map(src, dest);
    }

    public <S, T> List<T> map(List<S> src, Class<T> dest){
        return src.stream().map(e -> map(e, dest)).toList();
    }
}
