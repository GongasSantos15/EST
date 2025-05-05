<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    int tipo_user = (Integer) session.getAttribute("tipo_user");

    if (session.getAttribute("user") != null && tipo_user == 1) {
%>
    Bem vindo, <%= session.getAttribute("user") %>
    <a href="index.jsp">Logout</a>
<% 
    } else { 
%>
    O utilizador não existe!
<%
        response.setHeader("Refresh", "2; URL=login.jsp");
    } 
%>

