import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Result")

public class Result  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
		
	  try {
		  HttpSession ssn=req.getSession(false);
		  String uname=  (String)ssn.getAttribute("uname");
	  
	            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ncit", "root", ""); 
		  PreparedStatement ps=cn.prepareStatement("select * from result where uname=?");
			 ps.setString(1, uname);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next()) {
				 PrintWriter out = res.getWriter();
	            out.println("<html><body>");
	            out.println("<h2>Student Results</h2>");
	            out.println("<table border='1'><tr><th>Username</th><th>Semester</th><th>Calculus</th><th>Java</th><th>SEF</th><th>INS</th><th>DSA</th><th>PNS</th><th>Total</th><th>Remarks</th></tr>");
               
	                String semester = rs.getString("semester");
	                int calculus = rs.getInt("calculus");
	                int java = rs.getInt("java");
	                int sef = rs.getInt("SEF");
	                int ins = rs.getInt("INS");
	                int dsa = rs.getInt("DSA");
	                int pns = rs.getInt("PNS");
	              
	              int total=java+sef+ins+dsa+pns;
	              String remarks = (total > 400) ? "Pass" : "Fail";
	              String q = "UPDATE result SET Total=?, Remarks=? WHERE uname=?";
	             PreparedStatement pss = cn.prepareStatement(q);
	              pss.setInt(1, total);
	              pss.setString(2, remarks);
	              pss.setString(3, uname);
	              pss.executeUpdate();

	                out.println("<tr><td>" + uname + "</td><td>" + semester + "</td><td>" + calculus + "</td><td>" + java + "</td><td>" + sef + "</td><td>" + ins + "</td><td>" + dsa + "</td><td>" + pns + "</td><td>" +total+"</td><td>"+remarks+"</td></tr>");
	            

	            out.println("</table>");
	            out.println("</body></html>");
	  }
			 
	           
			 
			 rs.close();
	          cn.close();
		  
	        } catch (SQLException e) {
	            
	            res.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
	        }
	  
		 
	}
	}
