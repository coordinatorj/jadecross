/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.util.page;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    //
    private static final long serialVersionUID = 5594559667804809774L;

    /** 검색결과 */
    private List<T> results;

    /** 페이지 조건 */
    private PageCriteria criteria;

    //--------------------------------------------------------------------------
    
    /**
     * 생성자
     *
     * @param results
     *            검색결과
     * @param criteria
     *            페이지 조건
     */
    public Page(List<T> results, PageCriteria criteria) {
        this.results = results;
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    
    /**
     * @return 현재 페이지 번호
     */
    public int getPage() {
        return this.criteria.getPage();
    }

    /**
     * @return 한 페이지당 아이템 수
     */
    public int getItemLimitOfPage() {
        return this.criteria.getItemLimitOfPage();
    }

    /**
     * @return 전체 페이지 수
     */
    public int getTotalPageCount() {
        return this.criteria.getTotalPageCount();
    }

    /**
     * 전체 아이템 수
     * @return
     */
    public int getTotalItemCount() {
        return this.criteria.getTotalItemCount();
    }
    
    public boolean isEmptyResult() {
        if (this.results == null || this.results.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * 하단 네비게이션의 페이징 목록 처음 시작하는 번호 계산
     * UI 에서 사용하는 메소드
     * @return
     */
    public int getNavigateStartPage() {
        return ((getPage() - 1) / this.criteria.getNavigateLimit()) * this.criteria.getNavigateLimit() + 1;
    }

    /**
     * 현재 페이지에서 하단 네비게이션의 페이징 숫자 계산
     * UI 에서 사용하는 메소드
     * @return 하단 네비게이션의 페이징 숫자 계산
     */
    public int getNavigatePageCount() {
        return (getTotalPageCount() - getNavigateStartPage() + 1) > this.criteria.getNavigateLimit() ?
                this.criteria.getNavigateLimit() : (getTotalPageCount() - getNavigateStartPage() + 1);
    }

    public List<T> getResults() {
        return results;
    }

    public PageCriteria getCriteria() {
        return criteria;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
    
}
