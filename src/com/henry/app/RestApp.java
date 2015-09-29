package com.henry.app;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

 
@ApplicationPath("rest")
public class RestApp extends ResourceConfig{
    public RestApp(){
        packages("com.henry.controller");
//        register(CORSResponseFilter.class);
//        packages("com.henry.util");
    }
}
