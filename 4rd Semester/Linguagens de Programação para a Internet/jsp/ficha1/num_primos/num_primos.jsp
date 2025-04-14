<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verificação de Números Primos</title>
</head>
<body>
    <%
        int num = Integer.parseInt(request.getParameter("number"));
        int contador = 0;

        for (int i = 1; i < num; i++) {
            if (ePrimo(i)) {
                contador++;
                out.println(i + " é primo!<br>");
            }
        }
        out.println("Total = " + contador + " números primos menores que " + num);
    %>

    <%! 
        int contador = 0;
        public boolean ePrimo(int num) {
            if (num <= 1)
                return false;
            else if (num == 2)
                return true;
            else {
                for (int i = 2; i < num; i++) {
                    if (num % i == 0)
                        return false;
                }
                return true;
            }
        }
    %>
</body>
</html>
