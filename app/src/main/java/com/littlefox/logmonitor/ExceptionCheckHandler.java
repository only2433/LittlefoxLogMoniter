package com.littlefox.logmonitor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;

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

		final Runtime runtime = Runtime.getRuntime();
		final long usedMemInMB=(runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
		final long maxHeapSizeInMB=runtime.maxMemory() / 1048576L;
		final long availHeapSizeInMB = maxHeapSizeInMB - usedMemInMB;

		Log.f("usedMemInMB : "+usedMemInMB+", maxHeapSizeInMB : "+maxHeapSizeInMB+", availHeapSizeInMB : "+availHeapSizeInMB, LOG_TYPE.ERROR, true);

		ActivityManager am  = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
		{
			am.killBackgroundProcesses(mContext.getPackageName());
		}


		((Activity) mContext).moveTaskToBack(true); 
		((Activity) mContext).finish(); 
		Process.killProcess(Process.myPid());
	}

}
