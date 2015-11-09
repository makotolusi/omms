package m.w.mg.sso.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lile
 * 加密
 * 
 */
public class UserPassUtil{
	
    public static String seed = "PASSWORD_FOR_NRMS_PROXY_SETTLEMENT_SYSTEM";

	private static String byteArray2HexString(byte[] b){
		
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++){
			int i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	private static String md5s(String plainText) {
		
        String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			str = byteArray2HexString(b);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        return str.toUpperCase();
	}

	/**
	 * @param text
	 * @return
	 * 返回16位的验证码
	 */
    public static String encryptyEC(String text){
    	
        String hash = md5s(text);
        hash = hash + seed;
        
        return md5s(hash).substring(8,24);
        
    }
    
	public static void main(String args[]) {

		String s ="asdasdasd";
		System.out.println(encryptyEC(s));
	}
}
