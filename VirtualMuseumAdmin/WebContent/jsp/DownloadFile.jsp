<%     
	  String log = request.getParameter("log");
	  String filepath = "";
	  String filename = "";
	  if (log != null) {
      	  filepath = "C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\logs\\";
	  	  filename = log;
	  }
	  
	  else
	  	response.sendError(404, "NOT FONUD");
	  
      response.setContentType("APPLICATION/OCTET-STREAM");   
      response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
      
      java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);  
                
      int i;   
      while ((i=fileInputStream.read()) != -1) {  
        out.write(i);   
      }   
      fileInputStream.close();   


	// <img scr="FileServet?path=c:\parentDirectoryfile.jpg">

%>   