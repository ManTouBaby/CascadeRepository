package com.hy.cascade.database;

import android.arch.persistence.room.TypeConverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author:MtBaby
 * @date:2020/05/08 9:19
 * @desc:
 */
public class ConverterObject {
    @TypeConverter
    public Object json2Object(String json) {
        return JSON.parseObject(json, Object.class);
    }

    @TypeConverter
    public String object2Json(Object data) {
        return JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
    }
}
