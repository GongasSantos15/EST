<%
    session.removeAttribute("contador");

    response.setHeader("Refresh", "2; URL=contador_acessos.jsp");
%>