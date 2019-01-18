package cn.no7player.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



public class Tools {

	public static final String EMPTY = "";

	public static final String NULL = "null";

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param str
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 同 {@link notEmpty} 检测字符串是否不为空(null,"","null")
	 * 
	 * @author SevenWong<br>
	 * @date 2016年9月1日下午2:28:35
	 * @param str
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param str
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || EMPTY.equals(str) || NULL.equals(str);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (isNotEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return EMPTY;
		}
	}

	/**
	 * 将emoji表情处理成"?"保存
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月30日上午11:32:53
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("?");
			}
		}
		return source;
	}

	

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	//判断参数时间与当前时间相差分钟数
	public static long getDifferenceMinuteToNow(Date updateTime) {
		long differenceMinute=0;
		if(updateTime!=null) {
			differenceMinute= (new Date().getTime()-updateTime.getTime())/(1000 * 60);
		}
		return differenceMinute;
		
	}
	public static String dateStringToString(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd HH:mm:SS").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2Str(date);
	}
	
	public static BigDecimal getDivideByBigDecimal(String a1,String a2,int decimal) {
		if(decimal<=0) {
			decimal=2;
		}
		BigDecimal b1=a1==null?BigDecimal.ZERO:new BigDecimal(a1);
		BigDecimal b2=a2==null?BigDecimal.ZERO:new BigDecimal(a2);
		if(b1.compareTo(BigDecimal.ZERO)==0 || b2.compareTo(BigDecimal.ZERO)==0) {
			return BigDecimal.ZERO;
		}
		return b1.divide(b2,decimal,BigDecimal.ROUND_HALF_UP);
	}
	public static BigDecimal getAddByBigDecimal(String a1,String a2) {
		BigDecimal b1=BigDecimal.ZERO;
		BigDecimal b2=BigDecimal.ZERO;
		if(a1!=null && a1!="") {
			b1=new BigDecimal(a1);
		}
		if(a2!=null && a2!="") {
			b2=new BigDecimal(a2);
		}
		return b1.add(b2);
	}
	
	public static String formatRate(BigDecimal obj) {
		DecimalFormat RATE_PERCENT_FORMAT = new DecimalFormat("#0.0%");
		return RATE_PERCENT_FORMAT.format(obj);
	}
}
