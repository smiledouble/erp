package com.cxs.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * @author LJH
 *
 */
public class RandomUtils {

	static SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	static SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat format3=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
	static Random random=new Random();
	
	/**
	 * 根据文件名生成新名字---根据时间+4四位随机数
	 * @param oldName   aaaaa.png   aaaa.aaa.aaa.png
	 */
	public static String createFileNameUseTime(String oldName){
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		String time=format1.format(new Date());
		Integer random4=random.nextInt(9000)+1000;
		return time+random4+suffix;
	}
	/**
	 * 根据文件名生成新名字---使用UUID
	 */
	public static String createFileNameUseUUID(String oldName){
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return uuid+suffix;
	}
	
	
	/**
	 * 根据当前时间得到日期的字符字符串 
	 * 格式 为:yyyy-MM-dd
	 * @param args
	 */
	public static String createDateStrUseCurrrentDate(){
		return format2.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(createFileNameUseUUID(" aaaa.aaa.aaa.png"));
	}
	
	/**
	 * 生成出租单号和检查单号
	 * @param rentIdPrefix
	 * @return
	 */
	public static String createRandomStringUsePrefix(String prefix) {
		return prefix+"_"+format3.format(new Date())+"_"+(random.nextInt(90000)+10000);
	}
	
}
