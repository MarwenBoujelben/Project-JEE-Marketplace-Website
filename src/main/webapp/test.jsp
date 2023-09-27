<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%! String driverName = "com.mysql.jdbc.Driver";%>
<%!String url = "jdbc:mysql://localhost:3306/magasin";%>
<%!String user = "root";%>
<%!String psw = "";%>
<%



String newDesignation=request.getParameter("designation");
String newCategorie=request.getParameter("categorie");
String newPrix=request.getParameter("prixU");
String newQuantite=request.getParameter("quantite");
String newImage=request.getParameter("image");


Connection con = null;
PreparedStatement ps = null;
try
{
Class.forName(driverName);
con = DriverManager.getConnection(url,user,psw);
String sql="Update jeeproducts set id=?,designation=?,categorie=?,prix=?,qte=?,image=? where id=1";
ps = con.prepareStatement(sql);
ps.setString(1,"1");
ps.setString(2, newDesignation);
ps.setString(3, newCategorie);
ps.setString(4, newPrix);
ps.setString(5, newQuantite);
ps.setString(6, newImage);

int i = ps.executeUpdate();
if(i > 0)
{
out.print("Record Updated Successfully");
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
}
%>

<a href="index.jsp"><button name="btnBack" >Return</button></a>
