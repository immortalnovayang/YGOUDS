package subpanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.YGOUDS;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import data.DeckData;


public class PieChartPanel extends JPanel {

	private ChartPanel panel;
	
	public PieChartPanel()
	{
		
	}
	
	public PieChartPanel(String title,ArrayList<DeckData> deckDatas)
	{
		//super();
		setDefaultTheme();
        
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		for(int i = 0;i < deckDatas.size();i++)
        {
        	DeckData deckData = deckDatas.get(i);
        	
        	String TCname = deckData.getTCname();
        	String count = deckData.getCount()+"";
        	dataset.setValue(TCname, new Double(count));
        }
        
		createPieChart(title,dataset);
	}
	
	public PieChartPanel(String title,ArrayList<String> al1,ArrayList<String> al2)
	{
		setDefaultTheme();
        
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		for(int i = 0;i < al1.size();i++)
        {
        	dataset.setValue(al1.get(i), new Double(al2.get(i)));
        }
        
		createPieChart(title,dataset);
	}
	
	public void createPieChart(String title,DefaultPieDataset dataset)
	{
		JFreeChart chart = ChartFactory.createPieChart(
				title,  // chart title
	            dataset,             // data
	            true,               // include legend
	            true,
	            false
	        );

	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
	    plot.setNoDataMessage("No data available");
	    plot.setCircular(false);
	    plot.setLabelGap(0.02);
	    
	    panel = new ChartPanel(chart);
	    
		//this.panel=new JPanel( true );
		this.panel.setSize( 300, 200 );
		this.add( this.panel );
		this.setVisible( true );
	}
	
	
	public void setDefaultTheme()
	{
		//主題
        StandardChartTheme mChartTheme = new StandardChartTheme("TW");  
        //標題
        mChartTheme.setExtraLargeFont(new Font("SansSerif体", Font.BOLD, 20));  
        //軸
        mChartTheme.setLargeFont(new Font("SansSerif", Font.CENTER_BASELINE, 15));  
        //圖
        mChartTheme.setRegularFont(new Font("SansSerif", Font.CENTER_BASELINE, 15));  
        
        ChartFactory.setChartTheme(mChartTheme);  
	}
}
