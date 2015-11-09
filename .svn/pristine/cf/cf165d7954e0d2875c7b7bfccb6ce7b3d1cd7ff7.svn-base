package m.w.frs.mg;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImsRoles {
	/** 系统管理 */
	public static final String SYS_ADMIN = "系统管理员";
	
	
	public static final String EVA_MAINTAINER = "维护员";

	public static final String EVA_DATAENTRYSTAFF = "录入员";
	
	public static final String EVA_QUERY = "查看员";
	
	private static Set<String> ALL = new HashSet<String>();
	static{
		ALL.add(SYS_ADMIN);
		
		ALL.add(EVA_MAINTAINER);
		
		ALL.add(EVA_DATAENTRYSTAFF);
		
		ALL.add(EVA_QUERY);
	}
	
	/** 所有角色 */
	public static Set<String> all(){
		return Collections.unmodifiableSet(ALL);
	}
}
