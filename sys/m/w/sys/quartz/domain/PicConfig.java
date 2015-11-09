package m.w.sys.quartz.domain;

/**
 * 示例
 *	http://developer.qiniu.com/resource/gogopher.jpg?imageView2/1/w/200/h/200hortEdge>	限定缩略图的长边最少为<LongEdge>，短边最少为<ShortEdge>，进行等比缩放，居中裁剪。如果只指定 w 参数或只指定 h 参数，表示长边短边限定为同样的值。同上模式4，但超出限定的矩形部分会被裁剪。
 * @author lusi
 *
 */
public class PicConfig {

	private String imageView="imageView2";
	private String mode;// imageview mode 
	private String configStr;
	private String width;
	private String height;
	
	public PicConfig(String resolution) {
		super();
		if(resolution.indexOf("*")>0){
			this.width=resolution.split("\\*")[0];
			this.height=resolution.split("\\*")[1];
			this.mode="1";
		}
//		else
//			throw new Exception("PicConfig init exception");
	}
	public PicConfig(String mode, String width, String height) {
		super();
		this.mode = mode;
		this.width = width;
		this.height = height;
	}
	public String getImageView() {
		return imageView;
	}
	public void setImageView(String imageView) {
		this.imageView = imageView;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getConfigStr() {
		StringBuffer sb=new StringBuffer();
		sb.append(imageView).append("/").append(mode).append("/");
		if(width!=null)
			sb.append("w/").append(width).append("/");
		if(height!=null)
			sb.append("h/").append(height).append("/");
		return sb.toString();
	}
	public void setConfigStr(String configStr) {
		this.configStr = configStr;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
