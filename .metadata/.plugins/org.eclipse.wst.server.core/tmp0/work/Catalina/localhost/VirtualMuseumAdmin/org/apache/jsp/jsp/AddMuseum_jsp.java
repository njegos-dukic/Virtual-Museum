/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.56
 * Generated at: 2022-01-08 15:29:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.unibl.etf.virtualmuseum.entities.MuseumEntity;
import org.unibl.etf.virtualmuseum.services.MuseumService;

public final class AddMuseum_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.unibl.etf.virtualmuseum.entities.MuseumEntity");
    _jspx_imports_classes.add("org.unibl.etf.virtualmuseum.services.MuseumService");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.unibl.etf.virtualmuseum.beans.UserBean user = null;
      synchronized (session) {
        user = (org.unibl.etf.virtualmuseum.beans.UserBean) _jspx_page_context.getAttribute("user", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (user == null){
          user = new org.unibl.etf.virtualmuseum.beans.UserBean();
          _jspx_page_context.setAttribute("user", user, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write("  \r\n");
      out.write(" \r\n");

	MuseumEntity me = null;

	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String type = request.getParameter("type");
		String maps = request.getParameter("maps");
	    if(name != null && address != null && phone != null && city != null && country != null && type != null && maps != null) {
	    	me = new MuseumEntity(0, name, address, phone, city, country, type, maps);
	    	MuseumService.insert(me);
	    	response.sendRedirect("Museums.jsp");
	    }
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("	<head>\r\n");
      out.write("    	<meta charset=\"UTF-8\">\r\n");
      out.write("    	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    	<title>Virtual Museum Admin</title>\r\n");
      out.write("    	<link href=\"../css/Header.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    	<link href=\"../css/AddEdit.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    	<link rel=\"icon\" href=\"../images/logo.png\">\r\n");
      out.write("    	<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n");
      out.write("    	<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n");
      out.write("    	<link href=\"https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap\" rel=\"stylesheet\"> 				\r\n");
      out.write("	\r\n");
      out.write("		<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("	  \r\n");
      out.write("		<script>\r\n");
      out.write("	        $(document).ready(function() {  \r\n");
      out.write("	            $.get(\"https://restcountries.com/v3.1/region/europe\", function(data) {\r\n");
      out.write("	                data.forEach(country => $(\"#countries-select\").append(\"<option data-alpha2code=\\\"\" + country.cca2 + \"\\\" value=\\\"\" + country.name.common + \"\\\">\" + country.name.common + \"</option>\"))\r\n");
      out.write("	            }) \r\n");
      out.write("	            \r\n");
      out.write("	            $('#countries-select').change(function() {\r\n");
      out.write("	            	   $('#cities-select').html('');\r\n");
      out.write("	                   $(this).find(\":selected\").each(function () {\r\n");
      out.write("	                       var endpoint = \"http://battuta.medunes.net/api/region/\" + $(this).data('alpha2code') + \"/all/?key=e1efcc9e9bac7b924ccb5a74af63540f&callback=parseResponse\";\r\n");
      out.write("	                    $.getScript(endpoint);\r\n");
      out.write("	                });\r\n");
      out.write("	            });\r\n");
      out.write("	        });\r\n");
      out.write("	        \r\n");
      out.write("	        function parseResponse(data) {\r\n");
      out.write("	            data.forEach(region => $.getScript(\"http://battuta.medunes.net/api/city/\" + region.country +\"/search/?region=\" + region.region + \"&key=e1efcc9e9bac7b924ccb5a74af63540f&callback=parseCityResponse\"))\r\n");
      out.write("	        }\r\n");
      out.write("	        \r\n");
      out.write("	        function parseCityResponse(data) {\r\n");
      out.write("	            data.forEach(city => { $(\"#cities-select\").append(\"<option value=\\\"\" + city.city + \"\\\">\" + city.city + \"</option>\"); })\r\n");
      out.write("	        }\r\n");
      out.write("	    </script>\r\n");
      out.write("	</head>\r\n");
      out.write("	\r\n");
      out.write("	<body>\r\n");
      out.write("		<div class=\"header-container\">\r\n");
      out.write("            <div class=\"header-logo-container\">\r\n");
      out.write("				<a class=\"header-logo-container\" href=\"Homepage.jsp\">\r\n");
      out.write("			    	<img class=\"header-logo\" src=\"../images/logo.png\" />\r\n");
      out.write("			    </a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("			<div class=\"header-title\">\r\n");
      out.write("                VIRTUAL MUSEUM ADMINISTRATION\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("			<div class=\"header-login-container\">\r\n");
      out.write("				<form class=\"header-login-container\" action=\"#\" method=\"post\">\r\n");
      out.write("	                <div class=\"header-login-label\">\r\n");
      out.write("	                    ");
      out.print( user.getUsername() );
      out.write(" \r\n");
      out.write("	                </div>\r\n");
      out.write("	                <input type=\"hidden\" name=\"logout\" value=\"1\" />\r\n");
      out.write("		            <input type=\"image\" class=\"header-login-image\" src=\"../images/login.png\" />\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("		</div>\r\n");
      out.write("		\r\n");
      out.write("		<div class=\"museum-edit-container\">\r\n");
      out.write("	        <form action=\"#\" method=\"post\" class=\"museum-edit-form\">\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"name\">Museum name: </label>\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" name=\"name\" type=\"text\">\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"address\">Museum address: </label>\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" name=\"address\" type=\"text\" required>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"phone\">Museum phone: </label>\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" name=\"phone\" type=\"text\" required>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"country\">Museum country: </label>\r\n");
      out.write("	                <select id=\"countries-select\" class=\"museum-edit-input-field\" name=\"country\" required>\r\n");
      out.write("	                \r\n");
      out.write("	                </select>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"city\">Museum city: </label>\r\n");
      out.write("	                <select id=\"cities-select\" class=\"museum-edit-input-field\" name=\"city\" required>\r\n");
      out.write("	                \r\n");
      out.write("	                </select>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"type\">Museum type: </label>\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" name=\"type\" type=\"text\" required>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input\">\r\n");
      out.write("	                <label class=\"museum-edit-input-label\" for=\"maps\">Museum location: </label>\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" name=maps type=\"text\" required>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\"museum-edit-single-input margin-botton-4perc\">\r\n");
      out.write("	                <input class=\"museum-edit-input-field\" type=\"submit\" value=\"Add Museum\">\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </form>\r\n");
      out.write("	    </div>\r\n");
      out.write("	</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
