/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.entity.user;

public class BoardUser {
    //
    // BoardUser를 간단하게 하고자 DOM field에 비밀번호가 없기 때문에 비밀번호는 무조건 1234로 고정
    public final String USER_PASSWORD = "1234";
    
    private String email;        // == id and primary key
    private String name;
    private String phoneNumber;    // cell phone

    //--------------------------------------------------------------------------
    
    
    public BoardUser() {}
    
    public BoardUser(String email) {
        //
        this.email = email;
    }
    
    public BoardUser(String email, String name, String phoneNumber){
        //
        this.email = email; 
        this.name = name; 
        this.phoneNumber = phoneNumber; 
    }

    // da.jdbc
    public BoardUser(String email, String name){
        //
        this.email = email; 
        this.name = name; 
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString() {
        // 
        StringBuffer buffer = new StringBuffer(); 
        buffer.append("email: " + email); 
        buffer.append(", name: " + name); 
        buffer.append(", phoneNumber: " + phoneNumber); 
        
        return buffer.toString(); 
    }
    
    public boolean isValid() {
        // 
        if (email != null && name != null && phoneNumber != null) {
            return true; 
        }
        
        return false; 
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}