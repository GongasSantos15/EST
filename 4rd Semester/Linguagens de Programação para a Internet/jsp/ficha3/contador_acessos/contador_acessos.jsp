<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    int contador = 0;

    // Se não existir uma variável de sessão, criá-la igual a 1
    if (session.getAttribute("contador") == null) {
        session.setAttribute("contador", 1);
    } else {
        // Se já existe incrementá-la
        contador = (Integer) session.getAttribute("contador");
        session.setAttribute("contador", contador+=1);
    }

    // Apresentar o número de acessos
    out.println("Número de acessos: " + session.getAttribute("contador"));
%>