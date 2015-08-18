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

@WebServlet("/BenchmarkTest00472")
public class BenchmarkTest00472 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		java.util.Map<String,String[]> map = request.getParameterMap();
		String param = "";
		if (!map.isEmpty()) {
			String[] values = map.get("vector");
			if (values != null) param = values[0];
		}
		
		
		
		String bar = "safe!";
		java.util.HashMap<String,Object> map54814 = new java.util.HashMap<String,Object>();
		map54814.put("keyA-54814", "a_Value"); // put some stuff in the collection
		map54814.put("keyB-54814", param); // put it in a collection
		map54814.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map54814.get("keyB-54814"); // get it back out
		bar = (String)map54814.get("keyA-54814"); // get safe value back out
		
		
	org.owasp.benchmark.helpers.LDAPManager ads = new org.owasp.benchmark.helpers.LDAPManager();
	try {
		response.setContentType("text/html");
		String base = "ou=users,ou=system";
		javax.naming.directory.SearchControls sc = new javax.naming.directory.SearchControls();
		sc.setSearchScope(javax.naming.directory.SearchControls.SUBTREE_SCOPE);
		String filter = "(&(objectclass=person))(|(uid="+bar+")(street={0}))";
		Object[] filters = new Object[]{"The streetz 4 Ms bar"};
		
		javax.naming.directory.DirContext ctx = ads.getDirContext();
		javax.naming.directory.InitialDirContext idc = (javax.naming.directory.InitialDirContext) ctx;
		javax.naming.NamingEnumeration<javax.naming.directory.SearchResult> results = 
				idc.search(base, filter,filters, sc);
		while (results.hasMore()) {
			javax.naming.directory.SearchResult sr = (javax.naming.directory.SearchResult) results.next();
			javax.naming.directory.Attributes attrs = sr.getAttributes();

			javax.naming.directory.Attribute attr = attrs.get("uid");
			javax.naming.directory.Attribute attr2 = attrs.get("street");
			if (attr != null){
				response.getWriter().write("LDAP query results:<br>"
						+ " Record found with name " + attr.get() + "<br>"
								+ "Address: " + attr2.get()+ "<br>");
				System.out.println("record found " + attr.get());
			}
		}
	} catch (javax.naming.NamingException e) {
		throw new ServletException(e);
	}finally{
    	try {
    		ads.closeDirContext();
		} catch (Exception e) {
			throw new ServletException(e);
		}
    }
	}
}
