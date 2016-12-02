/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.util;

import java.util.regex.Pattern;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 입력된 오브젝트가 널이면 빈문자열("")을 반환하고
     * 널이 아니면 오브젝트의 문자열의  trim한 결과를 반환한다.
     * <pre>
     * [null] --> [""]
     * [""] --> [""]
     * ["     "] --> [""]
     * [" aaa"] --> ["aaa"]
     * ["aaa "] --> ["aaa"]
     * [" aaa "] --> ["aaa"]
     * [" aa aaa "] --> ["aa aaa"]
     * [" aa aaa aa"] --> ["aa aaa aa"]
     * </pre>
     * @param obj 문자열로 변환할 오브젝트
     * @return 빈문자열("") 또는 trim된 문자열
     */
    public static String trimToEmpty(Object obj) {
        return obj == null ? EMPTY : trimToEmpty(String.valueOf(obj));
    }

    /**
     * 입력된 문자열이 널 또는 빈문자열("")이면 입력된 기본문자열을 반환하고
     * 그렇지않으면 입력된 문자열의 trim한 결과를 반환한다.
     * @param anObject 문자열로 변환할 오브젝트
     * @param defaultString 기본문자열
     * @return 기본문자열 또는 trim된 문자열
     */
    public static String trimToDefault(String str, String defaultStr) {
        String ts = trim(str);
        return isEmpty(ts) ? defaultStr : ts;
    }

    /**
     * 입력된 오브젝트가 널 또는 빈문자열("")이면 입력된 기본문자열을 반환하고
     * 그렇지않으면 오브젝트의 문자열의  trim한 결과를 반환한다.
     * @param obj 문자열로 변환할 오브젝트
     * @param defaultStr 기본문자열
     * @return 기본문자열 또는 trim된 문자열
     */
    public static String trimToDefault(Object obj, String defaultStr) {
        return obj == null ? defaultStr : trimToDefault(String.valueOf(obj), defaultStr);
    }

    /**
     * 문자열을 치환한다.
     * @param srcString 원본문자열
     * @param oldString 변경할 문자열
     * @param newString 대체할 문자열
     * @return 대체된 문자열
     */
    public static String replace(String srcString, String oldString, String newString) {
        if (srcString == null) {
            return null;
        }
        StringBuilder destStr = new StringBuilder(srcString.length());
        int len = oldString.length();
        int srclen = srcString.length();
        int pos = 0;
        int oldpos = 0;

        while ((pos = srcString.indexOf(oldString, oldpos)) >= 0) {
            destStr.append(srcString.substring(oldpos, pos));
            destStr.append(newString);
            oldpos = pos + len;
        }

        if (oldpos < srclen) {
            destStr.append(srcString.substring(oldpos, srclen));
        }
        return destStr.toString();
    }

    /**
     * 숫자로 구성된 문자열 체크
     * @param str
     * @return 숫자 문자열 여부
     */
    public static boolean isDigit(String str) {
        return isNumeric(str);
    }

    /**
     * 원본문자열의 trim된 문자열의 길이보다 지정된 길이가 클 경우 부족한 만큼을 제공된 문자로
     * 왼쪽에서부터 채워진 문자열을 반환한다.
     * <p>
     * <pre>
     * [null, 'A', 3] --> ["AAA"]
     * ["1", 'A', 3] --> ["AA2"]
     * ["12", 'A', 3] --> ["A12"]
     * ["123", 'A', 3] --> ["123"]
     * ["1234", 'A', 3] --> ["1234"]
     * ["1234  ", 'A', 3] --> ["1234"]
     *
     * ["12 ", 'A', 3] --> ["A12"]
     * [" 12", 'A', 3] --> ["A12"]
     * </pre>
     *
     * @param srcStr 원본문자열
     * @param paddingChar 채울문자
     * @param length 문자가 채워진 후의 문자열 길이
     * @return 제공된 문자로 채워진 문자열
     */
    public static String leftPadding(String srcStr, char paddingChar, int length) {
        String resultString = srcStr = (srcStr == null) ? "" : srcStr.trim();

        // 원본문자열보다 클 경우에만 패딩을 함.
        if (length > srcStr.length()) {
            StringBuilder buff = new StringBuilder(length);
            int paddingSize = length - srcStr.length();
            for (int i = 0; i < paddingSize; i++) {
                buff.append(paddingChar);
            }
            resultString = buff.append(srcStr).toString();
        }

        return resultString;
    }

    /**
     * 입력된 값을 지정된 글자수만큼 왼쪽에서부터 0을 채워서 반환
     */
    /**
     * 원본문자열의 trim된 문자열의 길이보다 지정된 길이가 클 경우 부족한 만큼을 '0'으로
     * 왼쪽에서부터 채워진 문자열을 반환한다.
     * <p>
     * <pre>
     * [null, 3] --> ["000"]
     * ["1", 3] --> ["002"]
     * ["12", 3] --> ["012"]
     * ["123", 3] --> ["123"]
     * ["1234", 3] --> ["1234"]
     * ["1234  ", 3] --> ["1234"]
     *
     * ["12 ", 3] --> ["012"]
     * [" 12", 3] --> ["012"]
     * </pre>
     *
     * @param srcStr 원본문자열
     * @param length '0'이 후의 문자열 길이
     * @return '0'으로 채워진 문자열
     */
    public static String leftZeroPadding(String srcStr, int length) {
        return leftPadding(srcStr, '0', length);
    }

    /**
     * 이메일의 유효성(정합성)을 체크한다.
     *
     * @param email
     * @return 유효한 이메일인 경우 true 아니면 false를 리턴
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        boolean value = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return value;
    }

}
