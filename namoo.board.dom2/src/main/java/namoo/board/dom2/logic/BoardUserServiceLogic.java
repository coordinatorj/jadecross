/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.logic;

import java.util.List;

import namoo.board.dom2.entity.user.BoardMember;
import namoo.board.dom2.entity.user.BoardTeam;
import namoo.board.dom2.entity.user.BoardUser;
import namoo.board.dom2.lifecycle.BoardStoreLifecycler;
import namoo.board.dom2.service.BoardUserService;
import namoo.board.dom2.store.BoardTeamStore;
import namoo.board.dom2.store.BoardUserStore;
import namoo.board.dom2.util.exception.EmptyResultException;
import namoo.board.dom2.util.exception.NamooBoardException;
import namoo.board.dom2.util.numeral.UsidGen;

public class BoardUserServiceLogic implements BoardUserService {
    //
    private BoardUserStore userStore;
    private BoardTeamStore teamStore;
    
    public BoardUserServiceLogic(BoardStoreLifecycler lifecycler) {
        // 
        this.userStore = lifecycler.callBoardUserStore(); 
        this.teamStore = lifecycler.callBoardTeamStore(); 
    }
    
    //--------------------------------------------------------------------------
    // DCBoardUser
    
    @Override
    public void registerUser(BoardUser user) {
        // 
        if (!user.isValid()) {
            throw new NamooBoardException("Invalid BoardUser object."); 
        }
        if (userStore.isExistByEmail(user.getEmail())) {
            throw new NamooBoardException("Email already exists. --> " + user.getEmail());
        }
        
        userStore.create(user); 
    }

    @Override
    public BoardUser findUserWithEmail(String userEmail) {
        // 
        try {
            return userStore.retrieveByEmail(userEmail);
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a user --> " + userEmail);
        }
    }

    @Override
    public List<BoardUser> findAllUsers() {
        // 
        return userStore.retrieveAll();
    }
    
    @Override
    public void removeUserWithEmail(String userEmail) {
        // 
        if (!userStore.isExistByEmail(userEmail)) {
            throw new NamooBoardException("User does not exist. --> " + userEmail);
        }
        userStore.deleteByEmail(userEmail);
        
        // FIXME: User를 삭제할 때 팀멤버에서도 삭제해야 하는데...
        // 그러면 teamStore.retrieveMemberByEmail()? / teamStore.deleteMemberByEmail()?.. 복잡해지는것 같음
        // 아니면 teamStore.isExistMemberByEmail()로 
        // 팀이 존재하면 팀부터 탈퇴하라고 예외 발생만 시켜야 할까..?
    }

    @Override
    public boolean loginAsUser(String userEmail, String password) {
        // 
        try {
            BoardUser user = userStore.retrieveByEmail(userEmail);
            return user.USER_PASSWORD.equals(password);
            
        } catch (EmptyResultException e) {
            return false;
        }
    }
    
    
    //--------------------------------------------------------------------------
    // DCBoardTeam
    
    @Override
    public String registerBoardTeam(String teamName, String adminEmail) {
        // 
        if (teamStore.isExistByName(teamName)) {
            throw new NamooBoardException("Team name already exists. --> " + teamName);
        }
        
        try {
            BoardUser adminUser = userStore.retrieveByEmail(adminEmail); 
            BoardTeam team = new BoardTeam(teamName, adminUser); 
            
            int sequence = teamStore.nextSequence();
            team.setUsid(UsidGen.getStr36(sequence, 3));
            
            teamStore.create(team);
            
            
            BoardMember member = new BoardMember(team, adminUser); 
            teamStore.createMember(team.getUsid(), member);
            
            return team.getUsid();
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a user -->" + adminEmail);
        }
    }

    @Override
    public List<BoardTeam> findAllBoardTeams() {
        // 
        return teamStore.retrieveAll();
    }
    
    @Override
    public void removeBoardTeam(String teamUsid) {
        // 
        if (!teamStore.isExist(teamUsid)) {
            throw new NamooBoardException("Team does not exist. --> " + teamUsid);
        }
        teamStore.delete(teamUsid);
    }

    //--------------------------------------------------------------------------
    // DCBoardMember
    
    @Override
    public void addToBoardTeam(String teamUsid, List<String> userEmails) {
        //
        BoardTeam team = null;
        try {
            team = teamStore.retrieve(teamUsid);
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a board team --> " + teamUsid);
        }
        
        String noExistUserEmail = null;
        try {
            for (String userEmail : userEmails) {
                //
                noExistUserEmail = userEmail;
                BoardUser user = userStore.retrieveByEmail(userEmail); 
                BoardMember member = new BoardMember(team, user); 
                teamStore.createMember(teamUsid, member);
            }
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a board user --> " + noExistUserEmail);
        }
    }
    
    @Override
    public List<BoardMember> findTeamBoardMembers(String teamUsid) {
        // 
        return teamStore.retrieveMembers(teamUsid);
    }

    @Override
    public void removeFromBoardTeam(String teamUsid, String userEmail) {
        // 
        if (!teamStore.isExistMember(teamUsid, userEmail)) {
            throw new NamooBoardException("Member does not exist. --> teamUsId: " + teamUsid + ", email: " + userEmail);
        }
        teamStore.deleteMember(teamUsid, userEmail); 
    }
    
}
