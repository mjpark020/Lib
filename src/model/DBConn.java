package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    //oracle
	//oracle
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
       //mysql
       //private String driver = "com.mysql.jdbc.Driver";
       //private String url    = "jdbc:mysql://127.0.0.1:3306/dbuser";
       private  static String dbuid  = "LIBRARY_PRO";
       private  static String dbpwd  = "1234";
       
       private static Connection conn = null;
       //생성자:프로그램에서 한번만 new 할 수 있도록 private 생성자이용 
       //singleton 패턴 -getInstance();
       public DBConn() {
          
       }

     public static Connection getInstance() {
        if(conn!=null)
           return conn;
        try {
         Class.forName(driver);
         try {
            conn =DriverManager.getConnection(url,dbuid,dbpwd);
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
        
        return conn;
     }



}