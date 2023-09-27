<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>

<%
String sessionUsername=(String) session.getAttribute("username");
if(sessionUsername==null){
	response.sendRedirect("login.jsp");

}
//out.println("username from session: "+sessionUsername);

//out.println("username from session: "+sessionUsername);



String id = request.getParameter("userid");
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
%>
<style>
*{
}
.testemonial{
    padding-top:150px;
}
.testemonial .col-3{
    text-align:center;
    padding:40px 20px;
    box-shadow: 0 0 20px 0px rgba(0,0,0,0.1);
    cursor:pointer;
    transition:transform 0.5s;
    height:400px;
    
}
.testemonial .col-3 .imgtest{
    width:200px;
    height:150px;
    margin-top:20px;
}
.col-3:hover{
    transform: translateY(-10px);
}
.fa.fa-quote-left{
    color:#ff523b;
    font-size:35px;
}
.row{
    display:flex;
    align-items:center;
    flex-wrap: wrap;
    justify-content: space-around;
    flex-basis:30%;
    
}
.small-container{
    max-width:1080px;
    margin:auto;
    padding-left:25px;
    padding-right:25px;
}
.col-3{
    flex-basis:20%;
    min-width:250px;
    margin-bottom:30px;
    margin-right:30px;
    
    background-color:white;
    border-radius:10px;
}
.col-3 .imgtest{
    width:100%;
    margin-left:0px;
}
.col-3 .rating{
    text-align:center;
}
.col-3 h4{
    text-align:center;
    color:black;
}
    </style>
    
    
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<!--  <a href="newProd.jsp"><button>Click Me!</button></a>-->
 <nav class="navbar">
            <div class="navbar-container container">
                <input type="checkbox" name="" id="">
                <div class="hamburger-lines">
                    <span class="line line1"></span>
                    <span class="line line2"></span>
                    <span class="line line3"></span>
                </div>
                <ul class="menu-items">
                    <li><a href="indexProds.jsp">Home</a></li>
                    
                    <li><a href="newProd.jsp">New Prod</a></li>
                    <li><a href="ServletLogout">Log Out</a></li>

                </ul>
                <h1 class="logo"><h2><%=sessionUsername%></h1>
            </div>
        </nav>
<div class="testemonial">
    <div class="small-container">
        <div class="row">
        <%
        try{
			connection = DriverManager.getConnection(connectionUrl+database, userid, password);
			statement=connection.createStatement();
			String sql ="select * from productsjee where id_username=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, sessionUsername);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			%>
            <div class="col-3">
                <!--<i class="fa fa-quote-left"></i>-->
                <img class="imgtest"src="img/<%=rs.getString("image") %>"/>
                    <h4><%=rs.getString("designation") %></h4><br>
                    <h3 style="color:green"><%=rs.getString("prix") %>DT</h3><br>
                    
                    
                <div class="rating">
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star-o"></i>
                    </div>
                    <h5><%=rs.getString("description") %></h5><br>
                    <a href="update.jsp?id=<%= rs.getString("id") %>"><img src="img/modify.png"
					width="20" height="30"></a>&nbsp &nbsp<a href="delete.jsp?id=<%= rs.getString("id") %>"><img src="img/delete.png"
					width="20" height="30"></a>
                 
            </div>
           <%}
			}
		catch (Exception e) {
	e.printStackTrace();
	out.print("catch");
	} %> 
        </div>
    </div>
</div>

</body>

</html>
<style>

*,
*::after,
*::before{
    box-sizing: border-box;
    padding: 0;
    margin: 0;
}
body{
background-color:#EDF3F3;}
.html{
    font-size: 62.5%;
}

.navbar input[type="checkbox"],
.navbar .hamburger-lines{
    display: none;
}

.container{
    max-width: 1200px;
    width: 90%;
    margin: auto;
}

.navbar{
    box-shadow: 0px 5px 10px 0px #aaa;
    position: fixed;
    width: 100%;
    background: #fff;
    color: #000;
    opacity: 0.85;
    z-index: 100;
}

.navbar-container{
    display: flex;
    justify-content: space-between;
    height: 64px;
    align-items: center;
}

.menu-items{
    order: 2;
    display: flex;
}
.logo{
    order: 1;
    font-size: 2.3rem;
}

.menu-items li{
    list-style: none;
    margin-left: 1.5rem;
    font-size: 1.3rem;
}

.navbar a{
    color: #444;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease-in-out;
}

.navbar a:hover{
    color: #117964;
}

@media (max-width: 768px){
    .navbar{
        opacity: 0.95;
    }

    .navbar-container input[type="checkbox"],
    .navbar-container .hamburger-lines{
        display: block;
    }

    .navbar-container{
        display: block;
        position: relative;
        height: 64px;
    }

    .navbar-container input[type="checkbox"]{
        position: absolute;
        display: block;
        height: 32px;
        width: 30px;
        top: 20px;
        left: 20px;
        z-index: 5;
        opacity: 0;
        cursor: pointer;
    }

    .navbar-container .hamburger-lines{
        display: block;
        height: 28px;
        width: 35px;
        position: absolute;
        top: 20px;
        left: 20px;
        z-index: 2;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .navbar-container .hamburger-lines .line{
        display: block;
        height: 4px;
        width: 100%;
        border-radius: 10px;
        background: #333;
    }
    
    .navbar-container .hamburger-lines .line1{
        transform-origin: 0% 0%;
        transition: transform 0.3s ease-in-out;
    }

    .navbar-container .hamburger-lines .line2{
        transition: transform 0.2s ease-in-out;
    }

    .navbar-container .hamburger-lines .line3{
        transform-origin: 0% 100%;
        transition: transform 0.3s ease-in-out;
    }

    .navbar .menu-items{
        padding-top: 100px;
        background: #fff;
        height: 100vh;
        max-width: 300px;
        transform: translate(-150%);
        display: flex;
        flex-direction: column;
        margin-left: -40px;
        padding-left: 40px;
        transition: transform 0.5s ease-in-out;
        box-shadow:  5px 0px 10px 0px #aaa;
        overflow: scroll;
    }

    .navbar .menu-items li{
        margin-bottom: 1.8rem;
        font-size: 1.1rem;
        font-weight: 500;
    }

    .logo{
        position: absolute;
        top: 10px;
        right: 15px;
        font-size: 2.5rem;
    }

    .navbar-container input[type="checkbox"]:checked ~ .menu-items{
        transform: translateX(0);
    }

    .navbar-container input[type="checkbox"]:checked ~ .hamburger-lines .line1{
        transform: rotate(45deg);
    }

    .navbar-container input[type="checkbox"]:checked ~ .hamburger-lines .line2{
        transform: scaleY(0);
    }

    .navbar-container input[type="checkbox"]:checked ~ .hamburger-lines .line3{
        transform: rotate(-45deg);
    }

}

@media (max-width: 500px){
    .navbar-container input[type="checkbox"]:checked ~ .logo{
        display: none;
    }
}


</style>

