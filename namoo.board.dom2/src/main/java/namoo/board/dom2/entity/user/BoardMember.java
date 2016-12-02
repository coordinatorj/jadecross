/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.user;

public class BoardMember {
    //
    private String usid; 
    private String teamName; 
    private BoardUser user; 
    
    //--------------------------------------------------------------------------
    
    @SuppressWarnings("unused")
    private BoardMember() {}
    
    public BoardMember(String usid) {
        //
        this.usid = usid;
    }
    
    public BoardMember(BoardTeam team, BoardUser user) {
        //
        this.usid = team.getUsid() + "-" + user.getEmail(); 
        this.teamName = team.getName(); 
        this.user = user; 
    }

    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer(); 
        buffer.append("usid: " + this.usid); 
        buffer.append(", teamName: " + this.teamName); 
        buffer.append(", user " + this.user.toString()); 
        
        return buffer.toString(); 
    }
    
    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public BoardUser getUser() {
        return user;
    }

    public void setUser(BoardUser user) {
        this.user = user;
    }

    
}
