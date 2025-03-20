import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;
@WebServlet("/dashboard")

public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
	
		PrintWriter out=res.getWriter();
 HttpSession ssn=req.getSession(false);
		if(ssn==null) {
			out.println("<p>User not found</p>");
			RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
			rd.include(req, res);
			
		}
		out.println("<p> Hello "+ssn.getAttribute("uname")+"</p>");
out.println("<form action ='Attendance'><input type='submit' value='Attendance'></form>");
out.println("<form action ='Result'><input type='submit' value='Result'></form>");
out.println("<form action ='Logout'><input type='submit' value='Logout'></form>");


	}
}
