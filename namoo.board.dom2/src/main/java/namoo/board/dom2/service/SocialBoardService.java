/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.service;

import java.util.List;

import namoo.board.dom2.entity.board.SocialBoard;
import namoo.board.dom2.util.namevalue.NameValueList;

public interface SocialBoardService {
    //
    public String registerBoard(String teamUsid, String boardName, boolean commentable);
    public SocialBoard findSocialBoard(String boardUsid);
    public List<SocialBoard> findAllSocialBoards();
    public void modifySocialBoard(String boardUsid, NameValueList nameValues);
    public void removeSocialBoard(String boardUsid);
}