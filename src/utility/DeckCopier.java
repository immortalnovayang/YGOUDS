package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.YGOUDS;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import utility.StringUtility;
import data.DeckData;


public class DeckCopier {
	static String mainurl = "http://ocg.xpg.jp";
	static String rankurl = "http://ocg.xpg.jp";
	Connection conn = null;
	Elements tr = null;
	
	public DeckCopier()
	{
		
	}
	
	public void copydeck(int num, String savePath)
	{
		for(int i = 1;i < tr.size();i++)
		{
			if(i == num) //列第一個牌組
			{
				Elements td = tr.get(i).select("td");
				//System.out.println(td.get(0).text());
				DeckData dd = new DeckData();
				Elements links = td.select("a"); 
				String newurl = rankurl+links.attr("href");
				System.out.println(newurl);
				
				
				URL url2 = null;
				try {
					url2 = new URL(newurl);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				Document xmlDoc2 = null;
				try {
					xmlDoc2 = Jsoup.parse(url2, 3000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				Elements table2 = xmlDoc2.select("table"); 
				//建立檔案
				FileWriter out = null;
				//加上副檔名
				System.out.println("之前"+savePath);
				if(!savePath.matches("([^\\s]+(\\.(?i)(ydk))$)"))
				{
					savePath = savePath+".ydk";
					System.out.println("之後"+savePath);
				}
				
				try{
				     out = new FileWriter(savePath,false);	 
				     //寫入開頭
				     out.write("#created by UDS"+"\r\n");
				     out.write("#main"+"\r\n");
				     
				}catch(IOException ioe){
				     System.out.print(ioe);
				}
				//怪獸
				writeCardNum(table2,0,out,conn);
				//魔法
				writeCardNum(table2,1,out,conn);
				//陷阱
				writeCardNum(table2,2,out,conn);
				//額外
				try {
					out.write("#extra"+"\r\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writeCardNum(table2,3,out,conn);
				//備牌
				try {
					out.write("!side"+"\r\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writeCardNum(table2,4,out,conn);
				
				//關閉檔案
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			
		}
	}
	public void loadWeb()
	{
		StringUtility su = new StringUtility();
		String path = "/data/cards.accdb";
		
		File file = new File(YGOUDS.class.getResource(path).getFile()); 
		
		try{

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn=DriverManager.getConnection("jdbc:ucanaccess://"+file.getAbsolutePath());
			//String url = "jdbc:ucanaccess={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C://data//test.accdb";
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			///Connection conn = DriverManager.getConnection(url);
			//System.out.println(conn);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		URL url = null;
		try {
			url = new URL("http://ocg.xpg.jp/rank/rank.fcgi?Mode=5");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Document xmlDoc = null;
		try {
			xmlDoc = Jsoup.parse(url, 3000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Elements table = xmlDoc.select("table"); 
		Elements tbody = table.select("tbody");
		tr = tbody.select("tr");
		//System.out.println(tr.get(0).text());
		
		//System.out.println("Title is "+table.text()); 
		System.out.println("conn狀態 = " + conn);
	}
	
	public static void writeCardNum(Elements table2, int i,FileWriter out,Connection conn)
	{
		Elements tbody2 = table2.get(i).select("tbody");
		Elements tr2 = tbody2.select("tr");
		//System.out.println(tr2);
		
		
		for(int j = 2;j < tr2.size();j++)
		{
			
			Elements td2 = tr2.get(j).select("td");
			//System.out.println(td2.text());
			double usage = Double.parseDouble(td2.get(3).text());
			int oneCard = Integer.parseInt(td2.get(4).text());
			int twoCard = Integer.parseInt(td2.get(5).text());
			int threeCard = Integer.parseInt(td2.get(6).text());
			int putNum = getPutNum(oneCard,twoCard,threeCard);
			String cardName = td2.get(1).text();
			
			Statement sta = null;
			
			String cardNum = "";
			
			if(usage>=50.0)
			{
				try {
					if(conn==null)
					{
						System.out.println("conn is null");
					}
					sta = conn.createStatement();
					ResultSet rs = sta.executeQuery("select id from cards where name = \""+cardName+"\"");
					
					if(rs.next()){
						cardNum = rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int k = 0;k < putNum;k++){
					System.out.println(cardNum);
					try {
						out.write(cardNum+"\r\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static int getPutNum(int i, int j, int k)
	{
		if(i >= j && i >=k)
		{
			return 1;
		}else if(j >= i && j >=k)
		{
			return 2;
		}if(k >= i && k >=j)
		{
			return 3;
		}
		
		return 0;
	}
}
