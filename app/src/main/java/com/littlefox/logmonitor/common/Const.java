package com.littlefox.logmonitor.common;

import android.os.Environment;

public class Const
{
	public final static String PATH_SDCARD	= Environment.getExternalStorageDirectory().getAbsolutePath();
	public final static String PATH_ROOT	= PATH_SDCARD + "/LittleFox/Log/";
	public final static String CONTENT_URL 	= "content.html";
	public  static String LOG_FILE			= PATH_ROOT + "log.txt";
	
	public static boolean isLogcat	= false;
}
