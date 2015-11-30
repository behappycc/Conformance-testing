package band;

import java.sql.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.*;
import java.io.File;

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
		
	}
	public void connectDB(int sensitive)
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.conn = DriverManager.getConnection("jdbc:sqlserver://140.112.42.144\\SQLEXPRESS:1433;user=sa;password=bl618");
			if(sensitive==0)	
				this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			else this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connected!");
	}
	public ResultSet Query(String q)
	{
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(q); 
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		return rs;
	}
	public void closeConn()
	{
		try{
			conn.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void check(band b,jsonReader j,Multimap<String, List<String>> multiMap,File f)
	{
		List<String> checkList = b.getStandardList();
		List<String> priority = null;
		int [][] testList = b.getTestList();
		for (String key : multiMap.keySet()) {
			
            if(key!=null&&key.equals(b.table_id))
            {
            	Iterator<List<String>> s = multiMap.get(key).iterator();
            	String testband = "";
            	
            	int single_index = 255;
            	List<String> single =null;
            	List<String> ptcrb_list[] = new List[40];
            	priority = b.getPriority(2);
            	
            	while(s.hasNext())
            	{
            		List<String> temp = s.next();
            		
            		if(temp.get(2).equals(b.PTCRB_ver))
            		{
            			//System.out.println(temp.get(3));
            			if(temp.get(3).toLowerCase().equals("all"))
            			{
            				//System.out.println(temp.get(0)+" = ALL ptcrb,"+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            				testband = "all";
            			}
            			else if(temp.get(3).toLowerCase().equals("single"))
            			{
            				testband = "single";
            				//System.out.println(temp.get(0)+" = single ptcrb,"+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            			}
            			else if(temp.get(3).toLowerCase().equals("inter-rat all")||temp.get(3).toLowerCase().equals("i-rat all"))	
            			{
            				testband = "interall";
            				boolean iftest = true;
            				for(int i=7;i<Integer.valueOf(temp.get(6))+6;i++)
            				{
            					iftest = b.contains_test(temp.get(i));
            					if(iftest==false)break;
            				}
            				if(iftest==true&&checkList.indexOf(temp.get(4))>=0&&checkList.indexOf(temp.get(4))<ptcrb_list.length)
            				{
            					ptcrb_list[checkList.indexOf(temp.get(4))] = temp;
            					System.out.println(temp.get(0)+" ptcrb inter-rate all band = "+temp.get(4));
            				}
            				else System.out.println(temp.get(0)+" ptcrb no inter-rate all band = "+temp.get(4));
            			}
            			else if(temp.get(3).toLowerCase().equals("inter-rat single")||temp.get(3).toLowerCase().equals("i-rat single"))	
            			{
            				
            				testband = "intersingle";
            				boolean iftest = true;
            				for(int i=7;i<Integer.valueOf(temp.get(6))+6;i++)
            				{
            					iftest = b.contains_test(temp.get(i));
            					if(iftest==false)break;
            				}
            				if(iftest==true&&checkList.indexOf(temp.get(4))>=0&&checkList.indexOf(temp.get(4))<ptcrb_list.length)
            				{
            					if(ptcrb_list[checkList.indexOf(temp.get(4))]==null)
            						ptcrb_list[checkList.indexOf(temp.get(4))] = temp;
            					else if(b.singleCompare(ptcrb_list[checkList.indexOf(temp.get(4))],temp,2))
								{
            						ptcrb_list[checkList.indexOf(temp.get(4))] = temp;
								}
            					else System.out.println("id ="+ptcrb_list[checkList.indexOf(temp.get(4))].get(0)+" priority is higher than "+temp.get(0));
            					System.out.println(temp.get(0)+" ptcrb inter-rate single band = "+temp.get(4));
            				}
            				else System.out.println(temp.get(0)+" ptcrb no inter-rate single band = "+temp.get(4));
            				
            			}
            			if((testband.equals("single")||testband.equals("all"))&&checkList.contains(temp.get(4))&&testList[0][checkList.indexOf(temp.get(4))]==1)
            			{
            				System.out.println(b.table_id+","+testband+",ptcrb id = "+temp.get(0)+","+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            				ptcrb_list[checkList.indexOf(temp.get(4))] = temp;
            			}
            			
            		}
            	}
            	
				s = multiMap.get(key).iterator();
				single_index = 255;
				single = null;
            	
            	List<String> gcf_list[] = new List[40];
            	priority =b.getPriority(1);
            	
            	while(s.hasNext())
            	{
            		List<String> temp = s.next();
            		
            		if(temp.get(2).equals(b.GCF_ver))
            		{
            			//System.out.println(temp.get(3));
            			if(temp.get(3).toLowerCase().equals("all")||(testband.equals("all")&&temp.get(3).toLowerCase().equals("")))
            				System.out.println(temp.get(0)+" ALL ,"+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            			else if(temp.get(3).toLowerCase().equals("single")||(testband.equals("single")&&temp.get(3).toLowerCase().equals("")))
            				System.out.println(temp.get(0)+" single ,"+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            			else if(temp.get(3).toLowerCase().equals("inter-rat all")||temp.get(3).toLowerCase().equals("i-rat all")||testband.equals("interall"))	
            			{
            				boolean iftest = true;
            				for(int i=7;i<Integer.valueOf(temp.get(6))+7;i++)
            				{
            					iftest = b.contains_test(temp.get(i));
            					if(iftest==false)break;
            				}
            				if(iftest==true&&checkList.indexOf(temp.get(4))>=0&&checkList.indexOf(temp.get(4))<gcf_list.length)
            				{
            					gcf_list[checkList.indexOf(temp.get(4))] = temp;
            					System.out.println(temp.get(0)+" gcf inter-rate all band = "+temp.get(4));
            				}
            				else System.out.println(temp.get(0)+" gcf no inter-rate all band = "+temp.get(4));
            			}
            			else if(temp.get(3).toLowerCase().equals("inter-rat single")||temp.get(3).toLowerCase().equals("i-rat single"))	
            			{
            				boolean iftest = true;
            				for(int i=7;i<Integer.valueOf(temp.get(6))+7;i++)
            				{
            					iftest = b.contains_test(temp.get(i));
            					if(iftest==false)break;
            				}
            				if(iftest==true&&checkList.indexOf(temp.get(4))>=0&&checkList.indexOf(temp.get(4))<gcf_list.length)
            				{
            					if(gcf_list[checkList.indexOf(temp.get(4))]==null)
            						gcf_list[checkList.indexOf(temp.get(4))] = temp;
            					else if(b.singleCompare(gcf_list[checkList.indexOf(temp.get(4))],temp,1))
								{
            						gcf_list[checkList.indexOf(temp.get(4))] = temp;
								}
            					else System.out.println("id ="+gcf_list[checkList.indexOf(temp.get(4))].get(0)+" priority is higher than "+temp.get(0));
            					
            				}
            				else System.out.println(temp.get(0)+" gcf no inter-rate single band = "+temp.get(4));
            			}
            			if(((temp.get(3).toLowerCase().equals("single")||temp.get(3).toLowerCase().equals("all"))||(testband.equals("single")&&temp.get(3).toLowerCase().equals(""))||(testband.equals("all")&&temp.get(3).toLowerCase().equals("")))&&checkList.contains(temp.get(4))&&testList[0][checkList.indexOf(temp.get(4))]==1)
            			{
            				System.out.println(b.table_id+","+testband+",gcf id = "+temp.get(0)+","+temp.get(2)+","+temp.get(4)+","+temp.get(5));
            				gcf_list[checkList.indexOf(temp.get(4))] = temp;
            			}
            			
            		}
            	}
            	for(int i = 0;i<ptcrb_list.length;i++)
            	{
            		if(ptcrb_list[i]==null)
            		{
            			if(gcf_list[i]!=null)
            			{
            				List<String> clone = new ArrayList<String>();
            				for (int clone_index=0;clone_index<gcf_list[i].size();clone_index++)
            				{
            					clone.add(clone_index, gcf_list[i].get(clone_index));
            				}
            				ptcrb_list[i]=clone;
            			}
            		}
            		if(gcf_list[i]==null)
            		{
            			if(ptcrb_list[i]!=null)
            			{
            				List<String> clone = new ArrayList<String>();
            				for (int clone_index=0;clone_index<ptcrb_list[i].size();clone_index++)
            				{
            					clone.add(clone_index, ptcrb_list[i].get(clone_index));
            				}
            				gcf_list[i]=clone;
            			}
            		}
            	}
            	if (testband == "single"||testband == "intersingle")
            	{
            		priority = b.getPriority(2);
            		single_index = 255;
            		single = null;
            		for(int i = 0;i<ptcrb_list.length;i++)
                	{
            			if(ptcrb_list[i]!=null&&priority.contains(ptcrb_list[i].get(4))&&priority.indexOf(ptcrb_list[i].get(4))<single_index)
            			{
            				if(!ptcrb_list[i].get(5).equals("P"))
            				{
	            				single = ptcrb_list[i];
	        					single_index = priority.indexOf(single.get(4));
            				}
            			}
                	}
            		if(single!=null)
            		{
            			System.out.println(b.table_id+","+testband+", id = "+single.get(0)+","+single.get(2)+","+single.get(4)+","+single.get(5));
            			single.add("YES");
            			System.out.println("YES2== "+single.get(0));
	            		for(int i = 0;i<ptcrb_list.length;i++)
	                	{
	            			if(ptcrb_list[i]!=null&&ptcrb_list[i]!=single&&ptcrb_list[i].get(5)!="P")
	            			{
	            				if(testband=="single")ptcrb_list[i].add("ref "+single.get(4));
	            				else if (testband=="intersingle")ptcrb_list[i].add("NO");
	            			}
	            			else if (ptcrb_list[i]!=null&&ptcrb_list[i].get(5).equals("P"))
	            			{
	            				ptcrb_list[i].add("NO");
	            			}
	                	}
            		}
            		
            		priority = b.getPriority(1);
            		single_index = 255;
            		single = null;
            		for(int i = 0;i<gcf_list.length;i++)
                	{
            			if(gcf_list[i]!=null&&priority.contains(gcf_list[i].get(4))&&priority.indexOf(gcf_list[i].get(4))<single_index)
            			{
            				if(!gcf_list[i].get(5).equals("P"))
            				{
            					single = gcf_list[i];
        						single_index = priority.indexOf(single.get(4));
            				}
            			}
                	}
            		if(single!=null)
            		{
            			System.out.println(b.table_id+","+testband+", id = "+single.get(0)+","+single.get(2)+","+single.get(4)+","+single.get(5));
            			single.add("YES");
            			System.out.println("YES1== "+single.get(0));
	            		for(int i = 0;i<gcf_list.length;i++)
	                	{
	            			if(gcf_list[i]!=null&&gcf_list[i]!=single&&gcf_list[i].get(5)!="P")
	            			{
	            				if(testband=="single")gcf_list[i].add("ref "+single.get(4));
	            				else if (testband=="intersingle")gcf_list[i].add("NO");
	            			}
	            			else if( gcf_list[i]!=null&&gcf_list[i].get(5).equals("P"))
	            			{
	            				gcf_list[i].add("NO");
	            			}
	                	}
            		}
            	}
            	else if (testband == "all"||testband == "interall")
            	{
            		for(int i = 0;i<ptcrb_list.length;i++)
                	{
            			if(ptcrb_list[i]!=null)
            			{
	            			if( ptcrb_list[i].get(5).equals("P"))
	            			{
	            				ptcrb_list[i].add("NO");
	            			}
	            			else ptcrb_list[i].add("YES");
            			}
                	}
            		for(int i = 0;i<gcf_list.length;i++)
                	{
            			if(gcf_list[i]!=null)
            			{
	            			if( gcf_list[i].get(5).equals("P"))
	            			{
	            				gcf_list[i].add("NO");
	            			}
	            			else gcf_list[i].add("YES");
            			}
                	}
            	}
            	j.write(ptcrb_list,gcf_list,b,f);
            	for(int i = 0;i<gcf_list.length;i++)
            	{
        			if(gcf_list[i]!=null)
        			{
            			gcf_list[i].remove(gcf_list[i].size()-1);
        			}
        			if(ptcrb_list[i]!=null)
        			{
        				ptcrb_list[i].remove(ptcrb_list[i].size()-1);
        			}
            	}
            }
		}
	}
	public String[] bandTranslation(String s,band b)
	{
		String r[] = new String[3];
	
		if(s!=null)
		{
			if((s.contains("(")&&s.contains(")")))
			{
				String sub = s.substring(s.indexOf("(")+1, s.lastIndexOf(")"));
				r = sub.split("-");
				if(r.length<2)r = sub.split("�V");
				
			}
			else if((s.contains("[")&&s.contains("]")))
			{
				String sub = s.substring(s.indexOf("[")+1, s.lastIndexOf("]"));
				r = sub.split("-");
				if(r.length<2)r = sub.split("�V");
			}
			
			
			
			for(int i = 0;i<r.length;i++)
			{
				if(r[i]!=null&&!b.contains(r[i]))
				{
					char c = r[i].charAt(0);
					if(c=='E')
					{
						r[i]=r[i].replace("E", "FDD ");
					}
					else if(c=='C')
					{
						r[i]= r[i].replace("C", "FDD ");
					}
					else if(c=='U')
					{
						r[i]= r[i].replace("U", "FDD ");
					}
					else if(c=='G')
					{
						r[i]= r[i].replace("G", "");
					}
				}
				if(r[i].contains("FDD")&&!r[i].contains("FDD "))
				{
					r[i]= r[i].replace("FDD", "FDD ");
				}
				String[] tmp = r[i].split(" ");
				if(tmp.length==2)
				{
					try{
						if(Integer.valueOf(tmp[1])<10&&tmp[1].length()==1)
						{
							r[i]= r[i].replace(tmp[1],"0"+tmp[1]);
						}
					}catch(NumberFormatException e) { 
				       
				    } catch(NullPointerException e) {
				    
				    }
				}
				
				//System.out.println(r[i]);
			}
		}
		return r;
	}
	public merge()
	{
		jsonReader j = new jsonReader();
		List<File> fileList = j.listAllfile();
		band b = j.read(fileList.get(0));
	
		
		String select = null;
		if(b.getStandard()=="LTE")
			select = "SELECT a.IDENTIFY_CATEGORY ,[CATEGORY_TYPE] ,[INTERIMS_VERSION],[TABLE_SPEC],[SPEC_VERSION],[TABLE_ID],[TABLE_DESC] ,[PRIORITY] ,[OPTIONS],a.RELEASE,[TESTBAND],[WORK_ITEM],[MEMO],[ORDER_ID],[NEW_PERSON] ,[NEW_DATE],[MODIFY_PERSON],[MODIFY_DATE],[RFT],[IDENTIFY_CATEGORY_D],b.RELEASE as RELEASE_D,[CATEGORY_FDD_TYPE],[CATEGORY],[VALID_PALTFORM],[FORMERLY_VALID_PLATFORM],[E_PLATFORM],[D_PLATFORM]  FROM [NTUTC].[dbo].[CATEGORY_LTE_MAIN] as a inner join [NTUTC].[dbo].[CATEGORY_LTE_DETAIL] as b on a.IDENTIFY_CATEGORY = b.IDENTIFY_CATEGORY ";
		else if(b.getStandard()=="2G")
			select = "SELECT a.IDENTIFY_CATEGORY ,[CATEGORY_TYPE] ,[INTERIMS_VERSION],[TABLE_SPEC],[SPEC_VERSION],[TABLE_ID],[TABLE_DESC] ,[PRIORITY] ,[OPTIONS],a.RELEASE,[TESTBAND],[WORK_ITEM],[MEMO],[ORDER_ID],[NEW_PERSON] ,[NEW_DATE],[MODIFY_PERSON],[MODIFY_DATE],[RFT],[IDENTIFY_CATEGORY_D],b.RELEASE as RELEASE_D,[CATEGORY_FDD_TYPE],[CATEGORY],[VALID_PALTFORM],[FORMERLY_VALID_PLATFORM],[E_PLATFORM],[D_PLATFORM]  FROM [NTUTC].[dbo].[CATEGORY_2GMAIN] as a inner join [NTUTC].[dbo].[CATEGORY_2GDETAIL] as b on a.IDENTIFY_CATEGORY = b.IDENTIFY_CATEGORY ";
		else if(b.getStandard()=="3G")
			select = "SELECT a.IDENTIFY_CATEGORY ,[CATEGORY_TYPE] ,[INTERIMS_VERSION],[TABLE_SPEC],[SPEC_VERSION],[TABLE_ID],[TABLE_DESC] ,[PRIORITY] ,[OPTIONS],a.RELEASE,[TESTBAND],[WORK_ITEM],[MEMO],[ORDER_ID],[NEW_PERSON] ,[NEW_DATE],[MODIFY_PERSON],[MODIFY_DATE],[RFT],[IDENTIFY_CATEGORY_D],b.RELEASE as RELEASE_D,[CATEGORY_FDD_TYPE],[CATEGORY],[VALID_PALTFORM],[FORMERLY_VALID_PLATFORM],[E_PLATFORM],[D_PLATFORM]  FROM [NTUTC].[dbo].[CATEGORY_3GMAIN] as a inner join [NTUTC].[dbo].[CATEGORY_3GDETAIL] as b on a.IDENTIFY_CATEGORY = b.IDENTIFY_CATEGORY ";
		
		connectDB();
		ResultSet rs = Query(select);
		Multimap<String, List<String>> multiMap = ArrayListMultimap.create();
		
		
		try
		{
			
			while(rs.next())
			{
				List<String> val = new ArrayList<String>();
				
				
				val.add(rs.getString("IDENTIFY_CATEGORY"));//0
				val.add(rs.getString("IDENTIFY_CATEGORY_D"));//1
				val.add(rs.getString("INTERIMS_VERSION"));//2
				val.add(rs.getString("TESTBAND"));//3
				val.add(rs.getString("CATEGORY_FDD_TYPE"));//4
				val.add(rs.getString("CATEGORY"));//5
				String tc_id = rs.getString("TABLE_ID");
				//System.out.println(tc_id);
				if(tc_id!=null&&((tc_id.contains("(")&&tc_id.contains(")"))||(tc_id.contains("[")&&tc_id.contains("]"))))
				{
					String r[] = bandTranslation(tc_id,b);
					val.add(Integer.toString(r.length));//6
					for(int i = 0;i<r.length;i++)
					{
						val.add(r[i]);
					}
					if(tc_id.contains(" ("))
					{
						tc_id = tc_id.substring(0,tc_id.indexOf(" ("));
					}
					else if(tc_id.contains(" ["))
					{
						tc_id = tc_id.substring(0,tc_id.indexOf(" ["));
					}
					tc_id = tc_id +" inter";
				}
				multiMap.put(tc_id,val);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		long startTime = System.currentTimeMillis();
		check(b,j, multiMap,fileList.get(0));
		for(int i=1;i<fileList.size();i++)
		{
			if(fileList.get(i)!=null)
			{
				band t = j.read(fileList.get(i));
				check(t,j, multiMap,fileList.get(i));
			}
		}
		long endTime = System.currentTimeMillis();
		long totTime = endTime - startTime;
		System.out.println("Computing Time:" + totTime);
	}
}
