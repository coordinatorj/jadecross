/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.store;

import namoo.board.dom2.entity.board.Posting;
import namoo.board.dom2.entity.board.PostingComment;
import namoo.board.dom2.entity.board.PostingContents;
import namoo.board.dom2.util.exception.EmptyResultException;
import namoo.board.dom2.util.page.Page;
import namoo.board.dom2.util.page.PageCriteria;

public interface PostingStore {
    //
    // posting
    public void create(Posting posting); 
    public Posting retrieve(String usid) throws EmptyResultException; 
    public Page<Posting> retrievePage(String boardUsid, PageCriteria pageCriteria);
    public void update(Posting posting); 
    public void delete(String usid); 
    public void deleteByBoard(String boardUsid);

    public int nextSequence(String boardUsid);
    public void increaseReadCount(String usid);
    public boolean isExist(String usid);
    
    // contents
    public void createContents(PostingContents contents); 
    public PostingContents retrieveContents(String postingUsid) throws EmptyResultException; 
    public void updateContents(PostingContents contents);
    
    // comment
    public void createComment(String postingUsid, PostingComment comment);
    public PostingComment retrieveComment(String postingUsid, int sequence) throws EmptyResultException;
    public void deleteComment(String postingUsid, int sequence);
    
    public int nextCommentSequence(String postingUsid);
    public boolean isExistComment(String postingUsid, int sequence);
}
