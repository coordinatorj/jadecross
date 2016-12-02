/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.store;

import java.util.List;

import namoo.board.dom2.entity.user.BoardUser;
import namoo.board.dom2.util.exception.EmptyResultException;

public interface BoardUserStore {
    //
    public void create(BoardUser user);
    public BoardUser retrieveByEmail(String email) throws EmptyResultException;
    public List<BoardUser> retrieveAll();
    public List<BoardUser> retrieveByName(String name); 
    public void update(BoardUser user); 
    public void deleteByEmail(String email); 
    
    public boolean isExistByEmail(String email);
}
