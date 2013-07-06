package com.javacodegeeks.resteasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.javacodegeeks.resteasy.dao.GetReisAdvies;
import com.javacodegeeks.resteasy.model.Employee;
import com.javacodegeeks.resteasy.model.ReisAdvies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/sampleservice")
public class SampleService {
	
	private final Logger LOG = LoggerFactory.getLogger(SampleService.class);
    
    private static Map<String, Employee> employees = new HashMap<String, Employee>();
    private static Map<String, ReisAdvies> reisAdviezen;
    
    static {
        
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setEmployeeName("Fabrizio");
        employee1.setJob("Software Engineer");
        employees.put(employee1.getEmployeeId(), employee1);
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employee2.setEmployeeName("Justin");
        employee2.setJob("Business Analyst");
        employees.put(employee2.getEmployeeId(), employee2);
        
    }
    
    static {
    	
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
    }
    
    @GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;    
    }
    
    @GET
    @Path("/employees")
    @Produces("application/xml")
    public List<Employee> listEmployees(){
        return new ArrayList<Employee>(employees.values());
    }
    
    @GET
    @Path("/employee/{employeeid}")
    @Produces("application/xml")
    public Employee getEmployee(@PathParam("employeeid")String employeeId){
        return employees.get(employeeId);        
    }
    
    @GET
    @Path("/json/employees/")
    @Produces("application/json")
    public List<Employee> listEmployeesJSON(){
        return new ArrayList<Employee>(employees.values());
    }

    @GET
    @Path("/json/employee/{employeeid}")
    @Produces("application/json")
    public Employee getEmployeeJSON(@PathParam("employeeid")String employeeId){
        return employees.get(employeeId);        
    }
    
    @GET
    @Path("/json/reisadviezen")
    @Produces("application/json")
    public List<ReisAdvies> getReisAdviezenJSON() {
    	GetReisAdvies ra = new GetReisAdvies();
    	LOG.info("init get reisadviezen");
    	reisAdviezen = ra.init("all");
    	ArrayList<ReisAdvies> result = new ArrayList<ReisAdvies>();
    	if(reisAdviezen.values() == null) {
    		LOG.error("ReisAdviezen is null!");
    	} else {
    		result = new ArrayList<ReisAdvies>(reisAdviezen.values());
    	}
    	return result;
    }
    
    @GET
    @Path("json/reisadvies/{locationid}")
    @Produces("application/json")
    public List<ReisAdvies> getReisAdviesJSON(@PathParam("locationid")String locationId) {
    	GetReisAdvies ra = new GetReisAdvies();
    	reisAdviezen = ra.init(locationId);
    	return new ArrayList<ReisAdvies>(reisAdviezen.values());
    }
    
}
