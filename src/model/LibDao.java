package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.AdminPage;

public class LibDao {

	LibVo vo = null;
	String uid = "";
	String passwd = "";
	String username = "";
	int count = 0;

	public void loginck(String userid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID, PASSWD, USERNAME ";
		sql += "FROM LIB ";
		sql += "WHERE  USERID = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				uid = rs.getString("USERID");
				passwd = rs.getString("PASSWD");
				username = rs.getString("USERNAME");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
//            if (pstmt != null)
//             pstmt.close();
//         } catch (SQLException e) {
//         }
		}
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String insertUser(LibVo user) {
		String result = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "INSERT INTO LIB( USERID,PASSWD,PWQUEST,PWANSER,USERNAME,GENDER,BIRTH,TEL,ADDRESS,FAM,HOLDER )";
		sql += " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPwquest());
			pstmt.setString(5, user.getPwanser());
			pstmt.setString(6, user.getGender());
			pstmt.setString(7, user.getBirth());
			pstmt.setString(8, user.getTel());
			pstmt.setString(9, user.getAddress());
			pstmt.setInt(10, user.getFam());
			pstmt.setString(11, user.getHolder());

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.", "완료", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "필수항목이 입력되지 않았거나 중복된 계정입니다.", "중복", JOptionPane.OK_OPTION);
			System.out.println(e.getErrorCode());
		} finally {
//         try {
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}

		return result;
	}

	// 회원아이디 검색
	public LibVo getUser(String userid) {
		LibVo lv = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID, PASSWD,PWQUEST,PWANSER,USERNAME,BIRTH,GENDER,TEL,ADDRESS,FAM,HOLDER,INDATE,OVERDUE";
		sql += " FROM LIB ";
		sql += "WHERE  USERID = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				String uid = rs.getString("USERID");
				String passwd = rs.getString("PASSWD");
				String pwquest = rs.getString("PWQUEST");
				String pwanser = rs.getString("PWANSER");
				String username = rs.getString("USERNAME");
				String birth = rs.getString("BIRTH");
				String gender = rs.getString("GENDER");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				int fam = rs.getInt("FAM");
				String holder = rs.getString("HOLDER");
				String indate = rs.getString("INDATE");
				int overdue = rs.getInt("OVERDUE");

				lv = new LibVo(uid, passwd, pwquest, pwanser, username, birth, gender, tel, address, fam, holder,
						indate, overdue);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

//            if (rs != null)
//               rs.close();
//            if (pstmt != null)
//               pstmt.close();

		}
		return lv;
	}

	public void findPwd(String id, String name, String birth, String question, String anwser, String changePwd) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID, PASSWD, BIRTH, PWQUEST, PWANSER";
		sql += " FROM   LIB  ";
		sql += " WHERE  USERID = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String uid = rs.getString("userid");
				String passwd = rs.getString("passwd");
				String username = rs.getString("username");
				String job = rs.getString("job");
				String gender = rs.getString("gender");
				String intro = rs.getString("intro");
				String indate = rs.getString("indate");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
