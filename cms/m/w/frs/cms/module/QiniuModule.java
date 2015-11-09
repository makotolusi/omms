package m.w.frs.cms.module;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import m.w.App;

import org.joda.time.DateTime;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;


@IocBean
@At("/qiniu")
public class QiniuModule {
	private Auth auth = Auth.create(App.getQiNiuAccessKey(), App.getQiNiuSecretKey());

	@At
	@Ok("json")
	public String uptoken() {
		return auth.uploadToken(App.getQiNiuBucket());
	}
	
	@At
	@Ok("json")
	public String downloadPath(String key) {
		String url = "http://".concat(App.getQiNiuDomain()).concat("/").concat(key);
		if (App.getQiNiuBucketIsPublic()) {
			return url;
		} else {
			return auth.privateDownloadUrl(url);
		}
	}
	
//	@At("/upload")
//	@POST
//	@Ok("json")
//	@AdaptBy(type = UploadAdaptor.class,args = { "/uploadTemp", "8192", "UTF-8", "10" })  
//	public Object qiniuUpload(@Param("uploadFile") TempFile  f,HttpServletRequest req,  ServletContext context) {
//		HashMap<String, String> result = new HashMap<String, String>();
//		UploadManager uploadManager = new UploadManager();
//		Random random = new Random(DateTime.now().getMillis());
//		String key = "test/" + String.valueOf(random.nextLong()).replace("-", "").substring(1,5)+ f.getFile().getName();
//		try {
//			Response res = uploadManager.put(f.getFile(), key, uptoken());
//			DefaultPutRet ret = res.jsonToObject(DefaultPutRet.class);
//			if (!res.isOK()) {
//				throw new QiniuException(res);
//			} else {
//				result.put("key", ret.key);
//				result.put("url", downloadPath(ret.key));
//			}
//		} catch (QiniuException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			result.put("err", e.getMessage());
//		} 
//		return result;
//	}
	
	@At("/delete")
	@Ok("json")
	public Object qiniuDelete(String key) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		BucketManager bucketManager = new BucketManager(auth);
		try {
			BucketManager.Batch ops = new BucketManager.Batch().delete(App.getQiNiuBucket(), key
					+ "");
			Response re= bucketManager.batch(ops);
			result.put("success", true);
			result.put("status", re.statusCode);
//			bucketManager.delete(App.getQiNiuBucket(), URLEncoder.encode( "82050C.jpg", "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			result.put("failure", e.getMessage());
		}
		return result;
	}
	
	public Object qiniuDeleteAll() {
		HashMap<String, String> result = new HashMap<String, String>();
		BucketManager bucketManager = new BucketManager(auth);
		BucketManager.FileListIterator it = bucketManager.createFileListIterator(App.getQiNiuBucket(), "", 100, null);
		BucketManager.Batch ops = new BucketManager.Batch();
		while (it.hasNext()) {
		    FileInfo[] items = it.next();
		    if (items.length > 1) {
		    	for (FileInfo item : items) {
		    		ops.delete(App.getQiNiuBucket(), item.key);
		    	}
		    }
		}
		try {
			bucketManager.batch(ops);
		} catch (QiniuException e) {
			e.printStackTrace();
			result.put("err", e.getMessage());
		}
		return result;
	}
}
