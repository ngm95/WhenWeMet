package com.spring.project.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class RegisterRequest {

	@Column
	@Pattern(regexp="\\w{4,8}", message="���̵� 4~8�ڷ� �Է����ּ���.")
    private String id;
	
	@Column
	@NotEmpty(message="�̸����� �Է����ּ���.")
	@Email(message="�̸��� ���Ŀ� ���� �ùٸ��� �Է����ּ���.")
    private String email;
	
	@Column
	@Pattern(regexp="\\S{2,8}", message="�̸��� ������� 2~6�ڷ� �Է����ּ���.")
    private String name;
	
	@Column
	@Size(min=4, max=12, message="��й�ȣ�� 4~12�ڷ� �Է����ּ���.")
    private String password;
	
	@Column
	@Size(min=4, max=12, message="��й�ȣ�� 4~12�ڷ� �Է����ּ���.")
    private String checkPassword;
    
    //��й�ȣ Ȯ��
    public boolean isPwEqualToCheckPw() {
        return password.equals(checkPassword);
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

}
