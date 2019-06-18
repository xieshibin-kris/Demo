package com.miku.advs.core.util.ip;

import com.miku.advs.core.util.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 */
public class IpAddrUtils {
	
	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取服务器的ip地址
     *
     * @param request
     * @return
     */
    public static String getLocalIp(HttpServletRequest request) {
        return request.getLocalAddr();
    }

    public static long ipToLong(String ip){
		if(StringUtils.isEmpty(ip)) return 0;
		
		String [] array = ip.split("\\.");
		if(array.length != 4) return 0;
		
		StringBuffer sb = new StringBuffer();
		sb.append(array[0]);
		
		for (int i = 1; i < array.length; i++) {
			int temp = Integer.parseInt(array[i]);
			sb.append(String.format("%03d", temp));
		}
		
		try{
			return Long.parseLong(sb.toString());
		}catch(Exception e){
		}
		return 0;
	}

}