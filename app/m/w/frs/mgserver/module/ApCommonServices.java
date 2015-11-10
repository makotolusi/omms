package m.w.frs.mgserver.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m.w.Const;
import m.w.core.dto.Result;
import m.w.frs.mg.alipay.AlipayNotify;
import m.w.frs.mgserver.domain.DataAdapter;
import m.w.frs.mgserver.domain.DeviceResolutionRule;
import m.w.frs.mgserver.domain.MgTribeInfo;
import m.w.frs.mgserver.domain.MgUser;
import m.w.frs.mgserver.domain.PieceMaintain;
import m.w.frs.mgserver.domain.Product;
import m.w.frs.mgserver.domain.UserToken;
import m.w.frs.mgserver.service.ActivityProductDataService;
import m.w.frs.mgserver.service.ActivityService;
import m.w.frs.mgserver.service.CommentsTblService;
import m.w.frs.mgserver.service.DeviceResolutionRuleService;
import m.w.frs.mgserver.service.IndexPageService;
import m.w.frs.mgserver.service.MgTribelInfoService;
import m.w.frs.mgserver.service.MgUserService;
import m.w.frs.mgserver.service.PieceMaintainService;
import m.w.frs.mgserver.service.ProductHtmlService;
import m.w.frs.mgserver.service.ProductService;
import m.w.frs.mgserver.service.ProductService.CLIENT_TYPE;
import m.w.frs.mgserver.service.RewardService;
import m.w.frs.mgserver.service.SplashScreenService;
import m.w.frs.mgserver.service.UserTokenService;
import m.w.frs.mgserver.service.WishService;
import m.w.frs.mgserver.service.snapup.ActivitySnappingUpService;
import m.w.security.AuthFilter;
import m.w.security.PrivateUtil;
import m.w.sys.domain.CommerceUser;
import m.w.sys.quartz.domain.PicConfig;
import m.w.sys.service.CommerceUserService;
import m.w.sys.service.PicHandlerService;
import m.w.sys.util.DateUtils;
import m.w.sys.util.MirrorUtils;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.POST;

@At("/ApCommonServices")
@IocBean
public class ApCommonServices {

	@Inject
	private IndexPageService indexPageService;

	@Inject
	private MgUserService mgUserService;

	@Inject
	private CommerceUserService commerceUserService;

	@Inject
	private WishService wishService;

	@Inject
	private CommentsTblService commentsTblService;

	@Inject
	private MgTribelInfoService mgTribelInfoService;

	@Inject
	private ProductService productService;

	@Inject
	private PieceMaintainService pieceMaintainService;

	@Inject
	private ProductHtmlService productHtmlService;

	@Inject
	private ActivityProductDataService activityProductDataService;

	@Inject
	private ActivityService activityService;

	@Inject
	private SplashScreenService splashScreenService;

	@Inject
	private UserTokenService userTokenService;

	@Inject
	private DeviceResolutionRuleService deviceResolutionRuleService;

	@Inject
	private ActivitySnappingUpService activitySnappingUpService;

	@Inject
	private RewardService rewardService;

	@Inject
	private PicHandlerService picHandlerService;

	private static Log log = Logs.get();

	public static final String SORT_TYPE_TIME = "TIME";
	public static final String SORT_TYPE_HEAT = "HEAT";
	public static final String PIECE_CATEGORY = "9";// 拯救
	public static final String RUSH_STATUS = "开始";

	@At
	@POST
	public Object sendrUserReward(HttpServletRequest request) {

		String receivedObject = request.getParameter("content");
		return rewardService.sendrUserReward(receivedObject);
	}

	// 获取评论
	@At({ "/getUserRewards" })
	public Object getUserRewards() {

		return rewardService.fetch(1);

	}

