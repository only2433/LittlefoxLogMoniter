package com.littlefox.logmonitor;


import android.content.Context;
import android.os.Environment;

import com.littlefox.logmonitor.common.Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Log {
	private static final String none_tag = "System.out";
	public static enum LOG_TYPE {
		INFO, ERROR, SEND, RECEIVE
	}
	
	private static long stamp_time = System.currentTimeMillis();

    private Log() {
    }
    
    public static void init(Context context, String fileName)
    {
    	init(context, fileName, false);
    }
    
    public static void initWithDeleteFile(Context context,String fileName)
    {
    	init(context, fileName, true);
    }
    
    private static void init(Context context,  String fileName, boolean deleteFile)
    {
    	Common.PATH_ROOT = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/Log";
		Common.LOG_FILE = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + fileName;
    	
    	if(deleteFile)
    	{
    		File rootFolder = new File(Common.PATH_ROOT);
        	File[] files = rootFolder.listFiles();
        	
        	for(File childFile : files)
        	{
        		if(childFile.getName().equals(fileName))
        		{
        			childFile.delete();
        		}
        	}
    	}
    }
    
    public static void stampStart()
    {
    	stamp_time = System.currentTimeMillis();
    }
    
    public static void stampEnd(String msg)
    {
    	android.util.Log.e(none_tag, convertErrMsg(msg + (System.currentTimeMillis() - stamp_time)));
    }
    

    public static long getLogfileSize()
    {
    	File file = new File(Common.LOG_FILE);
    	return file.length();
    }
    
    public static String getLogfilePath()
    {
    	return Common.LOG_FILE;
    }
    


    public static int v(String msg) {
    	return android.util.Log.v(none_tag, convertErrMsg(msg));
    }
    

    public static int v(String tag, String msg) {
    	return android.util.Log.v(tag, convertErrMsg(msg));
    }


    public static int v(String msg, Throwable tr) {
    	return android.util.Log.v(none_tag, convertErrMsg(msg), tr);
    }
    

    public static int v(String tag, String msg, Throwable tr) {
    	return android.util.Log.v(tag, convertErrMsg(msg), tr);
    }


    public static int d(String msg) {
    	return android.util.Log.d(none_tag, convertErrMsg(msg));
    }
    

    public static int d(String tag, String msg) {
    	return android.util.Log.d(tag, convertErrMsg(msg));
    }


    public static int d(String msg, Throwable tr) {
    	return android.util.Log.d(none_tag, convertErrMsg(msg), tr);
    }
    

    public static int d(String tag, String msg, Throwable tr) {
    	return android.util.Log.d(tag, convertErrMsg(msg), tr);
    }


    public static int i(String msg) {
    	return android.util.Log.i(none_tag, convertErrMsg(msg));
    }
    

    public static int i(String tag, String msg) {
    	return android.util.Log.i(tag, convertErrMsg(msg));
    }


    public static int i(String msg, Throwable tr) {
    	return android.util.Log.i(none_tag, convertErrMsg(msg), tr);
    }
    

    public static int i(String tag, String msg, Throwable tr) {
    	return android.util.Log.i(tag, convertErrMsg(msg), tr);
    }


    public static int w(String msg) {
    	return android.util.Log.w(none_tag, convertErrMsg(msg));
    }
    

    public static int w(String tag, String msg) {
    	return android.util.Log.w(tag, convertErrMsg(msg));
    }


    public static int w(String msg, Throwable tr) {
    	return android.util.Log.w(none_tag, convertErrMsg(msg), tr);
    }
    

    public static int w(String tag, String msg, Throwable tr) {
    	return android.util.Log.w(tag, convertErrMsg(msg), tr);
    }


    public static int e(String msg) {
    	return android.util.Log.e(none_tag, convertErrMsg(msg));
    }
    

    public static int e(String tag, String msg) {
    	return android.util.Log.e(tag, convertErrMsg(msg));
    }


    public static int e(String msg, Throwable tr) {
    	return android.util.Log.e(none_tag, convertErrMsg(msg), tr);
    }
    

    public static int e(String tag, String msg, Throwable tr) {
    	return android.util.Log.e(tag, convertErrMsg(msg), tr);
    }
    

    public static int f(String msg) {
		String log = convertErrMsg("<I>" + msg);
		setFileLog(log);
		return android.util.Log.i(none_tag, log);
//		setFileLog(log, false);
	}
	
	public static int f(String msg, LOG_TYPE type) {
		String log = "";
		int rtn = 0;
		switch (type) {
			case INFO: 
				log = convertErrMsg("<I>" + msg);
				setFileLog(log);
				rtn = android.util.Log.i(none_tag, log);
				break;
			case ERROR: 
				log = convertErrMsg("<E>" + msg);
				setFileLog(log);
				rtn = android.util.Log.e(none_tag, log);
				break;
			case SEND: 
				log = convertErrMsg("<S>" + msg);
				setFileLog(log);
				rtn = android.util.Log.d(none_tag, log);
				break;
			case RECEIVE: 
				log = convertErrMsg("<R>" + msg);
				setFileLog(log);
				rtn = android.util.Log.w(none_tag, log);
				break;
			default: 
				log = convertErrMsg("<I>" + msg);
				setFileLog(log);
				rtn = android.util.Log.i(none_tag, log);
				break;
		}
		return rtn;
	}
	
	public static int f(String msg, LOG_TYPE type, boolean is_convert) {
		String log = "";
		int rtn = 0;
		switch (type) {
			case INFO: 
				if (is_convert == true) log = convertErrMsg("<I>" + msg);
				else log = msg;
				setFileLog(log);
				rtn = android.util.Log.i(none_tag, log);
				break;
			case ERROR: 
				if (is_convert == true) log = convertErrMsg("<E>" + msg);
				else log = msg;
				setFileLog(log);
				rtn = android.util.Log.e(none_tag, log);
				break;
			case SEND: 
				if (is_convert == true) log = convertErrMsg("<S>" + msg);
				else log = msg;
				setFileLog(log);
				rtn = android.util.Log.d(none_tag, log);
				break;
			case RECEIVE: 
				if (is_convert == true) log = convertErrMsg("<R>" + msg);
				else log = msg;
				setFileLog(log);
				rtn = android.util.Log.w(none_tag, log);
				break;
			default: 
				if (is_convert == true) log = convertErrMsg("<I>" + msg);
				else log = msg;
				setFileLog(log);
				rtn = android.util.Log.i(none_tag, log);
				break;
		}
		return rtn;
	}
	
	private static void setFileLog(String msg) {
		try {
			PrintWriter pw = null;
			File f = new File(Common.PATH_ROOT);
			if (!f.exists())
			{
				f.mkdirs();
			}
			f = new File(Common.LOG_FILE);
			try {
				if (!f.exists())
				{
					f.createNewFile();
				}
				pw = new PrintWriter(new FileWriter(f, true), true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.write(toDateAndTime(System.currentTimeMillis(), "MMdd HH:mm:ss") + "\t" + msg + "\n");
			pw.close();
			pw = null;
			f = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exception(String title, Exception ex) {
		try {
			String msg = "";
			msg += "<E>" + title + "\n";
			StackTraceElement[] arrErr = ex.getStackTrace();
			for (StackTraceElement stackTraceElement : arrErr) {
				msg += stackTraceElement.toString() + "\n";
			}
			f(msg, LOG_TYPE.ERROR, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exception(String title, StackTraceElement[] arrErr) {
		try {
			String msg = "";
			msg += "<E>" + title + "\n";
			for (StackTraceElement stackTraceElement : arrErr) {
				msg += stackTraceElement.toString() + "\n";
			}
			f(msg, LOG_TYPE.ERROR, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exception(Exception ex) {
		try {
			String msg = "<E>";
			msg += ex.toString() + "\n";
			StackTraceElement[] stack = ex.getStackTrace();
			for (StackTraceElement stackTraceElement : stack) {
				msg += "\tat " + stackTraceElement.toString() + "\n";
			}
			Throwable cause = ex.getCause();
	        if (cause != null) {
	        	msg += "Caused by: ";
	        	msg += cause.toString() + "\n";
	        	StackTraceElement[] stack2 = ex.getStackTrace();
	    		for (StackTraceElement stackTraceElement : stack2) {
	    			msg += "\tat " + stackTraceElement.toString() + "\n";
	    		}
	        }
	        f(msg, LOG_TYPE.ERROR, false);
		} catch (Exception e) { }
	}

	public static void exception(Throwable ex) {
		try {
			String msg = "<E>";
			msg += ex.toString() + "\n";
			StackTraceElement[] stack = ex.getStackTrace();
			for (StackTraceElement stackTraceElement : stack) {
				msg += "\tat " + stackTraceElement.toString() + "\n";
			}
			f(msg, LOG_TYPE.ERROR, false);
		} catch (Exception e) { }
	}
	
//	public static void exception(ExceptionEvent ex) {
//		try {
//			String msg = "<E>";
//			msg += ex.toString() + "\n";
//			Throwable cause = ex.getCause();
//			if (cause != null) {
//				msg += cause.toString() + "\n";
//				StackTraceElement[] stack = cause.getStackTrace();
//				for (StackTraceElement stackTraceElement : stack) {
//					msg += "\tat " + stackTraceElement.toString() + "\n";
//				}
//			}
//			f(msg, LOG_TYPE.ERROR, false);
//		} catch (Exception e) { }
//	}
	

    private static String toDateAndTime(long TimeInMillis, String exp){
        Calendar    cal = Calendar.getInstance(); 
        DateFormat  format = new SimpleDateFormat(exp);
        cal.setTimeInMillis(TimeInMillis);
        String result = format.format(cal.getTime());
        return result; 
    }
    
    private static String convertErrMsg(String val) {
    	String rtn = val;
    	try {
    		StackTraceElement[] a = new Throwable().getStackTrace();
    		if (Common.isLogcat) {
    			rtn += "\n\tat " + a[2].toString();
    		}
    		else {
    			String class_name = a[2].getClassName();
    			if (class_name.contains(".")) {
    				class_name = class_name.substring(class_name.lastIndexOf(".") + 1);
    			} else {
//    				class_name = class_name.replace(context.getPackageName() + ".", "");
    			}
    			if (class_name.contains("$")) {
    				class_name = class_name.substring(0, class_name.indexOf("$"));
    			}
    			rtn = class_name + "(" + a[2].getMethodName() + ":" + a[2].getLineNumber() + ")" + "\t" + val;
    		}
    	} catch (Exception e) {
			rtn = val;
		}
    	return rtn;
    }
}
