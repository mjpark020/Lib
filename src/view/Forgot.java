package view;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.LibDao;

public class Forgot extends JFrame implements ActionListener{
   // topPanel > findIdPan, findPwdPan
   
   // 아이디 찾기
   JPanel topPanel; // 아이디, 비밀번호 찾기 다 넣을 패널
   JPanel findIdPan; // 아이디 찾기 패널
   JLabel lblFindId; // 제목 : 아이디 찾기
   JLabel lblPhone; // 아이디 찾기시 전화번호 레이블
   JTextField tfPhone; // 아이디 찾기시 입력할 전화번호 텍스트필드
   JLabel findedId;   // 찾기 성공시 찾은 아이디
   JButton btnFind; // 아이디 찾기 버튼
   
   // 비밀번호 찾기
   JPanel findPwdPan; // 비밀번호 찾기 패널
   JLabel lblFindPwd; // 제목 : 비밀번호 찾기
   JLabel lblId; // 아이디 입력 레이블
   JTextField tfId; // 아이디 입력 텍스트필드
   JLabel lblName; // 이름 입력 레이블
   JTextField tfName; // 이름 텍스트필드
   JLabel lblQ; // 질문 레이블
   JComboBox cbQ; // 비밀번호 찾기 질문
   JLabel lblA; // 답 레이블
   JTextField tfA; // 비밀번호 찾기 질문 답
   JLabel lblPwd;
   JPasswordField pfPwd; // 변경할 비밀번호
   JButton changePwd;
   