////            if (pstmt != null)
////               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
	}

	public Vector getbooklist() {
		LibVo lv = null;
		Vector v = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
		sql += " FROM   BOOK  ";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int booknum = rs.getInt("BOOKNUM");
				String bookname = rs.getString("BOOKNAME");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String byear = rs.getString("BYEAR");
				int series = rs.getInt("SERIES");
				String bookcode = rs.getString("BOOKCODE");
				int ea = rs.getInt("EA");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					Vector v1 = new Vector();

					v1.add(rs.getInt("BOOKNUM"));
					v1.add(rs.getString("BOOKNAME"));
					v1.add(rs.getString("AUTHOR"));
					v1.add(rs.getString("PUBLISHER"));
					v1.add(rs.getString("BYEAR"));
					v1.add(rs.getInt("SERIES"));
					v1.add(rs.getString("BOOKCODE"));
					v1.add(rs.getInt("EA"));
					v.add(v1);

					lv = new LibVo(booknum, bookname, author, publisher, byear, series, bookcode, ea);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
////            if (pstmt != null)
////               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
		return v;
	}

	public String findId(String tel) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID";
		sql += " FROM   LIB  ";
		sql += " WHERE  TEL = ?";

		String uid = "";
		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				uid = rs.getString("USERID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
////            if (pstmt != null)
////               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}

		return uid;
	}

	public int findPwd(String userid, String username, String pwquest, String pwanser, String passwd) {
		int result = 0;
		String uid = "";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		conn = DBConn.getInstance();

		sql = "UPDATE      LIB";
		sql += " SET      PASSWD    =   ?";
		sql += " WHERE    USERID    =   ?";
		sql += " AND      USERNAME  =   ?";
		sql += " AND      PWQUEST   =   ?";
		sql += " AND      PWANSER   =   ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, userid);
			pstmt.setString(3, username);
			pstmt.setString(4, pwquest);
			pstmt.setString(5, pwanser);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			e.toString();
			e.getMessage();

		} finally {
//         try {
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}
		return result;
	}

	//
	public String findPwd_sub(String userid) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID, USERNAME, PWQUEST, PWANSER";
		sql += " FROM   LIB  ";
		sql += " WHERE  USERID = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("USERID");
				String name = rs.getString("USERNAME");
				String q = rs.getString("PWQUEST");
				String a = rs.getString("PWANSER");
				result = name + "," + q + "," + a;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
		return result;
	}

	// 가입회원 검색
	public LibVo getLib(String userid, String username, String tel) {
		LibVo lv1 = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID,USERNAME, TEL ";
		sql += "FROM LIB ";
		sql += "WHERE  USERID = ?";
		sql += " AND WHERE  USERNAME = ?";
		sql += " AND WHERE  TEL  = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, tel);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String uid = rs.getString("userid");
				String uname = rs.getString("username");
				String utel = rs.getString("tel");

				lv1 = new LibVo(userid, username, tel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
		return lv1;
	}

	public Vector getUserList() {
		Vector v = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  USERID,PASSWD,PWQUEST,PWANSER,USERNAME,BIRTH,GENDER,TEL,ADDRESS,FAM,HOLDER,INDATE ";
		sql += " FROM   LIB  ";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString("USERID");
				String passwd = rs.getString("PASSWD");
				String pwquest = rs.getString("PWQUEST");
				String pwanser = rs.getString("PWANSER");
				String username = rs.getString("USERNAME");
				String brith = rs.getString("BIRTH");
				String gender = rs.getString("GENDER");
				String Tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				int fam = rs.getInt("FAM");
				String holder = rs.getString("HOLDER");
				String indate = rs.getString("INDATE");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					Vector v1 = new Vector();

					v1.add(rs.getString("USERID"));
					// v1.add(rs.getString("PASSWD") );
					// v1.add( rs.getString("PWQUEST"));
					// v1.add(rs.getString("PWANSER") );
					v1.add(rs.getString("USERNAME"));
					v1.add(rs.getString("BIRTH"));
					v1.add(rs.getString("GENDER"));
					v1.add(rs.getString("TEL"));
					// v1.add(rs.getString("ADDRESS"));
					// v1.add(rs.getInt("FAM"));
					// v1.add(rs.getString("HOLDER"));
					v1.add(rs.getString("INDATE"));
					v.add(v1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//         try {
//            if (rs != null)
//               rs.close();
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
		return v;
	}

	public int deleteUser(String userid) {
		int result = 1; // 1: 성공, 0: 실패
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = " DELETE   FROM  LIB ";
		sql += " WHERE   USERID  = ? ";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			int aftcnt = pstmt.executeUpdate(); // 쿼리문 실행 개수를 aftcnt 에 저장
			if (aftcnt == 0) {

				result = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다");

		} finally {
//         try {
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}
		return result;

	}

	public Vector SearchBooklist(String item, String gettext) {
		String sql = "";
		Vector v = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		switch (item) {
		case "제목":
			sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
			sql += " FROM  BOOK";
			sql += " WHERE BOOKNAME LIKE ? ";
			break;
		case "저자":
			sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
			sql += " FROM  BOOK";
			sql += " WHERE AUTHOR LIKE ? ";
			break;
		case "출판사":
			sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
			sql += " FROM  BOOK";
			sql += " WHERE PUBLISHER LIKE ? ";
			break;
		}

		try {

			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gettext);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int booknum = rs.getInt("BOOKNUM");
				String bookname1 = rs.getString("BOOKNAME");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String byear = rs.getString("BYEAR");
				int series = rs.getInt("SERIES");
				String bookcode = rs.getString("BOOKCODE");
				int ea = rs.getInt("EA");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					Vector v1 = new Vector();

					v1.add(rs.getInt("BOOKNUM"));
					v1.add(rs.getString("BOOKNAME"));
					v1.add(rs.getString("AUTHOR"));
					v1.add(rs.getString("PUBLISHER"));
					v1.add(rs.getString("BYEAR"));
					v1.add(rs.getInt("SERIES"));
					v1.add(rs.getString("BOOKCODE"));
					v1.add(rs.getInt("EA"));
					v.add(v1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
//      } finally {
//         try {
//            if (rs != null)
//               rs.close();
////            if (pstmt != null)
////               pstmt.close();
//         } catch (SQLException e) {
//         }
		}
		return v;

	}

	public LibVo getBook(int bookcode) {
		LibVo b = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT BOOKNUM, BOOKNAME, AUTHOR, PUBLISHER,BYEAR,SERIES,BOOKCODE ,EA";
		sql += " FROM BOOK ";
		sql += " WHERE BOOKNUM = ? ";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookcode);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int booknum = rs.getInt("BOOKNUM");
				String bookname = rs.getString("BOOKNAME");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String byear = rs.getString("BYEAR");
				int series = rs.getInt("SERIES");
				String bookcode1 = rs.getString("BOOKCODE");
				int ea = rs.getInt("EA");

				b = new LibVo(booknum, bookname, author, publisher, byear, series, bookcode1, ea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//            try {
//               if( rs    != null )  rs.close();
////               if( pstmt != null )  pstmt.close();
//            } catch (SQLException e) {
//            }
		}
		return b;
	}

	// 책 연장
	public void rentExtension(int booknum, String userid) {
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";

		conn   =  DBConn.getInstance();
		sql    =  "UPDATE    RENT ";
		sql   +=  " SET      RETURNDAY = RETURNDAY + 3,";
		sql   +=  " 	        EXTENION = 1 ";
		sql   +=  " WHERE    BOOKNUM      = ?";
		sql   +=  " AND      USERID      = ?";

		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setInt(1, booknum );
			pstmt.setString(2, userid );

			pstmt.executeUpdate();

		} catch (SQLException e) {
			switch(e.getErrorCode()) {
			default:
				e.printStackTrace();
			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public boolean notRentExtension(int booknum, String userid) {
		boolean is = false;

		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		ResultSet          rs     =  null;


		String      sql   =  "SELECT   EXTENION ";
		sql   += "  FROM    RENT ";
		sql   += " WHERE   BOOKNUM      = ?";
		sql   += " AND      USERID       = ?";

		try {
			conn   =  DBConn.getInstance();
			pstmt  =  conn.prepareStatement(sql);

			pstmt.setInt(1, booknum );
			pstmt.setString(2, userid );

			rs  =  pstmt.executeQuery();

			int extenion = 0;
			while (rs.next()) {
				extenion = rs.getInt("EXTENION");
			}
			if(extenion == 1) {
				is = true;
			}

		} catch (SQLException e) {
			switch(e.getErrorCode()) {
			default:
				e.printStackTrace();
			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
			}
		}
		return is;
	}

	public int userRentBook(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DBConn.getInstance();
		String sql = "SELECT COUNT(BOOKNUM) BOOKS ";
		sql += " FROM   HISTORY ";
		sql += " WHERE   USERID = ? ";

		int books = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				books = rs.getInt("BOOKS");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//               try {
//                  if(conn != null)conn.close();
//               } catch (SQLException e) {
//                  e.printStackTrace();
//               }
		}

		return books;
	}

	public LibVo getUserinpo(String userid) {
		LibVo lVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT PASSWD, PWQUEST, PWANSER, USERNAME, BIRTH, GENDER, TEL, ADDRESS, FAM, HOLDER, ";
		sql += " TO_CHAR(INDATE, 'YYYY-MM-DD') INDATE  ";
		sql += " FROM   LIB  ";
		sql += " WHERE  USERID = ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String passwd = rs.getString("PASSWD");
				String pwquest = rs.getString("PWQUEST");
				String pwanser = rs.getString("PWANSER");
				String username = rs.getString("USERNAME");
				String birth = rs.getString("BIRTH");
				String gender = rs.getString("GENDER");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				int fam = rs.getInt("FAM");
				String holder = rs.getString("HOLDER");
				String indate = rs.getString("INDATE");

				lVo = new LibVo(userid, passwd, pwquest, pwanser, username, birth, gender, tel, address, fam, holder,
						indate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//               try {
////                  if( rs    != null )  rs.close();
//////                  if( pstmt != null )  pstmt.close();
////               } catch (SQLException e) {
////               }
		}
		return lVo;

	}

	public void UserInsertDao(String passwd, String pwquest, String pwanser, String tel, String address, int fam,
			String holder, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		conn = DBConn.getInstance();
		sql = "UPDATE      LIB ";
		sql += " SET      PASSWD  = ?,";
		sql += "         PWQUEST  = ?,";
		sql += "         PWANSER  = ?,";
		sql += "         TEL      = ?,";
		sql += "         ADDRESS   = ?,";
		sql += "         FAM   = ?,";
		sql += "         HOLDER   = ?";
		sql += " WHERE   USERID  = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, pwquest);
			pstmt.setString(3, pwanser);
			pstmt.setString(4, tel);
			pstmt.setString(5, address);
			pstmt.setInt(6, fam);
			pstmt.setString(7, holder);
			pstmt.setString(8, userid);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			e.toString();
			e.getMessage();

		} finally {
//                  try {
//                     if(conn != null)conn.close();
//                  } catch (SQLException e) {
//                     e.printStackTrace();
//                  }

		}

	}

	public Vector myBooks(String userid) {
		Vector outV = new Vector();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT R.BOOKNUM NUM, B.BOOKNAME BOOKNAME, TO_CHAR(R.RETURNDAY,'YYYY-MM-DD') RETURNDAY";
		sql += " FROM RENT R JOIN BOOK B ON R.BOOKNUM = B.BOOKNUM   ";
		sql += " WHERE USERID = ? ";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("NUM"));
				v.add(rs.getString("BOOKNAME"));
				v.add(rs.getString("RETURNDAY"));
				outV.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outV;
	}

	public void deleteRent(String userid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "DELETE   FROM  RENT ";
		sql += " WHERE   USERID  = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			switch (e.getErrorCode()) {
			default:
				e.printStackTrace();
			}
		} finally {
//               try {
////                  if(pstmt != null) pstmt.close();
//               } catch (SQLException e) {
//               }
		}
	}

	public Vector getgenre(int num1, int num2) {
		Vector g = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
		sql += " FROM   BOOK  ";
		sql += " WHERE  TO_NUMBER(BOOKCODE) BETWEEN ? AND ? ";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num1);
			pstmt.setInt(2, num2);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int booknum = rs.getInt("BOOKNUM");
				String bookname = rs.getString("BOOKNAME");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String byear = rs.getString("BYEAR");
				int series = rs.getInt("SERIES");
				String bookcode = rs.getString("BOOKCODE");
				int ea = rs.getInt("EA");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					Vector g1 = new Vector();

					g1.add(rs.getInt("BOOKNUM"));
					g1.add(rs.getString("BOOKNAME"));
					g1.add(rs.getString("AUTHOR"));
					g1.add(rs.getString("PUBLISHER"));
					g1.add(rs.getString("BYEAR"));
					g1.add(rs.getInt("SERIES"));
					g1.add(rs.getString("BOOKCODE"));
					g1.add(rs.getString("EA"));
					// v1.add(rs.getInt("EA"));
					g.add(g1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//               try {
//                  if( rs    != null )  rs.close();
////                  if( pstmt != null )  pstmt.close();
//               } catch (SQLException e) {
//               }
		}
		return g;

	}

	public Vector SearchGenre(String gen1, String gen2) {
		String sql = "";
		Vector v = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql = "SELECT  BOOKNUM,BOOKNAME,AUTHOR,PUBLISHER,BYEAR,SERIES,BOOKCODE,EA ";
		sql += " FROM  BOOK";
		sql += " WHERE BOOKCODE BETWEEN ? AND ?";

		try {
			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gen1);
			pstmt.setString(2, gen2);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int booknum = rs.getInt("BOOKNUM");
				String bookname1 = rs.getString("BOOKNAME");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String byear = rs.getString("BYEAR");
				int series = rs.getInt("SERIES");
				String bookcode = rs.getString("BOOKCODE");
				int ea = rs.getInt("EA");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					Vector v1 = new Vector();

					v1.add(rs.getInt("BOOKNUM"));
					v1.add(rs.getString("BOOKNAME"));
					v1.add(rs.getString("AUTHOR"));
					v1.add(rs.getString("PUBLISHER"));
					v1.add(rs.getString("BYEAR"));
					v1.add(rs.getInt("SERIES"));
					v1.add(rs.getString("BOOKCODE"));
					v1.add(rs.getInt("EA"));
					v.add(v1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//            try {
//               if (rs != null)
//                  rs.close();
////               if (pstmt != null)
////                  pstmt.close();
//            } catch (SQLException e) {
//            }
		}
		return v;

	}

	// 도서 추가
	public void insertBook(String bookname, String author, String publisher, String byear, int series, String bookcode,
			int ea) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "INSERT  INTO    BOOK ( BOOKNUM, BOOKNAME, AUTHOR, PUBLISHER, BYEAR, SERIES, BOOKCODE, EA )";
		sql += " VALUES ( (SELECT MAX(BOOKNUM) + 1 FROM BOOK), ?, ?, ?, ?, ?, ?, ? )";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookname);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.setString(4, byear);
			pstmt.setInt(5, series);
			pstmt.setString(6, bookcode);
			pstmt.setInt(7, ea);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "입력값이 올바르지 않습니다");
			e.printStackTrace();
		} finally {
			try {
          if(pstmt != null) pstmt.close();
//          if(conn  != null) conn.close();
       		} catch (SQLException e) {
       			
       		}
		}

	   }
	

	public LibVo userInfo(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void bookB(int booknum, String userid) {
		Connection         conn   =  null;
		PreparedStatement  pstmt  =  null;
		String             sql    =  "";
		
		conn   =  DBConn.getInstance();
		sql    =  "INSERT  INTO    RESERVATION ( BOOKNUM, USERID )";
		sql   +=  " VALUES ( ?, ? )";
		try {
			pstmt  =  conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn  != null) conn.close();
//			} catch (SQLException e) {
//			}
		}
	}

	public String rentbook(int booknum, String userid) {

		String result = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "INSERT INTO RENT( BOOKNUM,USERID)";
		sql += " VALUES(?,?)  ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid);

			this.count = userRentBook(userid);
			if (count == 3) {
				throw new Exception();
			}
			pstmt.executeUpdate();
			mibook(booknum);
			bookhistory(booknum, userid);

			JOptionPane.showMessageDialog(null, "대출 완료", "완료", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "대출 실패, 이미 대여된 책입니다", "실패", JOptionPane.OK_OPTION);
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "대출 실패, 최대 3권까지 대출 가능합니다", "실패", JOptionPane.OK_OPTION);
		} // finally {
