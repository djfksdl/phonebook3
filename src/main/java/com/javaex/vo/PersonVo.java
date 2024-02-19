package com.javaex.vo;

public class PersonVo {
	
	//필드
	private int personId; //나중에 일련번호도 가져와야해서 지금은 필요없지만 만들어 놓는다.
	private String name;
	private String hp;
	private String company;
	
	//생성자
	public PersonVo() {
		super();
	}
	
	public PersonVo(String name, String hp, String company) {
		super();
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PersonVo(int personId, String name, String hp, String company) {
		super();
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	//메소드-gs
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	//메소드-일반
	// 데이터 잘 들어갔는지 보기위해 toString 만들어줌
	@Override
	public String toString() {
		return "PersonVo [personId=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
	


	
}
