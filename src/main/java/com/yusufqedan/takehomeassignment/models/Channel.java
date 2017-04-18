package com.yusufqedan.takehomeassignment.models;


public class Channel {
    private String channel_id;
    private String device_type;

    public Channel(){}

    public Channel(String device_type, String channel_id){
        this.device_type = device_type;
        this.channel_id = channel_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Channel){
            Channel otherChannel = (Channel) obj;
            return otherChannel.getChannel_id().equals(this.channel_id);
        }
        return false;
    }
}
