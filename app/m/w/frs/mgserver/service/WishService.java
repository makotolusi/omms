package m.w.frs.mgserver.service;

import javax.servlet.http.HttpServletRequest;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.Product;
import m.w.frs.mgserver.domain.Wish;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@IocBean(fields = "dao")
public class WishService extends WxIdService<Wish> {

	@Inject
	private ProductService productService;

	public Result addWish(HttpServletRequest request) {
		Wish mu = null;
		String content = request.getParameter("content");
		if (!StringUtils.isEmpty(content)) {

			mu = Json.fromJson(Wish.class, content);
			mu.setFlg("1");
			Product p = productService.fetch(Cnd.where("productCode", "=",
					mu.getProductCode()));
			if (p != null) {
				mu.setImgUrl(p.getPicUrl1());
				mu.setPieceCategory(p.getPieceCategory());
			}
			return this.insert(mu, "成功", "失败");

		}

		return Result.err();
	}
}
