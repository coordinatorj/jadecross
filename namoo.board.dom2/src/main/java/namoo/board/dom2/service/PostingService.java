/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.service;

import namoo.board.dom2.entity.board.Posting;
import namoo.board.dom2.shared.CommentCdo;
import namoo.board.dom2.shared.PostingCdo;
import namoo.board.dom2.util.namevalue.NameValueList;
import namoo.board.dom2.util.page.Page;
import namoo.board.dom2.util.page.PageCriteria;

public interface PostingService {
    //
    // posting
    public String registerPosting(PostingCdo postingCdo);
    public Posting findPosting(String postingUsid);
    public Posting findPostingWithContents(String postingUsid);
    public Page<Posting> findPostingPage(String boardUsid, PageCriteria pageCriteria);
    public void modifyPostingOption(String postingUsid, NameValueList nameValues);
    public void modifyPostingContents(String postingUsid, String contents);
    public void removePosting(String postingUsid);
    
    public void increasePostingReadCount(String postingUsid);

    // comment
    public void attachComment(String postingUsid, CommentCdo commentCdo);
    public void detachComment(String postingUsid, int sequence); 
}