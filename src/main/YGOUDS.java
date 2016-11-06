package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import utility.StringUtility;
import utility.XMLLoader;
import data.DeckData;


/**
 * Yu-Gi-Oh! Upper Deck Statistics
 * 遊戲王 上位牌組統計
 */

/*
 * HTML格式 
 * table-1tbody-Xtr-3td
 */

public class YGOUDS {
	public static ArrayList<DeckData> deckDatas = null;
	public static JFrame frame = null;
	static StringUtility su = null;
	public static void main(String[] args) throws Exception {
		su = new StringUtility();
		deckDatas = new ArrayList<DeckData>();
		
		loadWeb();
		
		loadXML();
		
		dataToTable();
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
            }
        });
		
		
	}
	
	//資料匯入到Table上
	public static void dataToTable()
	{
		
	}
		
	//讀取XML資料
	public static void loadXML()
	{
		XMLLoader xmlLoader = new XMLLoader();
		//su.getURL("/deckinfo5.xml").getFile();
		xmlLoader.load("/data/deckinfo5.xml");
		
	}
	
	//讀取網頁資料
	public static void loadWeb()
	{
		URL url = null;
		try {
			url = new URL("https://ocg.xpg.jp/rank/rank.fcgi?Limit=999&Event1=1&Event2=1&Event3=1&Event4=1&Mode=6");
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
		Elements tr = tbody.select("tr");
		System.out.println(tr.get(0).text());
		//System.out.println("Title is "+table.text()); 
		for(int i = 1;i < 11;i++) //tr.size()
		{
			Elements td = tr.get(i).select("td");
			//System.out.println(td.get(0).text());
			DeckData dd = new DeckData();
			System.out.println(td.get(1).text());
			dd.setJPname(td.get(1).text());
			dd.setCount(Integer.parseInt(td.get(2).text()));
			
			deckDatas.add(dd);
			/*
			for(int j = 0;j < td.size();j++)
			{
				System.out.println(td.get(j).text()); 
			}
			*/
			//System.out.println(tr.get(i).text()); 
		}
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Yu-Gi-Oh Upper Deck Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.getContentPane().add(new TabbedPane(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	
	
	

	public static void load(){
		try {
			Parsing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Parsing() throws Exception {
		URL url = new URL("http://ocg.xpg.jp/rank/rank.fcgi?Mode=5&Limit=999&Event1=1&Event2=1&Event3=1&Event4=1"); 
		Document xmlDoc =  Jsoup.parse(url, 3000); 
		
		Elements table = xmlDoc.select("table"); 
		Elements tbody = table.select("tbody");
		Elements tr = tbody.select("tr");
		System.out.println(tr.get(0).text());
		//System.out.println("Title is "+table.text()); 
		for(int i = 0;i < 11;i++)//tr.size()
		{
			System.out.println(tr.get(i).text()); 
		}
		//System.out.println("you select mood is "+happy.get(1).text());
	}

}



