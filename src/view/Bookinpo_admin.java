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

public class Bookinpo_admin extends JFrame  implements ActionListener, MouseListener {
   JTextField tfbookname, tfAuthor, tfByear, tfPublisehr, tfBCode,tfGenre, tfRent,tfseri,tfea;
   String bookname;
   String author; 
   String publisher;
   String byear;
   String bookcode;
   String seri;
   double genre;
   JButton    btnupdate, btnBook,btncencel;
   int booknum;
   
   GridBagLayout gb;
   GridBagConstraints gbc; // option

   private BookList booklist;
   private BookList_admin admin;
   
   //기본 생성자
   public Bookinpo_admin() {
      initComponent();
   }

 
   //버튼 클릭했을 때 생성자
   public Bookinpo_admin(int booknum, BookList_admin admin) {
      this();
      
      this.admin = admin;
      this.booknum = booknum;
      viewData();


   }
   
   
   //버튼 클릭했을 때 자료 들어가게 
      private void viewData() {
         LibDao   ldao = new LibDao();
         int booknum = this.booknum;
         LibVo user = ldao.getBook(booknum);
         if (user != null) {
            setViewData(user);
         }
      }
      

      private void setViewData(LibVo user) {
         String bookname = user.getBookname();
         String author   = user.getAuthor(); 
         String publisher = user.getPublisher();
         String byear     = user.getByear();
         String seri =  Integer.toString(user.getSeries());
         String ea = Integer.toString(user.getEa());
         String bgenre ="";
         String bookcode = "";
         
         double genre = 0.0;
      
         try {
        	 bookcode  =  user.getBookcode();
         } catch (NumberFormatException e){
        	 
        	genre = -1;
         }
         if(bookcode == null) {
        	 genre = -1;
         }
         try {
        	 genre = Double.parseDouble(bookcode);
         } catch (NullPointerException e){
        	 genre = -1;
         } catch(NumberFormatException e) {
        	 genre = -1;
         }
         
         if(genre >=900) {
            bgenre = "역사";
         }
         else 
            if(genre >=800) {
               bgenre = "문학";
            }
            else 
               if(genre >=700) {
                  bgenre = "언어";
               }
               else 
                  if(genre >=600) {
                     bgenre = "예술";
                  }
                  else 
                     if(genre >=500) {
                        bgenre = "기술과학";
                     }
                     else 
                        if(genre >=400) {
                           bgenre = "자연과학";
                        }
                        else 
                           if(genre >=300) {
                              bgenre = "사회과학";
                           }
                           else 
                              if(genre >=200) {
                                 bgenre = "종교";
                              }
                              else 
                                 if(genre >=100) {
                                    bgenre = "철학";
                                 }
                                 else
                                	 if(genre >= 0)
                                	 bgenre = "총류";
                                	 else
                                		 if(genre == -1.0)
                                			 bgenre = "불명";
         
         this.tfbookname.setText(bookname);
         this.tfAuthor.setText(author);
         this.tfPublisehr.setText(publisher);
         this.tfByear.setText(byear);
         this.tfBCode.setText(bgenre);
         this.tfseri.setText(seri);
         this.tfea.setText(ea);

         
         
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
      tfbookname.setEditable(false);
      gbAdd(lblName, 0, 0, 1, 1);
      gbAdd(tfbookname, 1, 0, 3, 1);
      
      // 저자
      JLabel lblAthor = new JLabel("저자");
      tfAuthor = new JTextField(20);
      tfAuthor.setEditable(false);
      gbAdd(lblAthor, 0, 1, 2, 1);
      gbAdd(tfAuthor, 1, 1, 3, 1);
      
      // 발행일
      JLabel lbseri = new JLabel("시리즈");
      tfseri = new JTextField(20);
      tfseri.setEditable(false);
      
      gbAdd(lbseri, 0, 3, 2, 1);
      gbAdd(tfseri, 1, 3, 3, 1);
      
      // 발행일
      JLabel lblByear = new JLabel("발행일");
      tfByear = new JTextField(20);
      tfByear.setEditable(false);
      
      gbAdd(lblByear, 0, 4, 2, 1);
      gbAdd(tfByear, 1, 4, 3, 1);
      
      // 출판사
      JLabel lblPublisher = new JLabel("출판사");
      tfPublisehr = new JTextField(20);
      tfPublisehr.setEditable(false);
      
      gbAdd(lblPublisher, 0, 5, 2, 1);
      gbAdd(tfPublisehr, 1, 5, 3, 1);
      
      // 분류코드
      JLabel lblBCode = new JLabel("장르");
      tfBCode = new JTextField(20);
      tfBCode.setEditable(false);
      
      gbAdd(lblBCode, 0, 6, 2, 1);
      gbAdd(tfBCode, 1, 6, 3, 1);
      
   /*   // 책장르
      JLabel lblBGenre = new JLabel("장르");
      tfGenre = new JTextField(20);
      
      gbAdd(lblBGenre, 0, 5, 2, 1);
      gbAdd(tfGenre, 1, 5, 3, 1);
      */
      JLabel lblmany = new JLabel("수량");
      tfea = new JTextField(20);
      tfea.setEditable(false);

      
      gbAdd(lblmany, 0, 7, 2, 1);
      gbAdd(tfea, 1, 7, 3, 1);
   
      //버튼
       JPanel   pButton = new JPanel();
       btnupdate = new JButton("수정하기");
       btnBook = new JButton("삭제");
       btncencel = new JButton("취소");
       
       pButton.add(btnupdate);
       pButton.add(btnBook);
       gbAdd(pButton,0,8,4,1);
       
       //이벤트 연결  기능 추가
      this.btnupdate.addActionListener( this );
      this.btnBook.addActionListener(this);
      this.btncencel.addActionListener(this);
   

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

 //책수정    
   private void updatedook() {
      LibDao lDao = new  LibDao();
      String BOOKNAME = this.tfbookname.getText();
      String AUTHOR = this.tfAuthor.getText();
      String  PUBLISHER = this.tfPublisehr.getText();
      String BYEAR = this.tfByear.getText();
      int SERIES = Integer.parseInt(tfseri.getText());
      String BOOKCODE = this.tfBCode.getText();
      int EA = Integer.parseInt(tfea.getText());
    
      lDao.updatedook(this.booknum,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA);
     
  
     
   }
 
    
  
   
   //책 삭제
   private void bookdelet() {
       LibDao lDao = new  LibDao();
         int booknum = this.booknum;
         lDao.bookdel(booknum);
    
}
   

//버튼 이벤트 연결
  @Override
  public void actionPerformed(ActionEvent e) {
     switch(e.getActionCommand()) {
     case "수정하기":
    	 	tfbookname.setEditable(true);
    	 	tfAuthor.setEditable(true);
    	 	tfByear.setEditable(true);
    	 	tfPublisehr.setEditable(true);
    	 	tfBCode.setEditable(true);
    	 	tfseri.setEditable(true);
    	 	tfea.setEditable(true);
           
    	 	this.btnupdate.setText("수정");
            
            break;
     case "수정":
    	 updatedook();
    	 JOptionPane.showMessageDialog(null,"수정완료");
    	 break;
     case "삭제":
    	 bookdelet();
    	 this.dispose();
        break;
     case "취소":
    	 this.dispose();
        
     }
  }
   
   
}