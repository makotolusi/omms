package m.w.sys.util;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Mirror;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MirrorUtils {

	public static Object getFieldValue(Object obj, Class clazz, String fieldName) {
		Mirror mirror = Mirror.me(clazz);
		Object v = "";
		try {
			v = mirror.getValue(obj, mirror.getField(fieldName));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return v;
	}

	public static Object setFieldValue(Object obj, Class clazz,
			String fieldName, Object value) {
		Mirror mirror = Mirror.me(clazz);
		try {
			mirror.setValue(obj, fieldName, value);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return obj;
	}

	public static <T> Object convertJsontoBean(String json, Class<?> T) {
		try {
			Class<T> result = null;
			if (!StringUtils.isEmpty(json)) {
				return JSON.parseObject(json, T);
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	public static String convertBeanToJson(Object obj) {
		if (obj!=null) {
			
			return JSON.toJSONString(obj);

		}
		return null;
	}
}
