package com.fudan.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

//本action实现图片上传2015/07/22
public class UploadAction extends ActionSupport{
 
	private static final long serialVersionUID = 1L;
	private File[] mf;//代表临时目录下的上传文件
	private String[] mfFileName;////原始文件名
	private String[] mfContentType;//原始文件的MIME类型
	private String filePath;//文件路径
	private int houseId;
	private int userId;

	private int numberOfPic;//上传图片数目
	//显示图片宽高
	private int width;
	private int height;
	//图片容器大小
	private int padding;
	private int divHeight;
	private int paddingLeft;
	private int divWidth;
	//图片宽高缩小倍率
	private double toSmall;
	private int show;
	private int isCut;
	private int type;//0为房屋，1为头像
	
	public String execute(){
		if (mf == null) return "error";
		System.out.println(mf.length);
		String destFile="";
		for (int i=0;i<mf.length;i++){
			System.out.println("上传文件MIME类型:"+mfContentType[i]);
			//将临时文件对象f拷贝到目标位置
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(mf[i]));
				String destPath = ServletActionContext.getServletContext().getRealPath("/upload"); //获得服务器路径
				if(type==0) {
					destFile = destPath+"/raw_h"+(i+1)+"_"+houseId+".png";//临时图像
					filePath="raw_h"+(i+1)+"_"+houseId+".png";
				}
				else{
					destFile = destPath+"/user/raw_u_"+userId+".png";//临时图像
					filePath="user/raw_u_"+userId+".png";
				}
				System.out.println("---------"+destFile);
				bos = new BufferedOutputStream(new FileOutputStream(destFile));
				byte[] bts = new byte[1024];
				int len = -1;
				while((len = bis.read(bts)) != -1){
					//将bts字节信息写入目标文件
					bos.write(bts,0,len);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}finally{
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File f = new File(destFile);                                
        if(f.exists()){                          
        	 BufferedImage bi;
			try {
				bi = ImageIO.read(new File(destFile));
				int srcWidth = bi.getWidth(); // 源图宽度  
	            int srcHeight = bi.getHeight(); // 源图高度 
	            
	            //计算显示高度，缩放在800*600的框内
	            if(srcWidth>srcHeight){
	            	if(srcWidth>800){
		            	width=800;
		            	toSmall=(srcWidth/800.00);
		            	height=(int)(srcHeight/toSmall);
		            }
	            	else{
	            		width=srcWidth;
	            		height=srcHeight;
	            		toSmall=1;
	            	}
	            }
	            else{
	            	if(srcHeight>600){
	            		height=600;
	            		toSmall=(srcHeight/600.00);
		            	width=(int)(srcWidth/toSmall);
		            }
	            	else{
	            		width=srcWidth;
	            		height=srcHeight;
	            		toSmall=1;
	            	}
	            }
	            padding=0;//默认值
	            paddingLeft=0;
	            divHeight=600;//默认值
	            divWidth=800;
	            if(height<600){
	            	padding=(600-height)/2;//为使图片上下居中，而设padding
	            	divHeight=600-padding;
	            }
	            if(width<800){
	            	paddingLeft=(800-width)/2;
	            	divWidth=800-paddingLeft;
	            }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(type==0) {//上传房屋图片
				show=1;//设为1，显示裁剪图片窗口
				isCut=2;//设为2，隐藏原来上传图片控件
	            return "success";
			}
			else{//上传头像
				 return "userSuccess";
			}
        }
        else{
        	return "error";
        }
	}

	public File[] getMf() {
		return mf;
	}

	public void setMf(File[] mf) {
		this.mf = mf;
	}

	public String[] getMfFileName() {
		return mfFileName;
	}

	public void setMfFileName(String[] mfFileName) {
		this.mfFileName = mfFileName;
	}
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getMfContentType() {
		return mfContentType;
	}

	public void setMfContentType(String[] mfContentType) {
		this.mfContentType = mfContentType;
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
	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getDivHeight() {
		return divHeight;
	}

	public void setDivHeight(int divHeight) {
		this.divHeight = divHeight;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public int getDivWidth() {
		return divWidth;
	}

	public void setDivWidth(int divWidth) {
		this.divWidth = divWidth;
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
	public int getIsCut() {
		return isCut;
	}

	public void setIsCut(int isCut) {
		this.isCut = isCut;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
