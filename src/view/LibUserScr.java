// 2
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.LibDao;
import model.LibVo;


public class LibUserScr extends JFrame implements ActionListener, MouseListener{
   
   JPanel top;
   
   JLabel lblName; // 유저 이름 타이틀
   JButton btnLogout; // 로그아웃 버튼
   JButton btnBookLi; // 책 목록 버튼
   JButton btnBookRe; // 예약된 책 목록 버튼
   JLabel lblUserI; //  내 정보 타이틀
   
   JButton Refresh;
   
   JLabel lblId; // id 레이블
   JTextField tfId; // id 표시
   JLabel lblTel; // 내 정보 전화번호
   JTextField tfTel; // 전화번호 입력창
   
   JLabel lblAddr; // 내 정보 주소
   JTextField tfAddr; // 내 정보 주소 입력창
   
   JLabel lblIndate; // 내 정보 가입일
   JTextField tfIndate; // 가입일 입력창
   
   JLabel lblBooks; // 내 총 대출 권수 // rent count
   JTextField tfBooks; // 총 대출 권수 입력창
   JButton btnEdit; // 정보수정 버튼
    JButton btnDrop; // 회원탈퇴 버튼   
   
    JLabel lblMyBook; // 내 서재 타이틀
    JTable books; // 테이블
    JButton btnExten; // 대출 연장 신청 버튼
   
    // Vo로 유저정보 가져와서 getter 이용하여 내 정보 출력 예정
   
   String userid = Login.id.getText();
   LibDao dao = new LibDao();
   LibVo user = dao.getUser(userid);
   String name = user.getUsername(); // 가져올 유저 이름
   
   BookList list = null;
   Login log = null;
   // 연결 창들
   // 로그인창
   // 책 목록 (도서검색)
   LibEditUser edit = null; // 정보수정 창
   LibUserBookInfo  binpo = null;
   static LibUserScr libuser = null;
   LibReserve LibRe = null;

private AbstractButton jTable;

   Vector cols;

   Vector v;



   //LibUserBookInfo bInfo = null; // 책 상세정보 창
   
