package m.w.frs.mg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import m.w.core.dto.tree.Tree;
import m.w.core.dto.tree.TreeItem;
import m.w.core.util.Daos;
import m.w.frs.mg.domain.InfoSys;
import m.w.frs.mg.domain.InfoSysable;
import m.w.frs.mg.domain.InfoType;
import m.w.frs.mg.domain.InfoTypeable;
import m.w.mg.sso.util.UserUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.google.common.base.Strings;

public class Infos {
	private static List<InfoType> its = new ArrayList<InfoType>();
	private static List<InfoSys> iss = new ArrayList<InfoSys>();
	private static Map<Long, InfoType> map = new ConcurrentHashMap<Long, InfoType>();
	private static Map<Long, InfoSys> sysmap = new ConcurrentHashMap<Long, InfoSys>();
	private static Tree tree;
	private static boolean inited = false;
	private static boolean modified = false;
	private static Set<Long> allSysIds = new HashSet<Long>();

	static {
		init();
	}

	private static void init() {
		if (inited) {
			return;
		}
		Dao dao = Daos.dao();
		List<InfoType> ts = dao.query(InfoType.class, null);
		String query = UserUtils.CurrentUser().getRoleEditPieces();
		Set<String> sss = null;
		if (query != null) {
			String[] s = query.split(",");
			sss = new HashSet<String>();
			for (int i = 0; i < s.length; i++) {
				sss.add(s[i]);
			}
		}
		Cnd c = Cnd.limit().and("state", "=", "1");
		if (sss != null) {
			c.and("text", "in", sss);
		} else {
			its.clear();
			iss.clear();
			tree = new Tree();
			return;
		}
		List<InfoSys> ss = dao.query(InfoSys.class, c);
		tree = new Tree();
		tree.addItem(new TreeItem(0, "所有分类"));
		for (InfoType it : ts) {
			its.add(it);
			map.put(it.getId(), it);
			TreeItem t = it.toTreeItem();
			t.addAttr("level", 1);
			tree.addItem(t);
		}
		for (InfoSys s : ss) {
			iss.add(s);
			allSysIds.add(s.getId());
			sysmap.put(s.getId(), s);
			TreeItem t = s.toTreeItem();
			t.addAttr("typeId", s.getTypeId());
			t.addAttr("typeName", s.getTypeName());
			t.addAttr("contact", s.getContact());
			t.addAttr("contactDept", s.getContactDept());
			t.addAttr("tel", s.getTel());
			t.addAttr("level", 2);
			tree.addItem(t);
		}
		Collections.sort(its);
		Collections.sort(iss);
		tree.build().open();
		inited = false;
		// modified = true;
	}

	public synchronized static boolean reload() {
		its.clear();
		iss.clear();
		allSysIds.clear();
		map.clear();
		sysmap.clear();
		inited = false;
		init();
		return true;
	}

	public synchronized static InfoSys getSys(Long id) {
		return sysmap.get(id);
	}

	public synchronized static String getSysName(Long id) {
		if (id != null) {
			if (id == 0) {
				return "信息部";
			}
			InfoSys sys = sysmap.get(id);
			if (sys != null) {
				return sys.getText();
			}
		}
		return "";
	}

	public synchronized static InfoSys addSys(InfoSys it) {
		modified = true;
		iss.add(it);
		allSysIds.add(it.getId());
		return sysmap.put(it.getId(), it);
	}

	public synchronized static InfoSys removeSys(Long id) {
		InfoSys sys = sysmap.remove(id);
		if (sys != null) {
			iss.remove(sys);
			allSysIds.remove(sys.getId());
			modified = true;
		}
		return sys;
	}

	public synchronized static List<InfoSys> sysItems() {
		modified();
		return Collections.unmodifiableList(iss);
	}

	public synchronized static InfoType get(Long id) {
		return map.get(id);
	}

	public synchronized static InfoType add(InfoType it) {
		modified = true;
		its.add(it);
		return map.put(it.getId(), it);
	}

	public synchronized static InfoType remove(Long id) {
		InfoType type = map.remove(id);
		if (type != null) {
			its.remove(type);
			modified = true;
		}
		return type;
	}

	public synchronized static List<InfoType> items() {
		modified();
		return Collections.unmodifiableList(its);
	}

	public synchronized static Tree tree() {
		modified();
		init();
		return tree;
	}

	private synchronized static void modified() {
		if (modified) {
			Collections.sort(its);
			tree = Tree.toTree(map.values()).open();
			modified = false;
		}
	}

	public static void wrap(InfoTypeable clazz) {
		Map<String, String> fm = clazz.getInfoTypeFieldMap();
		for (String fild : fm.keySet()) {
			try {
				String value = BeanUtils.getProperty(clazz, fild);
				if (!Strings.isNullOrEmpty(value)) {
					InfoType it = map.get(Long.parseLong(value));
					if (it != null) {
						BeanUtils
								.setProperty(clazz, fm.get(fild), it.getText());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void wrapSys(InfoSysable clazz) {
		Map<String, String> fm = clazz.getInfoSysFieldMap();
		for (String fild : fm.keySet()) {
			try {
				String value = BeanUtils.getProperty(clazz, fild);
				if ("0".equals(value)) {
					BeanUtils.setProperty(clazz, fm.get(fild), "【信息部】");
				} else {
					if (!Strings.isNullOrEmpty(value)) {
						InfoSys it = sysmap.get(Long.parseLong(value));
						if (it != null) {
							BeanUtils.setProperty(clazz, fm.get(fild),
									it.getText());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取某个类别下的所有ID
	 * 
	 * @param typeId
	 * @return
	 */
	public static Set<Long> getTypeSysIds(Long typeId) {
		Set<Long> ids = new HashSet<Long>();
		if (iss != null && typeId != null) {
			for (InfoSys s : iss) {
				if (typeId.equals(s.getTypeId())) {
					ids.add(s.getId());
				}
			}
		}
		return ids;
	}

	/**
	 * 用户对所有类型节点过滤
	 * 
	 * @param idField
	 * @param nid
	 * @return
	 */
	public static Cnd allInfoLimits(String idField, String nid) {
		Set<Long> ids = null;
		if (Strings.isNullOrEmpty(nid) || "0".equals(nid)) {
			return Cnd.limit();
		} else if (nid.startsWith("type-")) {
			ids = getTypeSysIds(Long.parseLong(nid.substring(5)));
			if (ids == null || ids.isEmpty()) {
				return Cnd.where("1", "<>", "1");
			} else {
				return Cnd.where(idField, "in", ids);
			}
		} else {
			return Cnd.where(idField, "=", nid);
		}
	}
}
