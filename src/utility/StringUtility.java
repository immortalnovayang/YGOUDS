package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import main.YGOUDS;

public class StringUtility {

	public String getPath(String s)
	{
		//String path = YGOUDS.class.getResource(s).toString();
		  String path = getClass().getResource(s).getPath();
		  path = path.substring(1);
		  System.out.println(path);
		    
		  return path;
	}
	
	public URL getURL(String s)
	{

		URL url = getClass().getResource(s);
		
        return url;
	}
	
	public String getFilePath(String s){
		String local = YGOUDS.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if(local.endsWith("/")){
            local = local.substring(2, local.length() - 1);
        }
		
		System.out.println("A" + local);
		System.out.println("B" + s);
		
		String path = local + s;
		path = path.substring(1,path.length());
		//path = path.replace('/', '\\');
		System.out.println(local+path);
		return path;
	}
	public ArrayList<String> getStringAL(String path)
	{
		
		File file = new File(YGOUDS.class.getResource(path).getFile()); 
		
		BufferedReader br = null;
		
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		br = new BufferedReader(isr);

		

		
		//Scanner不能讀ini
		ArrayList<String> stringal = new ArrayList<String>();
		
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(s!=null)
		{
			
			//System.out.println(s);
			stringal.add(s);
			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("共讀取"+stringal.size()+"行資料");
		
		return stringal;
	}
}
