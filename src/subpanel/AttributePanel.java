package subpanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.YGOUDS;
import data.DeckData;

import javax.swing.table.*;

import utility.StringUtility;

public class AttributePanel extends JPanel
{
	public AttributePanel()
	{
		this.setSize( 600, 300 );
		
		ArrayList<DeckData> deckDatas = YGOUDS.deckDatas;
		
		
		
		
		StringUtility su = new StringUtility();
		ArrayList<String> tcAttribute = su.getStringAL("/lng/tc/attribute.txt");
		ArrayList<String> enAttribute = su.getStringAL("/lng/en/attribute.txt");
		ArrayList<String> jpAttribute = su.getStringAL("/lng/jp/attribute.txt");
		
		Object[][] tableInputs = new Object[tcAttribute.size()][];
		int[] counts = new int[tcAttribute.size()];
				
        for(int i = 0;i < deckDatas.size();i++)
        {
        	DeckData deckData = deckDatas.get(i);
        	
        	int count = deckData.getCount();
        	String attribute = deckData.getAttribute();
        	
        	for(int j = 0;j < tcAttribute.size();j++)
        	{
        		if(attribute.matches(tcAttribute.get(j)))
        		{
        			counts[j]+=count;
        		}
        	}
        }
        
        ArrayList<String> nameAL = new ArrayList<String>();
        ArrayList<String> valueAL = new ArrayList<String>();
        ArrayList<DeckData> sortedDDAL = new ArrayList<DeckData>();
        
        for(int i = 0;i < tcAttribute.size();i++)
        {
        	tableInputs[i] = new Object[4];

        	tableInputs[i][0] = tcAttribute.get(i);
        	tableInputs[i][1] = enAttribute.get(i);
        	tableInputs[i][2] = jpAttribute.get(i);
        	tableInputs[i][3] = counts[i];
        	if(counts[i]!=0)
        	{
        		//nameAL.add(tcRace.get(i));
        		//valueAL.add(counts[i]+"");
        		DeckData dd = new DeckData();
        		dd.setTCname(tcAttribute.get(i));
        		dd.setCount(counts[i]);
        		sortedDDAL.add(dd);
        	}
        }
        
        
        sortedDDAL.sort(new data.DeckDataComparator());
        
        String[] header = { "中文", "英文", "日文" ,
        		"使用數"};
        
        setLayout(new GridLayout(1, 2));
        
        TableModel model = new DefaultTableModel(tableInputs, header) {
            public Class<?> getColumnClass(int column) {
              return getValueAt(0, column).getClass();
            }
          };
          
        JTable table = new JTable(model);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        
        table.setPreferredScrollableViewportSize(new Dimension(300, 300));
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(scrollPane);
        
        //PieChartPanel pcp = new PieChartPanel(nameAL,valueAL);
        PieChartPanel pcp = new PieChartPanel("上位屬性比例",sortedDDAL);
        
        add(pcp);
	}
}
