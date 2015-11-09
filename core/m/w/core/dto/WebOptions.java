package m.w.core.dto;

import java.io.Serializable;

public class WebOptions implements Serializable{
    private static final long serialVersionUID = -3418948047446257528L;
    private String id;
    private String text;

    public WebOptions(String id, String text){
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
