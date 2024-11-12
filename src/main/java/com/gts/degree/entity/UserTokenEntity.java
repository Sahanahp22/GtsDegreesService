package com.gts.degree.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GTS_USER_TOKENS")
public class UserTokenEntity implements Serializable {

	private static final long serialVersionUID = -1671555207165312934L;
	@Id
	@Column(nullable = false)
	private long user_token_pk;

	private long user_id;
	private String user_token;

	public UserTokenEntity(long user_token_pk, long user_id, String user_token) {
		super();
		this.user_token_pk = user_token_pk;
		this.user_id = user_id;
		this.user_token = user_token;
	}

	public UserTokenEntity() {
		super();
	}

	public long getUser_id() {
		return user_id;
	}

	public String getUser_token() {
		return user_token;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public long getUser_token_pk() {
		return user_token_pk;
	}

	public void setUser_token_pk(long user_token_pk) {
		this.user_token_pk = user_token_pk;
	}

}
