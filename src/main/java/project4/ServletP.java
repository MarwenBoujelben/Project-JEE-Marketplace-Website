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
 * Servlet implementation class ServletP
 */
public class ServletP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.println("Hello world");
		
		HttpSession session=request.getSession(true);
		String sessionUsername=(String) session.getValue("username");
		out.println("username from session: "+sessionUsername);

		
		
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/magasin";
		String user = "root";
		String psw = "";

		
		String id=request.getParameter("id");
		String newDesignation=request.getParameter("designation");
		String newCategorie=request.getParameter("categorie");
		String newDescription=request.getParameter("description");
		String newPrix=request.getParameter("prixU");
		String newQuantite=request.getParameter("quantite");
		String newImage=request.getParameter("image");
		out.println("id="+id);

		
		
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
		Class.forName(driverName);
		con = DriverManager.getConnection(url,user,psw);
		String sql="Update productsjee set id=?,id_username=?,designation=?,categorie=?,description=?,prix=?,quantite=?,image=? where id="+id;
		ps = con.prepareStatement(sql);
		ps.setString(1,id);
		ps.setString(2,sessionUsername);
		ps.setString(3, newDesignation);
		ps.setString(4, newCategorie);
		ps.setString(5, newDescription);
		ps.setString(6, newPrix);
		ps.setString(7, newQuantite);
		ps.setString(8, newImage);

		int i = ps.executeUpdate();
		if(i > 0)
		{
			response.sendRedirect("indexProds.jsp");
		out.print("Record Updated Successfully");
		
		out.println("New Record:");
        out.println("Designation: "+newDesignation);
        out.println("Categorie: "+newCategorie);
        out.println("Prix Unitaire: "+newPrix+"DT");
        out.println("Quantite: "+newQuantite+" pieces");
        out.println("Image: "+newImage);
    	
        
        out.println(" <HTML>\n" +
                "<HEAD><TITLE>Hello WWW</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<H1>Hello WWW</H1>\n" +
                "<h1>"+newDesignation+"</h1>"+
                "</BODY></HTML>");
		
		
		
		}
		else
		{
		out.print("There is a problem in updating Record.");
		} 
		}
		catch(SQLException sql)
		{
		request.setAttribute("error", sql);
		out.println(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
