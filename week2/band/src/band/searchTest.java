package band;

import java.sql.ResultSet;
import java.util.*;

public class searchTest {
	
	String testQuery;
	String diffQuery;
	
	public void search2G()
	{
		 testQuery = "select  IDENTIFY_CATEGORY ,CATEGORY_TYPE ,TABLE_ID from [NTUTC].[dbo].[test] where testband = '' ";
		 diffQuery = "select * from [NTUTC].[dbo].[TC_DIFF]";
	}
	public void search3G()
	{
		 testQuery = "select IDENTIFY_CATEGORY ,CATEGORY_TYPE ,TESTBAND from [NTUTC].[dbo].[test3G] where testband = '' ";
		 diffQuery = "select * from [NTUTC].[dbo].[TC_DIFF_3G]";
		 
	}
	public void searchLTE()
	{
		 testQuery = "select IDENTIFY_CATEGORY,CATEGORY_TYPE,TESTBAND from [NTUTC].[dbo].[testLTE] where testband = '' ";
		 diffQuery = "select * from [NTUTC].[dbo].[TC_DIFF_LTE]";
	}
	
	public searchTest(int test)
	{
		merge db = new merge();
		merge diff = new merge();
		ArrayList<String> diffList = new ArrayList<String>();
		ArrayList<String> meet = new ArrayList<String>();
		db.connectDB();
		diff.connectDB(0);
		
		switch(test)
		{
			case 0:
				search2G();
				break;
			case 1:
				search3G();
				break;
			case 2:
				searchLTE();
				break;
		}
		
		ResultSet r = db.Query(testQuery);
		ResultSet d = diff.Query(diffQuery);
		try
		{
			while(d.next())
			{
				if(d.getString("STANDARD").equals("GCF"))
				{
					diffList.add(d.getString("ETSI_TC"));
				}
			}
			d.beforeFirst();
			while(d.next())
			{
				if(d.getString("STANDARD").equals("NAPRD"))
				{
					if(diffList.contains(d.getString("ETSI_TC")))
					{
						meet.add(d.getString("OTHER_TC"));
						System.out.println(d.getString("OTHER_TC"));
					}
				}
			}
			while(r.next())
			{
				//System.out.println(r.getString("TABLE_ID"));
				if(meet.contains(r.getString("TABLE_ID")))System.out.println("ID: "+r.getInt("IDENTIFY_CATEGORY")+",CATEGORY_TYPE = "+ r.getString("CATEGORY_TYPE")  +" ,TABLE_ID = " +r.getString("TABLE_ID"));
			}
		}catch (Exception e)
		{
			
		}
		
	}
	
}
