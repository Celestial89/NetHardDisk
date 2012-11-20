package org.renwei.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;
import com.opensymphony.xwork2.*;
import java.util.*;

public class AuthorizationInterceptor extends AbstractInterceptor
{
	private static final long serialVersionUID = 8165665474715262156L;
	
	private String ignoreActions;

	// ignoreActions属性的getter方法
	public String getIgnoreActios()
	{
		return ignoreActions;
	}

	// ignoreActions属性的setter方法
	public void setIgnoreActions(String ignoreActions)
	{
		this.ignoreActions = ignoreActions;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{

		ActionContext ctx = invocation.getInvocationContext();
		
		Map session = ctx.getSession();
		String user = (String) session.get("username");
	
		boolean ignore = false;
		String currentAction = invocation.getProxy().getActionName();
		String[] actions = ignoreActions.split(",");

		for (String action : actions)
		{
			if (currentAction.matches(action.trim()))
			{
				ignore = true;
				break;
			}
		}

		if (user != null || ignore == true)
		{

			return invocation.invoke();
		}
		else
		{
			return Action.LOGIN;
		}

	}
}
