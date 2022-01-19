package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.LibDao;
import model.LibVo;



public class BookList extends JFrame implements ActionListener,MouseListener,KeyListener,ItemListener
{
      // 아이디 찾기
   // Component  목록
      JButton             btnInsert,  btnRefresh,  btnselect,btntest;
      JPanel              topPane;
      JTable              jTable;    // 데이터 표시
      JScrollPane         pane;
      JTextField bookname;
      
      String bookcode = null;
      String bookcode1 = null;
      DefaultTableModel   dtm;
      
      JComboBox qa;
      
      JRadioButton jb0,jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;

      // Vector : ArrayList  thread safe 버전
      Vector              v;      // data list     
      Vector              cols;   // list 의 제목들
      
      static BookList blist = null;
      
      BookInpo binpo = null;
   
      
      // data  출력
      //  oracle resultset -> vector -> defaultmodel -> jtable
      
      
      
      // 생성자
      public BookList() {
         initComponent();
      }
      
      
      
      // initComponent()
      private void initComponent() {
         this.setTitle("도서검색");
         
         jTable  = new JTable();
         jTable.setModel(
            new DefaultTableModel( getDataList(), getColumns() ) {
               public boolean  isCellEditable(int row, int column) {
                  //각 셀마다 편집가능 false 편집기능 해제
                  return false;
               }
            }
         );
         
      
         
         jTable.setRowHeight(35);
         
         topPane     =   new JPanel();
         topPane.setLayout(new FlowLayout());
         btnselect = new JButton("검색");
      
         pane    =   new JScrollPane( jTable );
         this.add(pane);
         
         bookname = new JTextField(80);
         

         
         btnRefresh  =   new JButton("새로고침");
         String[] list =  {"제목","저자","출판사"};
         qa   =   new JComboBox(list);
       
         
       
         topPane.add(btnRefresh);
         topPane.add( qa );
         topPane.add(bookname);
         topPane.add(btnselect);
         
         
         //목록 버튼 추가하기
         jb0 = new JRadioButton("총류");
         jb1 = new JRadioButton("철학");
         jb2 = new JRadioButton("종교");
         jb3 = new JRadioButton("사회과학");
         jb4 = new JRadioButton("자연과학");
         jb5 = new JRadioButton("기술과학");
         jb6 = new JRadioButton("예술");
         jb7 = new JRadioButton("언어");
         jb8 = new JRadioButton("문학");
         jb9 = new JRadioButton("역사");
         jb10 = new JRadioButton("선택없음");
        
         ButtonGroup groups = new ButtonGroup();
         groups.add(jb0);
         groups.add(jb1);
         groups.add(jb2);
         groups.add(jb3);
         groups.add(jb4);
         groups.add(jb5);
         groups.add(jb6);
         groups.add(jb7);
         groups.add(jb8);
         groups.add(jb9);
         groups.add(jb10);
         
         JPanel pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
         pButton.add(jb10);
         pButton.add(jb0);
         pButton.add(jb1);
         pButton.add(jb2);
         pButton.add(jb3);
         pButton.add(jb4);
         pButton.add(jb5);
         pButton.add(jb6);
         pButton.add(jb7);
         pButton.add(jb8);
         pButton.add(jb9);
         
           JPanel  jP = new JPanel(new BorderLayout());
            jP.add(topPane,BorderLayout.NORTH);
            jP.add(pButton,BorderLayout.CENTER);

         this.add( jP, BorderLayout.NORTH);
         this.add(pane,BorderLayout.CENTER);
         
         // 버튼 이벤트 연결
         //btnInsert에 연결된 이벤트가 처리될 객체는 actionPerfromed()함수를 가지고 있는 this다
         
         this.btnRefresh.addActionListener( this );
         this.btnselect.addActionListener(this);
         this.jb0.addActionListener(this);
         this.jb1.addActionListener(this);
         this.jb2.addActionListener(this);
         this.jb3.addActionListener(this);
         this.jb4.addActionListener(this);
         this.jb5.addActionListener(this);
         this.jb6.addActionListener(this);
         this.jb7.addActionListener(this);
         this.jb8.addActionListener(this);
         this.jb9.addActionListener(this);
         this.jb10.addActionListener(this);
         
         this.jb10.addItemListener(this);
         this.bookname.addKeyListener(this);
                
         //  jtable 이벤트 연결 - 마우스 
         // jtable 안에 있는 리스트를 클릭하면
         this.jTable.addMouseListener(this);
         
         
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setSize(1920, 700);
         this.setVisible(true);      
      }

      // 테이블에 출력할 자료검색
      private Vector getDataList() {
         
         LibDao   ldao = new LibDao();
         Vector    v    = ldao.getbooklist();
   
         return  v;
      }

      // header 지정 - 제목
      private Vector getColumns() {
         Vector  cols = new Vector();
         cols.add("책번호");
         cols.add("제목");
         cols.add("저자");
         cols.add("출판사");
         cols.add("출판년도");
         cols.add("시리즈");
         cols.add("분류번호");
         cols.add("수량");        
         return  cols;
         
      }

      
      
