package band;

import java.sql.*;

public class merge {

	Connection conn = null;
	Statement stmt = null;

	public void connectDB()
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.conn = DriverManager.getConnection("jdbc:sqlserver://140.112.42.144\\SQLEXPRESS:1433;user=sa;password=bl618");
			this.stmt = this.conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connected!");
	}
	public void insertDB(ResultSet rs)
	{
		try{
			while(rs.next())
			{	
				PreparedStatement stmt2 =conn.prepareStatement("INSERT INTO [NTUTC].[dbo].[test] VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
				stmt2.setInt	(1,rs.getInt	("IDENTIFY_CATEGORY"));
				stmt2.setString	(2,rs.getString	("CATEGORY_TYPE"));
				stmt2.setString	(3,rs.getString	("INTERIMS_VERSION"));
				stmt2.setString	(4,rs.getString	("TABLE_SPEC"));
				stmt2.setString	(5,rs.getString	("SPEC_VERSION"));
				stmt2.setString	(6,rs.getString	("TABLE_ID"));
				stmt2.setString	(7,rs.getString	("TABLE_DESC"));
				stmt2.setString	(8,rs.getString	("PRIORITY"));
				stmt2.setString	(9,rs.getString	("OPTIONS"));
				stmt2.setString	(10,rs.getString("RELEASE"));
				stmt2.setString	(11,rs.getString("TESTBAND"));
				stmt2.setString	(12,rs.getString("WORK_ITEM"));
				stmt2.setString	(13,rs.getString("MEMO"));
				stmt2.setInt	(14,rs.getInt	("ORDER_ID"));
				stmt2.setString	(15,rs.getString("NEW_PERSON"));
				stmt2.setDate	(16,rs.getDate	("NEW_DATE"));
				stmt2.setString	(17,rs.getString("MODIFY_PERSON"));
				stmt2.setDate	(18,rs.getDate	("MODIFY_DATE"));
				stmt2.setString	(19,rs.getString("RFT"));
				/*��2g_main            ��2g_detail*/
				stmt2.setInt	(20,rs.getInt	("IDENTIFY_CATEGORY_D"));
				stmt2.setString	(21,rs.getString("RELEASE_D"));
				stmt2.setString	(22,rs.getString("CATEGORY_FDD_TYPE"));
				stmt2.setString	(23,rs.getString("CATEGORY"));
				stmt2.setString	(24,rs.getString("VALID_PALTFORM"));
				stmt2.setString	(25,rs.getString("FORMERLY_VALID_PLATFORM"));
				stmt2.setString	(26,rs.getString("E_PLATFORM"));
				stmt2.setString	(27,rs.getString("D_PLATFORM"));
				stmt2.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet Query(String q)
	{
		ResultSet rs = null;
		try
		{
			System.out.println(q);
			rs = stmt.executeQuery(q); 
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		return rs;
	}
	public void closeConn()
	{
		try
		{
			conn.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public merge()
	{
		
	}
}