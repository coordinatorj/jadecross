/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.logic;

import java.util.List;

import namoo.board.dom2.entity.board.SocialBoard;
import namoo.board.dom2.entity.user.BoardTeam;
import namoo.board.dom2.lifecycle.BoardStoreLifecycler;
import namoo.board.dom2.service.SocialBoardService;
import namoo.board.dom2.store.BoardTeamStore;
import namoo.board.dom2.store.PostingStore;
import namoo.board.dom2.store.SocialBoardStore;
import namoo.board.dom2.util.exception.EmptyResultException;
import namoo.board.dom2.util.exception.NamooBoardException;
import namoo.board.dom2.util.namevalue.NameValueList;
import namoo.board.dom2.util.numeral.UsidGen;

public class SocialBoardServiceLogic implements SocialBoardService {
    //
    private SocialBoardStore boardStore;
    private PostingStore postingStore;
    private BoardTeamStore teamStore;
    
    public SocialBoardServiceLogic(BoardStoreLifecycler lifecycler) {
        // 
        this.boardStore = lifecycler.callSocialBoardStore();
        this.postingStore = lifecycler.callPostingStore();
        this.teamStore = lifecycler.callBoardTeamStore(); 
    }

    //--------------------------------------------------------------------------
    
    @Override
    public String registerBoard(String teamUsid, String boardName, boolean commentable) {
        // 
        if (boardStore.isExistByName(boardName)) {
            throw new NamooBoardException("Board name already exists. --> " + boardName);
        }
        
        try {
            BoardTeam team = teamStore.retrieve(teamUsid); 
            SocialBoard board = new SocialBoard(team, boardName);
            board.setCommentable(commentable);
            
            long sequence = boardStore.nextSequence();
            board.setUsid(UsidGen.getStr36(sequence, 3));
            
            boardStore.create(board);
            return board.getUsid(); 
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a team --> " + teamUsid);
        }
    }

    @Override
    public SocialBoard findSocialBoard(String boardUsid) {
        // 
        try {
            return boardStore.retrieve(boardUsid);
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a board --> " + boardUsid);
        }
    } 

    @Override
    public List<SocialBoard> findAllSocialBoards() {
        //
        return boardStore.retrieveAll(); 
    }

    @Override
    public void modifySocialBoard(String boardUsid, NameValueList nameValues) {
        //
        try {
            SocialBoard board = boardStore.retrieve(boardUsid);
            board.setValues(nameValues);
            
            boardStore.update(board);
            
        } catch (EmptyResultException e) {
            throw new NamooBoardException("No such a board --> " + boardUsid);
        }
    }
    
    @Override
    public void removeSocialBoard(String boardUsid) {
        // 
        if (!boardStore.isExist(boardUsid)) {
            throw new NamooBoardException("No such a board --> " + boardUsid); 
        }
        postingStore.deleteByBoard(boardUsid);
        boardStore.delete(boardUsid);
    }
}
