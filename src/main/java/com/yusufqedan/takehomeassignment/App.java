package com.yusufqedan.takehomeassignment;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/",(req, res) ->{
            res.type("application/json");
            return "{\"ok\": true}";
        });
    }
}