	@At
	@POST
	public Object registerUser(HttpServletRequest request) {

		CommerceUser mu = null;
		String uuu = request.getParameter("content");
		if (!StringUtils.isEmpty(uuu)) {
			mu = Json.fromJson(CommerceUser.class, uuu);
			CommerceUser m = commerceUserService.fetch(Cnd.limit().and(
					"telNumber", "=", mu.getPhoneNum()));

			Result result = Result.ok();
			if (m != null) {
				return result.addAttr("id", m.getId()).addAttr("data", m);
			}
			return commerceUserService.insert(mu, "成功", "失败");

		}

		return Result.err();

	}

	@At
	@POST
	public Object doActivate(HttpServletRequest request, HttpServletResponse rep) {

		if (StringUtils.isEmpty(request.getHeader("c"))
				|| StringUtils.isEmpty(request.getHeader("k"))) {
			return Result.err();
		}

		String content = request.getParameter("content");
		if (StringUtils.isEmpty(content)) {
			return Result.err();
		}

		UserToken userToken = Json.fromJson(UserToken.class, content);

		return activate(userToken, request, rep);

	}

	/**
	 * 设备激活
	 * 
	 * @param UserToken
	 *            UserToken
	 * @return
	 */

	private Result activate(UserToken userToken, HttpServletRequest request,
			HttpServletResponse response) {

		Result r = Result.err();

		String token = PrivateUtil.decryptToken(request);
		if (StringUtils.isBlank(token)) {
			// response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return r;
		}

		userToken.setToken(token);
		try {
			UserToken userTokenTmp = userTokenService.getByToken(userToken
					.getToken());
			if (userTokenTmp == null) {
				userToken.setCommerceUserId(1L);
				userToken = userTokenService.insert(userToken);
				DeviceResolutionRule rule = deviceResolutionRuleService
						.getByResolution(userToken.getResolution());
				if (rule == null) {
					rule = deviceResolutionRuleService
							.calculateAndInsert(userToken.getResolution());
				}
				userToken.setResolutionId(rule.getId());
				userTokenService.update(userToken);
			} else {
				userTokenTmp.setLastReqDate(new Date());
				userTokenService.update(userTokenTmp);
			}
			// response.setStatus(HttpServletResponse.SC_OK);
			return Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
			// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return r;
		}

	}

	@At({ "/indexData", "/indexData/?" })
	@Filters({ @By(type = AuthFilter.class) })
	public Object indexData(String systemName, String clentName,
			HttpServletRequest request, HttpServletResponse rep) {
		rep.setHeader("Access-Control-Allow-Origin", "*");
		Cnd cnd = Cnd.limit();
		cnd.and("systemName", "=", systemName);
		// cnd.and("clentName", "=", clentName);
		return indexPageService.query(cnd, null);

	}

	// 用户
	@At({ "/getUser", "/getUser/?" })
	public Object getUser(String userName, HttpServletResponse rep) {

		rep.setHeader("Access-Control-Allow-Origin", "*");
		Cnd cnd = Cnd.limit();
		cnd.and("userName", "=", userName);
		return mgUserService.query(cnd, null);

	}

