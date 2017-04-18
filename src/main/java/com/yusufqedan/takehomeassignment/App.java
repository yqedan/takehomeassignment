package com.yusufqedan.takehomeassignment;
import com.google.gson.Gson;
import com.yusufqedan.takehomeassignment.models.Channel;
import com.yusufqedan.takehomeassignment.models.NamedUser;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        NamedUser user = new NamedUser("abc");
        user.addChannel(new Channel("ios","123"));

        post("/",(req, res) ->{
            Gson gson = new Gson();
            JsonInput input = gson.fromJson(req.body(), JsonInput.class);
            System.out.println(input.channel_id);
            res.type("application/json");
            return "{\"ok\": true}";
        });
    }
    class JsonInput{
        private String channel_id;
        private String device_type;
        private String named_user_id;

        public String getChannel_id() {
            return channel_id;
        }

        public String getDevice_type() {
            return device_type;
        }

        public String getNamed_user_id() {
            return named_user_id;
        }
    }
}