      // main ()
      public static void main(String[] args) {
      
         blist =  new BookList();
         
      }   
      //테이블에 출력할 자료 검색
      private Vector getgenre(int num1, int num2) {
         LibDao lBo = new LibDao();
         Vector g = lBo.getgenre(num1, num2);
         return g;
      }

      
      
      // 이벤트 목록들
      //  버튼 클릭
      @Override
      public void actionPerformed(ActionEvent e) {
    	  if(jb9.isSelected()) {
              bookcode = "900";
              bookcode1 = "999";
              jTableGenre();
           }
           else 
              if(jb8.isSelected()) {
              bookcode = "800";
              bookcode1 = "899";
              jTableGenre();
           }
              else
                 if(jb7.isSelected()) {
                    bookcode = "700";
                    bookcode1 = "799";
                    jTableGenre();
                 }
                 else
                    if(jb6.isSelected()) {
                       bookcode = "600";
                       bookcode1 = "699";
                       jTableGenre();
                    }
                    else
                       if(jb5.isSelected()) {
                          bookcode = "500";
                          bookcode1 = "599";
                          jTableGenre();
                       }
                       else
                          if(jb4.isSelected()) {
                             bookcode = "400";
                             bookcode1 = "499";
                             jTableGenre();
                          }
                          else
                             if(jb3.isSelected()) {
                                bookcode = "300";
                                bookcode1 = "399";
                                jTableGenre();
                             }
                             else
                                if(jb2.isSelected()) {
                                   bookcode = "200";
                                   bookcode1 = "299";
                                   jTableGenre();
                                }
                                else
                                   if(jb1.isSelected()) {
                                      bookcode = "100";
                                      bookcode1 = "199";
                                      jTableGenre();
                                   }
                                   else if(jb0.isSelected()) {
                                      bookcode = "000";
                                      bookcode1 = "099";
                                      jTableGenre();
                                   }
 
      
      
      

    	  switch( e.getActionCommand() ) {
          case  "검색":
        	  jTableSearch();
             break;
          case "새로고침":
        	  jTableRefresh();
        	 break;
        	 
        	  
    	  }
         
      }

	private Vector searchList() {
	       
		String search = "%" + this.bookname.getText() + "%";
		String item = (String)qa.getSelectedItem();
        LibDao   ldao = new LibDao();
        Vector    v    = ldao.SearchBooklist(item,search);
  
      
        return  v;

	}
	
	public void jTableSearch() {
         
         DefaultTableModel  dataModel = initTable();
         jTable.setModel(
         new DefaultTableModel( searchList(), getColumns() ) {
            public boolean  isCellEditable(int row, int column) {
               //각 셀마다 편집가능 false 편집기능 해제
               return false;
            }
         });

        
         jTable.repaint();  
	}


	// jTableRefresh - 데이터 새로고침
      public void jTableRefresh() {
        
    	 if(this.bookname.getText() == null  && (String)qa.getSelectedItem() == " ") {
         DefaultTableModel  dataModel = initTable();
         jTable.setModel(
         new DefaultTableModel( getDataList(), getColumns() ) {
            public boolean  isCellEditable(int row, int column) {
               //각 셀마다 편집가능 false 편집기능 해제
               return false;
            }
         });
    	 }else if(this.bookname.getText() != null && (String)qa.getSelectedItem() != " ") {
    		 jTableSearch();
    	 }
        
         jTable.setRowHeight(35);
         jTable.repaint();    // swing component : ui 요소를 새로그린다      
    	 
      }

      // initTable()
      private DefaultTableModel initTable() {
         // 제목줄 처리 : cols
         cols  =  getColumns();
         // 데이터 처리 : v
         v     =  getDataList();
         DefaultTableModel  dtm = new DefaultTableModel( v, cols );
         return  dtm;
      }
      @Override
      public void mouseClicked(MouseEvent e) {
          int       r      =  jTable.getSelectedRow();
          int       c      =  jTable.getSelectedColumn();
          int    bookcode     = (Integer) jTable.getValueAt(r,  0);  // 클릭한 userid
     
          if( binpo != null )
             binpo.dispose();
          binpo  = new BookInpo(bookcode,this);
       }

      private Vector genrelist() { 
          LibVo lvo = new LibVo();
        String ge1 = bookcode;
        String ge2 = bookcode1;
          LibDao   ldao = new LibDao();
          Vector    v    = ldao.SearchGenre(ge1,ge2);
          return  v;
     }
  
     //분류 눌렀을 때 나오는 데이터
     private void jTableGenre() {
         DefaultTableModel  dataModel = initTable();
           jTable.setModel(
           new DefaultTableModel( genrelist(), getColumns() ) {
              public boolean  isCellEditable(int row, int column) {
                 //각 셀마다 편집가능 false 편집기능 해제
                 return false;
              }
           });

     }

      @Override
      public void mousePressed(MouseEvent e) {
         
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
      
      //enter 눌렀을 때 도서 검색 
      @Override
      public void keyReleased(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	 jTableSearch();
         }
         
      }

      @Override
      public void keyTyped(KeyEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void keyPressed(KeyEvent e) {
         // TODO Auto-generated method stub
         
      }
      @Override
      public void itemStateChanged(ItemEvent e) {
    
    	  			jTableRefresh();
 
         }



      

      
   

   }