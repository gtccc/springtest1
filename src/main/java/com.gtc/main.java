package com.gtc;

import com.gtc.dao.UserDao;
import com.gtc.dao.UserDaoImp;
import com.gtc.entity.User;
import com.gtc.servrice.client;
import com.gtc.util.csvUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by gtc on 2016/12/23.
 */
public class main {
    public static void main(String[] args) {
        client cc = new client();
        User uu = new User(10, "ss", "ste");
        JSONObject json = JSONObject.fromObject(uu);
        String id = String.valueOf(10086);
        String name = "tiancai";
        String sex = "mun";
        String para = String.format("{\"id\":%s,\"sex\":\"%s\",\"name\":\"%s\"}", id, name, sex);
        UserDao userDao = new UserDaoImp();

        List<Map<String, Object>> dataList = null;
        String sTitle = "id,name,sex";
        String fName = "gtctest";
        String mapKey = "id,name,sex";
        dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        String listUsers = cc.sendGet("/users");
        System.out.print(listUsers);
        JSONArray jArray = JSONArray.fromObject(listUsers);
        Collection collection = JSONArray.toCollection(jArray, User.class);
        List<User> userList = new ArrayList<User>();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            User user = (User) it.next();
            userList.add(user);
        }
        for (int i = 0; i < userList.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("id", userList.get(i).getId());
            map.put("name", userList.get(i).getName());
            map.put("sex", userList.get(i).getSex());
            dataList.add(map);
        }

        try {
            FileOutputStream os = null;
            File file = new File("D:/newfile.csv");
            os = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            csvUtil.doExport(dataList, sTitle, mapKey, os);

        } catch (Exception e) {
            System.out.print(e);

        }
    }

}
