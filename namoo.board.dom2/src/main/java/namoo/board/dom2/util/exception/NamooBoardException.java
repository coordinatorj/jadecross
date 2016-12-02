/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2014. 5. 14.
 */
package namoo.board.dom2.util.exception;

import java.text.MessageFormat;

public class NamooBoardException extends RuntimeException {
    //
    private static final long serialVersionUID = -4616375993471996135L;
    private String DEFAULT_EXCEPTION_CODE = "EX0000";
    
    private String code; 
    
    //--------------------------------------------------------------------------
    
    public NamooBoardException(String message) {
        super(message);
        this.code = DEFAULT_EXCEPTION_CODE;
    }
    
    public NamooBoardException(String message, String code) {
        //
        super(message);
        this.code = code;
    }

    public NamooBoardException(String message, Throwable t) {
        super(message, t);
    }

    public NamooBoardException(String message, Object...objects) {
        super(MessageFormat.format(message, objects));
    }

    public NamooBoardException(String message, Throwable t, Object...objects) {
        super(MessageFormat.format(message, objects), t);
    }

    public NamooBoardException(Throwable t) {
        super(t);
    }
    
    //--------------------------------------------------------------------------
    
    public String getCode() {
        return code;
    }
    
}
