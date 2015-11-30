package band;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.google.gson.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;


public class jsonReader {
	
	
	private String path =  "D:\\testcase";
	JSONParser parser = new JSONParser();
	public List<File> listAllfile()
	{
		File dir = new File(path);
		List<File> directoryListing = new ArrayList<File>();
		for (File d:dir.listFiles())
		{
			if(!d.isDirectory())
			{
				directoryListing.add(d);
			}
		}
		return directoryListing;
	}
	
	public void write(List<String> p[],List<String> g[],band b,File f)
	{
		int[][] test = b.getTestList();
		int len = test[0].length;
		List<String> s = b.getStandardList();
		
		String fname = f.getName();
		int pos = fname.lastIndexOf(".");
		if (pos > 0) {
		    fname = path+"\\result\\"+fname.substring(0, pos)+"_result.json";
		}
		System.out.println(Arrays.toString(test[0]));
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		for(int i =0;i<len;i++)
		{
			
			if(test[0][i]==1)
			{	
				JSONObject obj2 = new JSONObject();
				
				if(p[i]!=null)
				{
					JSONArray element = new JSONArray();
					element.addAll(p[i]);
					obj2.put(s.get(i),element);
					
				}
				else
				{
					
					obj2.put(s.get(i),"NO");
					
				}
				list.add(obj2);
			}
		}
		obj.put("PTCRB", list);
		list = new JSONArray();
		
		for(int i =0;i<len;i++)
		{
			
			if(test[0][i]==1)
			{	
				JSONObject obj2 = new JSONObject();
				if(g[i]!=null)
				{
					JSONArray element = new JSONArray();
					element.addAll(g[i]);
					obj2.put(s.get(i),element);
				}
				else
				{
					obj2.put(s.get(i),"NO");
				}
				list.add(obj2);
			}
		}
		
		
		obj.put("GCF", list);
		try {
			System.out.println("fname= "+fname);
			FileWriter file = new FileWriter(fname);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String prettyJson = gson.toJson(obj);
			file.write(prettyJson);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public band read(File f)
	{
		//f = path +"\\"+String.valueOf(index)+".json";
		band b = new band();
		try{
			
			JSONObject j = (JSONObject)parser.parse(new FileReader(f));
			String standard = (String)j.get("standard");
			String main = (String)j.get("main");
			JSONArray band2G =(JSONArray) j.get("test_band_2G");
			JSONArray band3G =(JSONArray) j.get("test_band_3G");
			JSONArray bandLTE =(JSONArray) j.get("test_band_LTE");
			String id = (String)j.get("table_id");
			if(id.contains(" ("))
			{
				id = id.substring(0,id.indexOf(" ("));
				id = id + " inter";
			}
			else if(id.contains(" ["))
			{
				id = id.substring(0,id.indexOf(" ["));
				id = id + " inter";
			}
			String ptcrb = (String)j.get("ptcrb_ver");
			String gcf = (String)j.get("gcf_ver");
				
			
			
			int l2G[] = new int[4];
			if (band2G != null) { 
				int len = band2G.size();
				for (int array_ite=0;array_ite<len;array_ite++){ 
					if(b.list_2g.contains(band2G.get(array_ite).toString())==true)
					{
						l2G[b.list_2g.indexOf(band2G.get(array_ite).toString())] = 1;
						
					}
				}
			}
			int l3G[] = new int[6];
			if (band3G != null) { 
				int len = band3G.size();
				for (int array_ite=0;array_ite<len;array_ite++){ 
					if(b.list_3g.contains(band3G.get(array_ite).toString())==true)
					{
						l3G[b.list_3g.indexOf(band3G.get(array_ite).toString())] = 1;
						
					}
				}
			}
			int lLTE[] = new int[40];
			if (bandLTE != null) { 
				int len = bandLTE.size();
				for (int array_ite=0;array_ite<len;array_ite++){ 
					if(b.list_LTE.contains(bandLTE.get(array_ite).toString())==true)
					{
						lLTE[b.list_LTE.indexOf(bandLTE.get(array_ite).toString())] = 1;
						
					}
				}
			}
			b.set2G(l2G);
			b.set3G(l3G);
			b.setLTE(lLTE);
			b.setMain(main);
			if(standard.equals("2G"))
			{
				
				b.setStandard("2G");
				//b.set2G(l);
				b.setTable_id(id);
				b.setGCF(gcf);
				b.setPTCRB(ptcrb);
				System.out.println(b.getStandard());
				System.out.println(b.getTable_id());
				System.out.println(b.getGCF_ver());
				System.out.println(b.getPTCRB_ver());
				System.out.println(Arrays.toString(b.getTestList()[0]));
				
			}else if (standard.equals("3G"))
			{
				
				b.setStandard("3G");
				//b.set3G(l);
				b.setTable_id(id);
				b.setGCF(gcf);
				b.setPTCRB(ptcrb);
				System.out.println(b.getStandard());
				System.out.println(b.getTable_id());
				System.out.println(b.getGCF_ver());
				System.out.println(b.getPTCRB_ver());
				System.out.println(Arrays.toString(b.getTestList()[0]));
				
			}else if (standard.equals("LTE"))
			{
				
				
				b.setStandard("LTE");
				//b.setLTE(l);
				b.setTable_id(id);
				b.setGCF(gcf);
				b.setPTCRB(ptcrb);
				System.out.println(b.getStandard());
				System.out.println(b.getTable_id());
				System.out.println(b.getGCF_ver());
				System.out.println(b.getPTCRB_ver());
				System.out.println(Arrays.toString(b.getTestList()[0]));
			}
			
			
			
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
		return b;
		
		
	}
}
