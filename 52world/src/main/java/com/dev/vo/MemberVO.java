package com.dev.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private Date birth;
	private String gender;
	private String email;
	private String phone;
	private String intropoduce;
	private int dotori;

}
