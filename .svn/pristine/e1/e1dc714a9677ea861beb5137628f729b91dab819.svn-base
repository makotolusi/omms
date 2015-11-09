package m.w.sys.service;

import java.util.List;

import m.w.core.exception.AppException;
import m.w.core.service.WxIdService;
import m.w.sys.domain.DictItem;
import m.w.sys.domain.DictType;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields="dao")
public class DictTypeService extends WxIdService<DictType> {

	@Override
	protected void beforeInsert(DictType type) {
		List<DictType> types = dao().query(DictType.class, Cnd.where("code", "=", type.getCode()));
		if(types != null && types.size() > 0){
			throw new AppException("已存在代码为["+type.getCode()+"]的字典分类，不能重复添加。");
		}
		super.beforeInsert(type);
	}

	@Override
	protected void beforeDelete(DictType type) {
		List<DictItem> items = dao().query(DictItem.class, Cnd.where("typeId", "=", type.getId()));
		if(items != null && items.size() > 0){
			throw new AppException("字典分类下的字典项不为空，不可删除。");
		}
		super.beforeDelete(type);
	}

}
