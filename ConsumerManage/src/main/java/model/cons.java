package model;
import java.sql.*; 

public class cons {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //DB connection  
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project","root",""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	 public String insertItem(String name, String address, String phone,String email, String description) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into consumer(`cid`,`name`,`address`,`phone`,`email`,`description`)"
	 + " values (?, ?, ?, ?, ?,?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, address); 
	 preparedStmt.setString(4, phone); 
	 preparedStmt.setString(5, email);
	 preparedStmt.setString(6, description);
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newItems = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
	 newItems + "\"}"; 
	 

	 
	 
	 } 
	 catch (Exception e) 
	 
		 { 
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 

	
	 return output; 
	 } 
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the  table to be displayed
	 output = "<table border='1'><tr><th>Name</th><th>Address</th>" +
	 "<th>Phone</th>" + 
	 "<th>Email</th>" +
	 "<th>Description</th>" +
	 "<th>Update</th><th>Delete</th></tr>"; 
	 
	 String query = "select * from consumer"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String cid = Integer.toString(rs.getInt("cid")); 
	 String name = rs.getString("name"); 
	 String address = rs.getString("address"); 
	 String phone =rs.getString("phone"); 
	 String email = rs.getString("email");
	 String description = rs.getString("description");
	 
	 output += "<tr><td><input id='hidcidUpdate' name='hidcidUpdate' type='hidden' value='" + cid + "'>"
			 + name + "</td>"; 
			 output += "<td>" + address + "</td>"; 
			 output += "<td>" + phone + "</td>"; 
			 output += "<td>" + email + "</td>"; 
			 output += "<td>" + description + "</td>";
			 // buttons
	 output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-warning' data-cid='"+cid+"'>"
			 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cid='"+cid+"'>" + "</td></tr>";
			 } 
	 con.close(); 
	 // Complete the 
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updateItem(String cid, String name, String address, String phone, String email, String description)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE consumer SET name=?,address=?,phone=?,email=?,description=? WHERE cid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, address); 
		 preparedStmt.setString(3, phone); 
		 preparedStmt.setString(4, email); 
		 preparedStmt.setString(5, description); 
		 preparedStmt.setInt(6, Integer.parseInt(cid)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 

		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 
					 System.err.println(e.getMessage()); 

		 } 
		 return output; 
		 } 
		public String deleteItem(String cid) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from consumer where cid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(cid)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
  
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		 
}
