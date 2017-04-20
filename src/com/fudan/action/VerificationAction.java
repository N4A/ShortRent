package com.fudan.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


//此类用于生成随机验证码图片，内容为4个数字//2015/07/21
public class VerificationAction {


		private InputStream inputStream;
		
		public String execute(){
			BufferedImage image=new BufferedImage(60, 20,BufferedImage.TYPE_INT_RGB);
			Graphics g=image.getGraphics();
			Random r=new Random();
			//设置图片颜色
			g.setColor(Color.white);
			g.fillRect(0, 0, 60, 20);
			//设置随机颜色
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			int num=r.nextInt(9999);//生成随机验证码
			g.setFont(new Font("hello",Font.ITALIC,18));//设置字形和字体大小
			g.drawString(num+"", 10, 15);
			ActionContext.getContext().getSession().put("num", num+"");//将验证码加到session
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(bos);
			try {
				encoder.encode(image);
				inputStream=new ByteArrayInputStream(bos.toByteArray());
			} catch (ImageFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "success";
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

}
