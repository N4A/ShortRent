package com.fudan.action;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.fudan.biz.IHouseBiz;
import com.fudan.biz.impl.HouseBizImpl;
import com.fudan.entity.House;

public class CutImage {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int width;
	private int height;
	private String filePath;//文件路径
	private int houseId;
	private int userId;
	private int type;//0为房屋，1为用户头像
	private int isCut;//是否裁剪过
	private int show;
	private int numberOfPic;
	private double toSmall;//图片放大缩小倍率
	String destPath = ServletActionContext.getServletContext().getRealPath("/upload"); //获得服务器路径
	IHouseBiz hoBiz = new HouseBizImpl();
	House ho = new House();
	public String execute(){
		System.out.println(x1);
		System.out.println(y1);
		System.out.println(toSmall);
		System.out.println(height);
		System.out.println(width);
		String path;
		String newPath;
		//初始化裁剪位置和大小，乘以缩小倍率
		x1=(int) (x1*toSmall);
		y1=(int) (y1*toSmall);
		height=(int) (height*toSmall);
		width=(int) (width*toSmall);
		if(type==0) {//房屋上传
			ho=hoBiz.findHouse(houseId);
			numberOfPic=ho.getPicNum();//从数据库取出已上传图片数
			if(height==0){
				height=(int)(width/4.0*3.0);
			}
			else if(width==0){
				width=(int)(height*4.0/3.0);
			}
			path=destPath+"/"+filePath;
			newPath = destPath+"/h"+numberOfPic+"_"+houseId+".png";//裁剪后路径
		}
		else{
			if(height==0){
				height=(int)(width);
			}
			else if(width==0){
				width=(int)(height);
			}
			path=destPath+"/"+filePath;
			newPath = destPath+"/user/u_"+userId+".png";//裁剪后路径
		}
        try {  
            Image img;  
            ImageFilter cropFilter;  
            // 读取源图像  
            System.out.println(newPath);
            BufferedImage bi = ImageIO.read(new File(path));  
            int srcWidth = bi.getWidth(); // 源图宽度  
            int srcHeight = bi.getHeight(); // 源图高度            
            if (srcWidth >= width && srcHeight >= height) {  
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);  
                // 改进的想法:是否可用多线程加快切割速度  
                // 四个参数分别为图像起点坐标和宽高  
                // 即: CropImageFilter(int x,int y,int width,int height)  
                cropFilter = new CropImageFilter(x1, y1, width, height);  
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));  
                BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
                Graphics g = tag.getGraphics();  
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图  
                g.dispose();  
                // 输出为文件  
                ImageIO.write(tag, "JPEG", new File(newPath));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        File f = new File(newPath);                                
        if(f.exists()){    
        	System.out.println(newPath);                     
            System.out.println("剪切图片大小: "+width+"*"+height+"图片成功!"); 
            if(type==0) {
	            filePath="h"+numberOfPic+"_"+houseId+".png";//裁剪后路径
	            isCut=1;//裁剪后，显示继续上传的页面
	            numberOfPic+=1;//上传图片数加一
	            ho.setPicNum(numberOfPic);
	            hoBiz.update(ho);
	            return "success";
            }
            else{
            	return "userSuccess";
            }
        }
        else{
        	return "error";
        }
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public double getToSmall() {
		return toSmall;
	}

	public void setToSmall(double toSmall) {
		this.toSmall = toSmall;
	}
	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public int getIsCut() {
		return isCut;
	}

	public void setIsCut(int isCut) {
		this.isCut = isCut;
	}

	public int getNumberOfPic() {
		return numberOfPic;
	}

	public void setNumberOfPic(int numberOfPic) {
		this.numberOfPic = numberOfPic;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
