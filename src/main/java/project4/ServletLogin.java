package project4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.* ;



/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");

		
		PrintWriter out=response.getWriter();
		
		
		
		String driver = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/";
		String database = "magasin";
		String userid = "root";
		String password = "";
		try {
		Class.forName(driver);
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;


		
		
		String username=request.getParameter("username");
		String passw=request.getParameter("password");

		

		
		try{
			connection = DriverManager.getConnection(connectionUrl+database, userid, password);
			statement=connection.createStatement();
			String sql ="select * from user";
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()){
				String usernameDB=resultSet.getString("username");
				String passwordDB=resultSet.getString("password");
			

				if((usernameDB.equals(username))&&(passwordDB.equals(passw))) {
					HttpSession session=request.getSession(true);
					session.setAttribute("username", username);
					String sql2 ="select * from productsjee";
					response.sendRedirect("ServletTest");

					

				}
				
			}
			

			out.println("<h2 style=\"text-align: center;color:red;margin-top:50px\">Incorrect Username or password</h2><br>");
			out.println("<div style=\"text-align: center\">"
					+ "<form action='login.jsp' method='get'><button name='retry' >Retry</button></form></div><br>");
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
