package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.LibDao;
import model.LibVo;

public class AdminProc extends JFrame implements ActionListener, KeyListener, ItemListener  {
   // component
   JPanel p, Birth;
   JTextField tfId, tfName, tfPwA, tfAddress, tfTel, tfH, tfBirth,tfex;
   JPasswordField pfPwd, pfPwdC;
   JComboBox cbYear, cbMonth, cbDay;
   JRadioButton rbMale, rbFemale, rbFam;
   JTextField tfPwQ;
   JButton btnUpdate, btnCancel, btnIdCheck,btndel;

   GridBagLayout gb;
   GridBagConstraints gbc; // option
   LibDao dao = null;

   AdminPage page = null;

   // 생성자목록
   // 기본생성자
   public AdminProc() {
      initComponent();

   }

   public AdminProc(String id, AdminPage adminPage) {
      this();
      this.page = adminPage;
      // this.id = id;
      this.tfId.setText(id);
      viewData();
   }

   public void viewData() {
      LibDao lDao = new LibDao();
      String userid = this.tfId.getText();
      LibVo user = lDao.getUser(userid);
      if (user != null) {
         setViewData(user);

      

      } else {
         JOptionPane.showMessageDialog(null, "조회된 자료가 없습니다", "자료없습니다", JOptionPane.OK_OPTION);
         // 화면 초
         clearViewData();
      }

   }

   private void setViewData(LibVo user) {
      
      String userid = user.getUserid();
      String passwd = user.getPasswd();
      String pwquest = user.getPwquest();
      String pwanser = user.getPwanser();
      String username = user.getUsername();
      String birth = user.getBirth();
      String gender = user.getGender();
      String tel = user.getTel();
      String address = user.getAddress();
      int fam = user.getFam();
      String holder = user.getHolder();
      String indate = user.getIndate();
      

      this.tfId.setText(userid);
      this.pfPwd.setText(passwd);
      this.tfPwQ.setText(pwquest);
      this.tfPwA.setText(pwanser);
      this.tfName.setText(username);
      this.tfBirth.setText(birth);
  
      if (gender.equals("M"))
    	  this.rbMale.setSelected(true);
      if (gender.equals("F"))
    	  this.rbFemale.setSelected(true);

      this.tfTel.setText(tel);
      this.tfAddress.setText(address);
      if(fam == 1)
    	  this.rbFam.setSelected(true);
      if(fam == 0)
    	  this.rbFam.setSelected(false);
     this.tfH.setText(holder);
   

      
   }

   private void initComponent() {
      this.setTitle("회원정보");

      // component 배치
      // GridBagLayout : Layout
      gb = new GridBagLayout();
      this.setLayout(gb);

      gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 1.0;
      gbc.weighty = 1.0;

      // 아이디
      JLabel lblId = new JLabel("아이디");
      tfId = new JTextField(20);
      tfId.setEditable(false);
      
      JPanel pId = new JPanel(new BorderLayout());
      pId.add(tfId, BorderLayout.CENTER);
      gbAdd(lblId, 0, 0, 1, 1);
      gbAdd(pId, 1, 0, 3, 1);

      // 암호
      JLabel lblPwd = new JLabel("비밀번호");
      pfPwd = new JPasswordField(20);
      gbAdd(lblPwd, 0, 1, 1, 1);
      gbAdd(pfPwd, 1, 1, 3, 1);

      // 비밀번호 질문
      JLabel lblQuest = new JLabel("비밀번호 질문");
      tfPwQ = new JTextField(30); // 행 열
      tfPwQ.setEditable(false);
      JScrollPane pane = new JScrollPane(lblQuest);
      gbAdd(lblQuest, 0, 2, 1, 1);
      gbAdd(tfPwQ, 1, 2, 3, 1);

      // 비밀번호 답
      JLabel lblAnser = new JLabel("비밀번호 답");
      tfPwA = new JTextField(30); // 행 열
      gbAdd(lblAnser, 0, 3, 1, 1);
      gbAdd(tfPwA, 1, 3, 3, 1);

      // 이름
      JLabel lblName = new JLabel("이름");
      tfName = new JTextField(20);

      gbAdd(lblName, 0, 4, 1, 1);
      gbAdd(tfName, 1, 4, 3, 1);

      // 성별
      JLabel lblGender = new JLabel("성별");

      this.rbMale = new JRadioButton("남자", false);
      this.rbFemale = new JRadioButton("여자", false);
      rbMale.setEnabled(false);
      rbFemale.setEnabled(false);
      /// 선택 그룹 지정
      ButtonGroup group = new ButtonGroup();
      group.add(rbMale);
      group.add(rbFemale);
   
      // 패널 사용
      JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
      pGender.add(rbMale);
      pGender.add(rbFemale);

      gbAdd(lblGender, 0, 5, 1, 1);
      gbAdd(pGender, 1, 5, 3, 1);

      JLabel lblBirth = new JLabel("생년월일");
      this.tfBirth = new JTextField(20);
      this.tfBirth.setEditable(false);
      gbAdd(lblBirth, 0, 7, 1, 1);
      gbAdd(tfBirth, 1, 7, 3, 1);

      // 전화번호
      JLabel lblTel = new JLabel("전화번호");
      this.tfTel = new JTextField(20);

      gbAdd(lblTel, 0, 8, 1, 1);
      gbAdd(tfTel, 1, 8, 3, 1);

      // 주소
      JLabel lblAddress = new JLabel("주소");
      this.tfAddress = new JTextField(20);

      gbAdd(lblAddress, 0, 9, 1, 1);
      gbAdd(tfAddress, 1, 9, 3, 1);

      // 가족 대출 신청
      JLabel lblFam = new JLabel("가족대출 신청");
      this.rbFam = new JRadioButton();
      rbFam.addItemListener(this);

      gbAdd(lblFam, 0, 10, 1, 1);
      gbAdd(rbFam, 1, 10, 3, 1);

      // 신청 세대주명
      JLabel lblH = new JLabel("신청 세대주명");
      this.tfH = new JTextField(20);

      gbAdd(lblH, 0, 11, 1, 1);
      gbAdd(tfH, 1, 11, 3, 1);
      
      JLabel lbex = new JLabel("연체 여부");
      this.tfex = new JTextField();
      gbAdd(lbex, 0, 12, 1, 1);
      gbAdd(tfex, 1, 12, 3, 1);

      // 버튼들
      JPanel pButton = new JPanel();
      btnUpdate = new JButton("수정");
      btndel = new JButton("삭제");
      btnCancel = new JButton("취소");

      pButton.add(this.btnUpdate);
      pButton.add(this.btndel);
      pButton.add(this.btnCancel);
      gbAdd(pButton, 0, 13, 4, 1);

      // 이벤트 연결 - 기능추가\
      this.btnUpdate.addActionListener(this);
      this.btnCancel.addActionListener(this);
      this.btndel.addActionListener(this);

      // Enter 키 연결
      this.tfId.addKeyListener(this);

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize(350, 700);
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
      new AdminProc();
   }

