package com.yusufqedan.takehomeassignment;
import com.google.gson.Gson;
import com.yusufqedan.takehomeassignment.models.Channel;
import com.yusufqedan.takehomeassignment.models.NamedUser;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        //my fake database
        List<NamedUser> allUsers = new ArrayList<>();
        allUsers.add(new NamedUser("user-id-abcd"));
        allUsers.add(new NamedUser("user-id-1234"));
        allUsers.add(new NamedUser("user-id-efgh"));
        allUsers.add(new NamedUser("user-id-5678"));

        Gson gson = new Gson();
        post("/api/named_users/associate",(req, res) ->{
            JsonInput input = gson.fromJson(req.body(), JsonInput.class);
            String namedUserIdInput = input.getNamed_user_id();
            String channelIdInput = input.getChannel_id();
            String deviceTypeInput = input.getDevice_type();
            NamedUser userToAssociateChannel;

            //fake database query
            int i;
            for(i = 0; i < allUsers.size(); i++){
                if(allUsers.get(i).getNamed_user_id().equals(namedUserIdInput)){
                    break;
                }
            }

            //debugging
            System.out.println(gson.toJson(allUsers.get(i)));

            userToAssociateChannel = allUsers.get(i);
            userToAssociateChannel.addChannel(new Channel(deviceTypeInput,channelIdInput));
            System.out.println(gson.toJson(allUsers.get(i)));

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
