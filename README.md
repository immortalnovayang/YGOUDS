# YGOUDS
遊戲王抄牌網上位趨勢分析

功能說明：  
擷取[遊戲王抄牌網](https://ocg.xpg.jp/rank/rank.fcgi?Mode=6 "上位趨勢")上位趨勢統計  
並圖像化分析  

執行畫面：  
A.上位牌組列表  
![image](http://i.imgur.com/oqO2RRW.png "Upper Deck List")  
點"朕要抄牌"按鈕可產生該類型牌組上位使用率50%以上的牌列表構成的文字檔  
(每張牌資訊為右下角八位卡號數字組成)  

B.種族統計  
![image](http://i.imgur.com/g4LNkMm.png "Race Statistic")  

C.屬性統計  
![image](http://i.imgur.com/jgJAApu.png "Attribute Statistic")  

採用Lib:  
以下已包含在YGOUDS.jar，YGOUDS.jar可直接執行  
如需重新編譯請另外下載此專案的以下Jar並import到專案中  

HTML parser  
jsoup-1.8.1.jar  

資料庫讀取相關  
hsqldb.jar  

讀取XML  
dom4j-2.0.0-ALPHA-2.jar  
jaxen-1.1.1.jar  

圖表相關  
jfreechar-1.0.19.jar  
jcommon-1.0.23.jar  

讀取Access資料表相關  
ucanaccess-2.0.9.4.jar  
commons-lang-2.6.jar  
commons-logging-1.1.1.jar  
jackcess-2.0.8.jar  

名詞解釋：  
上位：比賽中拿前幾名的牌組  
