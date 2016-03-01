<!-- This test jsp is base on WebLogic Test JSP.
     2013.01.25 Created by Jooho Lee <Jay> (ljhiyh@gmail.com)  -->
     
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%!
  private int totalHits = 0;
%>

<%
	//To reset ServletContext attribute
	if(request.getParameter("ResetServletSession") != null){
		String executeFlag=request.getParameter("ResetServletSession").trim();
		if(executeFlag.equals("true")){
				application.setAttribute("simplesession.hitcount", 0);
		}
		request.setAttribute("ResetServletSession",null);
	}

	//To get HTTP Request Session
	session = request.getSession(true);
	
	Integer ival = (Integer)session.getAttribute("simplesession.counter");
	if (ival == null) 
	  ival = new Integer(1);
	else 
	  ival = new Integer(ival.intValue() + 1);
	session.setAttribute("simplesession.counter", ival);
	System.out.println("[SessionTest] count = " + ival );
	 
	//To get ServletContext Session 
	Integer cnt = (Integer)application.getAttribute("simplesession.hitcount");
	if (cnt == null)
	  cnt = new Integer(1);
	else
	  cnt = new Integer(cnt.intValue() + 1);
	  application.setAttribute("simplesession.hitcount", cnt);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FaiOver Test</title>
</head>
<script type="text/javascript" src="/TestSampleWar/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	//button Event handler
	$("button").on("click",function(){
		var btnName = $(this).attr("name");
		if(btnName.indexOf("ResetServletSession") >=0){
			location.href="http://localhost:8080/TestSampleWar/testcase/cluster/FailOver.jsp?ResetServletSession=true";
		}
	});
	
});

</script>

<body>

<body bgcolor="#FFFFFF">
<p>
<font face="Helvetica">

<h2>
<font color=#DB1260>
Failover Test
</font>
</h2>

<h3>Server Name 2: <%=System.getProperty("jboss.server.name")%></h3>
<p>
This JSP shows simple principles of session management
by incrementing a counter each time a user accesses a page.
</p>

<table>
	<tr>
		<td width=50% valign=top>
			<font face="Helvetica">
			<h3>
			You have hit this page <font color=red>  <%= cnt %></font> time<%= (cnt.intValue() == 1) ? "" : "s" %>, <br>before the session times out.
			</h3>
			The value in <font color=red><b>red</b></font> is stored in the HTTP session (<font face="Courier New" size=-1>javax.servlet.http.HttpSession</font>), in an object named <font face="Courier New" size=-1>simplesession.counter</font>. This object has <i>session</i> scope and its integer value is re-set to <font color=red><b>1</b></font> when you reload the page after the session has timed out.
			<p>
			You can change the time interval after which a session times out. For more information, see the <a href="https://access.redhat.com/knowledge/solutions/31400" target="_blank">Session Timeout</a> KCS under <a href="https://access.redhat.com"  target="_blank">RedHat Customer Portal Site</a>.
			</font>
		</td>

		<td width=50% valign=top><font face="Helvetica">
		<h3>
		You have hit this page a total of <font color=green><%= ival %></font> time<%= (ival.intValue() == 1) ? "" : "s" %>! <button name="ResetServletSession">Reset Session</button>
		</h3>	
		
		The value in <font color=green><b>green</b></font> is stored in the
		Servlet Context (<font face="Courier New" size=-1>javax.servlet.ServletContext)</font>, in an object named <font face="Courier New" size=-1>simplesession.hitcount</font>. This object
		has <i>application</i> scope and its integer value is incremented each time you
		reload the page.
		 
		</font> 
		</td>
	</tr>
</table>


</body>
</html>