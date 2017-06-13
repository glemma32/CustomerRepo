import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	public Database() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ArrayList<String> getCustomers(String last)
	{
		ArrayList<String> ids= new ArrayList<>();;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement("Select FirstName, LastName,CustID from customer where LastName= ?");
			stmt.setString(1, last);
			 
			rs=stmt.executeQuery();	
			ResultSetMetaData col=rs.getMetaData();
			for(int i=1;i<=col.getColumnCount();i++)
			{
				
				System.out.print(col.getColumnName(i) + "\t");
			}
			System.out.println();
			System.out.println();
					
			while(rs.next())
			{
							
				ids.add(rs.getString(3));
				for(int i=1;i<=col.getColumnCount();i++)
				{
						
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
			return ids;
			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ids;
		
		
	}
	
	public String[] getCustomer(int custID)
	{
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement("Select customer.CustID,Title,FirstName,LastName,StreetAddress,City,State,ZipCode,EmailAddress,position,company " 
					+"from ((customer join address using(addID)) join isEmployee using(CustID)) join companies using(compID) "
					+"where CustID=?");
			stmt.setInt(1, custID);
			 
			rs=stmt.executeQuery();	
			
					
			while(rs.next())
			{
				String[] vals=new String[11];
				
				for(int i=0;i<vals.length;i++)
				{
					vals[i]=rs.getString(i+1);
					
				}
				return vals;
				
				
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
	
	
	
	
	public void updateAddress(String street, String city,String state,String zip, int custID )
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "update address a join customer c using(addID) "
				+ "set a.StreetAddress = ?,a.City = ?,a.State = ?,a.ZipCode = ? "
						+ " where c.CustID= ?";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, street);
			stmt.setString(2, city);
			stmt.setString(3, state);
			stmt.setString(4, zip);
			stmt.setInt(5, custID);
			
			stmt.executeUpdate();
			
				 
				
				sql= "Select * from customer join address using(addID) where CustID= ?";
				
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, custID);
				
				rs=stmt.executeQuery();
				
				
				ResultSetMetaData col=rs.getMetaData();
				for(int i=1;i<=col.getColumnCount();i++)
				{
					
					System.out.print(col.getColumnName(i) + "\t");
				}
				System.out.println();
				System.out.println();
				while(rs.next()){
					
					
					
					for(int i=1;i<=col.getColumnCount();i++)
					{
						
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println();
				}
			 
		}
			catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		
	}
	public void addCustomer(String title, String FirstName, String LastName, String email)
	{
		Connection con=null;
		PreparedStatement stmt=null;
		
		String sql = "Insert into customer(Title, FirstName, LastName,EmailAddress) values(?,?,?,?)";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, FirstName);
			stmt.setString(3, LastName);
			stmt.setString(4, email);
			
			stmt.execute();
				 
				
			
			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void addAddress(String StreetAddress, String City, String State, String ZIP)
	{
		Connection con=null;
		PreparedStatement stmt=null;
		
		String sql = "Insert into address(StreetAddress,City,State,ZipCode) values(?,?,?,?)";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
	                            + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, StreetAddress);
			stmt.setString(2, City);
			stmt.setString(3, State);
			stmt.setString(4, ZIP);
			
			stmt.execute();
				 
			
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try {
				
				stmt.close();
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	
	}
	public void addEmployee(int CustID, String position, int compID)
	{
		Connection con=null;
		PreparedStatement stmt=null;
		
		String sql = "Insert into isEmployee(CustID,compID,position) values(?,?,?)";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,CustID);
			stmt.setInt(2, compID);
			stmt.setString(3, position);
			
			
			stmt.execute();
				 
				
			
			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void addCompany(String company)
	{
		Connection con=null;
		PreparedStatement stmt=null;
		
		String sql = "Insert into companies(company) values(?)";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setString(1,company);
			
			
			
			stmt.execute();
				 
				
			
			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public String getCustomerID()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select custID from customer";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			return rs.getString(1);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
		
		
		
		
	}
	public String getAddressID()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select addID from address";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			return rs.getString(1);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	
	}
	
	
	public String getCompanyID()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select compID from companies";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			return rs.getString(1);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	
	
		
	public void updateCustomer(int addID,int CustID)
	{
		Connection con = null;
		PreparedStatement stmt = null;
		
		String sql = "update customer c "
				+ "set c.addID=? "
						+ " where c.CustID= ?";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, addID);
			stmt.setInt(2, CustID);
			
			
			stmt.executeUpdate();
			
				 
				
				
			 
		}
			catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void getTotals()
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "Select  count(distinct CustID) as 'Number of Customers', count(distinct State) as 'Number of States' , count(distinct compID) as 'Number of Companies'"
				+"from (address join customer using(addID)) join isEmployee using(CustID)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?autoReconnect=true&useSSL=false&"
                                + "user=root&password=password");
            
            
            
            
            
			stmt = con.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			
			
			ResultSetMetaData col=rs.getMetaData();
			for(int i=1;i<=col.getColumnCount();i++)
			{
				
				System.out.print(col.getColumnName(i) + "\t");
			}
			System.out.println();
			System.out.println();
			while(rs.next()){
				
				
				
				for(int i=1;i<=col.getColumnCount();i++)
				{
					
					System.out.print(rs.getString(i) + "\t\t\t\t");
				}
				System.out.println();
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	
	}
	
	
	

}
