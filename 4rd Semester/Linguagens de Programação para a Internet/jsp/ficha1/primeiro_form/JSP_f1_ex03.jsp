<!DOCTYPE html>
<head>
    <meta charset="utf-8">
</head>
<body>
    <%@page	contentType="text/html"	pageEncoding="UTF-8"%>
    <%
        String nome = request.getParameter("name");
        int idade = Integer.parseInt(request.getParameter("age"));

        out.println(nome + " " + ((idade >= 18) ? "pode votar" : "não pode votar" + "!" ));
    %>
</body>
</html>