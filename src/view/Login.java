package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.LibDao;
import model.LibVo;

public class Login extends JFrame implements ActionListener, KeyListener {
	LibUserScr libscr = null;

	JLabel title; // 도서관명
	static JTextField id; // 아이디 입력
	JPasswordField password; // 비밀번호 입력
	JPanel idpwd; // ID와 비밀번호를 담을 패널
	RoundedButton login; // 로그인 버튼
	JPanel plogin; // 아이디, 비밀번호, 로그인 버튼을 담을 패널
	RoundedButton signUp; // 회원가입 버튼
	RoundedButton forgot; // 아이디/비밀번호 찾기 버튼
	JPanel petc; // 회원가입, 계정찾기 담을 패널. etc = 기타
	LibProc lpro = null;
	Forgot idpw = null;
	LibVo vo = null;
	LibDao dao = null;
	
	AdminPage page = null;
	

	public Login() {
		init();
	}

	private void init() {
		this.setTitle("도서관리프로그램");
		JPanel all = new JPanel();
		all.setBackground(Color.WHITE);
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		all.setAlignmentX(CENTER_ALIGNMENT); // 내부요소 가운데 정렬

		// 도서관명
		JPanel titleP = new JPanel(); // 레이블 담을 패널
		titleP.setBackground(Color.WHITE);
		title = new JLabel("도서관");
		title.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		titleP.add(title);

		// 아이디, 패스워드, 로그인 버튼
		id = new JTextField(15);
		id.setText("아이디");
		password = new JPasswordField(15);
		password.setText("비밀번호");

		// 아이디, 패스워드 입력창을 2행 1열로 배치
		idpwd = new JPanel(new GridLayout(2, 1, 8, 8));
		idpwd.setBackground(Color.WHITE);
		idpwd.add(id);
		idpwd.add(password);
		// 로그인 버튼
		login = new RoundedButton("로그인");
		login.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		login.setPreferredSize(new Dimension(70, 50));
		login.setBGColor(52, 152, 219);

		plogin = new JPanel();
		plogin.setBackground(Color.WHITE);

		plogin.add(idpwd);
		plogin.add(login);

		// 회원가입, 아이디/비밀번호 찾기
		petc = new JPanel();
		petc.setBackground(Color.WHITE);
		petc.setLayout(new BoxLayout(petc, BoxLayout.Y_AXIS));

		signUp = new RoundedButton("          회원가입         ");
		signUp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		signUp.setBGColor(230, 230, 230);
		signUp.setAlignmentX(CENTER_ALIGNMENT);
		forgot = new RoundedButton(" 아이디/비밀번호 찾기 ");
		forgot.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		forgot.setBGColor(230, 230, 230);
		forgot.setAlignmentX(CENTER_ALIGNMENT);

		petc.add(signUp);
		petc.add(Box.createVerticalStrut(15)); // 빈공간 삽입
		petc.add(forgot);

		all.add(Box.createVerticalStrut(20));
		all.add(titleP);
		all.add(Box.createVerticalStrut(20));
		all.add(plogin);
		all.add(petc);
		all.add(Box.createVerticalStrut(20));
		getContentPane().add(all);

		getContentPane().setBackground(Color.WHITE);
		this.pack();
		setSize(330, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		// 이벤트

		this.signUp.addActionListener(this);
		this.forgot.addActionListener(this);
		this.login.addActionListener(this);
		
		this.id.addKeyListener(this);
		this.password.addKeyListener(this);
		

	}

	public static void main(String[] args) {
		new Login();
	}

// 둥근 버튼
	public class RoundedButton extends JButton {
		public RoundedButton() {
			super();
			decorate();
		}

		public RoundedButton(String text) {
			super(text);
			decorate();
		}

		public RoundedButton(Action action) {
			super(action);
			decorate();
		}

		public RoundedButton(Icon icon) {
			super(icon);
			decorate();
		}

		public RoundedButton(String text, Icon icon) {
			super(text, icon);
			decorate();
		}

		protected void decorate() {
			setBorderPainted(false);
			setOpaque(false);
		}

		int bgRed = 240;
		int bgGreen = 240;
		int bgBlue = 240;

		public void setBGColor(int re, int gr, int bl) {
			bgRed = re;
			bgGreen = gr;
			bgBlue = bl;
		}

		int fontRed = 0;
		int fontGreen = 0;
		int fontBlue = 0;

		public void setFontColor(int re, int gr, int bl) {
			fontRed = re;
			fontGreen = gr;
			fontBlue = bl;
		}

		@Override
		protected void paintComponent(Graphics g) {
			Color c = new Color(bgRed, bgGreen, bgBlue); // 배경색 결정
			Color o = new Color(fontRed, fontGreen, fontBlue); // 글자색 결정
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (getModel().isArmed()) {
				graphics.setColor(c.darker());
			} else if (getModel().isRollover()) {
				graphics.setColor(c.brighter());
			} else {
				graphics.setColor(c);
			}
			graphics.fillRoundRect(0, 0, width, height, 10, 10);
			FontMetrics fontMetrics = graphics.getFontMetrics();
			Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
			int textX = (width - stringBounds.width) / 2;
			int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
			graphics.setColor(o);
			graphics.setFont(getFont());
			graphics.drawString(getText(), textX, textY);
			graphics.dispose();
			super.paintComponent(g);
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			liblogin();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "로그인":
			liblogin();
			break;
		case "          회원가입         ":
			if (lpro != null)
				lpro.dispose();
			lpro = new LibProc();

			break;
		case " 아이디/비밀번호 찾기 ":
			if (idpw != null)
				idpw.dispose();
			idpw = new Forgot();
			break;
		}

	}

	public void liblogin() {
	
		String id = "admin";
		String pw = "aaa";
		
		
		
		dao = new LibDao();
		dao.loginck(this.id.getText());

		
		
		if(id.equals(this.id.getText()) && pw.equals(this.password.getText())) {
			JOptionPane.showMessageDialog(null,"관리자님 환영합니다");
			page = new AdminPage();
			this.dispose();
		
		}
		else if(this.id.getText().equals(dao.getUid()) && this.password.getText().equals(dao.getPasswd())){
			JOptionPane.showMessageDialog(null, dao.getUsername() + " 님 환영합니다");	
			
			libscr = new LibUserScr();
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "이이디,비밀번호가 일치하지 않습니다");
		}
		

	}

}
