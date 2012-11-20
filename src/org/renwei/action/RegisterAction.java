package org.renwei.action;

import java.io.File;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import org.renwei.dao.UserDaoImpl;
import org.renwei.dao.interfaces.UserDao;
import org.renwei.model.User;
import org.renwei.common.*;


public class RegisterAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = 5554445045728540099L;
	private User user = new User();
	private UserDao userDao;
	
	public RegisterAction()
	{
		userDao = new UserDaoImpl();
	}

	public User getModel()
	{
		return user;
	}

	@Override
	public void validate()
	{
		if("".equals(user.getValidationCode())) return;
		
		Object obj = ActionContext.getContext().getSession().get(
				"validation_code");
		
		String validationCode = (obj != null) ? obj.toString() : "";

		if (!validationCode.equalsIgnoreCase(user.getValidationCode()))
		{
			if (user.getValidationCode() != null)
			{				
				this.addFieldError("validationCode", "验证码输入错误");
			}
		}
	}

	public String execute() throws Exception
	{
		try
		{
			String password_md5 = Encrypter.md5Encrypt(user.getPassword());
			user.setPasswordMD5(password_md5);
			userDao.saveUser(user);
			File dir = new File("E:\\netdisk\\" + user.getUserName());
            if(!dir.exists())
               dir.mkdirs();
			result = "<" + user.getUserName() + ">注册成功！";
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.getMessage();
			return ERROR;
		}

	}
}