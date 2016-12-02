/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.util.page;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class PageCriteria implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1297251309921049014L;

    /** 초기 페이지 번호 */
    private static final int DEFAULT_PAGE_NUM = 1;

    /** 한 페이지에 출력되는 기본 Item 수 */
    public static final int DEFAULT_ITEM_LIMIT_OF_PAGE = 10;

    /** 문자 캐릭터 코드 : UTF-8 */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /** 현재 페이지 */
    private int page;

    /** 전체페이지 수 */
    private int totalPageCount;

    /** 전체 아이템 수 */
    private int totalItemCount;

    /** 한 페이지당 아이템 수 */
    private int itemLimitOfPage;

    /** 페이징 네비게이션 바에서 보여질 페이지 갯수 */
    private int navigateLimit = 10;

    /** 검색조건 원본 : serialized되어 넘어온 모든 파라미터 원본임.*/
    private Map<String, String> params = new HashMap<String, String>();

    //--------------------------------------------------------------------------
    
    /**
     * 기본 생성자.
     */
    private PageCriteria() {
        //
        this.page = DEFAULT_PAGE_NUM;
        this.itemLimitOfPage = DEFAULT_ITEM_LIMIT_OF_PAGE;
    }

    /**
     * 페이지 번호와 페이지당 표시할 아이템수를 초기화하는 생성자.
     *
     * @param page
     *            페이지 번호
     * @param limit
     *            페이지당 표시할 아이템 갯수
     */
    public PageCriteria(int page, int itemLimitOfPage) {
        //
        this();
        if (page > 0 && itemLimitOfPage > 0) {
            this.page = page;
            this.itemLimitOfPage = itemLimitOfPage;
        }
    }

    /**
     *
     * @param defaultItemLimitOfPage 한 페이지당 아이템 수
     * @param serializedCriteria
     */
    protected void init(int defaultItemLimitOfPage, String serializedCriteria) {
        parseSerializedCriteria(defaultItemLimitOfPage, serializedCriteria);

        try {
            // deserialize();
        } catch (Exception e) {
            // serialize 에러 ...
            // Creteria 클래스 정보 & serializedCriteria를 예외에 정리해서 알려줘야 함.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    //--------------------------------------------------------------------------

    /**
     * @param page
     *            설정할 페이지
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param limit
     *            설정할 한 페이지당 아이템 수
     */
    public void setItemLimitOfPage(int itemLimitOfPage) {
        this.itemLimitOfPage = itemLimitOfPage;
    }

    /**
     * 전체 아이템수 설정
     *
     * @param totalItemCount
     *            전체 아이템수
     */
    public void setTotalItemCount(int totalItemCount) {
        if (totalItemCount == 0) {
            return;
        }
        this.totalItemCount = totalItemCount;
        this.totalPageCount = (totalItemCount / this.itemLimitOfPage) + (totalItemCount % this.itemLimitOfPage > 0 ? 1 : 0);
    }

    /**
     * 조건 추가
     *
     * @param key
     *            조건 키
     * @param value
     *            조건 값
     */
    private void addCondition(String key, String value) {
        //TODO:: 검색조건이 목록으로 오는경우가 있을경우 목록 처리 해야함.
        this.params.put(key, value);
    }

    /**
     *
     * @param defaultItemLimitOfPage
     * @param serializedCriteria
     */
    private void parseSerializedCriteria(int defaultItemLimitOfPage, String serializedCriteria) {
        if (serializedCriteria != null && serializedCriteria.length() > 0) {
            String[] splitedCondition = serializedCriteria.split("&");
            for (String keyValue : splitedCondition) {
                String[] param = keyValue.split("=");
                // 빈 값의 조건은 무시
                if (param.length <= 1) continue;
                try {
                    addCondition(param[0], URLDecoder.decode(param[1], UTF8.name()));
                } catch (UnsupportedEncodingException e) {
                    //무시
                }
            }
        }

        setCurrentPageAndItemLimitOfPage(defaultItemLimitOfPage);
    }

    /**
     *
     * @param defaultItemLimitOfPage
     */
    private void setCurrentPageAndItemLimitOfPage(int defaultItemLimitOfPage) {
        String page = getParamValue("page");
        if (page != null) {
            setPage(Integer.parseInt(page));
        } else {
            setPage(DEFAULT_PAGE_NUM);
        }

        String itemLimitOfPage = getParamValue("limit");
        if (itemLimitOfPage != null) {
            setItemLimitOfPage(Integer.parseInt(itemLimitOfPage));
        } else {
            setItemLimitOfPage(defaultItemLimitOfPage);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    private String getParamValue(String key) {
        return this.params.get(key);
    }

    /**
     * @return 시작 인덱스 (JPA에서 query.setFirstResult의 용도임)
     */
    public int getStart() {
        return ((this.page - 1) * this.itemLimitOfPage);
    }

    /**
     * @return 시작인덱스(mybatis oralce용 : rownum)
     */
    public int getStartIndex() {
        return getStart() + 1;
    }

    /**
     * @return 끝 인덱스(mybatis oracle용 : rownum)
     */
    public int getEnd() {
        return this.page * this.itemLimitOfPage;
    }

    /**
     * @return page 현재 페이지
     */
    public int getPage() {
        return page;
    }

    /**
     * @return itemLimitOfPage 한 페이지당 아이템 수 (query.setMaxResults의 용도로도 사용할 수 있음)
     */
    public int getItemLimitOfPage() {
        return itemLimitOfPage;
    }

    /**
     * @return totalPageCount 전체 페이지 수
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @return totalItemCount 전체 아이템 수
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * <PRE>
     * 페이징 처리되는 네비게이션 바에 표기 되는 페이징 수
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @return
     */
    public int getNavigateLimit() {
        return navigateLimit;
    }

    /**
     * <PRE>
     * 페이징 처리되는 네비게이션 바에 표기 되는 페이징 수
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @param navigateLimit
     */
    public void setNavigateLimit(int navigateLimit) {
        this.navigateLimit = navigateLimit;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
}
