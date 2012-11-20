package org.renwei.action;

import javax.servlet.http.Cookie;

public class ReloginAction extends BaseAction
{
	private static final long serialVersionUID = 2914188964756505435L;

	public String execute() throws Exception
	{
		try
		{
			Cookie cookie = new Cookie("JSESSIONID", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);			
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
}