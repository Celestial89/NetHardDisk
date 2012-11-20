package org.renwei.action;

import com.opensymphony.xwork2.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.*;

import org.apache.struts2.interceptor.*;

public class ValidationCodeAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware
{
	private static final long serialVersionUID = 1618054828440716548L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;

	}
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
		
	}

    // 图形验证码的字符集合，系统将随机从这个字符串中选择一些字符作为验证码 
	private String codeChars = "0123456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ";

	// 返回一个随机颜色（Color对象）	
	private Color getRandomColor(int minColor, int maxColor)
	{
		Random random = new Random();
		// 保存minColor最大不会超过255
		if (minColor > 255)
			minColor = 255;
		//  保存minColor最大不会超过255
		if (maxColor > 255)
			maxColor = 255;
		//  获得红色的随机颜色值
		int red = minColor + random.nextInt(maxColor - minColor);
		//  获得绿色的随机颜色值
		int green = minColor + random.nextInt(maxColor - minColor);
		//  获得蓝色的随机颜色值
		int blue = minColor + random.nextInt(maxColor - minColor);
		return new Color(red, green, blue);
	}
	
	
	public String execute() throws Exception
	{
		//  获得验证码集合的长度
		int charsLength = codeChars.length();

		//  下面三条记录是关闭客户端浏览器的缓冲区
		//  这三条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这三条语句的支持也不同
		//  因此，为了保险起见，建议同时使用这三条语句来关闭浏览器的缓冲区
		response.setHeader("ragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		//  设置图形验证码的长和宽（图形的大小）
		int width = 90, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);		
		Graphics g = image.getGraphics();//  获得用于输出文字的Graphics对象
		Random random = new Random();
		g.setColor(getRandomColor(180, 250));// 随机设置要填充的颜色
		g.fillRect(0, 0, width, height);//  填充图形背景
		//  设置初始字体
		g.setFont(new Font("Times New Roman", Font.ITALIC, height));
		
		g.setColor(getRandomColor(120, 180));// 随机设置字体颜色
	    //  用于保存最后随机生成的验证码
		StringBuilder validationCode = new StringBuilder();
		//  验证码的随机字体
		String[] fontNames = { "Times New Roman", "Book antiqua",  "Arial" };
        //  随机生成3个到5个验证码
		//for (int i = 0; i < 3 + random.nextInt(3); i++)
	//  随机生成5个验证码
		for (int i = 0; i < 5; i++)
		{
			//  随机设置当前验证码的字符的字体
			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC,
					height)); 
			//  随机获得当前验证码的字符
			char codeChar = codeChars.charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);			
			//  随机设置当前验证码字符的颜色
			g.setColor(getRandomColor(10, 100));
			//  在图形上输出验证码字符，x和y都是随机生成的			
			g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7),
					height - random.nextInt(6));


		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5 * 60);  // 设置session对象5分钟失效
		//  将验证码保存在session对象中，key为validation_code
		session.setAttribute("validation_code", validationCode.toString());
		
		g.dispose();//  关闭Graphics对象
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "JPEG", os);// 以JPEG格式向客户端发送图形验证码

		
		return null;
	}

}