package m.w.core.util;

public abstract class Files extends org.nutz.lang.Files{
    public static final long ONE_KB = 1024;
    public static final long ONE_MB = ONE_KB * ONE_KB;
    public static final long ONE_GB = ONE_KB * ONE_MB;
    public static final long ONE_TB = ONE_KB * ONE_GB;
    public static final long ONE_PB = ONE_KB * ONE_TB;
    public static final long ONE_EB = ONE_KB * ONE_PB;

    /**
     * 获取文件大小的显示值
     * @param size
     * @return
     */
    public static String byteCountToDisplaySize(long size) {
        String displaySize = "";
        if (size / ONE_EB > 0) {
            displaySize = String.valueOf(size / ONE_EB) + " EB";
        } else if (size / ONE_PB > 0) {
            displaySize = String.valueOf(size / ONE_PB) + " PB";
        } else if (size / ONE_TB > 0) {
            displaySize = String.valueOf(size / ONE_TB) + " TB";
        } else if (size / ONE_GB > 0) {
            displaySize = String.valueOf(size / ONE_GB) + " GB";
        } else if (size / ONE_MB > 0) {
            displaySize = String.valueOf(size / ONE_MB) + " MB";
        } else if (size / ONE_KB > 0) {
            displaySize = String.valueOf(size / ONE_KB) + " KB";
        } else {
            displaySize = String.valueOf(size) + " bytes";
        }
        return displaySize;
    }
}
