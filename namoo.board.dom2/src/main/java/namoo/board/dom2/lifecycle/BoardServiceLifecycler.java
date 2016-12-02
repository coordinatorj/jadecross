/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 5. 10.
 */

package namoo.board.dom2.lifecycle;

import namoo.board.dom2.service.BoardUserService;
import namoo.board.dom2.service.ExcelFileBatchLoader;
import namoo.board.dom2.service.PostingService;
import namoo.board.dom2.service.SocialBoardService;

public interface BoardServiceLifecycler {
    //
    public ExcelFileBatchLoader getExcelFileBatchLoader();
    public BoardUserService getBoardUserService(); 
    public SocialBoardService getSocialBoardService(); 
    public PostingService getPostingService(); 
}