/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.shared;

import namoo.board.dom2.entity.board.PostingOption;

public class PostingCdo {
    //
	private String boardUsid; 
	
    private String title; 
    private String contents; 
    private String writerEmail; 
    
    private boolean commentable; 
    private boolean anonymousComment; 
    
    public PostingCdo() {
    	//
    }
    
    public PostingCdo(String boardUsid, String title, String contents, String writerEmail) {
        // 
    	this.boardUsid = boardUsid; 
        this.title = title; 
        this.contents = contents; 
        this.writerEmail = writerEmail; 
        
        // default value
        this.commentable = true; 
        this.anonymousComment = false; 
    }
    
    public void setCommentable(boolean commentable) {
        this.commentable = commentable; 
    }
    
    public void setAnonymousComment(boolean anonymousComment) {
        this.anonymousComment = anonymousComment; 
    }

    public PostingOption createPostingOption() {
        //
        return new PostingOption()
            .commentable(commentable)
            .anonymousComment(anonymousComment); 
    }

    public String getBoardUsid() {
    	return boardUsid; 
    }
    
    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public boolean isAnonymousComment() {
        return anonymousComment;
    }
    
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}
    
}
