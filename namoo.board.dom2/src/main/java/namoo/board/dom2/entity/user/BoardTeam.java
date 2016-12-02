/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.user;

import java.util.ArrayList;
import java.util.List;

public class BoardTeam {
    //
    private String usid; 
    private String name;        
    private BoardUser admin;
    
    private List<BoardMember> members;

    //--------------------------------------------------------------------------
    
    @SuppressWarnings("unused")
    private BoardTeam() {}
    
    public BoardTeam(String usid) {
        //
        this.usid = usid;
    }
    
    public BoardTeam(String name, BoardUser admin){
        //
        this.name = name;
        this.admin = admin; 
        this.members = new ArrayList<BoardMember>(); 
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer(); 
        buffer.append("usid: " + this.usid); 
        buffer.append(", name: " + this.name); 
        buffer.append(", admin " + this.admin.toString()); 
        
        return buffer.toString(); 
    }
    
    public void addMember(BoardMember member) {
        //
        members.add(member);
    }
    
    public BoardMember getMember(String memberEmail) {
        //
        for (BoardMember member : members) {
            if (member.getUser().getEmail().equals(memberEmail)) {
                return member;
            }
        }
        return null;
    }
    
    public void removeMember(String memberEmail) {
        //
        BoardMember targetMember = null;
        for (BoardMember member : members) {
            if (member.getUser().getEmail().equals(memberEmail)) {
                targetMember = member;
                break;
            }
        }
        
        members.remove(targetMember);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardUser getAdmin() {
        return admin;
    }

    public void setAdmin(BoardUser admin) {
        this.admin = admin;
    }

    public List<BoardMember> getMembers() {
        return members;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }
}