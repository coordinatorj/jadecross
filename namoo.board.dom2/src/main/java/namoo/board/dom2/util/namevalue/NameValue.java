/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.util.namevalue;

import java.io.Serializable;
import java.util.List;

public class NameValue implements Serializable {
    //
    private static final long serialVersionUID = 3886859693924239225L;

    private String name;
    private String value;

    //--------------------------------------------------------------------------

    public NameValue() {}
    public NameValue(String name, String value) {
        //
        this.name = name;
        this.value = value;
    }

    //--------------------------------------------------------------------------

    public static String getValue(String name, List<NameValue> pairs) {
        //
        for(NameValue nameValue : pairs) {
            //
            if (nameValue.equalsName(name)) {
                return nameValue.getValue();
            }
        }

        return null;
    }
    
    public static boolean hasName(String name, List<NameValue> pairs) {
        //
        for(NameValue nameValue : pairs) {
            //
            if (nameValue.equalsName(name)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        // 
        StringBuilder builder = new StringBuilder(); 
        
        builder.append("name:" + this.name); 
        builder.append(", value:" + this.value); 
        
        return builder.toString(); 
    }
    
    public boolean equalsName(String name) {
        // 
        if (this.name == null) {
            return false; 
        }
        if (this.name.equals(name)) {
            return true; 
        }
        
        return false; 
    }
    
    public boolean getValueAsBoolean() {
        //
        return Boolean.valueOf(this.value);
    }

    //--------------------------------------------------------------------------
    
    public String getName() {
        return name;
        
    }
    public void setName(String name){
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}
