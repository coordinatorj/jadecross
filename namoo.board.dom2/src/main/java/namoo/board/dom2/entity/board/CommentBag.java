/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.board;

import java.util.ArrayList;
import java.util.List;

import namoo.board.dom2.util.exception.NamooBoardException;

public class CommentBag {
    //
    private List<PostingComment> comments;

    public CommentBag() {
        //
        this.comments = new ArrayList<PostingComment>(); 
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer();
        
        for (PostingComment comment : comments) {
            buffer.append("comment " + comment.toString() + "\n"); 
        }
        return buffer.toString(); 
    }
    
    public void addComment(PostingComment comment) {
        comments.add(comment); 
    }

    public PostingComment getComment(int sequence) {
        //
        for (PostingComment comment : comments) {
            if (sequence == comment.getSequence()) {
                return comment;
            }
        }
        return null;
    }
    
    public void removeComment(int sequence) {
        // 
        if (sequence == 0) {
            throw new NamooBoardException("Invalid sequence --> " + sequence);  
        }
        
        PostingComment targetComment = null; 
        for (PostingComment comment : comments) {
            // 
            if (comment.getSequence() == sequence) {
                targetComment = comment;
                break; 
            }
        }
        
        comments.remove(targetComment); 
    }
    
    public boolean hasComments() {
        //
        if (comments != null && !comments.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public List<PostingComment> getComments() {
        return comments; 
    }
}