   // getViewData()
   private LibVo getViewData1() {
      String userid = this.tfId.getText();
      String passwd = this.pfPwd.getText();
      String passwdq = this.tfPwQ.getText();
      String passwda = this.tfPwA.getText();
      String username = this.tfName.getText();
      String gender = "";
      if (rbMale.isSelected())
         gender = "M";
      if (rbFemale.isSelected())
         gender = "F";
      String birth = this.tfBirth.getText();
      String tel = this.tfTel.getText();
      String address = this.tfAddress.getText();
      int fam = 0;
      if (rbFam.isSelected())
         fam = 1;
      String holder = this.tfH.getText();
      LibVo user = new LibVo(userid, passwd, passwdq, passwda, username, gender, birth, tel, address, fam, holder);
      return user;
   }


   // 조회 실패 시 화면 초기화
   private void clearViewData() {
      this.tfId.setText("");
      this.pfPwd.setText("");
      this.tfPwQ.setText("");
      this.tfPwA.setText("");
      this.tfName.setText("");
      this.rbMale.setSelected(false);
      this.rbFemale.setSelected(false);
      this.tfBirth.setText("");
      this.tfTel.setText("");
      this.tfAddress.setText("");
      this.rbFam.setSelected(false);
      this.tfH.setText("");
   }

   // 이벤트 처리
   @Override
   public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
      
      case "수정":
            updateUser();
            this.dispose();
         break;
      case "삭제":
    	 delrent();
    	 deluser();
    	 this.dispose();
      case "취소": 
         this.dispose(); // 현재 창닫기
         break;

      }

   }

   private void delrent() {
	
	   LibDao lDao = new  LibDao();
       String id = this.tfId.getText();
       lDao.deleteUser(id);
	
}

private void deluser() {
	   LibDao lDao = new  LibDao();
       String id = this.tfId.getText();
       lDao.deleteUser(id);
       
       JOptionPane.showMessageDialog(null,"삭제 완료");
	
}

private void updateUser() {
      
	   	  LibDao lDao = new  LibDao();
	   	  String id = this.tfId.getText();
	      String pass = this.pfPwd.getText();
	      String  pwq = this.tfPwQ.getText();
	      String pwa = this.tfPwA.getText();
	      String name = this.tfName.getText();
	      String address = this.tfAddress.getText();
	      String tel = this.tfTel.getText(); 
	      String holder = this.tfH.getText();
	      int fam = 0;
			if (rbFam.isSelected())
				fam = 1;
			
	    lDao.UpdateUser(id,pass,pwq,pwa,name,address,tel,fam,holder);
		
	    
	      
      
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
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

@Override
public void itemStateChanged(ItemEvent e) {
	if(rbFam.isSelected()) {
		   tfH.setEditable(true);
	   }else {
		   tfH.setEditable(false);
	   }
	
	
}

}