package org.renwei.model;

public class User
{
	private Long id;
    private String userName;
    private String password;
    private String rePassword;
    private String passwordMD5;
    private String trueName;
    private String email;
    private String validationCode;
    private Integer version;
    
    public Integer getVersion()
	{
		return version;
	}
	
	public void setVersion(Integer version)
	{
		this.version = version;
	}

	public String getValidationCode()
	{
		return validationCode;
	}

	public void setValidationCode(String ValidationCode)
	{
		this.validationCode = ValidationCode;
	}

	public Long getId()
	{
		return id;
	}
	
	private void setId(Long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getTrueName()
	{
		return trueName;
	}

	public void setTrueName(String trueName)
	{
		this.trueName = trueName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPasswordMD5()
	{
		return passwordMD5;
	}

	public void setPasswordMD5(String passwordMD5)
	{
		this.passwordMD5 = passwordMD5;
	}

	public String getRePassword()
	{
		return rePassword;
	}

	public void setRePassword(String rePassword)
	{
		this.rePassword = rePassword;
	}
}
