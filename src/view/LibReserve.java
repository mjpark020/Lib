package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.DBConn;
import model.LibDao;
import model.LibVo;

public class LibReserve extends JFrame
implements ActionListener,MouseListener,KeyListener{
	//컴포넌트 목록 
	JButton bReserve,bCancel,bBorrow,bRefresh;
	JPanel topPane,bottomPane;
	JTable jTable;//데이터 표시
	JScrollPane pane;
	DefaultTableModel dtm;
	String userid = "aaa";
	Vector v;// data list
	Vector cols;//list 의 제목들 
	int booknum = 0; // 선택한 책 번호

	//static LibReserve blist =null;

	//   BookInpo binpo = null;

	public LibReserve() {

	}

	public LibReserve(String userid) {
		this.userid = userid;
		initComponent();
	}


	private void initComponent() {
		this.setTitle("예약목록");

		jTable =  new JTable();
		jTable.setModel(
				new DefaultTableModel(getReserveList(),getColumns()) {
					public boolean isCellEditable(int row, int column) {
						return  false;
					}
				}
				);
		jTable.setRowHeight(35);
		jTable.getColumn("책번호").setWidth(0);
		jTable.getColumn("책번호").setMinWidth(0);
		jTable.getColumn("책번호").setMaxWidth(0);

		topPane     =   new JPanel();
		topPane.setLayout(new FlowLayout());
		bRefresh = new JButton("새로고침");
		bCancel = new JButton("예약취소");
		pane    =   new JScrollPane( jTable );
		this.add(pane);


		topPane.add(bRefresh,BorderLayout.WEST);
		topPane.add(bCancel,BorderLayout.EAST);

		this.bRefresh.addActionListener( this );
		this.bCancel.addActionListener( this );

		JPanel  jP = new JPanel(new BorderLayout());
		jP.add(topPane,BorderLayout.NORTH);

		this.jTable.addMouseListener(this);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1920, 700);
		this.setVisible(true); 


		this.add( jP, BorderLayout.NORTH);
	}


	// 메소드
	// 테이블 행 - 예약 정보
	private Vector getReserveList() {
		Vector v = getreservelist( userid);
		return v;  
	}

	//header  지정 - 제목
	private Vector getColumns() {
		Vector cols = new Vector();
		cols.add("책번호");
		cols.add("제목");
		cols.add("저자");
		cols.add("시리즈");
		cols.add("분류번호");
		cols.add("예약일");      
		cols.add("예약순번");      

		return cols;
	}

	// 새로고침
	private void clearViewData() {
		DefaultTableModel  dataModel = new DefaultTableModel(getReserveList(), getColumns()) {
			public boolean isCellEditable(int row, int column) {
				return  false;
			}
		};
		jTable.setModel( dataModel );
		jTable.getColumn("책번호").setWidth(0);
		jTable.getColumn("책번호").setMinWidth(0);
		jTable.getColumn("책번호").setMaxWidth(0);
		jTable.repaint();
	}


	// dao
	//예약목록들어갔을때 예약된목록 조회
	public Vector getreservelist (String userid) {
		Vector  outV   = new Vector();   // 2차원 배열 : Resultset 전체

		Connection         conn   = null;
		PreparedStatement  pstmt  = null;
		ResultSet          rs     = null;

		String  sql  = " SELECT R.BOOKNUM NUM, B.BOOKNAME NAME, B.AUTHOR AUTH, B.SERIES SERI, B.BOOKCODE CODE, TO_CHAR(R.BOOKLENDING, 'YYYY-MM-DD') LENDINGDAY, R.PRIORITY PRI";
		sql         += " FROM BOOK B JOIN RESERVATION R ON R.BOOKNUM = B.BOOKNUM";
		sql         += " WHERE USERID = ?";

		try {
			conn      =  DBConn.getInstance();
			pstmt     =  conn.prepareStatement(sql);
			pstmt.setString(1, userid );

			rs  =  pstmt.executeQuery();
			while( rs.next() ) {
				//  v  :  조회한 한줄의 data
				// jTable 에 Vo 를 넣지 않도록 
				Vector    v  =   new  Vector(); 
				v.add( rs.getInt("NUM") );
				v.add( rs.getString("NAME") ); 
				v.add( rs.getString("AUTH") ); 
				v.add( rs.getString("SERI" ) ); 
				v.add( rs.getString("CODE") ); 
				v.add( rs.getString("LENDINGDAY") ); 
				v.add( rs.getInt("PRI") ); 
				outV.add( v );                     
			}
			/*cols.add("책번호");
	            cols.add("제목");
	            cols.add("저자");
	            cols.add("시리즈");
	            cols.add("분류번호");
	            cols.add("예약일");      
	            cols.add("예약순번");      
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs    != null )  rs.close();
				if( pstmt != null )  pstmt.close();
			} catch (SQLException e) {
			}
		} 
		return   outV;
	}


	// 예약 취소
	public int cancelReserve(int booknum, String userid) {
		// aftcnt > 0 예약목록에서 삭제 성공
		int aftcnt = 0;

		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";

		conn   =  DBConn.getInstance();
		sql    =  "DELETE   FROM  RESERVATION ";
		sql   +=  " WHERE   BOOKNUM  = ? ";
		sql   +=  " AND     USERID  = ? ";

		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setInt(1, booknum );
			pstmt.setString(2, userid );
			
			// 쿼리문 실행건수를 aftcnt에 저장 (몇개가 삭제되었는지)
			aftcnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return aftcnt;
	}



	// 이벤트
	//예약취소 , 새로고침 버튼 누를시
	public void actionPerformed(ActionEvent e) {
		switch( e.getActionCommand()  ) {
		case  "새로고침":  
			clearViewData();
			break;

		case  "예약취소": 
			// 대화창을 띄운다
			// showConfirmDialog = 예/아니오 선택가능한 대화창
			// 선택에 따라 int값을 리턴한다
			int i = JOptionPane.showConfirmDialog(null,
						"선택된 책을 예약취소합니다",
						"예약 취소",
						JOptionPane.YES_NO_OPTION
					);
			// 예를 눌렀을때
			if(i == JOptionPane.YES_OPTION) {
				// delete 실행
				// 같은 북넘버의 다른책 프라이어리티 1감소 - 나중에 무성이가 프로시저로 해준다했는지 기억이 안남 
				int is = cancelReserve(booknum, userid); // delete 실행 부분
				
				if(is > 0) {
					JOptionPane.showMessageDialog(null,
							"예약 취소 되었습니다",
							"취소 완료",
							JOptionPane.PLAIN_MESSAGE);
					// PLAIN_MESSAGE = 아무런 아이콘이 없는 확인창
					// OK_OPTION = 빨간 느낌표
				} else {
					JOptionPane.showMessageDialog(null,
							"이미 예약 취소된 책입니다",
							"에러",
							JOptionPane.OK_OPTION);
				}
			}
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseClicked(MouseEvent e) {
		int       r      =  jTable.getSelectedRow(); // 테이블에서 선택된 행번호

		// booknum에 r행 0번째 열의 값을 넣는다
		booknum = (int) jTable.getValueAt(r,  0);
		
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



	public static void main(String[] args) {

		
	}
}