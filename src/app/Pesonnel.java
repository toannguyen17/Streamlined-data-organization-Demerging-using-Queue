package app;

import java.util.Date;

public class Pesonnel {
	private String name;
	private String sex;
	private String birthBay;
	private String birthMonth;
	private String birthYear;

	private Date birthDay;

	public Pesonnel(){
	}

	public Pesonnel(String name, String sex, int birthBay, int birthMonth, int birthYear) {
		this.name       = name;
		this.sex        = sex;
		this.birthBay   = "0" + birthBay;
		this.birthMonth = "0" + birthMonth;
		this.birthYear  = String.valueOf(birthYear);
		birthDay = new Date(birthYear, birthMonth, birthBay);
	}

	public int getBirthDayTime(){
		return (int) birthDay.getTime();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthBay() {
		return birthBay;
	}

	public void setBirthBay(String birthBay) {
		this.birthBay = birthBay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return (String) birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = String.valueOf(birthYear);
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
