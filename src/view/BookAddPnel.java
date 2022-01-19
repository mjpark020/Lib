package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import model.LibDao;
import model.LibVo;

public class BookAddPnel extends JFrame  implements ActionListener, MouseListener {
   JTextField tfbookname, tfAuthor, tfByear, tfPublisehr, tfBCode,tfGenre, tfRent,tfseri,tfbookmany;
   String bookname;
   String author; 
   String publisher;
   String byear;
   String bookcode;
   String seri;
   double genre;
   JButton    btnRent, btnBook;
   int booknum;
   
   GridBagLayout gb;
   GridBagConstraints gbc; // option

   private BookList booklist;
   private BookList_admin admin;
   
   //기본 생성자
   public BookAddPnel() {
      initComponent();
   }

   
   //---------------------------------------------
   private void initComponent() {
      this.setTitle("책 상세정보");

      // component 배치
      // GridBagLayout : Layout
      gb = new GridBagLayout();
      this.setLayout(gb);

      gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 1.0;
      gbc.weighty = 1.0;
      

      // 책이름
      JLabel lblName = new JLabel("책이름");
      tfbookname = new JTextField(20);
      
      gbAdd(lblName, 0, 0, 1, 1);
      gbAdd(tfbookname, 1, 0, 3, 1);
      
      // 저자
      JLabel lblAthor = new JLabel("저자");
      tfAuthor = new JTextField(20);
      
      gbAdd(lblAthor, 0, 1, 2, 1);
      gbAdd(tfAuthor, 1, 1, 3, 1);
      
      
      
      // 발행일
      JLabel lbseri = new JLabel("시리즈");
      tfseri = new JTextField(20);
     
      
      gbAdd(lbseri, 0, 3, 2, 1);
      gbAdd(tfseri, 1, 3, 3, 1);
      
      // 발행일
      JLabel lblByear = new JLabel("발행일");
      tfByear = new JTextField(20);
      
      
      gbAdd(lblByear, 0, 4, 2, 1);
      gbAdd(tfByear, 1, 4, 3, 1);
      
      // 출판사
      JLabel lblPublisher = new JLabel("출판사");
      tfPublisehr = new JTextField(20);
      
      
      gbAdd(lblPublisher, 0, 5, 2, 1);
      gbAdd(tfPublisehr, 1, 5, 3, 1);
      
      // 분류코드
      JLabel lblBCode = new JLabel("분류코드");
      tfBCode = new JTextField(20);
      
      
      gbAdd(lblBCode, 0, 6, 2, 1);
      gbAdd(tfBCode, 1, 6, 3, 1);
      
      JLabel lblmany = new JLabel("수량");
      tfbookmany = new JTextField(20);

      
      gbAdd(lblmany, 0, 7, 2, 1);
      gbAdd(tfbookmany, 1, 7, 3, 1);
      
   /*   // 책장르
      JLabel lblBGenre = new JLabel("장르");
      tfGenre = new JTextField(20);
      
      gbAdd(lblBGenre, 0, 5, 2, 1);
      gbAdd(tfGenre, 1, 5, 3, 1);
      */
   
      //버튼
       JPanel   pButton = new JPanel();
       btnRent = new JButton("추가");
       btnBook = new JButton("취소");
       
       pButton.add(btnRent);
       pButton.add(btnBook);
       gbAdd(pButton,0,8,4,1);
       
       //이벤트 연결  기능 추가
      this.btnRent.addActionListener( this );
      this.btnBook.addActionListener(this);
   

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize(350, 350);
      this.setVisible(true);
   }
   
   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gb.setConstraints(c, gbc);
      gbc.insets = new Insets(2, 2, 2, 2);
      this.add(c, gbc);

   }
   
   
   // main
      public static void main(String[] args) {
         new BookInpo();
      }

   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

 //렌트북    
   private void addbook() {
      LibDao lDao = new  LibDao();
      
     
     String BOOKNAME = this.tfbookname.getText();
     String AUTHOR = this.tfAuthor.getText();
     String  PUBLISHER = this.tfPublisehr.getText();
     String BYEAR = this.tfByear.getText();
     int SERIES = Integer.parseInt(tfseri.getText());
     String BOOKCODE = this.tfBCode.getText();
     int EA = Integer.parseInt(tfbookmany.getText());
     
      String userid = Login.id.getText();
      int booknum = this.booknum;
      lDao.insertBook(BOOKNAME, AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA);
     
      
      
      
   
      this.dispose();
     
   }
   
 
  
   


//버튼 이벤트 연결
  @Override
  public void actionPerformed(ActionEvent e) {
     switch(e.getActionCommand()) {
     case "추가":
    	 	addbook();      
    	    JOptionPane.showMessageDialog(null,"추가완료");
            break;
     case "취소":
        this.dispose();
        break;
        
     }
  }
   
   
}