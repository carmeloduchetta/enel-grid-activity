package com.enel.permitting.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;


public class JSONHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONHelper.class);
    private static ObjectMapper objectMapper;
    private static Gson gson;

    public static String toJSON(Map<String, Object> map) throws JsonProcessingException {

        final ObjectMapper om = getObjectMapper();
        return om.writeValueAsString(map);
    }

    public static String toJSONwithPrettyPrint(Object map) throws JsonProcessingException {

        try {
            final ObjectMapper om = getObjectMapper();
            return om.writer() //
                    .withDefaultPrettyPrinter() //
                    .writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonGenerationException("JSON error", e);
        }
    }

    public static String toJSONwithNotNullAndPrettyPrint(Object map) throws JsonProcessingException {

        try {
            final ObjectMapper om = getObjectMapper();
            //            om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return om.writer() //
                    .withDefaultPrettyPrinter() //
                    .without(SerializationFeature.WRITE_NULL_MAP_VALUES) //
                    .writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonGenerationException("JSON error", e);
        }
    }

    public static ObjectMapper getObjectMapper() {

        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            objectMapper.setDateFormat(sdf);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static Map<String, Object> fromJSON(final String json) throws IOException {

        return getObjectMapper().readValue(json, Map.class);
    }

    public static Map<String, String> fromJSONasStringMap(final String json) throws IOException {

        return getObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {
        });
    }

    public static <T> T fromJSON(final String json, final Class<T> targetClass) throws IOException {

        return getObjectMapper().readValue(json, targetClass);
    }

    public static <T> T convertValue(Object value, Class<T> klass) {

        return getObjectMapper().convertValue(value, klass);
    }
    
    /*public static <T> T combine2Objects(T a, T b) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        // would require a noargs constructor for the class, maybe you have a different way to create the result.
        T result = (T) a.getClass().newInstance();
        BeanUtils.copyProperties(a, result);
        BeanUtils.copyProperties(b, result);
        LOGGER.info(JSONHelper.toJSONwithPrettyPrint(b));
        return result;
    }*/
    
    public static <T> T combine2Objects(T a, T b) throws InstantiationException, IllegalAccessException, InvocationTargetException, JsonProcessingException {
        // would require a noargs constructor for the class, maybe you have a different way to create the result.
        BeanUtils.copyProperties(a, b);   
        return b;
        
    }
    
    public static void nullAwareBeanCopy(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object dest, String name, Object value)
                    throws IllegalAccessException, InvocationTargetException {
                if(value != null) {
                    super.copyProperty(dest, name, value);
                }
            }
        }.copyProperties(dest, source);
    }
}
