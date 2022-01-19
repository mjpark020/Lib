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
import javax.swing.JCheckBox;
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

public class LibProc extends JFrame implements ActionListener, KeyListener, ItemListener {
	// component
	JPanel p, Birth;
	JTextField tfId, tfName, tfPwA, tfAddress, tfTel, tfH, tfBirth,tfPwQ;
	JPasswordField pfPwd, pfPwdC;
	JComboBox cbYear, cbMonth, cbDay,cbPwQ;
	JRadioButton rbMale, rbFemale;
	JButton btnJoin, btnCancel, btnIdCheck;
	JCheckBox chFam;
	
	GridBagLayout gb;
	GridBagConstraints gbc; // option
	LibDao dao = null;

	AdminPage page = null;

	// 생성자목록
	// 기본생성자
	public LibProc() {
		initComponent();
		btnOnOff(true);
	}

	public LibProc(String id, AdminPage adminPage) {
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

			btnOnOff(false);

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
		this.tfName.setText(username);
		

		if (gender.equals("M"))
			this.rbMale.setSelected(true);
		if (gender.equals("F"))
			this.rbFemale.setSelected(true);

		
	}

	private void initComponent() {
		this.setTitle("회원가입");

		// component 배치
		// GridBagLayout : Layout
		gb = new GridBagLayout();
		this.setLayout(gb);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// 아이디
		JLabel lblId = new JLabel("아이디 *");
		tfId = new JTextField(20);
		btnIdCheck = new JButton("중복확인");
		JPanel pId = new JPanel(new BorderLayout());
		pId.add(tfId, BorderLayout.CENTER);
		pId.add(btnIdCheck, BorderLayout.LINE_END);

		gbAdd(lblId, 0, 0, 1, 1);
		gbAdd(pId, 1, 0, 3, 1);

		// 암호
		JLabel lblPwd = new JLabel("비밀번호 *");
		pfPwd = new JPasswordField(20);
		gbAdd(lblPwd, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);

		// 비밀번호 확인
		JLabel lblPwdC = new JLabel("비밀번호확인");
		pfPwdC = new JPasswordField(20);
		gbAdd(lblPwdC, 0, 2, 1, 1);
		gbAdd(pfPwdC, 1, 2, 3, 1);

	    // 비밀번호 질문
	      JLabel lblQuest = new JLabel("비밀번호 질문 *");
	      String[] q = {" ","다니던 학교의 이름은?", "부모님 결혼기념일", "좋아하는 색은?"};
	      cbPwQ = new JComboBox(q); // 행 열
	      
	      gbAdd(lblQuest, 0, 3, 1, 1);
	      gbAdd(cbPwQ, 1, 3, 3, 1);

	      JScrollPane pane = new JScrollPane(lblQuest);

		// 비밀번호 답
		JLabel lblAnser = new JLabel("비밀번호 답 *");
		tfPwA = new JTextField(30); // 행 열
		gbAdd(lblAnser, 0, 4, 1, 1);
		gbAdd(tfPwA, 1, 4, 3, 1);

		// 이름
		JLabel lblName = new JLabel("이름 *");
		tfName = new JTextField(20);

		gbAdd(lblName, 0, 5, 1, 1);
		gbAdd(tfName, 1, 5, 3, 1);

		// 성별
		JLabel lblGender = new JLabel("성별 *");

		this.rbMale = new JRadioButton("남자", false);
		this.rbFemale = new JRadioButton("여자", false);

		/// 선택 그룹 지정
		ButtonGroup group = new ButtonGroup();
		group.add(rbMale);
		group.add(rbFemale);

		// 패널 사용
		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.add(rbMale);
		pGender.add(rbFemale);

		gbAdd(lblGender, 0, 6, 1, 1);
		gbAdd(pGender, 1, 6, 3, 1);

		// 생년월일

		/*
		 * Date now = new Date(); final SpinnerDateModel model = new
		 * SpinnerDateModel(now, null, now,Calendar.DAY_OF_WEEK); JSpinner spinner = new
		 * JSpinner(model); final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner,"yyyy-MM-dd");
		 * JFormattedTextField ftf = editor.getTextField(); ftf.setEditable(false);
		 * ftf.setHorizontalAlignment(JTextField.CENTER);
		 * 
		 * ftf.setBackground(new Color(255, 255, 255)); spinner.setEditor(editor);
		 * spinner.addChangeListener(new ChangeListener() { public void
		 * stateChanged(ChangeEvent e) { Date value = (Date) model.getValue(); Date next
		 * = (Date) model.getNextValue(); if (value != null && next != null)
		 * System.out.println("value = " + df.format(value) + "\t" + "next = " +
		 * df.format(next)); } });
		 */
		JLabel lblBirth = new JLabel("생년월일 *");
		this.tfBirth = new JTextField(20);
		gbAdd(lblBirth, 0, 7, 1, 1);
		gbAdd(tfBirth, 1, 7, 3, 1);

		// 전화번호
		JLabel lblTel = new JLabel("전화번호 *");
		this.tfTel = new JTextField(20);

		gbAdd(lblTel, 0, 8, 1, 1);
		gbAdd(tfTel, 1, 8, 3, 1);

		// 주소
		JLabel lblAddress = new JLabel("주소 *");
		this.tfAddress = new JTextField(20);

		gbAdd(lblAddress, 0, 9, 1, 1);
		gbAdd(tfAddress, 1, 9, 3, 1);

		// 가족 대출 신청 (체크박스)
		JLabel lblFam = new JLabel("가족대출 신청");
		this.chFam = new JCheckBox();
		chFam.addItemListener(this);
		
		gbAdd(lblFam, 0, 10, 1, 1);
		gbAdd(chFam, 1, 10, 3, 1);

		// 신청 세대주명
		JLabel lblH = new JLabel("신청 세대주명");
		this.tfH = new JTextField(20);
		tfH.setEditable(false);
		
		
		gbAdd(lblH, 0, 11, 1, 1);
		gbAdd(tfH, 1, 11, 3, 1);

		// 버튼들
		JPanel pButton = new JPanel();
		btnJoin = new JButton("가입");
		btnCancel = new JButton("취소");

		pButton.add(this.btnJoin);
		pButton.add(this.btnCancel);
		gbAdd(pButton, 0, 12, 4, 1);

		// 이벤트 연결 - 기능추가\
		this.btnJoin.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnIdCheck.addActionListener(this);

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

	private void btnOnOff(boolean sw) {
		this.btnJoin.setEnabled(sw);
		this.btnCancel.setEnabled(true);

	}

	// main
	public static void main(String[] args) {
		new LibProc();
	}

	// getViewData()
	private LibVo getViewData1() {
		String userid = this.tfId.getText();
		String passwd = this.pfPwd.getText();
		String passwdq = (String)cbPwQ.getSelectedItem();
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
		if (chFam.isSelected())
			fam = 1;
		String holder = this.tfH.getText();
		LibVo user = new LibVo(userid, passwd, passwdq, passwda, username, gender, birth, tel, address, fam, holder);
		return user;
	}

	// 회원 추가
	private void insertUser() {
		LibDao lDao1 = new LibDao();
		LibVo user = getViewData1();
		lDao1.insertUser(user);

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
		this.chFam.setSelected(false);
		this.tfH.setText("");
	}

	// 이벤트 처리
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "중복확인":
			if (tfId.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "입력", JOptionPane.PLAIN_MESSAGE);
				return; // 함수 종료
			}

			LibDao lDao = new LibDao();
			LibVo lv = lDao.getUser(tfId.getText());
			// System.out.println("lv:" + lv);
			// System.out.println("lv:" + tfId);
			if (lv == null) {
				JOptionPane.showMessageDialog(null, "사용가능 합니다.", "가능", JOptionPane.PLAIN_MESSAGE);
				return;
			} else {
				if (lv.getUserid().equals(tfId.getText())) {
					JOptionPane.showMessageDialog(null, "이미 등록된 아이디 입니다.", "중복", JOptionPane.OK_OPTION);
				}
			}
			break;
		case "가입":
			if (this.pfPwd.getText().equals(this.pfPwdC.getText())) {
				insertUser();
				
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호 불일치", "오류!", JOptionPane.OK_OPTION);
			}

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
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(chFam.isSelected()) {
			   tfH.setEditable(true);
		   }else {
			   tfH.setEditable(false);
		   }
		
	}

}
