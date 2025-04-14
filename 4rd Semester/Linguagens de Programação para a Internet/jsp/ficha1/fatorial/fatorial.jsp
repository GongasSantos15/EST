<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fatorial n</title>
</head>
<body>
    <%
        int num = Integer.parseInt(request.getParameter("number"));
        out.println(fatorial(num));
    %>

    <%!
    public int fatorial(int num) {
        int fatorial = 1;

        for (int i = 1; i <= num; i++) {
            fatorial *= i;
        }
        return fatorial;
    }
    %>
</body>
</html>
