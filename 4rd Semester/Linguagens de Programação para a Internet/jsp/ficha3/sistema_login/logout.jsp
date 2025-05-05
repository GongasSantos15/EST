<%
    session.removeAttribute("user");
    session.removeAttribute("pass");
    session.removeAttribute("tipo_user");

    response.sendRedirect("index.jsp");
%>