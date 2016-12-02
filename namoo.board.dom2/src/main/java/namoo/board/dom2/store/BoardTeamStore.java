/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.store;

import java.util.List;

import namoo.board.dom2.entity.user.BoardMember;
import namoo.board.dom2.entity.user.BoardTeam;
import namoo.board.dom2.util.exception.EmptyResultException;

public interface BoardTeamStore {
    // board team
    public void create(BoardTeam team);
    public BoardTeam retrieve(String usid) throws EmptyResultException; 
    public BoardTeam retrieveByName(String name) throws EmptyResultException; 
    public List<BoardTeam> retrieveAll(); 
    public void delete(String usid); 
    
    public int nextSequence();             // for usid
    public boolean isExist(String usid); 
    public boolean isExistByName(String name);
    
    // board member
    public void createMember(String teamUsid, BoardMember member); 
    public BoardMember retrieveMember(String teamUsid, String memberEmail) throws EmptyResultException; 
    public List<BoardMember> retrieveMembers(String teamUsid); 
    public void deleteMember(String teamUsid, String memberEmail); 
    
    public boolean isExistMember(String teamUsid, String memberEmail);
}
