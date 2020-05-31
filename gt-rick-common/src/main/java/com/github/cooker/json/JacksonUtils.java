package com.github.cooker.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * ZoomGrant 2020/2/28
 */
@Slf4j
public class JacksonUtils {
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        //格式美化
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //反序列化忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时忽略值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //解决转map数值科学计数法问题
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    }

    public static String toJson(Object obj) {
        return Try.of(()->objectMapper.writeValueAsString(obj)).onFailure(e->{
                    log.error("bean to json error", e);
                }
        ).getOrElse((String)null);
    }

    public static <T> T toBean(String json, Class<T> cl){
        return Try.of(()->objectMapper.readValue(json, cl))
                .onFailure(e->{
                    log.error("json to bean error", e);
                })
                .getOrElse((T)null);
    }
}
