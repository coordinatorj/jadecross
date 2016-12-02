package namoo.board.dom2.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonRequest {
    //
    private String requestId; 
    private List<String> paramJsons; 
    
    private JsonRequest(String requestId) {
        // 
        this.requestId = requestId; 
        this.paramJsons = new ArrayList<String>(); 
    }

    public static JsonRequest getInstance(String requestId) {
        return (new JsonRequest(requestId)); 
    }
    
    public JsonRequest addParam(Object object) {
        this.paramJsons.add(JsonUtil.toJson(object)); 
        return this; 
    }
    
    public String getRequestId() {
        return requestId;
    }

    public String getTargetId() {
        //
        return requestId.substring(0, requestId.indexOf(".")); 
    }
    
    public String getJsonParam(int index) {
        return paramJsons.get(index); 
    }
    
    public String getParamAsStr(int index) {
        return JsonUtil.fromJson(paramJsons.get(index), String.class); 
    }
    
    public List<String> getParamValues() {
        return paramJsons;
    }
    
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        // 
        JsonRequest request = new JsonRequest("serviceTarget.operation");
        request.addParam("param1"); 
        request.addParam(request); 
        
        System.out.println(request.getJsonParam(0)); 
        System.out.println(JsonUtil.fromJson(request.getJsonParam(0), String.class)); 
        System.out.println(JsonUtil.fromJson(request.getParamAsStr(0), String.class)); 
        System.out.println(request.getJsonParam(1)); 
        System.out.println(request.getTargetId()); 
    }
}
