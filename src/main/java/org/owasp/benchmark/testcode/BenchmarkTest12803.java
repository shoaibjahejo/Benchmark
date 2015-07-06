/**
* OWASP Benchmark Project v1.1
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details
*
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest12803")
public class BenchmarkTest12803 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String[] values = request.getParameterValues("foo");
		String param;
		if (values.length != 0)
		  param = request.getParameterValues("foo")[0];
		else param = null;

		String bar = new Test().doSomething(param);
		
		String sql = "SELECT * from USERS where USERNAME=? and PASSWORD='"+ bar +"'";
				
		try {
			java.sql.Connection connection = org.owasp.benchmark.helpers.DatabaseHelper.getSqlConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement( sql );
			statement.setString(1, "foo");
			statement.execute();
		} catch (java.sql.SQLException e) {
			throw new ServletException(e);
		}
	}  // end doPost

    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		// Chain a bunch of propagators in sequence
		String a33495 = param; //assign
		StringBuilder b33495 = new StringBuilder(a33495);  // stick in stringbuilder
		b33495.append(" SafeStuff"); // append some safe content
		b33495.replace(b33495.length()-"Chars".length(),b33495.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map33495 = new java.util.HashMap<String,Object>();
		map33495.put("key33495", b33495.toString()); // put in a collection
		String c33495 = (String)map33495.get("key33495"); // get it back out
		String d33495 = c33495.substring(0,c33495.length()-1); // extract most of it
		String e33495 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d33495.getBytes() ) )); // B64 encode and decode it
		String f33495 = e33495.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String bar = thing.doSomething(f33495); // reflection

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass
