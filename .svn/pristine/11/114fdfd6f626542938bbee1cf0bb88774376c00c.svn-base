package m.w.sys.module;

import java.util.List;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.sys.domain.DictItem;
import m.w.sys.domain.DictType;
import m.w.sys.service.DictItemService;
import m.w.sys.service.DictTypeService;
import m.w.sys.util.Dicts;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/sys/dict")
@IocBean
public class DictModule extends BasicModule{
    @Inject
    private DictTypeService dictTypeService;
    
    @Inject
    private DictItemService dictItemService;
    
    @At
    @Ok("jsp:/sys/dict/index")
    public void index() {

    }
    
    @At
    public DataGrid types() {
        return dictTypeService.datagrid("orders", "asc");
    }
    
    @At
    @POST
    public Result add(@Param("..") DictType type) {
        return dictTypeService.insert(type, null, null);
    }

    @At
    @POST
    public Result update(@Param("..") DictType type) {
        return dictTypeService.update(type, null, null);
    }

    @At("/delete/?")
    @DELETE
    public Result delete(Long id) {
        return dictTypeService.delete(id, null, null);
    }
    
    @At
    public Result reload(){
    	return Dicts.reload() ? Result.ok() : Result.err();
    }
    
    //=========================================================================
    // DictItem
    //=========================================================================
    
    @At("/dicts/?")
    public List<DictItem> dicts(String code){
    	return Dicts.dicts(code);
    }
    
    @At("/items/?")
    public DataGrid items(Long typeId) {
        return dictItemService.datagrid(Cnd.where("typeId", "=", typeId), "orders", "asc");
    }

    @At("/addItem/?")
    @POST
    public Result addItem(Long typeId, @Param("..") DictItem item) {
    	item.setTypeId(typeId);
        return dictItemService.insert(item, null, null);
    }

    @At
    @POST
    public Result updateItem(@Param("..") DictItem item) {
        return dictItemService.update(item, null, null);
    }

    @At("/deleteItem/?")
    @DELETE
    public Result deleteItem(Long id) {
        return dictItemService.delete(id, null, null);
    }
}
