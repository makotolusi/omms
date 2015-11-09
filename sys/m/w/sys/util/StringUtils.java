package m.w.sys.util;

/**
 * 字符串工具类
 * @author WenWu
 *
 */
public class StringUtils {
	private StringUtils(){
		
	}

	/**
	 * 字符串是否具有长度.<br>
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * 如果字符串为null，则返回空字符串；否则返回原串.<br>
	 * @param str
	 * @return
	 */
	public static String notNull(String str){
		return str == null ? "" : str;
	}
	
	/**
	 * 如果字符串为null，则返回空字符串；否则返回默认串.<br>
	 * @param str
	 * @return
	 */
	public static String notNull(String str, String defaultValue){
		return str == null ? defaultValue : str;
	}

	/**
	 * 字符串是否不为空，且包含正真的字符.<br>
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str){
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除字符串两端的空白字符（仅包括左边和右边的，不包括中间的）；如果字符串为null或空字符串，则原样返回.<br>
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}
	
	/**
	 * 删除字符串左边的空白字符；如果字符串为null或空字符串，则原样返回.<br>
	 * @param str
	 * @return
	 */
	public static String trimLeft(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}
	
	/**
	 * 删除字符串右边的空白字符；如果字符串为null或空字符串，则原样返回.<br>
	 * @param str
	 * @return
	 */
	public static String trimRight(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}
	
	/**
	 * 删除字符串中所有空白字符（包括左边的、右边的和中间的）；如果字符串为null或空字符串，则原样返回.<br>
	 * @param str
	 * @return
	 */
	public static String trimAll(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return buf.toString();
	}
	
