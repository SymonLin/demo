package com.example.demo.common.util;

import com.example.demo.common.constant.Constants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author linjian
 * @date 2021/2/24
 */
@Slf4j
public class CommonUtils {

    private CommonUtils() {
        // ignored
    }

    private static final Integer BOUND = 10;

    private static final List<String> LOCAL_HOST_LIST = Lists.newArrayList("0:0:0:0:0:0:0:1");

    public static String getRandomCode(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = Constants.ZERO; i < length; i++) {
            sb.append(random.nextInt(BOUND));
        }
        return sb.toString();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("wl-proxy-client-ip");
        if (isEmpty(ipAddress)) {
            ipAddress = request.getHeader("x-true-ip");
        }
        if (isEmpty(ipAddress)) {
            ipAddress = request.getHeader("x-real-ip");
        }
        if (isEmpty(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (LOCAL_HOST_LIST.contains(ipAddress)) {
            ipAddress = getHostAddress();
        }
        return ipAddress;
    }

    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String calculateWorkExperience(String workStartTime) {
        LocalDate workDate = LocalDate.parse(String.format("%s-01", workStartTime));
        BigDecimal days = BigDecimal.valueOf(LocalDate.now().toEpochDay() - workDate.toEpochDay());
        BigDecimal year = days.divide(BigDecimal.valueOf(365), BigDecimal.ROUND_HALF_UP);
        return year.compareTo(BigDecimal.ONE) <= Constants.ZERO ? "一年内" : year + "年";
    }

    public static Map<String, String> getRequestParamMap(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String, String> paramMap = Maps.newHashMap();
        Map<String, String[]> requestParamMap = request.getParameterMap();
        for (String name : requestParamMap.keySet()) {
            String[] values = requestParamMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            paramMap.put(name, valueStr);
        }
        return paramMap;
    }

    private static boolean isEmpty(String ipAddress) {
        return StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress);
    }

    private static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取机器ip发生异常,获取失败", e);
            return Constants.BLANK;
        }
    }
}
