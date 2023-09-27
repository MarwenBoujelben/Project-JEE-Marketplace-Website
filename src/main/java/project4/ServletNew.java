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
import java.sql.*;

/**
 * Servlet implementation class ServletNew
 */
public class ServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		
	    
	    HttpSession session =request.getSession(true);
	    String sessionUsername=(String) session.getAttribute("username");
	    out.println("username from session: "+sessionUsername);
	    
		String designation=request.getParameter("designation");
		String categorie=request.getParameter("categorie");
		String description=request.getParameter("description");
		String prixU=request.getParameter("prixU");
		String quantite=request.getParameter("quantite");
		String image=request.getParameter("image");
		out.println(description);
		try
		{
		         Class.forName("com.mysql.jdbc.Driver");
		           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin", "root", "");
		           Statement st=conn.createStatement();
		           int i=st.executeUpdate("insert into productsjee(id_username,designation,categorie,description,prix,quantite,image) values('"+sessionUsername+"','"+designation+"','"+categorie+"','"+description+"','"+prixU+"','"+quantite+"','"+image+"')");
		           if(i>0) {
						response.sendRedirect("indexProds.jsp");

		           }
		        out.println("Data is successfully inserted!");
		        out.println("Designation: "+designation);
		        out.println("Categorie: "+categorie);
		        out.println("Description: "+description);

		        out.println("Prix Unitaire: "+prixU+"DT");
		        out.println("Quantite: "+quantite+" pieces");
		        out.println("Image: "+image);

		        }
		        catch(Exception e)
		        {
		        System.out.print(e);
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
