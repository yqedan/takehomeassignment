package com.yusufqedan.takehomeassignment.models;


public class Channel {
    private String channel_id;
    private String device_type;

    public Channel(){}

    public Channel(String device_type, String channel_id){
        this.device_type = device_type;
        this.channel_id = channel_id;
    }
}
