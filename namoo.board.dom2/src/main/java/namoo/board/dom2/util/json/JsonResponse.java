package namoo.board.dom2.util.json;



public class JsonResponse {
    // 
    private String requestId; 
    private String resultJson; 
    private String exceptionStr; 
    private boolean fail; 
    
    private JsonResponse(String requestId) {
        // 
        this.requestId = requestId;
        this.fail = false; 
    }

    private JsonResponse(String requestId, boolean fail) {
        // 
        this.requestId = requestId;
        this.fail = fail; 
    }

    public static JsonResponse getInstance(JsonRequest request) {
        // 
        return new JsonResponse(request.getRequestId()); 
    }

    public static JsonResponse getInstance(JsonRequest request, Object object) {
        // 
        JsonResponse response = new JsonResponse(request.getRequestId());
        response.addResult(object); 
        
        return response; 
    }

    public static JsonResponse getInstance(JsonRequest request, String result) {
        // 
        JsonResponse response = new JsonResponse(request.getRequestId()); 
        response.addResult(result); 
        
        return response; 
    }

    public static JsonResponse getExceptionInstance(JsonRequest request, String message) {
        // 
        JsonResponse response = new JsonResponse(request.getRequestId(), true); 
        response.addExceptionStr(message); 
        
        return response; 
    }
    
    public boolean isFail() {
        return fail; 
    }
    
    public static JsonResponse getDummy() {
        // 
        return new JsonResponse("0"); 
    }

    public JsonResponse addResult(Object object) {
        // 
        this.resultJson = JsonUtil.toJson(object); 
        return this; 
    }

    public JsonResponse addExceptionStr(String message) {
        this.exceptionStr = message;
        return this; 
    }
    
    public String getRequestId() {
        return requestId;
    }

    public String getResultJson() {
        return resultJson;
    }
    
    public boolean isSuccess() {
        //
        if (exceptionStr == null) {
            return true; 
        }
        
        return false; 
    }
    
    public String getExceptionStr() {
        return exceptionStr; 
    }
    
    public String getResultAsStr() {
        // 
        return JsonUtil.fromJson(resultJson, String.class); 
    }
}