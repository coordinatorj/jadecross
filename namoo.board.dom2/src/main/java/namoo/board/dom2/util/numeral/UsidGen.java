/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:syhan@nextree.co.kr">Han, Seongyoung</a>
 * @since 2014. 8. 19.
 */
package namoo.board.dom2.util.numeral;


public class UsidGen {
    //
    private UsidGen() {}

    public static String getStr36(String prefix, long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);
        
        Numeral36 numeral36 = Numeral36.getInstance();
        String usid = String.format("%s-%s", 
                prefix,
                numeral36.getStr36(number, formatLength)); 
        return usid;
    }
    
    public static String getStr36(long number, int formatLength) {
        //
        number = (number < 0 ? -number : number);
        
        Numeral36 numeral36 = Numeral36.getInstance();    
        return numeral36.getStr36(number, formatLength);
    }
}
