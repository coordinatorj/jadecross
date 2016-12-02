/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 12.
 */
package namoo.board.dom2.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    //
    /**
     * Date 객체를 yyyy-MM-dd HH:mm:ss 패턴의 문자열로 변환
     * 
     * @param date  Date객체
     * @return  {@link String}
     */
    public static String parseStr(Date date) {
        //
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * yyyy-MM-dd HH:mm:ss 패턴의 Date 문자열을 Date 객체로 변환
     * 
     * @param dateStr   Date 문자열
     * @return  {@link java.util.Date}  
     */
    public static Date parseDate(String dateStr) {
        //
        try {
            return DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
