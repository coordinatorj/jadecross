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
import java.util.ArrayList;
import java.util.List;

import namoo.board.dom2.entity.user.BoardTeam;
import namoo.board.dom2.util.namevalue.NameValue;
import namoo.board.dom2.util.namevalue.NameValueList;

public class SocialBoard {
    //
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COMMENTABLE = "commentable";
            
    private String usid; 
    private String name;
    private String registTime;
    private String teamUsid; 
    
    private boolean commentable;

    private List<Posting> postings;

    //--------------------------------------------------------------------------
    
    public SocialBoard() {
        //
        this.postings = new ArrayList<Posting>();
    }
    
    public SocialBoard(BoardTeam team, String name) {
        //
        this();
        this.name = name; 
        this.teamUsid = team.getUsid(); 
        this.registTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME); 
        
        this.commentable = true; 
    }

    public SocialBoard(String usid, String name, String registTime, String teamUsid, boolean commentable) {
        this();
        this.usid = usid;
        this.name = name;
        this.registTime = registTime;
        this.teamUsid = teamUsid;
        this.commentable = commentable;
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer(); 
        buffer.append("usid: " + this.usid); 
        buffer.append(", name: " + this.name); 
        buffer.append(", registTime: " + this.registTime);
        buffer.append(", teamUsid: " + this.teamUsid);
        buffer.append(", commentable: " + this.commentable);
        
        return buffer.toString(); 
    }
    
    public void setValues(NameValueList nameValues) {
        // 
        for (NameValue nameValue : nameValues.getNameValues()) {
            // 
            if (nameValue.equalsName(PROPERTY_NAME)) {
                setName(nameValue.getValue()); 
            } else if (nameValue.equalsName(PROPERTY_COMMENTABLE)) {
                setCommentable(nameValue.getValueAsBoolean());
            } 
        }
    }
    
    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public String getTeamUsid() {
        return teamUsid;
    }

    public void setTeamUsid(String teamUsid) {
        this.teamUsid = teamUsid;
    }
    
    public List<Posting> getPostings() {
        return postings;
    }

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
}