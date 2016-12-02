/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.board;

import java.util.Date;


public class PostingComment {
    //
    private String postingUsid;
    private int sequence;         // in DCPosting
    private String comment;
    private String writerEmail;
    private Date writtenTime;
    

    //--------------------------------------------------------------------------
    
    @SuppressWarnings("unused")
    private PostingComment() {}
    
    public PostingComment(String comment, String writerEmail){
        //
        this.comment = comment; 
        this.writerEmail = writerEmail; 
        this.writtenTime = new Date(System.currentTimeMillis()); 
    }

    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer();
        buffer.append("sequence: " + this.sequence); 
        buffer.append(", comment: " + this.comment); 
        buffer.append(", writerEmail: " + this.writerEmail); 
        buffer.append(", writtenTime: " + this.writtenTime); 
        return buffer.toString();
    }
    
    public String getPostingUsid() {
        return postingUsid;
    }

    public void setPostingUsid(String postingUsid) {
        this.postingUsid = postingUsid;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }
    
    public Date getWrittenTime() {
        return writtenTime;
    }

    public void setWrittenTime(Date writtenTime) {
        this.writtenTime = writtenTime;
    }



}