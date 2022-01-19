package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyDefaultTableCellRenderer extends DefaultTableCellRenderer{


   @Override
   public Component getTableCellRendererComponent(JTable table,
         Object value, boolean isSelected, boolean hasFocus,
         int row, int column) {
      Component comp = null;
      JPanel p = new JPanel(new BorderLayout());
        if(column==8){
           JButton btndetal1 = new JButton("상세");
           JButton btndetal2 = new JButton("대출");
           JButton btndetal3 = new JButton("예약");
           
           btndetal1.setPreferredSize(new Dimension(50,25)); 
           btndetal2.setPreferredSize(new Dimension(50,25)); 
           btndetal3.setPreferredSize(new Dimension(50,25)); 
           
           btndetal1.setFont(new Font("맑은 고딕", Font.BOLD, 8));
           btndetal2.setFont(new Font("맑은 고딕", Font.BOLD, 8));
           btndetal3.setFont(new Font("맑은 고딕", Font.BOLD, 8));
           
           p.setBackground(Color.white);
           p.add(btndetal1, BorderLayout.WEST);
           p.add(btndetal2, BorderLayout.CENTER);
           p.add(btndetal3, BorderLayout.EAST);
           comp = p;

      }
      return comp;
   }


}
