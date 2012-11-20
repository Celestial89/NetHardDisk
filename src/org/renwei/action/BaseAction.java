package org.renwei.action;

import com.opensymphony.xwork2.*;
import java.util.*;
import javax.servlet.http.*;

import org.renwei.common.UserInfo;


public class BaseAction extends ActionSupport implements
		org.apache.struts2.interceptor.ServletRequestAware,
		org.apache.struts2.interceptor.ServletResponseAware
{
	private static final long serialVersionUID = 7199027981977555591L;
	protected UserInfo userInfo;
	protected String result;	
	protected Map<String, String> cookies;
	protected javax.servlet.http.HttpServletResponse response;
	protected javax.servlet.http.HttpServletRequest request;

	public BaseAction()
	{
		userInfo = new UserInfo();
	}
	
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	protected String getCookieValue(String name)
	{
		javax.servlet.http.Cookie cookies[] = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (!cookie.getName().equals(name))
					continue;
				return cookie.getValue();
			}

		}
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
		userInfo.setRoot("E:\\netdisk\\");
		userInfo.setCookieUser(getCookieValue("user"));
		userInfo.setUserRoot(userInfo.getRoot() + userInfo.getCookieUser());
	}
	
	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public void setUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
	}

	protected void saveCookie(String name, String value, int maxAge)
	{
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name,
				value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

}