   public LibUserScr(){
      init();
      
      setTitle("사용자 화면");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
   
   public LibUserScr(String userid, String name) {
      this.userid = userid;
      this.name = name;
   }
   
   public void init() {
      
      top = new JPanel();
      top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
      top.setAlignmentX(LEFT_ALIGNMENT);
      
      // 이름, 로그아웃 버튼
      JPanel mid1 = new JPanel(new FlowLayout());
      
      JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
      lblName = new JLabel("홍길동님");
      lblName.setText(name + " 님");     // 로그인된 유저 이름 가져와서 넣을것
      lblName.setFont(new Font("맑은 고딕", Font.BOLD, 28));
      
      JPanel myBtns = new JPanel();
      myBtns.setLayout(new GridLayout(3,1));
      btnLogout = new JButton("로그아웃");
      btnLogout.addActionListener(this);
      btnBookLi = new JButton(" 책  목록 "); 
      btnBookLi.addActionListener(this);
      btnBookRe = new JButton("예약목록");
      btnBookRe.addActionListener(this);
      
      Refresh = new JButton("새로고침");
      Refresh.addActionListener(this);
      
      
      pName.add(lblName);
      myBtns.add(btnLogout);
      myBtns.add(btnBookLi);
      myBtns.add(btnBookRe);
//      myBtns.add(Refresh);
      
      mid1.add(pName);
      mid1.add(Box.createHorizontalStrut(110));
      mid1.add(myBtns);
   
      
      // 내 정보
      JPanel mid2top = new JPanel();
      mid2top.setLayout(new BoxLayout(mid2top, BoxLayout.Y_AXIS));
      JPanel mid2 = new JPanel();
      mid2.setLayout(new GridLayout(6,2));
      
      lblUserI = new JLabel("내 정보");
      lblUserI.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      
      lblId = new JLabel("아이디: ");
      tfId = new JTextField();
      tfId.setText(userid);
      tfId.setEditable(false);
      tfId.setBackground(Color.white);
      
      lblTel = new JLabel("전화번호: ");
      tfTel = new JTextField();
      tfTel.setText(user.getTel());
      tfTel.setEditable(false);
      tfTel.setBackground(Color.white);
      
      lblAddr = new JLabel("주소: "); // 내 정보 주소
      tfAddr = new JTextField();
      tfAddr.setText(user.getAddress());
      tfAddr.setEditable(false);
      tfAddr.setBackground(Color.white);
      
      lblIndate = new JLabel("가입일: "); // 내 정보 가입일
      tfIndate = new JTextField();
      tfIndate.setText(user.getIndate());
      tfIndate.setEditable(false);
      tfIndate.setBackground(Color.white);
      
      lblBooks = new JLabel("총 대출 권수: ");
      tfBooks = new JTextField();
      tfBooks.setText(Integer.toString(dao.userRentBook(userid)));
      tfBooks.setEditable(false);
      tfBooks.setBackground(Color.white);
      
      mid2.add(lblUserI);
      mid2.add(Box.createHorizontalStrut(10));
      mid2.add(lblId);
      mid2.add(tfId);
      mid2.add(lblTel);
      mid2.add(tfTel);
      mid2.add(lblAddr);
      mid2.add(tfAddr);
      mid2.add(lblIndate);
      mid2.add(tfIndate);
      mid2.add(lblBooks);
      mid2.add(tfBooks);
      
      JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      btnEdit = new JButton("정보수정");
      btnEdit.addActionListener(this);
      
      btnDrop = new JButton("회원탈퇴");
      btnDrop.addActionListener(this);
      btns.add(btnEdit);
      btns.add(btnDrop);
      
      mid2top.add(mid2);
      mid2top.add(btns);
      
      // 내 서재
      JPanel pMybook = new JPanel();
      pMybook.setLayout(new BoxLayout(pMybook, BoxLayout.Y_AXIS));
      
      JPanel plbl = new JPanel(new FlowLayout(FlowLayout.LEFT));
      lblMyBook = new JLabel("내 서재");
      lblMyBook.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      plbl.add(lblMyBook);
      
      JPanel pbtnre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      pbtnre.add(Refresh);
      
      // 연장신청
      JPanel pbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//      btnExten = new JButton("연장신청");
//      btnExten.addActionListener(this);
//      pbtn.add(btnExten);

      
      books = new JTable();
      books.setModel(
            new DefaultTableModel( getDataList(), getColumns() ) {
               public boolean  isCellEditable(int row, int column) {
                  return false;
               }
            }
      );
      // 행 클릭
      books.addMouseListener(this);
      
      // 테이블 안 버튼
//      DefaultTableCellRenderer renderer = new MyDefaultTableCellRenderer();
//      books.getColumn("정보 / 연장").setCellRenderer(renderer);
      
      books.setRowHeight(30);
      // 책 번호 숨기기
      books.getColumn("bn").setWidth(0);
      books.getColumn("bn").setMinWidth(0);
      books.getColumn("bn").setMaxWidth(0);
      
      books.getColumn("반납예정일").setWidth(80);
      books.getColumn("반납예정일").setMinWidth(80);
      books.getColumn("반납예정일").setMaxWidth(80);
      
      
      JScrollPane scroll = new JScrollPane(books);
      
      pMybook.add(plbl);
      pMybook.add(pbtnre);
      pMybook.add(pbtn);
      pMybook.add(scroll);
      

      // top.add------------------------------
      top.add(Box.createVerticalStrut(10));
      
      // 사용자 이름, 로그아웃 버튼
      top.add(mid1);
      // 사용자 정보
      top.add(mid2top);
      top.add(Box.createVerticalStrut(10));
      top.add(pMybook);
      
      add(top);
      
      this.addWindowListener(new  WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			user = new LibVo();
			user = dao.getUser(userid);
			tfId.setText(userid);
		    tfTel.setText(user.getTel());
		    tfAddr.setText(user.getAddress());
		    tfIndate.setText(user.getIndate());
		    tfBooks.setText(Integer.toString(dao.userRentBook(userid)));
		    
		    System.out.println(user.getTel());
			
		}
	});
      
   }
   
   
   private Vector getColumns() {
      Vector v = new Vector();
      v.add("bn");
      v.add("책이름");
      v.add("반납예정일");
//      v.add("정보 / 연장");
      
      return v;
   }
   private Vector getDataList() {
      Vector v = new Vector();
      LibDao lDao = new LibDao();
      v = lDao.myBooks(userid);
      
      return v;
   }
   
   // 이벤트
   @Override
   public void actionPerformed(ActionEvent e) {
      switch( e.getActionCommand() ) {
      case "로그아웃":
         this.dispose();
         new Login();
         break;
      
      case " 책  목록 ": 
         if(list != null)
            list.dispose();
         list = new BookList();
         break;
      
      case "예약목록": 
         if(LibRe != null)
        	 LibRe.dispose();
        	 LibRe = new LibReserve(Login.id.getText());
         
         
         break;
      
      case "정보수정" : 
         if( edit == null) {
            edit = new LibEditUser(userid);
         }else {
            edit.dispose();
            edit = new LibEditUser(userid);
         }
         break;
      
      case  "회원탈퇴": 
         
            int i = JOptionPane.showConfirmDialog(null,
                     "회원탈퇴 하시겠습니까?",
                     "회원탈퇴",
                     JOptionPane.YES_NO_OPTION
                  );
            if(i == JOptionPane.YES_OPTION) {
               LibDao lDao = new LibDao();
               lDao.deleteRent(userid);
               int result = lDao.deleteUser(userid);
               if(result != 0) {
                  JOptionPane.showMessageDialog(null,
                        "회원탈퇴 되었습니다",
                        "탈퇴 완료",
                        JOptionPane.PLAIN_MESSAGE);
                  // 이 창 닫고 로그인 창 띄우기
                  this.dispose();
               }else {
               JOptionPane.showMessageDialog(null,
                     "잘못된 접근입니다",
                     "탈퇴 실패",
                     JOptionPane.OK_OPTION);
               }
            }
            break;
         
         
         case "새로고침":
        	jTableRefresh();
        	break;
        	 
         
      }
      
   }
   
   public void jTableRefresh() {

	      DefaultTableModel  dataModel = initTable();
	      books.setModel( dataModel );
	      books.getColumn("bn").setWidth(0);
	      books.getColumn("bn").setMinWidth(0);
	      books.getColumn("bn").setMaxWidth(0);
	      books.getColumn("반납예정일").setWidth(80);
	      books.getColumn("반납예정일").setMinWidth(80);
	      books.getColumn("반납예정일").setMaxWidth(80);
	      books.repaint();    // swing component : ui 요소를 새로그린다.

	   }
   
   private DefaultTableModel initTable() {
	      // 제목줄 처리 : cols
	      cols  =  getColumns();
	      // 데이터 처리 : ㅍ
	      v     =  getDataList();
	      DefaultTableModel  dtm = new DefaultTableModel( v, cols );
	      return  dtm;
	   }

// mouseListener
   @Override
   public void mouseClicked(MouseEvent e) {
      int       r      =  books.getSelectedRow();
      int       c      =  books.getSelectedColumn();
      String    id     =  userid;  // 로그인한 userid
      int booknum = (Integer) books.getValueAt(r,  0); // 선택한 북넘버
      System.out.println(booknum); // 테스트  
      if( binpo != null )
    	  binpo.dispose();
      binpo =  new LibUserBookInfo(booknum, Login.id.getText());
   
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
   
   
   // TableCellRenderer 테이블 안 버튼
   public class MyDefaultTableCellRenderer extends DefaultTableCellRenderer{

      @Override
      public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
           Component comp = null;
         JPanel p = new JPanel();
           if(column==2){
              JButton btn1 = new JButton("정보");
              JButton btn2 = new JButton("연장");
              
              btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
              btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
              
              btn1.setPreferredSize(new Dimension(54,20));
              btn2.setPreferredSize(new Dimension(54,20));
              p.add(btn1);
              p.add(btn2);
              comp = p;
         }
         return comp;
      }
   }
   
   
   public static void main(String[] args) {
     libuser =  new LibUserScr();

   }

}