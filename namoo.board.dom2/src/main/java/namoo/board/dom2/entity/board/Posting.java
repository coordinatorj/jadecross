/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import namoo.board.dom2.entity.user.BoardUser;

public class Posting {
    //
    private String usid; 
    private String title;
    private String writerEmail;
    private String writerName;
    private int readCount;
    private String registTime;
    
    private String boardUsid; 
    
    private PostingOption option;
    private PostingContents contents;
    
    //--------------------------------------------------------------------------

    @SuppressWarnings("unused")
    private Posting() {}
    
    public Posting(String boardUsid, String title, BoardUser user){
        //
        this.title = title; 
        this.writerEmail = user.getEmail(); 
        this.writerName = user.getName();
        this.readCount = 0;
        this.registTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME); 
        this.boardUsid = boardUsid; 
        this.option = new PostingOption(); 
    }

    public Posting(String usid) {
        this.usid = usid;
    }
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer();
        buffer.append("usid: " + this.usid); 
        buffer.append(", title: " + this.title); 
        buffer.append(", writerEmail: " + this.writerEmail); 
        buffer.append(", writerName: " + this.writerName); 
        buffer.append(", readCount: " + this.readCount); 
        buffer.append(", registTime: " + this.registTime); 
        buffer.append(", option " + this.option.toString()); 
        return buffer.toString();
    }
    
    public String getUsid() {
        return usid; 
    }
    
    public void setUsid(String usid) {
        this.usid = usid; 
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }
    
    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getRegistTime() {
        return this.registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getBoardUsid() {
        return boardUsid;
    }

    public void setBoardUsid(String boardUsid) {
        this.boardUsid = boardUsid;
    }
    
    public PostingOption getOption() {
        return option;
    }

    public void setOption(PostingOption option) {
        this.option = option;
    }

    public PostingContents getContents() {
        return contents;
    }

    public void setContents(PostingContents contents) {
        this.contents = contents;
    }

    
}