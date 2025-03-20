import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


import jakarta.servlet.annotation.*;
@WebServlet("/Forget")

public class Forget   extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String cpwd=req.getParameter("cpwd");
	    String mobile=req.getParameter("mobile");
	  
	    PrintWriter out=res.getWriter();
	    
		if(pwd!=null&&!pwd.equals(cpwd)) {
		  	out. println("<p style='color: red;'> Password and Confirm Password doesnot Match.</p>");
					RequestDispatcher rd=req.getRequestDispatcher("/forget.html");
					rd.include(req, res);
		     return;
		}
	    
	    	

	    try {
	    boolean b= forg(uname,pwd,cpwd,mobile);
	    if(b) {
	    	out.println("<p style='color: black;'> Password Changed Successfully.</p>");
			
	    	
	    	return;
		}
	    else{
	    out.println("<p style='color: red;'> Username or Mobile no doesnot Match.</p>");
	    RequestDispatcher rd=req.getRequestDispatcher("/forget.html");
		rd.include(req, res);
		return;
	    }
	    }catch(Exception e) {
			 res.getWriter().println("<p style='color: red;'>"+e.getMessage()+"</p>");
		}
	}
			
	    public static boolean forg(String uname,String pwd, String cpwd,String mobile)throws ClassNotFoundException,SQLException{
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			 String url=("jdbc:mysql://localhost:3306/Ncit");
					 String user="root";
					String pd="";
			 Connection cn=DriverManager.getConnection(url,user,pd);
			 PreparedStatement ps=cn.prepareStatement("select * from databaseinfo where uname=? AND mobile=?");
			 ps.setString(1, uname);
			 ps.setString(2, mobile);
			 
			 ResultSet rs=ps.executeQuery();
			 if(rs.next()) {
				Statement st=cn.createStatement();
				String q = "UPDATE databaseinfo SET pwd='" + pwd + "' WHERE uname='" + uname + "'";
				 		 st.executeUpdate(q);
				 		  
				 		 return true;
		 }
			 else {
				 return false;
				 
				 
			 
			  }
		 	
          
	  
	    }
		}
		

	


		
		


