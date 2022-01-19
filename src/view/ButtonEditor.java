package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

class ButtonEditor extends AbstractCellEditor implements   
TableCellEditor,ActionListener 
{     
JButton button;    
private JTable cwwObjTblMainTable = null;
boolean enabled = true;     
int clickCountToStart = 1;  


public ButtonEditor() 
{     
    button = new JButton();     
    button.addActionListener(this);     
}     

public Component getTableCellEditorComponent(JTable table,     
        Object value,     
        boolean isSelected,     
        int row, int column) {     

    cwwObjTblMainTable=table;
    button.setText("Install");     
    button.setEnabled(enabled);     
    return button;     
}     

public void setEnabled(boolean enabled) {     
    this.enabled = enabled;     
}     

public Object getCellEditorValue() {     
    return button.getText();     
}     

public boolean isCellEditable(EventObject anEvent) {     
    if (anEvent instanceof MouseEvent) {     
        return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;     
    }     
    return true;     
}     

public void actionPerformed(ActionEvent e) 
{  
    enabled=false;
    button.setEnabled(false);
    //Business logic execution 
    System.out.println("Clicked");

}
}
