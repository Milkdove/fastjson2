package com.alibaba.fastjson.jsonp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by wenshao on 21/02/2017.
 */
public class JSONPParseTest3 {
    @Test
    public void test_f() throws Exception {
        String text = "parent.callback ({'id':1, 'name':'ido)nans'},1,2 );   /**/ ";

        JSONPObject jsonpObject = JSON.parseObject(text, JSONPObject.class);
        assertEquals("parent.callback", jsonpObject.getFunction());

        assertEquals(3, jsonpObject.getParameters().size());
        JSONObject param = (JSONObject) jsonpObject.getParameters().get(0);
        assertEquals(1, param.get("id"));
        assertEquals("ido)nans", param.get("name"));

        String json = JSON.toJSONString(jsonpObject, SerializerFeature.BrowserSecure, SerializerFeature.MapSortField);
        assertEquals("/**/parent.callback({\"id\":1,\"name\":\"ido\\u0029nans\"},1,2)", json);
    }
}
