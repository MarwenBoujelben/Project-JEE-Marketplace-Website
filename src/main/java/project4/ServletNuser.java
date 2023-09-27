package project4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 * Servlet implementation class ServletNew
 */
public class ServletNuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		
		//String htmlRespone = "<html>";
	    //htmlRespone += "<h2>Your username is:<br/>";      
	    //htmlRespone += "Your password is:</h2>";    
	    //htmlRespone += "</html>";
	    //out.println(htmlRespone);
	    
	    
	    
		String nom=request.getParameter("nom");
		String phone=request.getParameter("phone");
		String username=request.getParameter("username");
		String passw=request.getParameter("password");
		//if((nom!=null)&&(!nom.isEmpty())&&(phone!=null)&&(!phone.isEmpty())&&(username!=null)&&(!username.isEmpty())&&(passw!=null)&&(!passw.isEmpty())){
			
		try
		{
		         Class.forName("com.mysql.jdbc.Driver");
		           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin", "root", "");
		           Statement st=conn.createStatement();
		           int i=st.executeUpdate("insert into user(username,password,nom,phonenumber) values('"+username+"','"+passw+"','"+nom+"','"+phone+"')");
		        out.println("<h2 style='color:green;text-align:center;margin-top:50px'>Congratulations ! User Successfully Created!</h2>");
				//response.sendRedirect("index.jsp");
		        response.setHeader("Refresh", "3;url=login.jsp");

		        }
		
		        catch(Exception e)
		        {
		        System.out.print(e);
		        e.printStackTrace();
		        }
		//}

		//else {
			//response.sendRedirect("signup.jsp");

		//}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
