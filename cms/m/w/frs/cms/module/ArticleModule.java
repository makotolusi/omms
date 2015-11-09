package m.w.frs.cms.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import m.w.App;
import m.w.core.dto.DataGrid;
import m.w.frs.cms.domain.Article;
import m.w.frs.cms.service.ArticleService;
import m.w.frs.cms.service.DictService;
import m.w.mg.sso.util.UserUtils;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@At("/cms/article")
public class ArticleModule {
	
	private static final Log log = Logs.get();

	@Inject
	private ArticleService articleService;
	@Inject
	private DictService dictService;
	
	@At("/")
	@Ok("json")
	public DataGrid getAll() {
		return articleService.datagrid();
	}
	
	@At("/?")
	@Ok("json")
	public Article get(String id) {
		return  articleService.fetch(Long.parseLong(id));
	}
	
	@At("/?")
	@DELETE
	public Object delete(String id) {
		return articleService.delete(Long.parseLong(id), "删除成功", "删除失败");
	}
	
	@At("/")
	@POST
	@AdaptBy(type=JsonAdaptor.class)
	public Object save(@Param("..")Article article) {
		Calendar cld = Calendar.getInstance();
		if (article.getId() != null) {
			Article older = articleService.fetch(article.getId());
			older.setDescription(article.getDescription());
			older.setExperience(article.getExperience());
			older.setCurrency(article.getCurrency());
			older.setPrice(article.getPrice());
			older.setAddr(article.getAddr());
			older.setPiece(article.getPiece());
			older.setLastUpdateTime(cld.getTime());
			older.setLastUpdateUserId(UserUtils.CurrentUser().getId());
			older.setState(Article.STATE_UNAUDITED); // 修改后恢复为未审核状态
			return articleService.update(older, "保存成功", "保存失败");
		} else {
			Article newer = new Article();
			newer.setDescription(article.getDescription());
			newer.setExperience(article.getExperience());
			newer.setCurrency(article.getCurrency());
			newer.setPrice(article.getPrice());
			newer.setAddr(article.getAddr());
			newer.setPiece(article.getPiece());
			newer.setAuthorName(article.getAuthorName());
			newer.setImgUrl(article.getImgUrl());
			newer.setDescription(article.getDescription());
			newer.setLastUpdateTime(cld.getTime());
			newer.setLastUpdateUserId(UserUtils.CurrentUser().getId());
			newer.setCreateTime(newer.getLastUpdateTime());
			newer.setAuthorId(newer.getLastUpdateUserId());
			newer.setState(Article.STATE_UNAUDITED);
			newer.setPushTimes(0);
			newer.setReadTimes(0);
			newer.setReplyTimes(0);
			newer.setCollectTimes(0);
//			newer.setState(Article.STATE_AUDITED); // TODO 测试阶段默认设为审批通过
			return articleService.insert(newer, "保存成功", "保存失败");
		}
	}
	
	@At
	@Ok("jsp:/ex/article/list")
	public void list(String title) {
	}
	
	@At
	public DataGrid listData(String title) {
		//Cnd.where("title", "like", "%" + title + "%")
		DataGrid datagrid= articleService.datagrid();
		return datagrid;
	}
	
	@At("/preview/?")
	@Ok("jsp:/ex/article/preview")
	public Article preview(String id) {
		Article article = articleService.fetch(Long.parseLong(id));
//		article.setTypeName(App.getArticleTypeName(article.getType()));
		article.setAppName(App.getAppName());
		return  article;
	}
	
	@At
	@Ok("jsp:/ex/article/queryDialog")
	public void queryDialog() {		
	}
	
	@At
	public Object comboxTypeData() {
//		App._articleTypes.clear();
//		App._articleTypes.addAll(dictService.query(Cnd.where("type", "=", "文章分类"), null));
//		return App._articleTypes;
		return null;
	}
	
	@At
	@Ok("json")
	public String audit(String id, int state) {
		Article article = articleService.fetch(Long.parseLong(id));
		article.setState(state);
		articleService.save(article);
		return "ok";
	}
	
	/* 
	 * 封面上传
     * 注释"@Param("Filedata ") List<TempFile>  tempFiles," 中的Filedata是客户端 
     *  <input  type =" Filedata "  name =" Filedata "  id ="file" /> 中的name名 
     */  
//    @At("/upload")  
//    @AdaptBy(type = UploadAdaptor.class,args = { "/upload/image", "8192", "UTF-8", "10" })  
//	@Ok("jsp:/ex/article/edit_showTitleImg")
//    public String upload(  
//                @Param("Filedata") TempFile  f,  
//                HttpServletRequest req,  
//                ServletContext context) throws SQLException, IOException {  
//    	String dayPath =  new SimpleDateFormat("yyyyMMdd").format(new Date());
//    	String path = context.getRealPath("/upload") +File.separator + "image" + File.separator +dayPath + File.separator;
//    	
//    	File imageFile =new File(path + new SimpleDateFormat("HHmmss").format(new Date()) + ".jpg");
//    	File parent = imageFile.getParentFile();
//    	if (parent!= null && !parent.exists()) {
//    		parent.mkdirs();
//    	}
//    	FileOutputStream fos = new FileOutputStream(imageFile);
//        try{
//	    	FileInputStream fis = new FileInputStream(f.getFile());
//	    	byte[] content = Streams.readBytesAndClose(fis);
//	    	
//	    	fos.write(content);
//	    	
//        }catch(IOException e) {
//        	return e.getMessage();
//        } finally {
//        	fos.close();
//        }
//        return context.getContextPath() + "/upload/image/" + dayPath + "/" + imageFile.getName() ;  
//    }  
}
