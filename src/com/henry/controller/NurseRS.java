package com.henry.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.henry.dao.AppDao;
import com.henry.model.Nurse;

@Path("/nurse")
public class NurseRS {
	
	private AppDao dao;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getNurses() {
    	    dao = new AppDao();
        String json = new Gson().toJson(dao.getAllNurse());
    	    return json;
    }   
 
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    public String getNurse(@PathParam("id") int id) {
//    	    dao = new AppDao();
//    	    String json = new Gson().toJson(dao.getNurseById(id));
//    	    return json;
//    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String deleteNurse(@PathParam("id") int id) {
    	    dao = new AppDao();
    	    dao.deleteNurse(id);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String updateNurse(@PathParam("id") int id, Nurse nurse) {
    	    dao = new AppDao();
    	    nurse.setId(id);
    	    dao.updateNurse(nurse);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String addNurse(Nurse nurse) {
    	    dao = new AppDao();
    	    dao.addNurse(nurse);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
}
