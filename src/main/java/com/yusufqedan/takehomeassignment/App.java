package com.yusufqedan.takehomeassignment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
            JsonInput input;
            res.type("application/json");
            NamedUser userToAssociateChannel;

            try{
                input = gson.fromJson(req.body(), JsonInput.class);
            }catch (JsonSyntaxException e){
                res.status(400);
                return "{\"Incorrect request body format\": true}";
            }

            String namedUserIdInput = input.getNamed_user_id();
            String channelIdInput = input.getChannel_id();
            String deviceTypeInput = input.getDevice_type();

            if(namedUserIdInput == null || namedUserIdInput.equals("")){
                res.status(400);
                return "{\"Named user id not specified\": true}";
            }
            if(channelIdInput == null || channelIdInput.equals("")){
                res.status(400);
                return "{\"Channel id not specified\": true}";
            }
            if(deviceTypeInput == null || deviceTypeInput.equals("")){
                res.status(400);
                return "{\"Device type not specified\": true}";
            }

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

            if(userToAssociateChannel.getChannels().size() >= 20){
                res.status(405);
                return "{\"Exceeded the maximum number of channels for this user\": true}";
            }

            //fake save the user to the database
            userToAssociateChannel.addChannel(new Channel(deviceTypeInput,channelIdInput));

            System.out.println(gson.toJson(allUsers.get(i)));

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
