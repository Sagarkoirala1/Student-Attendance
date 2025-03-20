import java.sql.*; 
public class Database {
	public static boolean insert(String uname,String pwd)throws ClassNotFoundException,SQLException{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 String url=("jdbc:mysql://localhost:3306/Ncit");
				 String user="root";
				String pd="";
		 Connection cn=DriverManager.getConnection(url,user,pd);
		 PreparedStatement ps=cn.prepareStatement("select * from databaseinfo where uname =? AND pwd =?");
		 ps.setString(1, uname);
		 ps.setString(2,pwd);
		 
		 ResultSet rs=ps.executeQuery();
		 if(rs.next()) {
			
			
				 return true;
			 }
		 
		 
		 else {
			 return false;
		 }
	}

}
