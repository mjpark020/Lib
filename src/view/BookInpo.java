package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.LibDao;
import model.LibVo;

public class BookInpo extends JFrame  implements ActionListener, MouseListener {
   JTextField tfbookname, tfAuthor, tfByear, tfPublisehr, tfBCode,tfGenre, tfRent,tfseri;
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
   public BookInpo() {
      initComponent();
   }

 //버튼 클릭했을 때 생성자
   public BookInpo(int booknum, BookList booklist) {
      this();
      LibDao   ldao = new LibDao();
      this.booklist = booklist;
      this.booknum = booknum;
      viewData();
      LibVo user = ldao.getBook(booknum);
      if(user.getEa()>0) {
         this.btnRent.setEnabled(true);
         this.btnBook.setEnabled(false);
      }else {
         this.btnBook.setEnabled(true);
         this.btnRent.setEnabled(false);
      }
      
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
         String bookcode  =  user.getBookcode();
         String seri =  Integer.toString(user.getSeries());
         
         double genre = Double.parseDouble(bookcode);
         String bgenre;
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
                                    bgenre = "총류";
         
         this.tfbookname.setText(bookname);
         this.tfAuthor.setText(author);
         this.tfPublisehr.setText(publisher);
         this.tfByear.setText(byear);
         this.tfBCode.setText(bgenre);
         this.tfseri.setText(seri);

         
         
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
   
      //버튼
       JPanel   pButton = new JPanel();
       btnRent = new JButton("대출");
       btnBook = new JButton("예약");
       
       pButton.add(btnRent);
       pButton.add(btnBook);
       gbAdd(pButton,0,7,4,1);
       
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
   private void rentbook() {
      LibDao lDao = new  LibDao();
      String userid = Login.id.getText();
      int booknum = this.booknum;
      String result = lDao.rentbook(booknum, userid);
     
   }
   
   
   

    
  
   
   //예약
   private void bookB() {
       LibDao lDao = new  LibDao();
         String userid = Login.id.getText();
         int booknum = this.booknum;
         
         int is = lDao.isReturned(booknum, userid);
         if(is == 0) {
        	 lDao.bookB(booknum, userid);
        	 JOptionPane.showMessageDialog(null, 
        			 "예약되었습니다",
        			 "예약",
        			 JOptionPane.PLAIN_MESSAGE);
         }else {
        	 JOptionPane.showMessageDialog(null, 
        			 "이미 대출중인 책입니다",
        			 "예약불가",
        			 JOptionPane.OK_OPTION);
         }
}
   
   private void mibook() {
	  LibDao lDao = new LibDao();
	  
	  lDao.mibook(this.booknum);
			  
	   
   }
   

//버튼 이벤트 연결
  @Override
  public void actionPerformed(ActionEvent e) {
     switch(e.getActionCommand()) {
     case "대출":
            rentbook();
            this.dispose();
            break;
     case "예약":
        bookB();
        this.btnBook.setEnabled(false);
        break;
        
     }
  }

private void history() {
	
	LibDao lDao = new  LibDao();
     String userid = Login.id.getText();
     int booknum = this.booknum;
    lDao.bookhistory(booknum, userid);
    
	
}
   
   
}