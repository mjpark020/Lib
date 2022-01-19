package model;

public class LibVo {
   //필드 
   private String userid;
   private String passwd;
   private String pwquest;
   private String pwanser;
   private String username;
   private String birth;
   private String gender;
   private String tel;
   private String address;
   private int fam;
   private String holder;
   private String indate;
   private int overdue;
   private String bookname;
   private String author;
   private String publisher;
   private String byear;
   private String bookcode;
   private int booknum;
   private int series;
   private int ea;
   
   //Constructor
   public LibVo(String userid, String passwd, String pwquest, String pwanser, String username, String birth,
         String gender, String tel, String address, int fam, String holder, String indate) {
      this.userid = userid;
      this.passwd = passwd;
      this.pwquest = pwquest;
      this.pwanser = pwanser;
      this.username = username;
      this.birth = birth;
      this.gender = gender;
      this.tel = tel;
      this.address = address;
      this.fam = fam;
      this.holder = holder;
      this.indate = indate;
   }
   
   public String getBookname() {
	return bookname;
}

public void setBookname(String bookname) {
	this.bookname = bookname;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

public String getByear() {
	return byear;
}

public void setByear(String byear) {
	this.byear = byear;
}

public String getBookcode() {
	return bookcode;
}

public void setBookcode(String bookcode) {
	this.bookcode = bookcode;
}

public LibVo(String userid, String passwd,String username, String pwquest, String pwanser,
         String gender, String birth,String tel, String address, int fam, String holder) {
      this.userid = userid;
      this.passwd = passwd;
      this.pwquest = pwquest;
      this.pwanser = pwanser;
      this.username = username;
      this.gender = gender;
      this.birth = birth;
      this.tel = tel;
      this.address = address;
      this.fam = fam;
      this.holder = holder;
   }
   
   public LibVo(String udserid) {
      this.userid = udserid;
   }

    public LibVo(String bookname,String author,String publisher,String byear,String bookcode) {
	      this.bookname = bookname;
	      this.author = author;
	      this.publisher = publisher;
	      this.byear = byear;
	      this.bookcode = bookcode;
	   }
 
   

   public LibVo(String userid, String passwd, String username) {
      this.userid   = userid;
      this.passwd   = passwd;
      this.username = username;
   }


public LibVo(String uid, String passwd, String pwquest, String pwanser, String username, String birth,
		String gender, String tel, String address, int fam, String holder, String indate, int overdue) {
	
    this.userid = uid;
    this.passwd = passwd;
    this.pwquest = pwquest;
    this.pwanser = pwanser;
    this.username = username;
    this.birth = birth;
    this.gender = gender;
    this.tel = tel;
    this.address = address;
    this.fam = fam;
    this.holder = holder;
    this.indate = indate;
    this.overdue = overdue;
}



public LibVo(int booknum, String bookname, String author, String publisher, String byear, int series, String bookcode, int ea) {
    this.booknum = booknum;
	this.bookname = bookname;
    this.author = author;
    this.publisher = publisher;
    this.byear = byear;
    this.series = series;
    this.bookcode = bookcode;
    this.ea = ea;
}

public LibVo() {
	// TODO Auto-generated constructor stub
}

//getter setter
   public String getUserid() {
      return userid;
   }

   public int getOverdue() {
	return overdue;
}

public int getBooknum() {
	return booknum;
}

public void setBooknum(int booknum) {
	this.booknum = booknum;
}

public int getSeries() {
	return series;
}

public void setSeries(int series) {
	this.series = series;
}

public int getEa() {
	return ea;
}

public void setEa(int ea) {
	this.ea = ea;
}

public void setOverdue(int overdue) {
	this.overdue = overdue;
}

public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getPasswd() {
      return passwd;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public String getPwquest() {
      return pwquest;
   }

   public void setPwquest(String pwquest) {
      this.pwquest = pwquest;
   }

   public String getPwanser() {
      return pwanser;
   }

   public void setPwanser(String pwanser) {
      this.pwanser = pwanser;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getBirth() {
      return birth;
   }

   public void setBirth(String birth) {
      this.birth = birth;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public int getFam() {
      return fam;
   }

   public void setFam(int fam) {
      this.fam = fam;
   }

   public String getHolder() {
      return holder;
   }

   public void setHolder(String holder) {
      this.holder = holder;
   }

   public String getIndate() {
      return indate;
   }

   public void setIndate(String indate) {
      this.indate = indate;
   }

   //toString()
   @Override
   public String toString() {
      return "LibVo [userid=" + userid + ", passwd=" + passwd + ", pwquest=" + pwquest + ", pwanser=" + pwanser
            + ", username=" + username + ", birth=" + birth + ", gender=" + gender + ", tel=" + tel + ", address="
            + address + ", fam=" + fam + ", holder=" + holder + ", indate=" + indate + "]";
   }
   
   
   

}