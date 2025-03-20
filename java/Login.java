import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Login")

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		
		String action = req.getParameter("action");

		PrintWriter out=res.getWriter();
		


		if("change".equals(action)) 
		{
			RequestDispatcher rd=req.getRequestDispatcher("/forget.html");
			rd.forward(req, res);
		}
		
		else if("create".equals(action)) 
		{
			
			RequestDispatcher rd=req.getRequestDispatcher("/Register.html");
			rd.forward(req, res);
	
			
		
	}	
		if("login".equals(action))  {
		
		try {
			if(Database.insert(uname,pwd)) {
			HttpSession ssn=req.getSession();
			ssn.setAttribute("uname", uname);
			res.sendRedirect("dashboard");
			}
			
		
		else {
			out.println("<p style=\"color: red;\"> Wrong username or Password</p>");
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			rd.include(req, res);
			
		}
	} catch (Exception e) {
        out.println("<p style=\"color: red;\">An error occurred: " + e.getMessage() + "</p>");
    } finally {
        out.close();
    }
		}


}
}

