package com.yundui.hikaricp.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class YdUtil {
    public static HashMap<String, Object> hashMap(String keyStr, Object... objs) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        if (empty(keyStr)) {
            return resultMap;
        }
        String[] keys = keyStr.split(",");
        for (int i = 0; i < keys.length; i++) {
            resultMap.put(keys[i], objs[i]);
        }
        return resultMap;
    }

    /**
     * url转成map
     *
     * @param param
     * @return
     */
    public static Map<String, Object> urlString2map(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (empty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }
    public static String getSubString(String str,int maxLength) {
        str=str.replaceAll("&nbsp;"," ")
                .replaceAll("&lt;","<")
                .replaceAll("&gt;",">")
                .replaceAll("&quot;","“")
                .replaceAll("&amp;","&");
        if(str.length()>maxLength){
            str=str.substring(0,maxLength);
        }
        return str;
    }
    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String map2UrlString(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        String s = sb.toString();
        return s.replaceFirst("&", "");
    }

    /**
     * 字符串做urlencode
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        String res = "";
        if (empty(text)) {
            return res;
        }
        try {
            res = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 解码
     *
     * @Author gaoxiang
     * @Date 2017年5月19日  下午4:49:09
     */
    public static String decode(String text) {
        String res = "";
        if (empty(text)) {
            return res;
        }
        try {
            res = URLDecoder.decode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 类似js里join，字符串拼接
     *
     * @Author gaoxiang
     * @Date 2017年6月8日  下午4:25:15
     */
    public static <T> String join(T[] arr) {
        if (arr == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (T t : arr) {
            sb.append(",");
            sb.append(t);
        }
        return sb.toString().replaceFirst(",", "");
    }

    /**
     * 拼成sql里的'a','b','c'等可用字符串
     *
     * @Author gaoxiang
     * @Date 2017年6月8日  下午4:25:15
     */
    public static <T> String sqlJoin(T[] arr) {
        StringBuffer sb = new StringBuffer();
        for (T t : arr) {
            sb.append("','");
            sb.append(t);
        }
        return "'" + sb.toString().replaceFirst("','", "") + "'";
    }

    /**
     * 类似js里join，字符串拼接
     *
     * @Author gaoxiang
     * @Date 2017年6月8日  下午4:25:15
     */
    public static String join(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(",");
            sb.append(s);
        }
        return sb.toString().replaceFirst(",", "");
    }

    /**
     * 类似js里join，字符串拼接
     *
     * @Author gaoxiang
     * @Date 2017年6月8日  下午4:25:15
     */
    public static String join(List<String> list, String c) {
        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(c);
            sb.append(s);
        }
        return sb.toString().replaceFirst(c, "");
    }

    /**
     * 是否是非负整数
     *
     * @Author gaoxiang
     * @Date 2017年7月21日  上午10:24:45
     */
    public static boolean isNonNegativeInteger(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\d+$");
    }


    /**
     * 是否是非负整数
     *
     * @Author gaoxiang
     * @Date 2017年7月21日  上午10:24:45
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否是数字
     *
     * @Author gaoxiang
     * @Date 2017年7月21日  上午10:24:45
     */
    public static boolean isDigit(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\d+(\\.\\d+)?$");
    }

    /**
     * 处理null的情况
     *
     * @Author gaoxiang
     * @Date 2017年9月1日  上午10:30:16
     */
    public static <T> T ifNull(Object obj, T notNullValue) {
        if (obj == null) {
            return notNullValue;
        }
        try {
            T t = (T) obj;
            return t;
        } catch (Exception e) {
            return notNullValue;
        }
    }
    /**
     * 拆分字符串并转成list
     *
     * @Author gaoxiang
     * @Date 2017年10月19日  下午2:58:55
     */
    public static List<String> split(String string) {
        if (empty(string)) {
            return new ArrayList<String>();
        }
        String[] arr = string.split(",");
        if (arr == null || arr.length == 0) {
            return new ArrayList<String>();
        }
        return new ArrayList<String>(Arrays.asList(arr));
    }

    /**
     * 判断多个对象,任何一个为null或者""即返回true
     *
     * @param objs
     * @return
     * @Author gaoxiang
     * @Date 2017年4月12日  上午10:32:58
     */
    public static boolean empty(Object... objs) {
        for (Object obj : objs) {
            if (null == obj || "".equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查字符串是否是多个数字和逗号拼接成，如1,2,3
     *
     * @Author gaoxiang
     * @Date 2017年5月17日  下午3:46:12
     */
    public static boolean isIdStr(Object idStr) {
        if (empty(idStr)) {
            return false;
        }
        return idStr.toString().matches("^(\\d+,)*\\d+$");
    }


    //这些controller查询参数不会构建成sql查询参数
    static final Set<String> noBuildSet = new HashSet<>();

    static {
        noBuildSet.add("_t");
        noBuildSet.add("column");
        noBuildSet.add("order");
        noBuildSet.add("field");
        noBuildSet.add("pageSize");
        noBuildSet.add("pageNo");
        noBuildSet.add("pageNO");
    }

    /**
     * 根据请求参数构建查询map
     *
     * @param parameterMap
     * @return
     */
    public static HashMap<String, Object> sqlMap(Map<String, String[]> parameterMap) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        if (parameterMap == null) {
            resultMap.put("start", 0);
            resultMap.put("num", 20);
            return resultMap;
        }
        //解析分页参数
        Integer pageSize = null;
        try {
            pageSize = Integer.valueOf(parameterMap.get("pageSize")[0] + "");
        } catch (Exception e) {
            //e.printStackTrace();
            pageSize = 10;
        }
        Integer pageNO = null;
        try {
            pageNO = Integer.valueOf(parameterMap.get("pageNo")[0] + "");
        } catch (Exception e) {
            //e.printStackTrace();
            pageNO = 1;
        }
        //加入分页参数
        resultMap.put("start", Math.max(pageNO - 1, 0) * pageSize);
        resultMap.put("num", pageSize);

        //不在黑名单中,则构建成sql查询参数
        parameterMap.forEach((k, v) -> {
            if (v != null && v[0] != null && !"".equals(v[0].trim()) && !noBuildSet.contains(k.trim())) {
                resultMap.put(k.trim(), v[0].trim());
            }
        });
        return resultMap;
    }


    public static String stringEngine(String text, Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return text;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String k = it.next();
            text = text.replaceAll("{" + k + "}", map.get(k));
        }
        return text;
    }

    /**
     * 获取随机int，包含min,max
     *
     * @Author gaoxiang
     * @Date 2017年10月24日  下午4:43:21
     */
    public static int getRandom(int min, int max) {
        return new Random().nextInt(max + 1) % (max - min + 1) + min;
    }

    /**
     * 给电话打码
     *
     * @Author gaoxiang
     * @Date 2017年10月25日  上午10:35:36
     */
    public static String getPhoneMask(String phone) {
        if (empty(phone) || phone.length() != 11) {
            return "182*****582";
        }
        return phone.substring(0, 3) + "*****" + phone.substring(8, 11);
    }

    /**
     * 生成签名
     *
     * @param map
     * @param key
     * @param secret
     * @return
     */
    public static String getSignature(Map<String, Object> map, String key, String secret) {
        ArrayList<String> list = new ArrayList<String>(8);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += key + "=" + secret;
        result = result.trim();

        result = DigestUtils.md5DigestAsHex(result.getBytes()).toUpperCase();
        return result;
    }

    public static String switchCase(Object obj, String... values) {
        if (obj == null) {
            if (values == null || values.length == 0) {
                return null;
            }
            return values[values.length - 1];
        }
        String str = obj.toString();
        if (values == null || values.length == 0) {
            return obj.toString();
        }
        try {
            for (int i = 0; i < values.length; ) {
                if (values[i].equals(str) && (i + 1) < values.length) {
                    return values[i + 1];
                }
                i = i + 2;
            }
        } catch (Exception e) {
            return values[values.length - 1];
        }
        return values[values.length - 1];
    }

    /**
     * 将集合拆分
     *
     * @param sourceList
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> segmentList(List<T> sourceList, int size) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = sourceList.size() % size;
        int number = sourceList.size() / size;
        int offset = 0;
        for (int i = 0; i < size; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = sourceList.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = sourceList.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 日期转为指定格式的字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String transDateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(date);
        } catch (Exception pse) {

            return null;
        }
    }

    /**
     * 将日志类型字符串转换为日期对象
     *
     * @param pattern
     * @param sourceStr
     * @return
     */
    public static Date transStrToDate(String pattern, String sourceStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(sourceStr);
        } catch (Exception pse) {

            return null;
        }
    }

    /**
     * 获取指定日期的天数
     *
     * @param date
     * @return
     */
    public static Integer toDays(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime.hourOfDay().setCopy(0).minuteOfHour().setCopy(0).secondOfMinute().setCopy(0);
        return Days.daysBetween(new DateTime(1970, 1, 1, 0, 0, 0), dateTime).getDays();
    }

    /**
     * 使用原文链接地址获取5组hash值
     *
     * @param sourceUrl 原文地址
     * @return
     */
    public static String[] genShortUrlCode(String sourceUrl) {
        String[] codeArr = new String[5];
        // MD5值
        String MD5 = DigestUtils.md5DigestAsHex(sourceUrl.trim().getBytes());
        // 直接返回切分后的5组6位数code值
        for (int i = 0; i < 5; i++) {
            String subMD5 = MD5.substring(i * 6, i * 6 + 6);
            codeArr[i] = subMD5;
        }
        return codeArr;
    }

    /**
     * 获取当前日期的指定天数
     *
     * @return
     */
    public static Integer toDays() {
        DateTime dateTime = new DateTime();
        dateTime.hourOfDay().setCopy(0).minuteOfHour().setCopy(0).secondOfMinute().setCopy(0);
        return Days.daysBetween(new DateTime(1970, 1, 1, 0, 0, 0), dateTime).getDays();
    }

    public static boolean isEmail(String text) {
        if (text == null) {
            return false;
        }
        return text.matches("[a-zA-Z0-9_\\.-]+@[a-zA-Z0-9_\\.-]\\.[a-zA-Z0-9_\\.-]");
    }




    /**
     * 截取阅读原文地址
     *
     * @param souText
     * @param startText
     * @param endText
     * @return
     */
    public static String getMiddleString(String souText, String startText, String endText) {
        String res = "";
        String sou = souText;
        int index = sou.toLowerCase().indexOf(startText.toLowerCase());
        if (index == -1) {
            return res;
        }
        sou = sou.substring(startText.length() + index);
        index = sou.toLowerCase().indexOf(endText.toLowerCase());
        if (index == -1) {
            return res;
        }
        res = sou.substring(0, index);
        return res;
    }

    /**
     * 日期加减天数
     *
     * @param date
     * @param a    加减天数,负数为减少天数
     * @return
     */
    public static Date DateAddOrSub(Date date, int a) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, a);
        date = calendar.getTime();
        return date;
    }

}
