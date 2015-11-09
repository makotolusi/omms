package ${basePackage}.module;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import ${basePackage}.domain.${domainName};
import ${basePackage}.service.${domainName}Service;
import m.w.ztjs.sys.ZtjsLimits;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

/**
 * ${chineseName}
 *
 */
@At("${uriPrefix}")
@IocBean
public class ${domainName}Module extends BasicModule {
    private static final String PS = "${uriPrefix}";
    private static final String NM = "${chineseName}";
    
    @Inject
    private ${domainName}Service ${uncapDomainName}Service;
    
    /** 打开主页面 */
    @At
    @Ok("jsp:"+PS+"/index")
    public void index() {

    }

	/** 列表/查询数据获取 */
    @At({"/items/","/items/?/?"})
    public DataGrid items(String nodeId, String projectStates) {
        return ${uncapDomainName}Service.datagrid(true, ZtjsLimits.limitByCompanyCode(nodeId, projectStates));
    }
    
    /** 打开增加页面 */
    @At("/add/?")
    @GET
    @Ok("jsp:/ex"+PS+"/add")
    public Object add(String nodeId) {
		${domainName} ${uncapDomainName} = new ${domainName}();
		return ${uncapDomainName};
    }

    /** 新增 */
    @At
    @POST
    public Result add(@Param("..") ${domainName} ${uncapDomainName}) {
        return ${uncapDomainName}Service.insert(${uncapDomainName}, NM+"新建成功", NM+"新建失败");
    }

    /** 打开修改页面 */
    @At("/update/?")
    @GET
    @Ok("jsp:/ex"+PS+"/update")
    public Object update(Long id) {
        return ${uncapDomainName}Service.view(id);
    }

    /** 修改 */
    @At
    @POST
    public Result update(@Param("..") ${domainName} ${uncapDomainName}) {
        return ${uncapDomainName}Service.update(${uncapDomainName}, NM+"更新成功", NM+"更新失败");
    }

    /** 按主键删除 */
    @At("/delete/?")
    @DELETE
    public Result delete(Long id) {
        return ${uncapDomainName}Service.delete(id, NM+"删除成功", NM+"删除失败");
    }

    /** 按主键批量删除 */
    @At
    @DELETE
    public Result xdelete(String ids) {
        return ${uncapDomainName}Service.xdelete(ids, NM+"删除成功", NM+"删除失败");
    }

    /** 查看 */
    @At("/view/?")
    @GET
    @Ok("jsp:/ex"+PS+"/view")
    public Object view(Long id) {
        return ${uncapDomainName}Service.view(id);
    }

    /** 打开查询条件页面 */
    @At
    @GET
    @Ok("jsp:/ex"+PS+"/query")
    public void query() {

    }
}
