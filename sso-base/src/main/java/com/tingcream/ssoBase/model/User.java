package com.tingcream.ssoBase.model;

import java.io.Serializable;

/**
 * 用户信息
 * @author jelly
 */
public class User  implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	private String userId; //用户id 主键uuid
	private String username;//用户名
	private String password;//密码
	private String birthday;//生日
	private String city;//城市
	private String email;//邮箱
	private String createtime;//创建时间
	private String remark;//备注

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	
}
