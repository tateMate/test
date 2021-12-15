package edu.test.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	private int user_id;
	private String user_email;
	private String user_pw;
	private String user_nickname;
	private Boolean user_gender;
	private String user_nationality;
	private int user_age;
	private Boolean user_smoking;
	private Boolean user_vaccine;
	private boolean user_room;
	private boolean user_matching;
	private boolean user_pet;
	private String user_intro;
	private String user_ideal;
	private String user_location;
	private String user_profile;
	private String user_sns;
	
	private UserVO(String user_email, String user_pw, String user_nickname, Boolean user_gender,
			String user_nationality, int user_age, Boolean user_smoking, Boolean user_vaccine, boolean user_room,
			boolean user_matching) {
		this.user_email = user_email;
		this.user_pw = user_pw;
		this.user_nickname = user_nickname;
		this.user_gender = user_gender;
		this.user_nationality = user_nationality;
		this.user_age = user_age;
		this.user_smoking = user_smoking;
		this.user_vaccine = user_vaccine;
		this.user_room = user_room;
		this.user_matching = user_matching;
	}
	
}
