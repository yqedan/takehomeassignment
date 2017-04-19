package com.yusufqedan.takehomeassignment.models;


import java.util.ArrayList;

public class NamedUser {
    private String named_user_id;
    private ArrayList<Channel> channels;

    public NamedUser(){}

    public NamedUser(String named_user_id){
        this.named_user_id = named_user_id;
        this.channels = new ArrayList<>();
    }

    public void addChannel(Channel channel){
        channels.add(channel);
    }

    public boolean removeChannel(Channel channel){
        return channels.remove(channel);
    }

    public String getNamed_user_id() {
        return named_user_id;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof NamedUser){
            NamedUser otherUser = (NamedUser) obj;
            return otherUser.getNamed_user_id().equals(this.named_user_id);
        }
        return false;
    }
}
