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
import com.henry.model.Assignment;
import com.henry.model.Station;

@Path("/station")
public class StationRS {
	
	private AppDao dao;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getStation() {
    	    dao = new AppDao();
        String json = new Gson().toJson(dao.getAllStation());
    	    return json;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String deleteNurse(@PathParam("id") int id) {
    	    dao = new AppDao();
    	    dao.deleteStation(id);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String updateStation(@PathParam("id") int id, Station station) {
    	    dao = new AppDao();
    	    station.setId(id);
    	    dao.updateStation(station);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String addStation(Station station) {
    	    dao = new AppDao();
    	    dao.addStation(station);
    	    boolean result = true;
    	    String json = new Gson().toJson(result);
    	    return json;
    }
    
    /**************
     * 
     * 
     * 護理人員管理
     * 
     *
     **************/
    
    
    @POST
    @Path("/{id}/nurse")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String addAssignment(@PathParam("id") int station_id, Assignment assignment) {
    		
    	    assignment.setStation_id(station_id);
    	    dao = new AppDao();
    		dao.addAssignment(assignment);
    		
    		Result r = new Result(); r.setResult_code(200);
    	    String json = new Gson().toJson(r);
    	    return json;
    }
    
    @GET
    @Path("/{id}/nurse")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getAssignment(@PathParam("id") int station_id) {
    	
    		Station station = new Station();
    		station.setId(station_id);
    	
    		dao = new AppDao();
    		String json = new Gson().toJson(dao.getAllAssignmentByNurse(station));
	    return json;
    }
    
    @GET
    @Path("/{id}/nurse-not-in")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getAllNurseNotInByStation(@PathParam("id") int station_id) {
    	
    		dao = new AppDao();
    		String json = new Gson().toJson(dao.getAllNurseNotInByStation(station_id));
	    return json;
    }
    
    @DELETE
    @Path("/{id}/nurse")
    public String deleteAssignment(@PathParam("id") int station_id, Assignment assignment) {

    	    assignment.setStation_id(station_id);
    	
    	    dao = new AppDao();
    	    dao.deleteAssignment(assignment);
    	    Result r = new Result(); r.setResult_code(200);
    	    String json = new Gson().toJson(r);
    	    return json;
    }
    
    private class Result{
    		private int response_code;

		public int getResult_code() {
			return response_code;
		}

		public void setResult_code(int result_code) {
			this.response_code = result_code;
		}
    		
    		
    }
}
