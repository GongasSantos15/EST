<%@ page contentType="text/html; charset=UTF-8" language="java" %>~
<%@ page language="java" import="java.sql.*" %>
<%
    // Variáveis de Conexão à BD
    Connection conn = null;
    PreparedStatement psSelectRecord = null;
    ResultSet result = null;
    String sqlSelectRecord = null;

    // Informações da BD
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    String jdbcURL="jdbc:mysql://localhost:3306/ficha4"; // BD "ficha4"
    conn = DriverManager.getConnection(jdbcURL,"root", "");
    

    // Criar as variáveis neecssárias
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");

    // Query SQL
    sqlSelectRecord = "SELECT * FROM user WHERE user = ? AND pass = ?"; // tabela "user"
    psSelectRecord = conn.prepareStatement(sqlSelectRecord);

    // Obter os dados da tabela
    psSelectRecord.setString(1, user);
    psSelectRecord.setString(2, pass);

    result = psSelectRecord.executeQuery();

    // Obter os dados da tabela
    if (result.next()) {

        // Criar as variáveis de sessão
        session.setAttribute("user", user);
        session.setAttribute("pass", pass);
        session.setAttribute("tipo_user", 1);
        
        // Redirecionar o utilizador para a página menu_admin
        response.setHeader("Refresh", "2; URL=menu_admin.jsp");
    } else {
        // Se o utilizador não for encontrado, redirecionar para a página index
        out.println ("User não encontrado! Vai ser redirecionado para a página principal...");
        response.setHeader("Refresh", "2; URL=index.jsp");
    }

    conn.close();
%>