   Forgot(){
      init();
      pack();
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   

   private void init() {
      topPanel = new JPanel();
      topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
//      topPanel.setAlignmentX(LEFT_ALIGNMENT);
      
      // 아이디 찾기
      findIdPan = new JPanel();
      findIdPan.setLayout(new BoxLayout(findIdPan, BoxLayout.Y_AXIS));
      
      JPanel num00 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      lblFindId = new JLabel("아이디 찾기");
      lblFindId.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      num00.add(lblFindId);
      
      JPanel num01 = new JPanel(new GridLayout(3,1));
      lblPhone = new JLabel("전화번호");
      tfPhone = new JTextField(20);
      findedId = new JLabel(" ");
      num01.add(lblPhone);
      num01.add(tfPhone);
      num01.add(findedId);
      
      JPanel btn = new JPanel(new FlowLayout(FlowLayout.CENTER));
      btnFind = new JButton("찾기");
      // 찾기 버튼 클릭
      btnFind.addActionListener(this);
      btn.add(btnFind);
      
      findIdPan.add(num00);
      findIdPan.add(Box.createVerticalStrut(10));
      findIdPan.add(num01);
      
      findIdPan.add(btn);
      
      topPanel.add(findIdPan);
      
      
      // 비밀번호 찾기
      findPwdPan = new JPanel();
      findPwdPan.setLayout(new BoxLayout(findPwdPan, BoxLayout.Y_AXIS));
      
      JPanel num0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      lblFindPwd = new JLabel("비밀번호 찾기");
      lblFindPwd.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      num0.add(lblFindPwd);
      
      JPanel num1 = new JPanel(new GridLayout(2,1));
      lblId = new JLabel("아이디");
      tfId = new JTextField(20);
      num1.add(lblId);
      num1.add(tfId);
      
      JPanel num2 = new JPanel(new GridLayout(2,1));
      lblName = new JLabel("이름");
      tfName = new JTextField(20);
      num2.add(lblName);
      num2.add(tfName);
      
      JPanel num4 = new JPanel(new GridLayout(2,1));
      lblQ = new JLabel("비밀번호 질문");
      String[] q = {" ","다니던 학교의 이름은?", "부모님 결혼기념일", "좋아하는 색은?"};
      cbQ = new JComboBox(q);
      lblA = new JLabel("비밀번호 질문 답");
      tfA = new JTextField(10);
      num4.add(lblQ);
      num4.add(cbQ);
      num4.add(lblA);
      num4.add(tfA);
      
      JPanel num5 = new JPanel(new GridLayout(2,1));
      lblPwd = new JLabel("변경할 비밀번호");
      pfPwd = new JPasswordField(20);
      num5.add(lblPwd);
      num5.add(pfPwd);
      
      JPanel num6 = new JPanel();
      changePwd = new JButton("변경");
      // 비밀번호 변경 클릭
      changePwd.addActionListener(this);
      num6.add(changePwd);
      
      findPwdPan.add(num0);
      findPwdPan.add(Box.createVerticalStrut(10));
      
      findPwdPan.add(num1);
      findPwdPan.add(Box.createVerticalStrut(5));
      findPwdPan.add(num2);
      findPwdPan.add(Box.createVerticalStrut(5));
//      findPwdPan.add(num3);
      findPwdPan.add(Box.createVerticalStrut(5));
      findPwdPan.add(num4);
      findPwdPan.add(Box.createVerticalStrut(5));
      findPwdPan.add(num5);
      findPwdPan.add(Box.createVerticalStrut(5));
      findPwdPan.add(num6);
      
      
      topPanel.add(Box.createVerticalStrut(30));
      topPanel.add(findPwdPan);
      
      add(topPanel);
   }

   //--------------------------------------------------------------------------
   // 이벤트
   @Override
   public void actionPerformed(ActionEvent e) {
      LibDao lDao = null;
      switch( e.getActionCommand() ) {
         // 찾기 버튼
         case "찾기": 
            lDao = new LibDao();
            
            if( lDao.findId(tfPhone.getText()).equals("") ) {
               JOptionPane.showMessageDialog(null, 
                     "해당 번호로 가입된 계정이 존재하지 않습니다",
                     "아이디를 찾지 못함",
                     JOptionPane.OK_OPTION
                     );
            } else {
               String ID = lDao.findId(tfPhone.getText());
               findedId.setText(ID);
            }
            break;
         
         //변경 버튼
         case "변경": 
            lDao = new LibDao();
            String id = tfId.getText();
            String name = tfName.getText();
            String ques = (String) cbQ.getSelectedItem();
            String answer = tfA.getText();
            String pwd = pfPwd.getText();
            
            String rname = ""; // select로 가져올 이름
            String rques = ""; //  가져올 질문
            String ranswer = ""; // 가져올 답
            
            LibDao ldao = new LibDao();
            
            // result에 아이디로 검색한 결과(이름, 질문, 답) 가져옴
            String result = ldao.findPwd_sub(id);
            
            // 아이디로 검색한 결과 가져올 배열
            if( !(result.equals("")) ) {
               
               String[] vo = ldao.findPwd_sub(id).split(",");
               rname = vo[0];
               rques = vo[1];
               ranswer = vo[2];
            }
            
//            int result = ldao.findPwd(id, name, ques, answer, pwd);
            
            // 아이디 검색 안되었을때
            if(result.equals("")) {
               JOptionPane.showMessageDialog(null, 
                     "아이디가 존재하지 않습니다",
                     "입력값 오류",
                     JOptionPane.OK_OPTION
                     );
            } else if( !(name.equals(rname)) ){
               JOptionPane.showMessageDialog(null, 
                     "틀린 이름입니다",
                     "입력값 오류",
                     JOptionPane.OK_OPTION
                     );
            } else if ( !(ques.equals(rques)) ) {
               JOptionPane.showMessageDialog(null, 
                     "질문이 틀렸습니다",
                     "입력값 오류",
                     JOptionPane.OK_OPTION
                     );
            } else if ( !(answer.equals(ranswer)) ) {
               JOptionPane.showMessageDialog(null, 
                     "답이 틀렸습니다",
                     "입력값 오류",
                     JOptionPane.OK_OPTION
                     );
            } else {
               lDao.findPwd(id, name, ques, answer, pwd);
               JOptionPane.showMessageDialog(null, 
                     "비밀번호를 변경했습니다",
                     "변경성공",
                     JOptionPane.PLAIN_MESSAGE
                     );
            }
            break;
         
         }
   }
   
   public static void main(String[] args) {
      new Forgot();
   }
}