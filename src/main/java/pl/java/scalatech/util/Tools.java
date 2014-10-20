package pl.java.scalatech.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public final class Tools {
    
    
    private Tools(){
        throw new AssertionError();
    }
    
    public static <T> String ObjectTojson(T t) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper om = new ObjectMapper();
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        om.writeValue(ba, t);
        
        return ba.toString();
        
    }
   
}
