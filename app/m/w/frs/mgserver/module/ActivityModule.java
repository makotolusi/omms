package m.w.frs.mgserver.module;

import java.util.Date;
import java.util.List;

import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.frs.mg.Utils;
import m.w.frs.mgserver.domain.Activity;
import m.w.frs.mgserver.domain.ActivityProductData;
import m.w.frs.mgserver.domain.Product;
import m.w.frs.mgserver.service.ActivityProductDataService;
import m.w.frs.mgserver.service.ActivityService;
import m.w.frs.mgserver.service.ProductService;
import m.w.mg.sso.util.UserUtils;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/activity")
@IocBean
public class ActivityModule {

	@Inject
	private ActivityService activityService;

	@Inject
	private ProductService productService;
	
	@Inject
	private ActivityProductDataService activityProductDataService;

	private static final String PS = "/page/activity";

	/** 打开主页面 */
	@At
	@Ok("jsp:" + PS + "/index")
	public void index() {

	}
	
	/** 新增活动-页面 */
	@At({ "/add", "/add/?" })
	@Ok("jsp:" + PS + "/add")
	public Object add(Long id) {

		if (id != null) {
			return activityService.fetch(id);
		}
		Activity queryObj = new Activity();
		return queryObj;
	}
	
	/** 保存 */
	@At
	@POST
	public Object save(@Param("..") Activity addObject) {
		addObject.setEntertime(new Date());
		addObject.setUserName(UserUtils.CurrentUser().getUsername());
		if (addObject.getId() == null) {

			return activityService.insert(addObject, "操作成功", "操作失败");
		}
		Activity queryObj = activityService.fetch(addObject.getId());
		Utils.copyFieldValue(queryObj, addObject);

		return activityService.update(queryObj, "操作成功", "操作失败");

	}	
	
	/** 观看bid记录*/
	@At({ "/viewstartbidrecord", "/viewstartbidrecord/?" })
	@Ok("jsp:" + PS + "/_viewpopupform")
	public Object viewstartbidrecord(Long id) {

		if (id != null) {
			return activityService.fetch(id);
		}
		Activity queryObj = new Activity();
		return queryObj;
	}
	
	/** 删除记录 */
	@At({ "/delete", "/delete/?" })
	@DELETE
	public Result delete(Long id) {
		return activityService.delete(id, "删除成功", "删除失败");
	}
	
	/**  */
	@At({ "/items", "/items/?/?" })
	public DataGrid items() {

		return activityService.datagrid();

	}
	
	/** 获取更多修改活动的信息，并获得可供选择商品一览 */
	@At("/guanlian/?")
	@GET
	@Ok("jsp:" + PS + "/guanlianproduct")
	public Object update(Long id){
		Activity activity = activityService.view(id);
		return activity;
	}
	
	/** 迁移到活动的商品一览 */
	@At("/toactivityproduct/?")
	@GET
	@Ok("jsp:" + PS + "/edit_activity_product")
	public Object toActivityProductList(Long id){
		Activity activity = activityService.view(id);
		return activity;
	}
	
//	/** 获取活动的商品一览 
//	 * @throws Exception */
//	@At("/getactivityproducts/?")
//	public String getActivityProductList(Long activityId) throws Exception{
//
//		Cnd cnd = Cnd.limit().and("activityId", "=", activityId);
//		List<ActivityProductData> dg = activityProductDataService.query(cnd,null);
//		
//		String[] headers = new String[]{};
//		StringWriter writer = new StringWriter();
//		try {
//			writer.write("{ \"total\":"+dg.size()+",\"rows\":");
//			String str = Json.toJson(dg);
//	//		writer.write(str);
//			JsonUtils.write(dg, writer, JsonUtils.configJson());
//			writer.write("}");
//		} catch (Exception e) {
//			throw e;
//		}
//		
//        return writer.toString();
////		Cnd cnd = Cnd.limit().and("activityId", "=", activityId);
////		DataGrid dg = activityProductDataService.datagrid(false, cnd);
////        return dg.toString();
//	}
	
	/** 获取活动的商品一览 
	 * @throws Exception */
	@At("/getactivityproducts/?")
	public DataGrid getActivityProductList(Long activityId) throws Exception{

		Cnd cnd = Cnd.limit().and("activityId", "=", activityId);
		DataGrid dg = activityProductDataService.datagrid(false, cnd);
        return dg;
	}
	
//	/** 更新活动商品 */
//	@At("/updactivityproduct/?")
//	public Object updActivityProduct(Long id){
//		Activity activity = activityService.view(id);
//		return activity;
//	}

	/** 更新活动商品 */
	@At("/updactivityproduct/?")
	@POST
	public Object updActivityProduct(@Param("..") ActivityProductData object) {

		return activityProductDataService.update(object, "操作成功", "操作失败");
	}
	
	@At("/closeactivityproducts/?")
	@POST
	public Result closeActivityProductList(Long activityId){
		Cnd cnd = Cnd.limit().and("activityId", "=", activityId);
		List<ActivityProductData> activityProductDataList = activityProductDataService.query(cnd,null);

		Result r = Result.ok();
		for(int i=0; i<activityProductDataList.size();i++)
		{
			ActivityProductData tempAP = activityProductDataList.get(i);
			tempAP.setStatus("停用");
			r = activityProductDataService.update(tempAP, "操作成功", "操作失败");
			if(!r.isSuccess())
			{
			    return r;
			}
		}
		return r;
	}
	
	@At("/openallactivityproducts/?")
	public Result openAllActivityProductList(Long activityId){
		Cnd cnd = Cnd.limit().and("activityId", "=", activityId);
		List<ActivityProductData> activityProductDataList = activityProductDataService.query(cnd,null);
		
		Result r = Result.ok();
		for(int i=0; i<activityProductDataList.size();i++)
		{
			ActivityProductData tempAP = activityProductDataList.get(i);
			tempAP.setStatus("启用");
			r = activityProductDataService.update(tempAP, "操作成功", "操作失败");
			if(!r.isSuccess())
			{
				return r;
			}
		}
		return r;
	}
	
	@At
	@POST
	public Result yesguanlianproduct(Long activityId, String productIds, String productCodes){

		String[] productIdStrArr = productIds.split(",");
		String[] productCodeStrArr = productCodes.split(",");
		Result retVal = Result.ok();
		
		for(int i= 0;i<productIdStrArr.length;i++){

			ActivityProductData addObject = new ActivityProductData();
			addObject.setEntertime(new Date());
			addObject.setUsername(UserUtils.CurrentUser().getUsername());
			addObject.setActivityId(activityId);

			//get product name
		    Product tempProduct = productService.fetch(Long.parseLong(productIdStrArr[i]));
		    if(tempProduct != null){
		    	String productName = tempProduct.getName();
		    	addObject.setProductName(productName);	
		    	addObject.setPicUrl1(tempProduct.getPicUrl1());	
		    }else{
		    	System.err.println("没有找到这个产品的名字， product ID =" + productIdStrArr[i]);
		    }

			addObject.setProductCode(productCodeStrArr[i]);	
			retVal = activityProductDataService.insert(addObject, "操作成功", "操作失败");
		    if (retVal.isSuccess() == false)
		    {
		    	return retVal;
		    }
		}
		return retVal;	
	}
}
