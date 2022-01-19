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

public class LibEditUser extends JFrame implements ActionListener, KeyListener, ItemListener  {
   // component
   JPanel p, Birth;
   JTextField tfId, tfName, tfPwA, tfAddress, tfTel, tfH, tfBirth, tfIndate,tfPwQ;
   JPasswordField pfPwd, pfPwdC;
   JComboBox cbYear, cbMonth, cbDay;
   JRadioButton rbMale, rbFemale, rbFam;
   JButton btnUpdate, btnCancel, btnIdCheck;

   GridBagLayout gb;
   GridBagConstraints gbc; // option
   LibDao dao = null;
   LibVo vo = null;
   String loginUserid;
   
//   AdminPage page = null;

   // 생성자목록
   // 기본생성자
   public LibEditUser() {
      initComponent();

   }
   
   public LibEditUser(String user) {
      this.loginUserid = user;
      dao = new LibDao();
      vo = dao.getUser(user);
      initComponent();

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
      tfName.setEditable(false);

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
      tfBirth.setEditable(false);
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
      
      // 가족회원 신청시 세대주 수정가능
      if(rbFam.isSelected()) {
         tfH.setEditable(true);
      }else {
         tfH.setEditable(false);
      }
      
      
      // 가입일
      JLabel lblIndate = new JLabel("가입일");
      this.tfIndate = new JTextField(20);
      tfIndate.setEditable(false);
      gbAdd(lblIndate, 0, 12, 1, 1);
      gbAdd(tfIndate, 1, 12, 3, 1);
      

      // 버튼들
      JPanel pButton = new JPanel();
      btnUpdate = new JButton("수정");
      btnCancel = new JButton("취소");

      pButton.add(this.btnUpdate);
      pButton.add(this.btnCancel);
      gbAdd(pButton, 0, 13, 4, 1);

      // 이벤트 연결 - 기능추가\
      this.btnUpdate.addActionListener(this);
      this.btnCancel.addActionListener(this);

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setSize(350, 700);
      this.setVisible(true);
      
      getUser(vo);
   }

   // 로그인된 유저 정보 불러오기
   private void getUser(LibVo vo) {
      LibVo user = dao.getUser(loginUserid);
      tfId.setText(user.getUserid());
      pfPwd.setText(user.getPasswd());
      tfPwQ.setText(user.getPwquest());
      tfPwA.setText(user.getPwanser());
      tfName.setText(user.getUsername());
      
      String gender = user.getGender();
      if(gender.equals("M")) {
         rbMale.setSelected(true);
         rbFemale.setSelected(false);
      }else {
         rbMale.setSelected(false);
         rbFemale.setSelected(true);
      }
      
      tfBirth.setText(user.getBirth());
      tfTel.setText(user.getTel());
      tfAddress.setText(user.getAddress());
      
      int fam = user.getFam();
      if(fam == 1) {
         rbFam.setSelected(true);
      }else {
         rbFam.setSelected(false);
      }
      
      tfH.setText(user.getHolder());
      
      tfIndate.setText(user.getIndate());
      
   }
   
   // 입력된 정보로 수정
   private void updateUser() {
      String userid = this.tfId.getText();
      String passwd = this.pfPwd.getText();
      String passwdq = this.tfPwQ.getText();
      String passwda = this.tfPwA.getText();
      String tel = this.tfTel.getText();
      String address = this.tfAddress.getText();
      int fam = 0;
      if (rbFam.isSelected()) {
         fam = 1;
      }
      String holder = this.tfH.getText();
      dao.UserInsertDao(passwd, passwdq, passwda, tel, address, fam, holder, userid);
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
      new LibEditUser();
   }

   // 조회 실패 시 화면 초기화
   private void clearViewData() {
      this.tfId.setText("");
      this.pfPwd.setText("");
      this.pfPwdC.setText("");
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
            JOptionPane.showMessageDialog(null, "수정되었습니다", "수정완료", JOptionPane.PLAIN_MESSAGE);
         break;
      case "취소": // 창닫기
         this.dispose(); // 현재 창닫기
         break;

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
   public void keyReleased(KeyEvent e) {
      

   }

   // 가족회원 여부에 따른 세대주칸 수정
   @Override
   public void itemStateChanged(ItemEvent e) {
      
      if(rbFam.isSelected()) {
         tfH.setEditable(true);
      }else {
         tfH.setEditable(false);
      }
   
   }

}