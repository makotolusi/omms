package m.w.core.dto;


public class KeResult {
    private static final int OK = 0;
    private static final int ERR = 1;
    
    private int error;
    private String url;
    private String message;
    private Object data;
    
    private KeResult(int error, String url, String message, Object data){
        this.error = error;
        this.url = url;
        this.message = message;
        this.data = data;
    };
    
    public static KeResult err(String message){
        return new KeResult(ERR, null, message, null);
    }
    
    public static KeResult ok(String url){
        return new KeResult(OK, url, null, null);
    }
    
    public static KeResult ok(String url, Object data){
        return new KeResult(OK, url, null, data);
    }


    // =========================================================================

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
