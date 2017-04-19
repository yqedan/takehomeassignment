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
        NamedUser namedUser = new NamedUser("user-id-1234");
        namedUser.addChannel(new Channel("ios","df6a6b50-9843-0304-d5a5-743f246a4946"));
        allUsers.add(namedUser);
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

            userToAssociateChannel = getUserById(allUsers, namedUserIdInput);

            if(userToAssociateChannel.getChannels().size() >= 20){
                res.status(405);
                return "{\"Exceeded the maximum number of channels for this user\": true}";
            }

            List<Channel> usersChannels = userToAssociateChannel.getChannels();
            Channel channelToAssociate = new Channel(deviceTypeInput,channelIdInput);

            //test for duplicate channel
            int j;
            for(j = 0; j < usersChannels.size(); j++){
                if(usersChannels.get(j).equals(channelToAssociate)){
                    res.status(405);
                    return "{\"User already has this channel id associated\": true}";
                }
            }

            //fake save the user to the database
            userToAssociateChannel.addChannel(channelToAssociate);

            System.out.println(gson.toJson(userToAssociateChannel));

            return "{\"ok\": true}";
        });

        post("/api/named_users/disassociate",(req, res) ->{
            //todo refactor the duplicate code
            JsonInput input;
            res.type("application/json");
            NamedUser userToDisassociateChannel;

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

            userToDisassociateChannel = getUserById(allUsers, namedUserIdInput);
            Channel channelToDisassociate = new Channel(deviceTypeInput,channelIdInput);

            if(!userToDisassociateChannel.removeChannel(channelToDisassociate)){
                res.status(400);
                return "{\"Channel requested to be removed is not associated to user\": true}";
            }

            //debugging
            System.out.println(gson.toJson(userToDisassociateChannel));

            return "{\"ok\": true}";
        });
    }

    private static NamedUser getUserById(List<NamedUser> allUsers, String namedUserIdInput) {
        //fake database query
        return allUsers.get(allUsers.indexOf(new NamedUser(namedUserIdInput)));
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
