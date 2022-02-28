package com.egrine.mailSpammer.utilities;

import lombok.SneakyThrows;
import org.json.JSONObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class JsonToStringConverter implements AttributeConverter<JSONObject, String> {
    @Override
    public String convertToDatabaseColumn(JSONObject jsonObject) {
        return jsonObject == null ? null : jsonObject.toString();
    }

    @SneakyThrows
    @Override
    public JSONObject convertToEntityAttribute(String dbData) {
        return dbData == null ? new JSONObject() : new JSONObject(dbData);
    }
}



