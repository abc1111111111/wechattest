package com.testerhome.hgwz.wechat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

/**
 * @author: gq
 * @createtime: 2020/6/9 11:50 上午
 * @description: TODO
 */
public class Restful {
    HashMap<String, Object> query = new HashMap<String, Object>();
    public RequestSpecification requestSpecification = given();
    public Response send() {
        requestSpecification = given().log().all();
        query.entrySet().forEach(entry -> {
            requestSpecification.queryParam(entry.getKey(),entry.getValue());
        });
//        requestSpecification.when().get();
//        for (HashMap.Entry<String,Object> entry : query.entrySet()){
//            given().queryParam(entry.getKey(), entry.getValue());
//        }
        return requestSpecification.when().request("get", "www.baidu.com");
    }

    public static String template(String jsonname, HashMap<String, Object> hashMap) {
        DocumentContext documentContext = JsonPath.parse(Restful.class.getResourceAsStream(jsonname));
        hashMap.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }

}