package m.w.sys.service;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.qiniu.util.Auth;

import m.w.App;
import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.DeviceResolutionRule;
import m.w.frs.mgserver.service.DeviceResolutionRuleService;
import m.w.sys.domain.Atta;
import m.w.sys.quartz.domain.PicConfig;
import m.w.sys.util.Consts;
import m.w.sys.util.MirrorUtils;
import m.w.sys.util.RedisManager;
import redis.clients.jedis.Jedis;

@IocBean(fields = "dao")
public class PicHandlerService extends WxIdService<Atta> {

	private Auth auth = Auth.create(App.getQiNiuAccessKey(),
			App.getQiNiuSecretKey());

	private final static Integer EXPIRE_TIME = 3500;
	@Inject
	private DeviceResolutionRuleService deviceResolutionRuleService;

	/**
	 * 简单上传，使用默认策略
	 * 
	 * @return
	 */
	public String getUpToken() {
		Jedis jedis = null;
		try {
			jedis = RedisManager.getJedis();// cache
			String uptoken = jedis.get(Consts.REDIS_PIC_UPTOKEN);
			if (uptoken == null) {
				uptoken = auth.uploadToken(App.getQiNiuBucket());
				jedis.set(Consts.REDIS_PIC_UPTOKEN, uptoken);// cache
				jedis.expire(Consts.REDIS_PIC_UPTOKEN, EXPIRE_TIME);// expire
			}
			return uptoken;
		} catch (Exception e) {// 任何异常都返回默认
			return null;
		} finally {
			RedisManager.returnJedis(jedis);
		}
	}

	public PicConfig getPicConfig(String res, String ru) {
		if (res == null || ru == null)
			return null;
		Jedis jedis = null;
		try {
			jedis = RedisManager.getJedis();// cache
			String w = jedis.hget(Consts.REDIS_PIC_RES + res + ":" + ru, "w");
			String h = jedis.hget(Consts.REDIS_PIC_RES + res + ":" + ru, "h");
			if (w == null || h == null) {
				DeviceResolutionRule rule = deviceResolutionRuleService
						.getByResolution(res);
				if (rule == null)
					rule = deviceResolutionRuleService.getDefaultResolution();
				String r = MirrorUtils.getFieldValue(rule,
						DeviceResolutionRule.class, ru).toString();
				if (r.indexOf("*") <= 0) {// 格式错误
					rule = deviceResolutionRuleService.getDefaultResolution();
					r = MirrorUtils.getFieldValue(rule,
							DeviceResolutionRule.class, ru).toString();
				}
				PicConfig picc = getPicRes(r.split("\\*")[0], r.split("\\*")[1]);
				cachePicConfig(Consts.REDIS_PIC_RES + res + ":" + ru, jedis,
						picc);
				return picc;
			} else {
				PicConfig picc = getPicRes(w, h);
				return picc;
			}
		} catch (Exception e) {// 任何异常都返回默认
			String r = MirrorUtils.getFieldValue(
					deviceResolutionRuleService.getDefaultResolution(),
					DeviceResolutionRule.class, ru).toString();
			PicConfig picc = getPicRes(r.split("\\*")[0], r.split("\\*")[1]);
			return picc;
		} finally {
			RedisManager.returnJedis(jedis);
		}
	}

	private void cachePicConfig(String key, Jedis jedis, PicConfig picc) {
		jedis.hset(key, "w", picc.getWidth());
		jedis.hset(key, "h", picc.getHeight());
	}

	private PicConfig getPicRes(String w, String h) {
		PicConfig picc = new PicConfig("1", w, h);
		return picc;
	}

	public String getQiniuPrivateUrl(String url, PicConfig picConfig) {
		if (StringUtils.isEmpty(url))
			return url;
		Jedis jedis = null;
		try {
			jedis = RedisManager.getJedis();// cache
			if (App.getQiNiuBucketIsPublic()) {
				return url;
			} else {
				String key = "";
				String prefix = App.getQiNiuPrefix();
				if (url.indexOf(prefix) > 0) {
					key = url.substring(url.indexOf(prefix));
					String cacheKey = Consts.REDIS_PIC_ETOKEN + key;
					if (picConfig != null) {
						cacheKey += ":" + picConfig.getWidth() + "*"
								+ picConfig.getHeight();
					}
					String eToken = jedis.get(cacheKey);
					if (eToken != null) {
						return url + "?" + eToken;
					} else {
						if (picConfig != null) {
							url = url
									+ (picConfig == null ? "" : "?"
											+ picConfig.getConfigStr());
						}
						String qiniuPrivateUrl = auth.privateDownloadUrl(url);
						String nEandToken = qiniuPrivateUrl.split("\\?")[1];
						jedis.set(cacheKey, nEandToken);// cache
														// "mg:pic:etoken:test/196011.jpg"
						jedis.expire(cacheKey, EXPIRE_TIME);// expire 3500
															// seconds
						return qiniuPrivateUrl;
					}
				} else
					return url;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		} finally {
			RedisManager.returnJedis(jedis);
		}
	}

	public String getQiniuPrivateUrl(String url, String res, String rule) {
		return this.getQiniuPrivateUrl(url, this.getPicConfig(res, rule));
	}

	public String getQiniuPrivateUrl(String url, String token) {
		return this.getQiniuPrivateUrl(url, token, null);
	}

	public String getQiniuPrivateUrl(String url) {
		return this.getQiniuPrivateUrl(url, null, null);
	}

}
