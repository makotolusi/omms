package m.w.frs.cms.service;

import java.util.List;

import m.w.App;
import m.w.core.service.WxIdService;
import m.w.frs.cms.domain.Article;
import m.w.frs.cms.domain.Dict;

import org.apache.shiro.util.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class ArticleService extends WxIdService<Article> {
	
	public List<Article> query(String title, String type, Pager page) {
		Cnd cnd = Cnd.where("state", "=", 1);
		String typeId = "";
		if (StringUtils.hasText(type)) {
//			for (Dict d : App._articleTypes) {
//				if (d.getName().equals(type)) {
//					typeId = d.getId().toString();
//					break;
//				}
//			}
		}
		if (StringUtils.hasText(typeId)) {
				cnd.and("type","=",  typeId);
		}
		if (StringUtils.hasText(title)) {
			cnd.and("title","like","%" + title + "%");
		}
		
		page.setRecordCount(count(cnd));
		
		cnd.desc("createTime");
		List<Article> articles = query(cnd, page);
//		for (Article a : articles) {
//			a.setTypeName(App.getArticleTypeName(a.getType()));
//		}
		return articles;
	}
}
