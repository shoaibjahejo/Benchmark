/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest00859")
public class BenchmarkTest00859 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String queryString = request.getQueryString();
		String paramval = "vector"+"=";
		int paramLoc = -1;
		if (queryString != null) paramLoc = queryString.indexOf(paramval);
		if (paramLoc == -1) {
			response.getWriter().println("getQueryString() couldn't find expected parameter '" + "vector" + "' in query string.");
			return;
		}
		
		String param = queryString.substring(paramLoc + paramval.length()); // 1st assume "vector" param is last parameter in query string.
		// And then check to see if its in the middle of the query string and if so, trim off what comes after.
		int ampersandLoc = queryString.indexOf("&", paramLoc);
		if (ampersandLoc != -1) {
			param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
		}
		param = java.net.URLDecoder.decode(param, "UTF-8");
		
		
		String bar = "safe!";
		java.util.HashMap<String,Object> map23263 = new java.util.HashMap<String,Object>();
		map23263.put("keyA-23263", "a_Value"); // put some stuff in the collection
		map23263.put("keyB-23263", param); // put it in a collection
		map23263.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map23263.get("keyB-23263"); // get it back out
		bar = (String)map23263.get("keyA-23263"); // get safe value back out
		
		
		try {
	        String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='"
	            + bar + "'";
	
			org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(sql);
			java.io.PrintWriter out = response.getWriter();
	//		System.out.println("no results for query: " + sql + " because the Spring batchUpdate method doesn't return results.");
			out.write("No results can be displayed for query: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(sql) + "<br>");
			out.write(" because the Spring batchUpdate method doesn't return results.");
		} catch (org.springframework.dao.DataAccessException e) {
			if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
        		response.getWriter().println("Error processing request.");
        		return;
        	}
			else throw new ServletException(e);
		}
	}
}
