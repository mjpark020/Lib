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

public class LibUserBookInfo extends JFrame  implements ActionListener, MouseListener {
   JTextField tfbookname, tfAuthor, tfByear, tfPublisehr, tfBCode,tfGenre, tfRent,tfseri;
   String bookname;
   String author; 
   String publisher;
   String byear;
   String bookcode;
   String seri;
   double genre;
   JButton    btnExtens, btnReturn;
   
   int booknum;
   String userid;
   
   
   GridBagLayout gb;
   GridBagConstraints gbc; // option

   
   
   //기본 생성자
   public LibUserBookInfo() {
      initComponent();
   }
   
   //버튼 클릭했을 때 생성자
   public LibUserBookInfo(int booknum, String userid) {
//	   this();
	   this.booknum = booknum;
	   this.userid = userid;
	   initComponent();
	   
   }
   
   //---------------------------------------------
   private void initComponent() {
	   LibDao   ldao = new LibDao();
       LibVo book = ldao.getBook(booknum);
	   
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
      bookname = book.getBookname();
      tfbookname.setText(bookname);
      tfbookname.setEditable(false);
      gbAdd(lblName, 0, 0, 1, 1);
      gbAdd(tfbookname, 1, 0, 3, 1);
      
      // 저자
      JLabel lblAthor = new JLabel("저자");
      tfAuthor = new JTextField(20);
      author   = book.getAuthor(); 
      tfAuthor.setText(author);
      tfAuthor.setEditable(false);
      gbAdd(lblAthor, 0, 1, 2, 1);
      gbAdd(tfAuthor, 1, 1, 3, 1);
      
      // 시리즈
      JLabel lbseri = new JLabel("시리즈");
      tfseri = new JTextField(20);
      seri =  Integer.toString(book.getSeries());
      tfseri.setText(seri);
      tfseri.setEditable(false);
      
      gbAdd(lbseri, 0, 3, 2, 1);
      gbAdd(tfseri, 1, 3, 3, 1);
      
      // 발행일
      JLabel lblByear = new JLabel("발행일");
      tfByear = new JTextField(20);
      byear     = book.getByear();
      tfByear.setText(byear);
      tfByear.setEditable(false);
      
      gbAdd(lblByear, 0, 4, 2, 1);
      gbAdd(tfByear, 1, 4, 3, 1);
      
      // 출판사
      JLabel lblPublisher = new JLabel("출판사");
      tfPublisehr = new JTextField(20);
      publisher = book.getPublisher();
      tfPublisehr.setText(publisher);
      tfPublisehr.setEditable(false);
      
      gbAdd(lblPublisher, 0, 5, 2, 1);
      gbAdd(tfPublisehr, 1, 5, 3, 1);
      
      // 분류코드
      JLabel lblBCode = new JLabel("분류코드");
      tfBCode = new JTextField(20);
      bookcode  =  book.getBookcode();
      tfBCode.setText(bookcode);
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
       btnExtens = new JButton("연장");
       LibDao exDao = new LibDao();
       // 연장이력 있으면 true, 없으면 false 
       boolean is = exDao.notRentExtension(booknum, userid);
       if( is ) {
    	   btnExtens.setEnabled(false);
       }
       
       btnReturn = new JButton("반납");
       
       pButton.add(btnExtens);
       pButton.add(btnReturn);
       gbAdd(pButton,0,7,4,1);
       
       //이벤트 연결  기능 추가
      this.btnExtens.addActionListener( this );
      this.btnReturn.addActionListener(this);
   

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
   
   private void setBcode(LibVo book) {
       
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
       
       
       this.tfAuthor.setText(author);
       this.tfBCode.setText(bgenre);
       this.tfseri.setText(seri);

    }
   
   // main
      public static void main(String[] args) {
         new LibUserBookInfo();
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

   @Override
   public void actionPerformed(ActionEvent e) {
	   
	   LibDao exDao = null;
	   switch( e.getActionCommand() ) {
	   		case "연장" : 
	   			exDao = new LibDao();
	   			// 연장이력 있으면 true, 없으면 false 
	   			boolean is = exDao.notRentExtension(booknum, userid);
	   			if( !is ) {
	   				exDao.rentExtension(booknum, userid);
	   				JOptionPane.showMessageDialog(null, "연장되었습니다","대출 연장",JOptionPane.PLAIN_MESSAGE);
	   			}else {
	   				btnExtens.setEnabled(false);
	   				JOptionPane.showMessageDialog(null, "연장불가", "실패", JOptionPane.OK_OPTION);
	   			}
	   			break;
	   			
	   		case "반납" : 
	   			int i = JOptionPane.showConfirmDialog(null,
						"반납 하시겠습니까?",
						"도서 반납",
						JOptionPane.YES_NO_OPTION
					);
	   			if(i == JOptionPane.YES_OPTION) {
	   				exDao = new LibDao();
	   				// 반납 여부
	   				int isReturned = exDao.isReturned(booknum, userid);
	   				exDao.deleteBook(booknum, userid);
	   				if( isReturned > 0 ) {
	   					JOptionPane.showMessageDialog(null, 
	   							"반납이 완료되었습니다", 
	   							"반납 완료", 
	   							JOptionPane.PLAIN_MESSAGE);
	   					this.dispose();
	   				}else {
	   					JOptionPane.showMessageDialog(null, 
	   							"이미 반납된 책입니다", 
	   							"중복에러", 
	   							JOptionPane.OK_OPTION);	   					
	   					this.dispose();
	   				}
	   			}
	   			
	   }
      
   }
  
}