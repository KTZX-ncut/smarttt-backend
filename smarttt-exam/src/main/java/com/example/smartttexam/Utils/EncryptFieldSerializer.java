package com.example.smartttexam.Utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 加密 序列化
 */
public class EncryptFieldSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) {
        // 在这里对字段进行加密操作
        String encryptedValue = EncryptionUtil.encrypt(value);
        try {
            gen.writeString(encryptedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