	// 根据设备标识 取得当时注册用户信息
	@At({ "/getUserByToken" })
	public Object getUserByToken(String userName, HttpServletRequest request,
			HttpServletResponse rep) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		if (StringUtils.isBlank(token)) {
			// response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user=	commerceUserService.getUserByToken(userTokenTmp);
				if (user == null) {
					return r;
				} else {
					r = new Result();
					r.setSuccess(true);
					
					String bigImg= picHandlerService.getQiniuPrivateUrl(user.getImgUrl(),new PicConfig("500*500"));
					String smallImg= picHandlerService.getQiniuPrivateUrl(user.getImgUrl(),new PicConfig("60*60"));
					user.setImgUrl(bigImg);
					user.setSmallImgUrl(smallImg);
					r.setData(MirrorUtils.convertBeanToJson(user));
					return r;
				}
		} catch (Exception e) {
			e.printStackTrace();
			return r;
		}

	}

	@At
	@POST
	@AdaptBy(type = JsonAdaptor.class)
	public Result saveUser(MgUser mgUser, HttpServletResponse rep,
			HttpServletRequest req) {

		rep.setHeader("Access-Control-Allow-Origin", "*");
		MgUser m = mgUserService.fetch(Cnd.limit().and("userName", "=",
				mgUser.getUsername()));
		if (m != null) {
			return mgUserService.update(mgUser, "成功", "失败");
		}
		return mgUserService.insert(mgUser, "成功", "失败");
	}

	// 部落
	@At({ "/getTribe", "/getTribe/?" })
	public Object getTribe(String name, HttpServletResponse rep) {

		rep.setHeader("Access-Control-Allow-Origin", "*");
		Cnd cnd = Cnd.limit();
		cnd.and("name", "=", name);
		return mgTribelInfoService.query(cnd, null);

	}

	@At
	@POST
	@AdaptBy(type = JsonAdaptor.class)
	public Result saveTribe(MgTribeInfo mgTribelInfo, HttpServletResponse rep,
			HttpServletRequest req) {

		rep.setHeader("Access-Control-Allow-Origin", "*");
		MgTribeInfo m = mgTribelInfoService.fetch(Cnd.limit().and("name", "=",
				mgTribelInfo.getName()));
		if (m != null) {
			return mgTribelInfoService.update(mgTribelInfo, "成功", "失败");
		}
		return mgTribelInfoService.insert(mgTribelInfo, "成功", "失败");
	}

	// 产品
	@At({ "/getProduct", "/getProduct/?" })
	public Object getProduct(String productCode, HttpServletResponse rep) {

		rep.setHeader("Access-Control-Allow-Origin", "*");
		return productService.getProduct(productCode, CLIENT_TYPE.H5);

	}

	// 获取分类
	@At({ "/getPiece", "/getPiece/?" })
	public Object getPiece(String pieceCategory) {

		// rep.setHeader("Access-Control-Allow-Origin", "*");
		Cnd cnd = Cnd.limit();
		cnd.and("pieceCategory", "=", pieceCategory);
		List<PieceMaintain> pms = pieceMaintainService.query(cnd, null);
		ArrayList<DataAdapter> das = new ArrayList<DataAdapter>();
		for (PieceMaintain p : pms) {
			DataAdapter d = new DataAdapter();
			d.setId(p.getId());

			d.setImageUrl(picHandlerService.getQiniuPrivateUrl(p.getImgUrl()));
			d.setSortTitle(p.getText());
			d.setTitle(p.getNotes());
			d.setSortUrl(p.getCountryImgUrl());
			d.setPieceCategory(p.getPieceCategory());

			List<Product> ps = productService.query(
					Cnd.where("piece", "=", p.getType()), null);
			if (ps.size() > 0) {
				if (ps.get(0).getEntertime() != null) {
					d.setDescription(DateUtils.toString(ps.get(0)
							.getEntertime(), Const.DEFAULT_DATE_FORMAT));
				}
			}

			das.add(d);
		}
		return das;

	}

	// 获取活动商品对应表数据
	@At({ "/getActivityProcut", "/getActivityProcut/?" })
	// @Filters({ @By(type = AuthFilter.class) })
	public Object getActivityProcut(String activityId,
			HttpServletRequest request) {
		String resolution = null;
		if (request.getAttribute("resolution") != null) {
			resolution = request.getAttribute("resolution").toString();
		}
		return activityProductDataService.getActivityProcut(activityId,
				resolution);

	}

	// 产品
	@At({ "/getProductForMobile", "/getProductForMobile/?" })
	public Object getProductForMobile(String productCode) {
		Cnd cnd = Cnd.limit();
		cnd.and("productCode", "=", productCode);
		Product p = productService.fetch(cnd);
		for (int i = 0; i < 10; i++) {
			Object obj = MirrorUtils.getFieldValue(p, Product.class, "picUrl"
					+ (i + 1));
			String url = (obj == null ? null : obj.toString());
			url = picHandlerService.getQiniuPrivateUrl(url,
					picHandlerService.getPicConfig(null, "rule2"));
			MirrorUtils
					.setFieldValue(p, Product.class, "picUrl" + (i + 1), url);
		}
		return p;
	}

	// 拯救
	@At({ "/getRescueProcut", "/getRescueProcut/?/?/?" })
	public Object getRescueProcut(Long pieceId, String sortType, int index,
			HttpServletRequest req) {

		return productService.getRescueProcut(pieceId, sortType, index);

	}

	// 产品
	@At({ "/getProductHtml", "/getProductHtml/?" })
	public Object getProductHtml(String productCode) {

		Cnd cnd = Cnd.limit();
		cnd.and("productCode", "=", productCode);
		return productHtmlService.query(cnd, null);

	}

	// 获取分类
	@At({ "/getActivity", "/getActivity/?" })
	// @Filters({ @By(type = AuthFilter.class) })
	public Object getActivity(String appUserId, HttpServletRequest request) {
		String resolution = null;
		if (request.getAttribute("resolution") != null) {
			resolution = request.getAttribute("resolution").toString();
		}
		return activityService.getActivity(appUserId, resolution);

	}

	// 获取分类
	@At({ "/getSplashScreen", "/getSplashScreen/?" })
	public Object getSplashScreen(String appUserId) {

		return splashScreenService.getSplashScreen();

	}

	// 获取分类
	@At({ "/getSplashForHomeHeaderCell", "/getSplashForHomeHeaderCell/?/?" })
	public Object getSplashForHomeHeaderCell(String appUserId, String size) {

		return splashScreenService.getSplashForHomeHeaderCell("ios", size);

	}

	// 输入想要的需求到后台
	@At
	@POST
	public Object addWish(HttpServletRequest request) {

		return wishService.addWish(request);

	}

	// 添加评论
	@At
	@POST
	public Object addComment(HttpServletRequest request) {

		return commentsTblService.addComment(request);

	}

	// 获取评论
	@At({ "/getComments", "/getComments/?" })
	public Object getComments(String productCode) {

		return commentsTblService.getComments(productCode);

	}

	@At
	@POST
	public void addAlipayInfo(HttpServletRequest request,
			HttpServletResponse rep) {
		log.info("zhifubo start callback! ");
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		log.info("zhifubo params is  " + params);
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		try {

			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			log.info("out_trade_no is " + out_trade_no);
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			log.info("trade_no is " + trade_no);
			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");
			log.info("trade_no is " + trade_no);
			if (AlipayNotify.verify(params)) {// 验证成功
				log.info("verify success");
				log.info("TRADE_status is " + trade_status);
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				boolean suc = activitySnappingUpService
						.sanpupSuccess(out_trade_no);
				if (suc) {
					log.info("SUCCESS");
				} else {
					log.info("FAILED");
				}
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					log.info("TRADE_FINISHED");
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					log.info("TRADE_SUCCESS");
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				} else if (trade_status.equals("WAIT_BUYER_PAY")) {
					log.info("WAIT_BUYER_PAY");
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				rep.getWriter().write("success");
				// return "success"; // 请不要修改或删除

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				rep.getWriter().write("fail");
				log.info("verify fail");
				// return "fail";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 获取token
	@At({ "/getUpToken" })
	public Object getUpToken() {

		return picHandlerService.getUpToken();

	}
	// 获取token
	@At({ "/getUpTokenX" })
	public Object getUpTokenX() {
		Result r = Result.err();
		try {
			r.setSuccess(true);
			r.setData(picHandlerService.getUpToken());
			return r;
		} catch (Exception e) {
			return r;
		}
		

	}
}
