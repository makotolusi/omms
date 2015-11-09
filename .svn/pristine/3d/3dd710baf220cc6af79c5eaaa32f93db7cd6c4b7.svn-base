package m.w.frs.mgserver.service;

import java.util.List;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.DeviceResolutionRule;
import m.w.frs.mgserver.domain.Product;
import m.w.sys.quartz.domain.PicConfig;
import m.w.sys.service.PicHandlerService;
import m.w.sys.util.MirrorUtils;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class ProductService extends WxIdService<Product> {

	public enum CLIENT_TYPE {
		H5, APP
	}

	@Inject
	private PicHandlerService picHandlerService;

	public Object getRescueProcut(Long pieceId, String sortType, int index) {

		Dao dao = this.dao();
		StringBuffer sqltext = new StringBuffer();
		sqltext.append("SELECT * FROM tbl_product where piece ='" + pieceId
				+ "' ");
		index = index + 9;
		sqltext.append(" ORDER BY entertime DESC limit " + (index) + " ");

		Sql sql = Sqls.create(sqltext.toString());
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Product.class));
		dao.execute(sql);
		List<Product> as = sql.getList(Product.class);

		PicConfig pc = null;

		for (Product a : as) {
			String url = picHandlerService.getQiniuPrivateUrl(a.getPicUrl1(),
					pc);
			a.setPicUrl1(url);
		}
		return as;

	}

	public Object getProduct(String productCode, CLIENT_TYPE type) {

		Cnd cnd = Cnd.limit();
		cnd.and("productCode", "=", productCode);

		Product p = this.fetch(cnd);

		PicConfig pc = null;
		if (CLIENT_TYPE.H5 == type) {
			pc = new PicConfig("535*535");
		}

		for (int i = 1; i < 10; i++) {
			String picUrl = "picUrl" + i;
			Object value = MirrorUtils.getFieldValue(p, Product.class, picUrl);
			value = picHandlerService.getQiniuPrivateUrl(value.toString(), pc);
			if (value != null && value != "")
				p = (Product) MirrorUtils.setFieldValue(p, Product.class, picUrl, value);

		}

		return p;

	}

}
