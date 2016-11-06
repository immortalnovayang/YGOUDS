package utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.YGOUDS;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Node;

import data.DeckData;

public class XMLLoader {
	static StringUtility su = null;
	public XMLLoader()
	{
		su = new StringUtility();
	}
	
	public void load(String path)
	{
		try{
			URL url = su.getURL(path);
			System.out.println(path);
			//將src下面的xml轉換為輸入流
			//InputStream inputStream = new FileInputStream(new File(path)); 
			//InputStream inputStream = this.getClass().getResourceAsStream("/module01.xml");//也可以根據類的編譯檔相對路徑去找xml
			//創建SAXReader讀取器，專門用於讀取xml
            SAXReader saxReader = new SAXReader();
            //根據saxReader的read重寫方法可知，既可以通過inputStream輸入流來讀取，也可以通過file物件來讀取 
            //Document document = saxReader.read(inputStream);  
            Document document = saxReader.read(url);//必須指定檔的絕對路徑
            
            //另外還可以使用DocumentHelper提供的xml轉換器也是可以的。
            //Document document = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><modules id=\"123\"><module> 這個是module標籤的文本資訊</module></modules>");
            
            //獲取根節點對象
            //Element rootElement = document.getRootElement();  
            //System.out.println("根節點名稱：" + rootElement.getName());//獲取節點的名稱
            //System.out.println("根節點有多少屬性：" + rootElement.attributeCount());//獲取節點屬性數目
            //System.out.println("根節點id屬性的值：" + rootElement.attributeValue("id"));//獲取節點的屬性id的值
            //System.out.println("根節點內文本：" + rootElement.getText());//如果元素有子節點則返回空字串，否則返回節點內的文本
            //rootElement.getText() 之所以會換行是因為 標籤與標籤之間使用了tab鍵和分行符號佈局，這個也算是文本所以顯示出來換行的效果。
            //System.out.println("根節點內文本(1)：" + rootElement.getTextTrim());//去掉的是標籤與標籤之間的tab鍵和分行符號等等，不是內容前後的空格
            
            //System.out.println("根節點子節點文本內容：" + rootElement.getStringValue()); //返回當前節點遞迴所有子節點的文本資訊。
            
            List<? extends Node> nodes =  document.selectNodes("/deckinfo/deck");
            
            improveArrayList(nodes);
            
            
            //rootElement.setName("root");//支持修改節點名稱
            //System.out.println("根節點修改之後的名稱：" + rootElement.getName());
            //rootElement.setText("text"); //同樣修改標籤內的文本也一樣
            //System.out.println("根節點修改之後的文本：" + rootElement.getText());
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	public String getPath(String s)
	{
	  String path = getClass().getResource(s).getPath();
	  path = path.substring(1);
	  System.out.println(path);
	    
	  return path;
	}
	
	public void improveArrayList(List<? extends Node> nodes)
	{
		//獲取子節點
		for(Node node:nodes)
		{
			String TCname = node.selectSingleNode("TCname").getText();
	    	String ENname = node.selectSingleNode("ENname").getText();
	    	String JPname = node.selectSingleNode("JPname").getText();
	    	//String count = rootElement.element("count").getText();
	    	String attribute = node.selectSingleNode("attribute").getText();
	    	String race = node.selectSingleNode("race").getText();
	        
	    	//System.out.println("TCname"+TCname);
	    	
	    	ArrayList<DeckData> dds = YGOUDS.deckDatas;
	    	for(int i = 0;i < dds.size();i++)
	    	{
	    		DeckData dd = dds.get(i);
	    		if(dd.getJPname().matches(JPname))
	    		{
	    			dd.setTCname(TCname);
	    			dd.setENname(ENname);
	    			dd.setAttribute(attribute);
	    			dd.setRace(race);
	    		}
	    	}
	    	//System.out.println(deck.getStringValue());
		}
		
	}
	
	/*
    if(element != null){
    	System.out.println("子節點的文本：" + element.getText());//因為子節點和根節點都是Element物件所以它們的操作方式都是相同的
    }
    //但是有些情況xml比較複雜，規範不統一，某個節點不存在直接java.lang.NullPointerException，所以獲取到element物件之後要先判斷一下是否為空
    */
	
	public String eraseSpace(String s)
	{
		//s = s.replace("\n", "");
		s = s.replace("\\s+", "");
		return s;
	}
}