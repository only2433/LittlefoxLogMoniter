package com.littlefox.logmonitor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.littlefox.logmonitor.Log.LOG_TYPE;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionCheckHandler implements UncaughtExceptionHandler
{
	private Context mContext;
	public ExceptionCheckHandler(Context context)
	{
		mContext = context;
	}
	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		Log.f(sw.toString(), LOG_TYPE.ERROR, true);

		ActivityManager am  = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
		am.killBackgroundProcesses(mContext.getPackageName());
		
		((Activity) mContext).moveTaskToBack(true); 
		((Activity) mContext).finish(); 
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
