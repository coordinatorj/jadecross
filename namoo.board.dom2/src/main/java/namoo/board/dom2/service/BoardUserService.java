/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.service;

import java.util.List;

import namoo.board.dom2.entity.user.BoardMember;
import namoo.board.dom2.entity.user.BoardTeam;
import namoo.board.dom2.entity.user.BoardUser;

public interface BoardUserService {
    //
    public void registerUser(BoardUser user); 
    public BoardUser findUserWithEmail(String userEmail);
    public List<BoardUser> findAllUsers();
    public void removeUserWithEmail(String userEmail);
    
    public boolean loginAsUser(String userEmail, String password);
    
    public String registerBoardTeam(String teamName, String adminEmail);
    public List<BoardTeam> findAllBoardTeams();
    public void removeBoardTeam(String teamUsid); 
    
    public void addToBoardTeam(String teamUsid, List<String> userEmails);
    public List<BoardMember> findTeamBoardMembers(String teamUsid);
    public void removeFromBoardTeam(String teamUsid, String userEmail); 
}