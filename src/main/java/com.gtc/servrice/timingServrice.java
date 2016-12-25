package com.gtc.servrice;

import com.gtc.dao.UserDao;
import com.gtc.entity.User;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by gtc on 2016/12/22.
 */
@Component
public class timingServrice {
    private UserDao userDao;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void insertUser() {

        int id = (int) (Math.random() * 1000);
        String name = RandomStringUtils.randomAlphanumeric(5);
        int rd = Math.random() > 0.5 ? 1 : 0;
        String sex = (rd == 0 ? "boy" : "girl");
        User insert = new User(id, name, sex);
        client cc = new client();
        User user = new User(id, name, sex);

        JSONObject json = JSONObject.fromObject(user);
        cc.sendPost("user", json.toString());
    }

}
