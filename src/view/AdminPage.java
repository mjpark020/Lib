package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.LibDao;



public class AdminPage extends JFrame implements ActionListener,MouseListener{

   
   
   // Component  목록
   JButton             btnbooklist,  btnRefresh,btnlogout;
   JPanel              topPane;
   JTable              jTable;    // 데이터 표시
   JScrollPane         pane;
   
   DefaultTableModel   dtm;
   
   // Vector : ArrayList  thread safe 버전
   Vector              v;      // data list     
   Vector              cols;   // list 의 제목들
   
   static AdminPage apge = null;
   
   // data  출력
   //  oracle resultset -> vector -> defaultmodel -> jtable
   
   //LibProc  lProc = null;
   AdminProc aProc = null;
   
   BookList list = null;
   BookList_admin admin= null;
   Login login = null;
   
   // 생성자
   public AdminPage() {
      initComponent();
   }
   
   // initComponent()
   private void initComponent() {
      this.setTitle("관리자");
      
      jTable  = new JTable();
      jTable.setModel(
         new DefaultTableModel( getDataList(), getColumns() ) {
            public boolean  isCellEditable(int row, int column) {
               //각 셀마다 편집가능 false 편집기능 해제
               return false;
            }
         }
      );
      pane    =   new JScrollPane( jTable );
      this.add(pane);
      
      topPane     =   new JPanel();
      btnbooklist   =   new JButton("도서목록");
      topPane.add( btnbooklist );
      btnRefresh  =   new JButton("새로고침");
      topPane.add(btnRefresh);

      btnlogout  =   new JButton("로그아웃");
      topPane.add(btnlogout);


      this.add( topPane, BorderLayout.NORTH);
      
      // 버튼 이벤트 연결
      //btnInsert에 연결된 이벤트가 처리될 객체는 actionPerfromed()함수를 가지고 있는 this다
      this.btnbooklist.addActionListener( this );
      this.btnRefresh.addActionListener( this );
      this.btnlogout.addActionListener(this);
             
      //  jtable 이벤트 연결 - 마우스 
      // jtable 안에 있는 리스트를 클릭하면
      this.jTable.addMouseListener(this);
      
      
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize(600, 500);
      this.setVisible(true);      
   }

   // 테이블에 출력할 자료검색
   private Vector getDataList() {
      
      
      LibDao lDao = new LibDao();
      Vector    v    = lDao.getUserList();

      
      return  v;
   }

   // header 지정 - 제목
   private Vector getColumns() {
      Vector  cols = new Vector();
      cols.add("아이디");
      cols.add("이름");
      cols.add("생년월일");
      cols.add("성별");
      cols.add("전화번호");
      cols.add("가입일");
      return  cols;
   }

   // main ()
   public static void main(String[] args) {
   
      apge = new AdminPage();
      
   }   
   
   
   // 이벤트 목록들
   //  버튼 클릭
   @Override
   public void actionPerformed(ActionEvent e) {
      switch( e.getActionCommand() ) {
      case  "도서목록": 
    	  if(admin != null)
    		  admin.dispose();
    	  admin = new BookList_admin();
         break;
      case  "새로고침":
         jTableRefresh();
         break;
      case "로그아웃":{
			this.dispose();
			login = new Login();
			break;
		}
   
      }
      
   }

   // jTableRefresh - 데이터 새로고침
   public void jTableRefresh() {
      
      DefaultTableModel  dataModel = initTable();
      jTable.setModel(
      new DefaultTableModel( getDataList(), getColumns() ) {
         public boolean  isCellEditable(int row, int column) {
            //각 셀마다 편집가능 false 편집기능 해제
            return false;
         }
      });
      jTable.repaint();    // swing component : ui 요소를 새로그린다.
      
      
   }

   // initTable()
   private DefaultTableModel initTable() {
      // 제목줄 처리 : cols
      cols  =  getColumns();
      // 데이터 처리 : ㅍ
      v     =  getDataList();
      DefaultTableModel  dtm = new DefaultTableModel( v, cols );
      return  dtm;
   }

   
   
   @Override
   public void mouseClicked(MouseEvent e) {
      int       r      =  jTable.getSelectedRow();
      int       c      =  jTable.getSelectedColumn();
      String    id     =  (String) jTable.getValueAt(r,  0);  // 클릭한 userid
      if( aProc != null )
         aProc.dispose();
      aProc  = new AdminProc(id,this);
      
   
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

}
