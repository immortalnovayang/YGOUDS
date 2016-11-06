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

public class RacePanel extends JPanel
{
	public RacePanel()
	{
		this.setSize( 600, 300 );
		
		ArrayList<DeckData> deckDatas = YGOUDS.deckDatas;
		
		
		
		
		StringUtility su = new StringUtility();
		ArrayList<String> tcRace = su.getStringAL("/lng/tc/race.txt");
		ArrayList<String> enRace = su.getStringAL("/lng/en/race.txt");
		ArrayList<String> jpRace = su.getStringAL("/lng/jp/race.txt");
		
		Object[][] tableInputs = new Object[tcRace.size()][];
		int[] counts = new int[tcRace.size()];
				
        for(int i = 0;i < deckDatas.size();i++)
        {
        	DeckData deckData = deckDatas.get(i);
        	
        	int count = deckData.getCount();
        	String race = deckData.getRace();
        	
        	for(int j = 0;j < tcRace.size();j++)
        	{
        		if(race.matches(tcRace.get(j)))
        		{
        			counts[j]+=count;
        		}
        	}
        }
        
        ArrayList<String> nameAL = new ArrayList<String>();
        ArrayList<String> valueAL = new ArrayList<String>();
        ArrayList<DeckData> sortedDDAL = new ArrayList<DeckData>();
        
        for(int i = 0;i < tcRace.size();i++)
        {
        	tableInputs[i] = new Object[4];

        	tableInputs[i][0] = tcRace.get(i);
        	tableInputs[i][1] = enRace.get(i);
        	tableInputs[i][2] = jpRace.get(i);
        	tableInputs[i][3] = counts[i];
        	if(counts[i]!=0)
        	{
        		//nameAL.add(tcRace.get(i));
        		//valueAL.add(counts[i]+"");
        		DeckData dd = new DeckData();
        		dd.setTCname(tcRace.get(i));
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
        PieChartPanel pcp = new PieChartPanel("上位種族比例",sortedDDAL);
        
        add(pcp);
	}
}
