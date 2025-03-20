import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


import jakarta.servlet.annotation.*;
@WebServlet("/CreateAccount")

public class CreateAccount  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String cpwd=req.getParameter("cpwd");
	    String Gender=req.getParameter("Gender");
		String mobile=req.getParameter("mobile");
		
		 
	    if (cpwd == null) {
	        res.getWriter().println("<p style='color: red;'>Confirm Password cannot be empty.</p>");
	        return;
	    }

	    else if(!cpwd.equals(pwd))
			 {
				 res.getWriter().println("<p style='color: red;'>Password Doesnot Match.</p>"); 
			return;
			 }
			 try{
				 boolean b=ins(uname,pwd,Gender,mobile);
			 if (b) {
				 
		 
		     res.getWriter().println("<p style='color: black'>Account created successfully.</p>");
		 
			 }   else{
             res.getWriter().println("<p style='color: red;'>Username already exists.</p>");
		 }
		 
		
			 
	}catch(Exception e) {
		 res.getWriter().println("<p style='color: red;'>"+e.getMessage()+"</p>");
	}
		
	}
	
		public static boolean ins(String uname,String pwd, String Gender,String mobile)throws  ClassNotFoundException,SQLException{
			try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			 String url=("jdbc:mysql://localhost:3306/Ncit");
					 String user="root";
					String pd="";
			 Connection cn=DriverManager.getConnection(url,user,pd);
			 PreparedStatement ps=cn.prepareStatement("select * from databaseinfo where uname=?");
			 ps.setString(1, uname);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next()) {
				 return false;
			 }
			 else {
			 ps=cn.prepareStatement("insert into databaseinfo(uname, pwd,Gender,mobile) values(?,?,?,?) ");
			 ps.setString(1, uname);
			 ps.setString(2, pwd);
	         ps.setString(3, Gender);
			 ps.setString(4, mobile);
			 ps.executeUpdate();
			 ps.close();
			 cn.close();
			 rs.close();
			 return true;
			 
			 
			
			 }
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    
		        return false;
		    } catch (SQLException e) {
		        e.printStackTrace();
		      
		        return false;

		}
			
}
}
	
