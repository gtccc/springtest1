package com.gtc;

import com.fasterxml.jackson.databind.JsonNode;
import com.gtc.entity.User;
import com.gtc.servrice.client;
import net.sf.json.JSONObject;

/**
 * Created by gtc on 2016/12/23.
 */
public class main {
    public static void main(String[] args){
        client cc = new client();
        User uu =new User(10,"ss","ste");
        JSONObject json = JSONObject.fromObject(uu);
        String id = String.valueOf(10086);
        String name = "tiancai";
        String sex = "mun";
        String para = String.format("{\"id\":%s,\"sex\":\"%s\",\"name\":\"%s\"}",id,name,sex);

        String i = cc.sendGet("csv");
        System.out.println(para);
    }
}