//	         try {
//	            if (pstmt != null)
//	               pstmt.close();
//	         } catch (SQLException e) {
//	            e.printStackTrace();
//	         }
//	      }

		return result;
	}

	public void updatedook(int booknum, String bOOKNAME, String aUTHOR, String pUBLISHER, String bYEAR, int sERIES,
			String bOOKCODE, int eA) {

		int result = 0;
		String uid = "";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		conn = DBConn.getInstance();

		sql = "UPDATE      BOOK";
		sql += " SET       ";
		sql += "BOOKNAME         =   ?,";
		sql += "AUTHOR           =   ?,";
		sql += "PUBLISHER       =   ?,";
		sql += "BYEAR           =   ?,";
		sql += "SERIES          =   ?,";
		sql += "BOOKCODE        =   ?,";
		sql += "EA             =   ? ";
		sql += "WHERE BOOKNUM   =   ? ";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(8, booknum);
			pstmt.setString(1, bOOKNAME);
			pstmt.setString(2, aUTHOR);
			pstmt.setString(3, pUBLISHER);
			pstmt.setString(4, bYEAR);
			pstmt.setInt(5, sERIES);
			pstmt.setString(6, bOOKCODE);
			pstmt.setInt(7, eA);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			e.toString();
			e.getMessage();

		} finally {
//         try {
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}

	}

	public void bookdel(int booknum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "DELETE   FROM  BOOK ";
		sql += " WHERE BOOKNUM =  ? ";

		JOptionPane.showMessageDialog(null, "삭제 완료");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
//            try {
//               if(pstmt != null) pstmt.close();
//            } catch (SQLException e) {
//            }
		}
	}

	public void UpdateUser(String userid, String pass, String pwq, String pwa, String name, String address, String tel,
			int fam, String holder) {

		int result = 0;
		String uid = "";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		conn = DBConn.getInstance();

		sql = "UPDATE LIB ";
		sql += "SET ";
		sql += "PASSWD = ?,";
		sql += "PWQUEST = ?,";
		sql += "PWANSER = ?,";
		sql += "USERNAME = ?,";
		sql += "ADDRESS = ?,";
		sql += "TEL = ?,";
		sql += "FAM = ?,";
		sql += "HOLDER = ? ";
		sql += "WHERE USERID = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pass);
			pstmt.setString(2, pwq);
			pstmt.setString(3, pwa);
			pstmt.setString(4, name);
			pstmt.setString(5, address);
			pstmt.setString(6, tel);
			pstmt.setInt(7, fam);
			pstmt.setString(8, holder);
			pstmt.setString(9, userid);

			JOptionPane.showMessageDialog(null, "수정되었습니다");

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			e.toString();
			e.getMessage();
			JOptionPane.showMessageDialog(null, "입력값을 확인 해 주세요");

		} finally {
//       try {
//          if (pstmt != null)
//             pstmt.close();
//       } catch (SQLException e) {
//          e.printStackTrace();
//       }
		}

	}

	public void mibook(int booknum) {

		int result = 0;
		String uid = "";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		conn = DBConn.getInstance();

		sql = "UPDATE      BOOK";
		sql += " SET      EA    =   EA-1";
		sql += " WHERE    BOOKNUM    =   ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			e.toString();
			e.getMessage();

		} finally {
//        try {
//           if (pstmt != null)
//              pstmt.close();
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
		}

	}

	public void bookhistory(int booknum, String userid) {
		String result = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = "INSERT INTO HISTORY( BOOKNUM,USERID)";
		sql += " VALUES(?,?)  ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		} finally {
//         try {
//            if (pstmt != null)
//               pstmt.close();
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
		}

	}

	public boolean extention(int booknum, String userid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean b = false;
		int ck = 0;

		String sql = "SELECT  EXTENION ";
		sql += "FROM RENT ";
		sql += "WHERE  BOOKNUM = ?";
		sql += "AND  USERID = ?";

		try {

			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid);

			rs = pstmt.executeQuery();

			if(rs.next()) {
			ck = rs.getInt("EXTENION");
			}

			if (ck == 1) {
				b = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//        try {
//           if (rs != null)
//              rs.close();
//           if (pstmt != null)
//            pstmt.close();
//        } catch (SQLException e) {
//        }
		}

		return b;
	}

	public int returnck(int booknum, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ck = 0;

		String sql = "SELECT  RETURN ";
		sql += "FROM RENT ";
		sql += "WHERE  BOOKNUM = ? ";
		sql += "AND  USERID = ?";

		try {

			conn = DBConn.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid);

			rs = pstmt.executeQuery();
			if(rs.next()) {
			ck = rs.getInt("RETURN");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//        try {
//           if (rs != null)
//              rs.close();
//           if (pstmt != null)
//            pstmt.close();
//        } catch (SQLException e) {
//        }
		}
		return ck;
	}

	public void deleteBook(int booknum, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		conn = DBConn.getInstance();
		sql = " DELETE   FROM  RENT ";
		sql += " WHERE   BOOKNUM  = ? ";
		sql += " AND USERID = ?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			pstmt.setString(2, userid);

			pstmt.executeUpdate(); // 쿼리문 실행 개수를 aftcnt 에 저장
		

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다");

		} finally {
//        try {
//           if (pstmt != null)
//              pstmt.close();
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
		}

	}
	
	public int isReturned(int booknum, String userid) {
		int cnt = 0;    // 조회없음:0 , 조회됨: !=0 
		Connection         conn   = null;
		PreparedStatement  pstmt  = null;
		ResultSet          rs     = null;

		String sql   =   "SELECT  BOOKNUM";
		sql         +=   " FROM   RENT ";
		sql         +=   " WHERE  BOOKNUM = ?";
		sql         +=   " AND    USERID = ?";

		LibVo user = null;
		try {
			conn   =  DBConn.getInstance();
			pstmt  =  conn.prepareStatement(sql);

			pstmt.setInt(1, booknum );
			pstmt.setString(2, userid );

			rs  =  pstmt.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt("BOOKNUM");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//		   try {
			//			   if(conn != null)conn.close();
			//			   if(pstmt != null)pstmt.close();
			//		   } catch (SQLException e) {
			//			   e.printStackTrace();
			//		   }
		}
		return cnt;
	}


}
