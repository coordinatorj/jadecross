/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.shared;

public class CommentCdo {
    //
    private String comment;
    private String email;

    public CommentCdo() {}
    public CommentCdo(String comment, String email) {
        // 
        this.comment = comment; 
        this.email = email; 
    }
    
    public String getComment() {
        return comment; 
    }
    
    public String getEmail() {
        return email; 
    }
    
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
