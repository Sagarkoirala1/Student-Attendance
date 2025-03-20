import  jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Logout")

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html");
 Cookie ck=new Cookie("user","");
 ck.setMaxAge(0);
 res.addCookie(ck);
 PrintWriter out= res.getWriter();
 out.println("<p> You Have Logged Out </p>");

}
}