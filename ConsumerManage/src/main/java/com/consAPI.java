package com;
import model.cons;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
/**
 * Servlet implementation class consAPI
 */
@WebServlet("/consAPI")
public class consAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	cons itemObj = new cons(); 

       
     
    public consAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String output = itemObj.insertItem(request.getParameter("name"), 
				 request.getParameter("address"), 
				request.getParameter("phone"), 
				request.getParameter("email"),
				request.getParameter("description")); 
				response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = itemObj.updateItem(paras.get("hidcidSave").toString(), 
		paras.get("name").toString(), 
		paras.get("address").toString(), 
		paras.get("phone").toString(),
		paras.get("email").toString(),
		paras.get("description").toString()); 
		response.getWriter().write(output); 
	
	}

	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteItem(paras.get("cid").toString());
		 
		response.getWriter().write(output); 
	}
	private Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {  
		 String[] p = param.split("="); 
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
	}

}
