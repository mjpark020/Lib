package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer implements TableCellRenderer {

	
	   
	JButton button;     
	boolean enabled = true; 

	public ButtonRenderer() 
	{     
	    button = new JButton();     
	}     

	public Component getTableCellRendererComponent(JTable table,     
	        Object value,     
	        boolean isSelected,     
	        boolean hasFocus,     
	        int row, int column) 
	{   

	    button.setText("Install");     
	    button.setEnabled(enabled);     
	    return button;     
	}     

	public void setEnabled(boolean enabled) 
	    {     
	    this.enabled = enabled;     
	}     
	}     

	

