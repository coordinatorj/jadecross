/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 5. 10.
 */

package namoo.board.dom2.lifecycle;

import namoo.board.dom2.store.BoardTeamStore;
import namoo.board.dom2.store.BoardUserStore;
import namoo.board.dom2.store.PostingStore;
import namoo.board.dom2.store.SocialBoardStore;

public interface BoardStoreLifecycler { 
    //
    public BoardUserStore callBoardUserStore(); 
    public BoardTeamStore callBoardTeamStore(); 
    public SocialBoardStore callSocialBoardStore(); 
    public PostingStore callPostingStore(); 
}