    /**
     * 将所给字符串首字母小写；如果字符串为null或空字符串，则原样返回.<br>
     * @param str
     * @return
     */
    public static String uncapitalize(String str) {
        if (!hasLength(str)) {
            return str;
        }
        return new StringBuffer(str.length())
            .append(Character.toLowerCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
    
    /**
     * 将所给字符串首字母大写；如果字符串为null或空字符串，则原样返回.<br>     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (!hasLength(str)) {
            return str;
        }
        return new StringBuffer(str.length())
            .append(Character.toTitleCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
    
    /**
     * 获取类的包名.<br>
     * @param fullQualifiedClassName 全限定类名
     * @return
     */
    public static String extractPackageName(String fullQualifiedClassName){
        if (!hasLength(fullQualifiedClassName)) {
            return fullQualifiedClassName;
        }
        int pos = fullQualifiedClassName.lastIndexOf(".");
        if(pos == -1){//不含'.' 没有包名
        	return "";
        }
        return fullQualifiedClassName.substring(0, pos);
    }
    
    /**
     * 将路径名中的'\\'正规化为'/'.<br>
     * @param fileName
     * @return
     */
    public static String normalizeFileName(String fileName){
    	if(!hasLength(fileName)){
    		return fileName;
    	}
    	return fileName.replaceAll("\\\\", "/");
    }
    
    /**
     * 获取类的SimpleName.<br>
     * @param fullQualifiedClassName 全限定类名
     * @return
     */
    public static String extractSimpleClassName(String fullQualifiedClassName){
    	if (!hasLength(fullQualifiedClassName)) {//无效串
            return fullQualifiedClassName;
        }
    	int pos = fullQualifiedClassName.lastIndexOf(".");
        if(pos == -1){//不含'.' 没有包名
        	return fullQualifiedClassName;
        }else if(pos + 1 > fullQualifiedClassName.length()){// 最后一个字符为'.'
        	return "";
        }
        return fullQualifiedClassName.substring(pos + 1, fullQualifiedClassName.length());
    }
    
    /**
     * 删除字符串起始的斜杠(/).<br>
     * 如：//abc -> abc<br>
     *    /abc  -> abc<br>
     *    abc   -> abc<br>
     * @param str
     * @return
     */
    public static String removeStartSlash(String str){
        if (!hasLength(str)) {
            return str;
        }
       	return str.replaceAll("^/*", "");

    }
    
    /**
     * 在末尾添加斜杠(/)，并确保末尾只有一个斜杠.<br>     * 如： abc   -> abc/<br>
     *     abc/  -> abc/<br>
     *     abc// -> abc/<br>
     * @param str
     * @return
     */
    public static String appendTailSlash(String str){
        if (!hasLength(str)) {
            return str;
        }
       	if(!str.endsWith("/")){
       		return str + "/";
       	}else{
       		return str.replaceAll("/*$", "") + "/";
       	}

    }
    
    /**
     * 删除开始的斜杠，并且在末尾添加斜杠.<br>
     * @param str
     * @return
     */
    public static String removeStartAndAppendTailSlash(String str){
    	return appendTailSlash(removeStartSlash(str));
    }
    
    public static String removeStartAndAppendTailSlashWithNormalize(String str){
    	return appendTailSlash(removeStartSlash(normalizeFileName(str)));
    }
    
    /**
     * 将包名转换为路径名.<br>     * 如：com.fund.core -> com/fund/core<br>
     * @param packageName
     * @return
     */
    public static String packageNameToPath(String packageName){
        if (!hasLength(packageName)) {
            return packageName;
        }
        return packageName.replaceAll("\\.", "/");
    }
    
    /**
     * 路径转换为包名.<br>     * 如：com/fund/core -> com.fund.core<br>
     * @param path
     * @return
     */
    public static String pathToPackageName(String path){
    	if (!hasLength(path)) {
            return path;
        }
        return path.replaceAll("/", ".");
    }
    
    /**
     * 给文件添加扩展名.<br>
     * 如：fileName='thisFile'      fileExt='pdf'    -> thisFile.pdf<br>
     *    fileName='thisFile'      fileExt='.pdf'   -> thisFile.pdf<br>
     *    fileName='thisFile.pdf'  fileExt='pdf'    -> thisFile.pdf<br>
     *    fileName='thisFile.pdf'  fileExt='.pdf'   -> thisFile.pdf<br>
     * @param fileName
     * @param fileExt
     * @return
     */
    public static String appendFileExt(String fileName, String fileExt){
    	if(!hasLength(fileName) || !hasLength(fileExt)){
    		return fileName;
    	}
    	if(!fileExt.startsWith(".")){
    		fileExt = "." + fileExt;
    	}
    	if(hasLength(fileExt) && !fileName.endsWith(fileExt)){
    		return fileName + fileExt;
    	}else{
    		return fileName;
    	}
    }
    
    /**
     * 将引号转义.<br>
     * 转义前：this 'is' a "TEST".<br>
     * 转义后：this \'is\' a \"TEST\".<br>
     * @param str
     * @return
     */
    public static String escapeQuotationMarks(String str){
    	if (!hasLength(str)) {
            return str;
        }
    	return str.replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'");
    }
    
    /**
     * 将引号反转义.<br>
     * 转义前：this \'is\' a \"TEST\".<br>
     * 转义后：this 'is' a "TEST".<br>
     * @param str
     * @return
     */
    public static String unescapeQuotationMarks(String str){
    	if (!hasLength(str)) {
            return str;
        }
    	return str.replaceAll("\\\\\"", "\"").replaceAll("\\\\'", "'");
    }
    
    /**
     * 将字符串进行HTML编码
     * @param str
     * @return
     */
    public static String htmlEncode(String str){
    	if (!hasLength(str)) {
            return str;
        }
    	StringBuffer buffer = new StringBuffer();
    	for(int i=0; i<str.length(); i++){
    		char c = str.charAt(i);
    		if (c < '\200') {
                switch (c) {
                case '"':
                	buffer.append("&quot;");

                    break;

                case '&':
                	buffer.append("&amp;");

                    break;

                case '<':
                	buffer.append("&lt;");

                    break;

                case '>':
                	buffer.append("&gt;");

                    break;

                default:
                	buffer.append(c);
                }
            }else{
            	buffer.append(c);
            }
    	}
    	return buffer.toString();
    }
    
    /**
     * 将字符串进行HTML解码
     * @param str
     * @return
     */
    public static String htmlDecode(String str){
    	if (!hasLength(str)) {
            return str;
        }
    	return str.replaceAll("&quot;", "\"")
    		.replaceAll("&amp;", "&")
    		.replaceAll("&lt;", "<")
    		.replaceAll("&gt;", ">");
    }
    
    /**
     * 左填充
     * @param str
     * @param length
     * @param append
     * @return
     */
    public static String paddingLeft(String str, int length, String append){
    	if(str != null && append != null){
    		int appendLen = append.length();
    		StringBuffer buffer = new StringBuffer(str);
        	while((buffer.length() < length) && ((buffer.length() + appendLen) <= length)){
        		buffer.insert(0, append);
        	}
        	return buffer.toString();
    	}else{
    		return null;
    	}
    }
    
    /**
     * 右填充
     * @param str
     * @param length
     * @param append
     * @return
     */
    public static String paddingRight(String str, int length, String append){
    	if(str != null && append != null){
    		int appendLen = append.length();
    		StringBuffer buffer = new StringBuffer(str);
        	while((buffer.length() < length) && ((buffer.length() + appendLen) <= length)){
        		buffer.append(append);
        	}
        	return buffer.toString();
    	}else{
    		return null;
    	}
    }
    
    public static String wrap(String str, int length){
    	StringBuffer buffer = new StringBuffer();
    	if(str != null){
    		int start = 0;
    		int end = 0;
    		int max = str.length();
    		while(start < max){
    			end = start + length;
    			if(end > max){
    				end = max;
    			}
    			buffer.append(str.substring(start, end));
    			buffer.append("\n");
    			start = end;
    		}
    	}
    	return buffer.toString();
    }
}
