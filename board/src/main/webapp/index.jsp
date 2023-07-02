<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%! String fname = null; %>
<%
	fname = request.getParameter("fname");
if(fname==null || "null".equals(fname))
{
	fname="main.jsp";
}
else
{
	fname+= ".jsp";
}
%>

<%@include file="include/header.jsp" %>

<jsp:include page="<%=fname%>" />

<%@include file="include/footer.jsp" %>