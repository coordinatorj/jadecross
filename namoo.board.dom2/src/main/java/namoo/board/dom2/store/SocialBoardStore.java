/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.store;

import java.util.List;

import namoo.board.dom2.entity.board.SocialBoard;
import namoo.board.dom2.util.exception.EmptyResultException;

public interface SocialBoardStore {
    //
    public String create(SocialBoard board); 
    public SocialBoard retrieve(String usid) throws EmptyResultException; 
    public SocialBoard retrieveByName(String name) throws EmptyResultException; 
    public List<SocialBoard> retrieveAll(); 
    public void update(SocialBoard board); 
    public void delete(String usid); 
    
    public int nextSequence();
    public boolean isExist(String usid);
    public boolean isExistByName(String name);
}
