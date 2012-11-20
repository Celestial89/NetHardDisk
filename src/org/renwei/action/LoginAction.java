package org.renwei.action;

import javax.servlet.http.HttpSession;

import org.renwei.model.User;
import org.renwei.common.Encrypter;
import org.renwei.dao.UserDaoImpl;
import org.renwei.dao.interfaces.UserDao;
import org.renwei.action.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = 6543243677028320111L;
	private User user = new User();
	public UserDao userDao;
	
	public LoginAction()
	{
		userDao=new UserDaoImpl();
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
				this.addFieldError("validationCode", "—È÷§¬Î ‰»Î¥ÌŒÛ!");
			}
		}
	}
	
	public boolean verifyUser(User user)
	{
		User userOld = userDao.loadUser(user);
		String passwordMD5 = null;
		try {
			passwordMD5 = Encrypter.md5Encrypt(user.getPassword());
		} catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		boolean result = false;
		try
		{
			result = (passwordMD5.equals(userOld.getPasswordMD5()))?true:false;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	public String execute() throws Exception
	{
		try
		{         
			if(verifyUser(user))
			{
				saveCookie("user", user.getUserName(), 24 * 60 * 60);
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUserName());
				session.setMaxInactiveInterval(60 * 60 * 3);
 			    return SUCCESS;
			}
		}
		catch (Exception e)
		{
		}
		return ERROR;
	}
	
}
