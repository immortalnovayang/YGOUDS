package subpanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.YGOUDS;
import data.DeckData;

/**
 * Upper Data List Panel
 */

public class UDLPanel extends JPanel
{
	public UDLPanel()
	{
		this.setSize( 600, 300 );
		
		ArrayList<DeckData> deckDatas = YGOUDS.deckDatas;
		
		Object[][] tableInputs = new Object[deckDatas.size()][];
		
        for(int i = 0;i < deckDatas.size();i++)
        {
        	DeckData deckData = deckDatas.get(i);
        	
        	String TCname = deckData.getTCname();
        	String ENname = deckData.getENname();
        	String JPname = deckData.getJPname();
        	String count = deckData.getCount()+"";
        	String attribute = deckData.getAttribute();
        	String race = deckData.getRace();
        	
        	tableInputs[i] = new Object[7];
        	
        	tableInputs[i][0] = TCname;
        	tableInputs[i][1] = ENname;
        	tableInputs[i][2] = JPname;
        	tableInputs[i][3] = count;
        	tableInputs[i][4] = attribute;
        	tableInputs[i][5] = race;
        	tableInputs[i][6] = "朕要抄牌";
        }

        String[] header = { "中文", "英文", "日文" ,
        		"使用數","屬性","種族","牌組"};
        
        setLayout(new GridLayout(1, 2));
        
        JTable table = new JTable(tableInputs, header);
        
        table.setPreferredScrollableViewportSize(new Dimension(300, 300));
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.setModel(new DefaultTableModel() {
            @Override
            public Object getValueAt(int row, int column) {
                return tableInputs[row][column];
            }

            @Override
            public int getRowCount() {
                return deckDatas.size();
            }

            @Override
            public int getColumnCount() {
                return 7;
            }
            @Override
            public void setValueAt(Object aValue, int row, int column){
            	tableInputs[row][column] = aValue;
                fireTableCellUpdated(row, column);
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        table.getColumnModel().getColumn(6).setCellEditor(
                new MyButtonEditor());

        table.getColumnModel().getColumn(6).setCellRenderer(
                new MyButtonRenderer());

        table.setRowSelectionAllowed(false);
        
        add(scrollPane);
        
        PieChartPanel pcp = new PieChartPanel("上位牌組類型比例",YGOUDS.deckDatas);
        
        add(pcp);
	}
}
