package com.zds.util.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MapUtils
 *
 * @author zhongdongsheng
 * @since 2022/3/23
 */
public class MapUtils {
    /**
     * 将指定的map转换按key作正向排序后，以key=value&的形式生成字符串
     *
     * @param originMap
     * @return
     */
    public static String convertMapToOrderedString(Map<String, Object> originMap) {
        Map<String, Object> sortedMap =
                originMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1,
                                LinkedHashMap::new));

        StringBuffer buffer = new StringBuffer();
        sortedMap.forEach((k, v) -> buffer.append(k).append("=").append(v).append("&"));

        return buffer.toString();
    }
}
