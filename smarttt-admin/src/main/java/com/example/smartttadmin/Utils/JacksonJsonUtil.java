package com.example.smartttadmin.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JavaType;
import java.util.List;

public class JacksonJsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将List<T>转换为JSON数组字符串
     * @param list 要转换的对象列表
     * @param <T> 对象类型
     * @return JSON数组字符串
     * @throws JsonProcessingException
     */
    public static <T> String listToJsonArray(List<T> list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }

    /**
     * 将JSON数组字符串转换为List<T>
     * @param jsonArray JSON数组字符串
     * @param clazz 目标对象类型
     * @param <T> 对象类型
     * @return 对象列表
     * @throws JsonProcessingException
     */
    public static <T> List<T> jsonArrayToList(String jsonArray, Class<T> clazz) throws JsonProcessingException {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        return objectMapper.readValue(jsonArray, javaType);
    }

    /**
     * 将JSON数组字符串转换为List<T> (支持复杂泛型)
     * @param jsonArray JSON数组字符串
     * @param typeReference 类型引用，用于复杂泛型
     * @param <T> 对象类型
     * @return 对象列表
     * @throws JsonProcessingException
     */
    public static <T> List<T> jsonArrayToList(String jsonArray, TypeReference<List<T>> typeReference)
            throws JsonProcessingException {
        return objectMapper.readValue(jsonArray, typeReference);
    }
}