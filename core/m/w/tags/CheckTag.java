package m.w.tags;

public class CheckTag {
    private static final String CHECKED = " checked ";
    private static final String UN_CHECKED = " ";
    
    /**
     * 判断 checkbox 是否选中
     * @param isChecked
     * @return
     */
    public static String checked(boolean isChecked){
        return isChecked ? CHECKED : UN_CHECKED ;
    }
}
