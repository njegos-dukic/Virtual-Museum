/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.56
 * Generated at: 2022-01-11 21:33:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.unibl.etf.virtualmuseum.services.TourService;
import org.unibl.etf.virtualmuseum.entities.TourEntity;
import org.unibl.etf.virtualmuseum.beans.UserBean;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.Timestamp;

public final class Tours_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("org.unibl.etf.virtualmuseum.services.TourService");
    _jspx_imports_classes.add("org.unibl.etf.virtualmuseum.beans.UserBean");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("java.sql.Timestamp");
    _jspx_imports_classes.add("java.text.SimpleDateFormat");
    _jspx_imports_classes.add("java.text.DateFormat");
    _jspx_imports_classes.add("org.unibl.etf.virtualmuseum.entities.TourEntity");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.unibl.etf.virtualmuseum.beans.UserBean user = null;
      synchronized (session) {
        user = (org.unibl.etf.virtualmuseum.beans.UserBean) _jspx_page_context.getAttribute("user", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (user == null){
          user = new org.unibl.etf.virtualmuseum.beans.UserBean();
          _jspx_page_context.setAttribute("user", user, javax.servlet.jsp.PageContext.SESSION_SCOPE);
          out.write('\r');
          out.write('\n');
          out.write('	');
          org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("user"), "username", "", null, null, false);
          out.write(" \r\n");
          out.write("	");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("user"), "password", "", null, null, false);
          out.write(' ');
          out.write('\r');
          out.write('\n');
        }
      }
      out.write("  \r\n");
      out.write("	");
  
		if (!user.isLoggedIn()) {
			response.sendRedirect("Login.jsp");
		}
		
		if (request.getParameter("logout") != null) {
			user.logOut();
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}
		
		String id = request.getParameter("delete-id");
		boolean deleteStatus = true;
	    if (id != null) 
	    	deleteStatus = TourService.delete(Integer.parseInt(id));
	    
	    TourEntity editedTe = null;
	    String tid = request.getParameter("tid");
	    String mid = request.getParameter("mid");
		String tname = request.getParameter("tname");
		String startDate = request.getParameter("date");
		String startTime = request.getParameter("time");
		String duration = request.getParameter("duration");
	    if(tid != null && mid != null && tname != null && startDate != null && startTime != null  && duration != null) {
	    	editedTe = new TourEntity(Integer.parseInt(tid), Integer.parseInt(mid), tname, Timestamp.valueOf(startDate + " " + startTime), Double.parseDouble(duration));
	    	TourService.update(editedTe);
		}
    
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("	<head>\r\n");
      out.write("    	<meta charset=\"UTF-8\">\r\n");
      out.write("    	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    	<title>Virtual Museum Admin</title>\r\n");
      out.write("    	<link href=\"../css/Header.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    	<link href=\"../css/Menu.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    	<link href=\"../css/Content.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    	<link rel=\"icon\" href=\"../images/logo.png\">\r\n");
      out.write("    	<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n");
      out.write("    	<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("    	<link href=\"https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap\" rel=\"stylesheet\"> 				\r\n");
      out.write("	</head>\r\n");
      out.write("	\r\n");
      out.write("	<body>\r\n");
      out.write("		<div class=\"header-container\">\r\n");
      out.write("           	<div class=\"header-logo-container\">\r\n");
      out.write("				<a class=\"header-logo-container\" href=\"Homepage.jsp\">\r\n");
      out.write("			    	<img class=\"header-logo\" src=\"../images/logo.png\" />\r\n");
      out.write("			    </a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("			<div class=\"header-title\">\r\n");
      out.write("                VIRTUAL MUSEUM ADMINISTRATION\r\n");
      out.write("            </div>\r\n");
      out.write(" \r\n");
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
      out.write("\r\n");
      out.write("		<div class=\"menu-container\">\r\n");
      out.write("			<a class=\"menu-item right-margin-3\" href=\"Homepage.jsp\">\r\n");
      out.write("		        HOME \r\n");
      out.write("		    </a>\r\n");
      out.write("			<a class=\"menu-item right-margin-3\" href=\"Museums.jsp\">\r\n");
      out.write("		        MUSEUMS\r\n");
      out.write("		    </a>\r\n");
      out.write("		    <a style=\"background-color: #8b84bf;\" class=\"menu-item left-margin-3 right-margin-3\" href=\"Tours.jsp\">\r\n");
      out.write("		        TOURS\r\n");
      out.write("		    </a>\r\n");
      out.write("		    <a class=\"menu-item left-margin-3 right-margin-3\" href=\"Users.jsp\">\r\n");
      out.write("		        USERS\r\n");
      out.write("		    </a>\r\n");
      out.write("		    <a class=\"menu-item left-margin-3\" href=\"Logs.jsp\">\r\n");
      out.write("		        LOGS\r\n");
      out.write("		    </a>\r\n");
      out.write("		</div>\r\n");
      out.write("		\r\n");
      out.write("		<a href=\"AddTour.jsp\" class=\"new-museum-container\" >Add new Tour</a>	\r\n");
      out.write("		\r\n");
      out.write("		<div class=\"content-container\">\r\n");
      out.write("			<div class=\"content-custom-table-header\">\r\n");
      out.write("		    	<div style=\"width: 17%;\" class=\"content-custom-table-column right-margin-2\">\r\n");
      out.write("		        	MUSEUM\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 17%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	TOUR NAME\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 17%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	START DATE\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 17%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	START TIME\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 17%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	DURATION\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 5%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	ARTIFACTS\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 5%;\" class=\"content-custom-table-column left-right-margin-2\">\r\n");
      out.write("		        	EDIT\r\n");
      out.write("		        </div>\r\n");
      out.write("		        <div style=\"width: 5%;\" class=\"content-custom-table-column left-margin-2\">\r\n");
      out.write("		        	REMOVE\r\n");
      out.write("		        </div>\r\n");
      out.write("			</div>\r\n");
      out.write("			");
 for (TourEntity te : TourService.selectAll()) { 
        			Date date = new Date(te.getStartDateTime().getTime()); 
        			DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        			DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        			DateFormat timeFormatterWithSeconds = new SimpleDateFormat("HH:mm:ss");
        			DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        	
      out.write("\r\n");
      out.write("				<div class=\"content-custom-table-record-container\">\r\n");
      out.write("			    	<div style=\"width: 17%;\" class=\"content-custom-table-column content-custom-table-data-column-color right-margin-2\">\r\n");
      out.write("			        	");
      out.print( te.getMuseumName() );
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			        <div style=\"width: 17%;\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\">\r\n");
      out.write("			        	");
      out.print( te.getName() );
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			        <div style=\"width: 17%;\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\">\r\n");
      out.write("			        	");
      out.print( (dateFormatter.format(date)) );
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			        <div style=\"width: 17%;\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\">\r\n");
      out.write("			        	");
      out.print( (timeFormatter.format(date)) + " H" );
      out.write(" \r\n");
      out.write("			        </div>\r\n");
      out.write("			        <div style=\"width: 17%;\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\">\r\n");
      out.write("			        	");
      out.print( te.getDuration() + " H" );
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			        <a href=\"#\" target=\"_blank\" style=\"width: 5%; justify-content: center; overflow: hidden;\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\" > 	\r\n");
      out.write("				 		<img style=\"height: min(calc(8px + 1.5vw), 16px);\" src=\"../images/artifact.png\" />\r\n");
      out.write("			        </a>\r\n");
      out.write("			        <div style=\"width: 5%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);\" class=\"content-custom-table-column content-custom-table-data-column-color left-right-margin-2\">\r\n");
      out.write("			        	<form style=\"height: min(calc(8px + 1.5vw), 16px);\" action=\"EditTour.jsp\" method=post>\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-tid\" value=\"");
      out.print( te.getId() );
      out.write("\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-mid\" value=\"");
      out.print( te.getMuseumId() );
      out.write("\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-tname\" value=\"");
      out.print( te.getName() );
      out.write("\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-date\" value=\"");
      out.print( (sqlDateFormatter.format(date)) );
      out.write("\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-time\" value=\"");
      out.print( (timeFormatterWithSeconds.format(date)) );
      out.write("\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"current-duration\" value=\"");
      out.print( te.getDuration() );
      out.write("\">\r\n");
      out.write("			        		<input style=\"height: min(calc(8px + 1.5vw), 16px);\" src=\"../images/edit.png\" type=\"image\">\r\n");
      out.write("			        	</form>\r\n");
      out.write("			        </div>\r\n");
      out.write("			        <div style=\"width: 5%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);\" class=\"content-custom-table-column content-custom-table-data-column-color left-margin-2\">\r\n");
      out.write("			        	<form action=\"#\" method=\"post\">\r\n");
      out.write("			        		<input type=\"hidden\" name=\"delete-id\" value=\"");
      out.print( te.getId() );
      out.write("\" >\r\n");
      out.write("			        		<input style=\"height: min(calc(8px + 1.5vw), 16px);\" src=\"../images/remove.png\" type=\"image\">\r\n");
      out.write("			        	</form>\r\n");
      out.write("			        </div>\r\n");
      out.write("			    </div>\r\n");
      out.write("			");
 } 
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("		");
 if(!deleteStatus) { 
      out.write("\r\n");
      out.write("			<script>alert(\"Can't delete tour!\")</script>\r\n");
      out.write("		");
 } 
      out.write("	\r\n");
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
