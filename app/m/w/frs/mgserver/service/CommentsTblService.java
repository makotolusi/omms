package m.w.frs.mgserver.service;

import javax.servlet.http.HttpServletRequest;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.CommentsTbl;
import m.w.frs.mgserver.domain.Product;
import m.w.sys.domain.CommerceUser;
import m.w.sys.service.CommerceUserService;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@IocBean(fields = "dao")
public class CommentsTblService extends WxIdService<CommentsTbl> {

	@Inject
	private ProductService productService;

	@Inject
	private CommerceUserService commerceUserService;

	public Result addComment(HttpServletRequest request) {
		CommentsTbl mu = null;
		String content = request.getParameter("content");
		if (!StringUtils.isEmpty(content)) {

			mu = Json.fromJson(CommentsTbl.class, content);

			CommerceUser cu = commerceUserService.fetch(mu.getUserId());
			if (cu != null) {
				mu.setUserHeadPortraitUrl(cu.getImgUrl());
				mu.setUserName(cu.getUsername());
			}

			Product p = productService.fetch(Cnd.where("productCode", "=",
					mu.getProductCode()));
			if (p != null) {
				mu.setImgUrl(p.getPicUrl1());
			}
			return this.insert(mu, "成功", "失败");

		}

		return Result.err();
	}

	public Object getComments(String productCode) {

		Cnd cnd = Cnd.limit();
		cnd.and("productCode", "=", productCode);
		return this.query(cnd, null);
	}
}
