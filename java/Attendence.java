import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.sql.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Attendance")

public class Attendence   extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
	  try {
		  HttpSession ssn=req.getSession(false);
		  String uname=  (String)ssn.getAttribute("uname");
	  
	            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ncit", "root", ""); 
	            LocalDate log = LocalDate.now();
	            java.sql.Date login = java.sql.Date.valueOf(log);

		  PreparedStatement ps=cn.prepareStatement("select *  from attendance where uname=? AND login_date = ?");
			 ps.setString(1, uname);
			 ps.setDate(2, login);
			 ResultSet rs=ps.executeQuery();
			 if(!rs.next())  { 
				 String attendance="Present";
				 
				String q="insert into attendance (uname,login_date,attendance) values (?,?,?)";
				 PreparedStatement pss=cn.prepareStatement(q);
				pss.setString(1,uname);
				pss.setDate(2, login);
				pss.setString(3,attendance);
				pss.executeUpdate();
				
				out.println("<html><body>");
	            out.println("<h2> Attendance</h2>");
	            out.println("<table border='1'><tr><th>Username</th><th>Login date</th><th>Attendance</th></tr>");
               
	                out.println("<tr><td>" + uname + "</td><td>" +login.toString()   + "</td><td>" + attendance + "</td></tr>");
	            
	                out.println("</table>");
		            out.println("</body></html>");  
	      
	            pss.close();
	            return;
	            
		          
	  }
			 PreparedStatement pt=cn.prepareStatement("select *  from attendance where uname=?");
			 pt.setString(1, uname);
			 ResultSet rd = pt.executeQuery();
			 out.println("<h3 style=\"color: red;\">Attendance already recorded for " + uname + " on " + login.toString() + ".</h3>");
			 out.println("<html><body>");
	            out.println("<h2> Attendance</h2>");
	            out.println("<table border='1'><tr><th>Username</th><th>Login date</th><th>Attendance</th></tr>");
             
			while(rd.next()){
			out.println("<tr><td>" + rd.getString(2) + "</td><td>" +rd.getDate(3).toString()   + "</td><td>" + rd.getString(4) + "</td></tr>");
			 }
			  out.println("</table>");
	            out.println("</body></html>");
	           rd.close();
	          cn.close();
	          pt.close();
	         
		  
	        } catch (SQLException e) {
	            
	            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
	        }
	  
	  
	}
	}




