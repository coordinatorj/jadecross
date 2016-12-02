/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom2.util.namevalue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class NameValueList {
    //
    private List<NameValue> nameValues = new ArrayList<NameValue>(); 
    
    //--------------------------------------------------------------------------
    //  constructor 
    
    private NameValueList() {
    }

    //--------------------------------------------------------------------------
    
    public static NameValueList getInstance() {
        // 
        return new NameValueList(); 
    }
    
    public static NameValueList createFromJson(String json){
        return new Gson().fromJson(json, NameValueList.class);
    }
    
    //--------------------------------------------------------------------------
    // 
    
    @Override
    public String toString() {
        // 
        StringBuilder builder = new StringBuilder(); 
        
        for (NameValue nameValue : this.nameValues)  {
            builder.append("[" + nameValue + "]"); 
        }
        
        return builder.toString(); 
    }
    
    public int size() {
        //
        return nameValues.size(); 
    }
    
    public NameValue get(int index) {
        //
        return nameValues.get(index); 
    }
    
    public boolean hasName(String name) {
        // 
        for (NameValue nameValue : this.nameValues)  {
            //
            if (nameValue.equalsName(name)) {
                return true; 
            }
        }
        
        return false; 
    }
    
    public boolean hasNameValue() {
        // 
        if (nameValues == null || nameValues.isEmpty()) {
            return false; 
        }
        
        return true; 
    }
    
    public NameValueList add(String id, String name) {
        //
        nameValues.add(new NameValue(id, name));
        return this; 
    }

    public NameValueList add(NameValue nameValue) {
        //
        nameValues.add(nameValue); 
        return this; 
    }
    
    public NameValueList add(String name, boolean value) {
        //
        nameValues.add(new NameValue(name, String.valueOf(value)));
        return this;
    }
    
    public Iterator<NameValue> iterator() {
        // 
        return nameValues.iterator(); 
    }
    
    public String getJson() {
        // 
        Gson gson = new Gson(); 
        return gson.toJson(this); 
    }
    
    public List<String> getNames() {
        // 
        List<String> names = new ArrayList<String>();
        
        for (NameValue nameValue : this.nameValues)  {
            //
            names.add(nameValue.getName()); 
        }
        
        return names; 
    }
    
    public List<NameValue> getIdNames() {
        return nameValues; 
    }
    
    public List<NameValue> getNameValues() {
        return nameValues; 
    }
    
}
