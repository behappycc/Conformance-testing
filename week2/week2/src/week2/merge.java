package week2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.*;

import java.sql.*;


public class merge {

	Connection conn = null;
	Statement stmt = null;
	Multimap<String, List<String>> multiMap = ArrayListMultimap.create();
	public void connectDB(Connection conn)
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
	public void insert_DB(ResultSet rs,Connection conn)
	{
		int i = 0 ;
		try{
			while(rs.next())
			{
				
				PreparedStatement stmt2 =conn.prepareStatement("INSERT INTO [NTUTC].[dbo].[2G_TEST] VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				i++;
				if( i % 10000 == 0 ) System.out.println("10000 times insert");
				stmt2.setInt(1, rs.getInt("IDENTIFY_CATEGORY"));
				stmt2.setString(2,rs.getString("CATEGORY_TYPE"));
				stmt2.setString(3,rs.getString("INTERIMS_VERSION"));
				stmt2.setString(4,rs.getString("TABLE_SPEC"));
				stmt2.setString(5,rs.getString("SPEC_VERSION"));
				stmt2.setString(6,rs.getString("TABLE_ID"));
				stmt2.setString(7,rs.getString("TABLE_DESC"));
				stmt2.setString(8,rs.getString("PRIORITY"));
				stmt2.setString(9,rs.getString("OPTIONS"));
				stmt2.setString(10,rs.getString("RELEASE"));
				stmt2.setString(11,rs.getString("TESTBAND"));
				stmt2.setString(12,rs.getString("WORK_ITEM"));
				stmt2.setString(13,rs.getString("MEMO"));
				stmt2.setInt(14,rs.getInt("ORDER_ID"));
				stmt2.setString(15,rs.getString("NEW_PERSON"));
				stmt2.setDate(16,rs.getDate("NEW_DATE"));
				stmt2.setString(17,rs.getString("MODIFY_PERSON"));
				stmt2.setDate(18,rs.getDate("MODIFY_DATE"));
				stmt2.setString(19,rs.getString("RFT"));
				/*¡ô2g_main            ¡õ2g_detail*/
				stmt2.setInt(20,rs.getInt("IDENTIFY_CATEGORY_D"));
				stmt2.setString(21,rs.getString("RELEASE_D"));
				stmt2.setString(22,rs.getString("CATEGORY_FDD_TYPE"));
				stmt2.setString(23,rs.getString("CATEGORY"));
				stmt2.setString(24,rs.getString("VALID_PALTFORM"));
				stmt2.setString(25,rs.getString("FORMERLY_VALID_PLATFORM"));
				stmt2.setString(26,rs.getString("E_PLATFORM"));
				stmt2.setString(27,rs.getString("D_PLATFORM"));
				stmt2.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public merge()
	{
		int i = 0;
		try {
			connectDB(this.conn);
			String select = "SELECT a.IDENTIFY_CATEGORY ,[CATEGORY_TYPE] ,[INTERIMS_VERSION],[TABLE_SPEC],[SPEC_VERSION],[TABLE_ID],[TABLE_DESC] ,[PRIORITY] ,[OPTIONS],a.RELEASE,[TESTBAND],[WORK_ITEM],[MEMO],[ORDER_ID],[NEW_PERSON] ,[NEW_DATE],[MODIFY_PERSON],[MODIFY_DATE],[RFT],[IDENTIFY_CATEGORY_D],b.RELEASE as RELEASE_D,[CATEGORY_FDD_TYPE],[CATEGORY],[VALID_PALTFORM],[FORMERLY_VALID_PLATFORM],[E_PLATFORM],[D_PLATFORM]  FROM [NTUTC].[dbo].[CATEGORY_2GMAIN] as a inner join [NTUTC].[dbo].[CATEGORY_2GDETAIL] as b on a.IDENTIFY_CATEGORY = b.IDENTIFY_CATEGORY ";		
			ResultSet rs = stmt.executeQuery(select); 
			System.out.println("SELECT!");
			
			
			while(rs.next())
			{
				i++;
				List<String> val = new ArrayList<String>();
				val.add(rs.getString("IDENTIFY_CATEGORY"));
				val.add(rs.getString("CATEGORY_TYPE"));
				val.add(rs.getString("INTERIMS_VERSION"));
				val.add(rs.getString("TESTBAND"));
				val.add(rs.getString("CATEGORY_FDD_TYPE"));
				multiMap.put(rs.getString("TABLE_ID"),val);
			}
			this.conn.close();
			rs.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(i);
		i = 0;
		Set<String> keys = multiMap.keySet();
		
        // iterate through the key set and display key and values
        for (String key : keys) {
        	i++;
        
            if(key.equals("42.4.8.3.6"))
            {
            	Iterator<List<String>> s = multiMap.get(key).iterator();
            	while(s.hasNext())
            	{
            		List<String> temp = s.next();
            		
            		if(temp.get(2).equals("NAPRD03_v5.24_20151004"))
            		{
            			if(temp.get(3).equals("All")||temp.get(3).equals("all"))
            				System.out.println("42.4.8.3.6 = ALL ,"+temp.get(2)+","+temp.get(4));
            			else if(temp.get(3).equals("Single")||temp.get(3).equals("single"))
            				System.out.println("42.4.8.3.6 = single"+temp.get(4));
            		}
            	}
            }
         
        }
		System.out.println(i);
	}
	public static void main(String args[])
	{
		new merge();
	}

}
