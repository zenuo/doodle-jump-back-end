package yz.doodlejump.core;

import com.google.common.hash.Hashing;
import yz.doodlejump.constant.UtilConstant;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 工具类
 */
public class Util {

    /**
     * 对字符串进行sha384散列处理
     * @param string 字符串
     * @return 散列值
     */
    public static String hash(final String string) {
        return Hashing.sha384()
                .hashString(salt(string), StandardCharsets.UTF_8)
                .toString();
    }

    /**
     * 对字符串加盐
     * @param string 需要加盐的字符串
     * @return 加盐后的字符串
     */
    private static String salt(final String string) {
        return String.format("%s%s%s", UtilConstant.PRE_SALT, string, UtilConstant.POST_SALT);
    }

    /**
     * 返回当前字符串时间
     * @return 当前字符串时间
     */
    public static String getTimeString() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(UtilConstant.TIME_FORMAT);
        return time.format(dateTimeFormatter);
    }

    /**
     * 返回当前UNIX时间戳
     * @return 当前UNIX时间戳
     */
    public static long getTimeLong(){
        return System.currentTimeMillis() / 1000;
    }
}
