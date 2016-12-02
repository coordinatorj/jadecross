/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.logic;

import namoo.board.dom2.entity.board.Posting;
import namoo.board.dom2.entity.board.PostingComment;
import namoo.board.dom2.entity.board.PostingContents;
import namoo.board.dom2.entity.board.PostingOption;
import namoo.board.dom2.entity.board.SocialBoard;
import namoo.board.dom2.entity.user.BoardUser;
import namoo.board.dom2.lifecycle.BoardStoreLifecycler;
import namoo.board.dom2.service.PostingService;
import namoo.board.dom2.shared.CommentCdo;
import namoo.board.dom2.shared.PostingCdo;
import namoo.board.dom2.store.BoardUserStore;
import namoo.board.dom2.store.PostingStore;
import namoo.board.dom2.store.SocialBoardStore;
import namoo.board.dom2.util.exception.EmptyResultException;
import namoo.board.dom2.util.exception.NamooBoardException;
import namoo.board.dom2.util.namevalue.NameValueList;
import namoo.board.dom2.util.numeral.UsidGen;
import namoo.board.dom2.util.page.Page;
import namoo.board.dom2.util.page.PageCriteria;

public class PostingServiceLogic implements PostingService {
    //
    private PostingStore postingStore;
    private SocialBoardStore boardStore;
    private BoardUserStore userStore; 
    
    public PostingServiceLogic(BoardStoreLifecycler lifecycler) {
        // 
        this.postingStore = lifecycler.callPostingStore();
        this.boardStore = lifecycler.callSocialBoardStore();
        this.userStore = lifecycler.callBoardUserStore(); 
    }
    
    //--------------------------------------------------------------------------

    @Override
    public String registerPosting(PostingCdo postingCdo) {
        // 
    	String boardUsid = postingCdo.getBoardUsid(); 
        Posting posting = null;
        try {
            BoardUser user = userStore.retrieveByEmail(postingCdo.getWriterEmail());
            posting = new Posting(boardUsid, postingCdo.getTitle(), user);
            
            int sequence = postingStore.nextSequence(boardUsid);
            posting.setUsid(UsidGen.getStr36(boardUsid, sequence, 4));
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a user --> " + postingCdo.getWriterEmail());
        }
        
        try {
            SocialBoard board = boardStore.retrieve(boardUsid);
            PostingOption option = new PostingOption();
            
            if (!board.isCommentable()) {
                option.setCommentable(false);
                option.setAnonymousComment(false);
            }
            else {
                option = postingCdo.createPostingOption();
                option.validation();
            }
            posting.setOption(option);
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a board --> " + boardUsid);
        }
        
        postingStore.create(posting); 
        
        PostingContents contents = new PostingContents(posting, postingCdo.getContents()); 
        postingStore.createContents(contents); 
        
        return posting.getUsid();
    }

    @Override
    public Posting findPosting(String postingUsid) {
        //
        try {
            return postingStore.retrieve(postingUsid);
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a posting --> " + postingUsid);
        }
    }
    
    @Override
    public Posting findPostingWithContents(String postingUsid) {
        // 
        try {
            Posting posting = postingStore.retrieve(postingUsid);
            PostingContents contents = postingStore.retrieveContents(postingUsid);
            posting.setContents(contents);
            
            return posting;
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a posting --> " + postingUsid);
        } 
    }

    @Override
    public Page<Posting> findPostingPage(String boardUsid, PageCriteria pageCriteria) {
        // 
        return postingStore.retrievePage(boardUsid, pageCriteria);
    }

    @Override
    public void modifyPostingOption(String postingUsid, NameValueList nameValues) {
        //
        try {
            Posting posting = postingStore.retrieve(postingUsid);
            PostingOption option = posting.getOption(); 
            option.setValues(nameValues);
            
            postingStore.update(posting);
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a posting --> " + postingUsid);
        }
    }
    
    @Override
    public void modifyPostingContents(String postingUsid, String contents) {
        // 
        try {
            PostingContents savedContents = postingStore.retrieveContents(postingUsid);
            savedContents.setContents(contents);
            
            postingStore.updateContents(savedContents);
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a postig --> " + postingUsid);
        }
    }

    @Override
    public void removePosting(String postingUsid) {
        // 
        if (!postingStore.isExist(postingUsid)) {
            throw new NamooBoardException("No such a posting --> " + postingUsid); 
        }
        postingStore.delete(postingUsid);
    }
    
    @Override
    public void increasePostingReadCount(String postingUsid) {
        // 
        postingStore.increaseReadCount(postingUsid);
    }
    
    
    //--------------------------------------------------------------------------
    // Comment

    @Override
    public void attachComment(String postingUsid, CommentCdo commentCdo) {
        //
        PostingComment comment = new PostingComment(commentCdo.getComment(), commentCdo.getEmail());
        comment.setSequence(postingStore.nextCommentSequence(postingUsid));

        postingStore.createComment(postingUsid, comment);
    }

    @Override
    public void detachComment(String postingUsid, int sequence) {
        // 
        if (!postingStore.isExistComment(postingUsid, sequence)) {
            throw new NamooBoardException("No such a posting comment --> postingUsid: "
                    + postingUsid + ", sequence: " +  sequence); 
        }
        postingStore.deleteComment(postingUsid, sequence);
    }
}
