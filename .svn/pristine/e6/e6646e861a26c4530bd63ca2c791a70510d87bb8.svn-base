package m.w.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;

import m.w.core.dto.WebOptions;
import m.w.sys.domain.Constable;

public class Consts {
	private Consts() {
	};

	private static Map<String, String> code2Text = new HashMap<String, String>();
	private static Map<String, List<WebOptions>> options = new HashMap<String, List<WebOptions>>();
	public static Map<String, String> qiniuEandToken = new ConcurrentHashMap<String, String>();
	private static final String SP = "#";
	private static final String EQ = "=";
	private static final String DT = ".";
	private static final String C = ",";
	private static final String D = ":";
	public static final String REDIS_PREFIX = "mg:";
	public static final String REDIS_SPLIT = ":";
	public static final String REDIS_PIC_ETOKEN = REDIS_PREFIX + "pic:etoken:";
	public static final String REDIS_PIC_UPTOKEN = REDIS_PREFIX + "pic:uptoken:";
	public static final String REDIS_PIC_RES = REDIS_PREFIX + "pic:res:";
	public static final String REDIS_STOCK= REDIS_PREFIX + "snapup:stock:";
	public static final String REDIS_ORDER= REDIS_PREFIX + "snapup:order:";
	
	private static boolean inited = false;

	static {
		init();
	}

	private synchronized static void init() {
		if (inited) {
			return;
		}
		BufferedReader br = new BufferedReader(Streams.fileInr("consts.txt"));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				if (!Strings.isBlank(line)) {
					line = line.trim();
					if (!line.startsWith(SP) && line.indexOf(EQ) > 0) {
						String[] s = Strings.splitIgnoreBlank(line, EQ);
						// f is ClassName.fieldName
						String f = s[0].trim();
						String[] vs = Strings.splitIgnoreBlank(s[1].trim(), C);
						List<WebOptions> ops = new ArrayList<WebOptions>();
						for (String v : vs) {
							String[] k = Strings.splitIgnoreBlank(v.trim(), D);
							code2Text.put(f + DT + k[0].trim(), k[1].trim());
							ops.add(new WebOptions(k[0].trim(), k[1].trim()));
						}
						options.put(f, Collections.unmodifiableList(ops));
					}
				}
			}
			br.close();
			inited = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static boolean reload() {
		code2Text.clear();
		options.clear();
		inited = false;
		init();
		return true;
	}

	/**
	 * 获取某个选项
	 * 
	 * @param f
	 * @return
	 */
	public static List<WebOptions> options(String f) {
		return options.get(f);
	}

	/**
	 * 获取常量的显示值
	 * 
	 * @param clazz
	 * @param fild
	 * @param value
	 * @return
	 */
	public static String getText(Class<?> clazz, String fild, String value) {
		return code2Text.get(clazz.getSimpleName() + DT + fild + DT + value);
	}

	/**
	 * 包装常量对象
	 * 
	 * @param clazz
	 */
	public static void wrap(Constable clazz) {
		Map<String, String> map = clazz.getConstFieldMap();
		for (String fild : map.keySet()) {
			try {
				String value = BeanUtils.getProperty(clazz, fild);
				BeanUtils.setProperty(clazz, map.get(fild), getText(clazz.getClass(), fild, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
