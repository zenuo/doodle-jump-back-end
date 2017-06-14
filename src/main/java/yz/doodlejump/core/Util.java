package yz.doodlejump.core;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * 工具类
 */
public class Util {
    static final String hash(final String string) {
        return Hashing.sha384().hashString(string, StandardCharsets.UTF_8).toString();
    }
}
