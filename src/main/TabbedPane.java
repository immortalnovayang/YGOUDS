package main;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import subpanel.AttributePanel;
import subpanel.RacePanel;
import subpanel.UDLPanel;

public class TabbedPane extends JPanel {
	public TabbedPane() {
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
        //ImageIcon icon = createImageIcon("images/middle.gif");
         
        //上位牌組列表
        JComponent udlPanel = new UDLPanel();
        tabbedPane.addTab("上位牌組列表", udlPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        //種族統計
        //JComponent panel2 = makeTextPanel("Panel #2");
        JComponent panel2 = new RacePanel();
        tabbedPane.addTab("種族統計", panel2);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
         
        //屬性統計
        JComponent panel3 = new AttributePanel();
        tabbedPane.addTab("屬性統計", panel3);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
