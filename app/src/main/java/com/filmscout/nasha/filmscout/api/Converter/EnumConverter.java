package com.filmscout.nasha.filmscout.api.Converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Converter;
import retrofit2.Retrofit;

public class EnumConverter extends Converter.Factory{

    @Override
    public Converter<?, String> stringConverter(Type type,
                                                Annotation[] annotations, Retrofit retrofit){
        Converter<?, String> stringConverter = null;
        if(type instanceof Class && ((Class<?>)type).isEnum()){
            stringConverter = (Converter)(value) -> {
                return value.toString();
            };
        }
        return stringConverter;
    }
}
