package subpanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import utility.DeckCopier;
import utility.YDKFileFilter;
import main.YGOUDS;

public class MyButtonEditor extends AbstractCellEditor implements
        TableCellEditor {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;

    private JPanel panel;

    private JButton button;

    private int num;

    private String deckName;
    
    public MyButtonEditor() {

        initButton();

        initPanel();

        panel.add(this.button, BorderLayout.CENTER);
    }

    private void initButton() {
        button = new JButton();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/*
                int res = JOptionPane.showConfirmDialog(null,
                        "Do you want to add 1 to it?", "choose one",
                        JOptionPane.YES_NO_OPTION);
                
                if(res ==  JOptionPane.YES_OPTION){
                    num++;
                }
                */
                //stopped!!!!
                fireEditingStopped();

            }
        });

    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //num = (Integer) value;
    	int decknum = row+1;
    	System.out.println("想抄第"+decknum+"副");
    	
    	//讀取上次的路徑
    	Preferences pref = Preferences.userRoot().node(this.getClass().getName()); 
    	String lastPath = pref.get("lastPath", ""); 
    	
    	JFileChooser fileChooser = null;
    	if(!lastPath.equals("")){ 
    		fileChooser = new JFileChooser(lastPath);
    	}
    	else
    	{
    		fileChooser=new JFileChooser();
    	}
    	
    	fileChooser.setDialogTitle("Specify a ydk deck file to save");   
    	fileChooser.setFileFilter(new YDKFileFilter("ydk", "*.ydk(ADS Deck File)"));
    	
    	int userSelection = fileChooser.showSaveDialog(YGOUDS.frame);
    	 
    	String savePath = "";
    	
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    File fileToSave = fileChooser.getSelectedFile();
    	    //存這次路徑給下次用
    	    pref.put("lastPath",fileToSave.getPath());
    	    
    	    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    	    savePath = fileToSave.getAbsolutePath();
    	    
    	    DeckCopier dc = new DeckCopier();
        	dc.loadWeb();
        	dc.copydeck(decknum,savePath);
            
            button.setText("已抄");
    	}
    	else
    	{
    		
    	}

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "已抄";
    }

}