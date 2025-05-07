package com.dr7.dr7.infra.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Convertejson {
    public <T> T converter(String dataTransfor,Class<T> className){
        Gson gson = new Gson();
        T data = gson.fromJson(dataTransfor,className);
        return  data;
    }
}
