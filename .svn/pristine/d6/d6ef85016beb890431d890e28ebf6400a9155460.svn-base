package m.w.sys.domain;

import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.name.Rename;

/**
 * 上传配置信息
 * 
 * @author WenWu
 * 
 */
public class UploadConf {
    /** 允许上传的最大文件大小(字节)，仅值大于0时有效 */
    private Long maxSize;

    /** 允许上传的最小文件大小（字节），仅值大于0时有效 */
    private Long minSize;

    /** 上传图片允许的最大宽度（像素），仅值大于0时有效 */
    private Integer maxWidth;

    /** 上传图片允许的最小宽度（像素），仅值大于0时有效 */
    private Integer minWidth;

    /** 上传图片允许的最大高度（像素），仅值大于0时有效 */
    private Integer maxHeight;

    /** 上传图片允许的最小高度（像素），仅值大于0时有效 */
    private Integer minHeight;

    /** 是否制作缩略图 */
    private boolean makeThumb = false;

    /** 是否保持比例缩放，否则为固定尺寸缩放，默认是的 */
    private boolean keepAspectRatio = true;

    /** 缩略图的重命名策略 */
    private Rename rename = Rename.SUFFIX_HYPHEN_THUMBNAIL;

    /** 如果制作缩略图，小缩略图的宽度，默认200 */
    private Integer smallThumbWidth = 200;

    /** 如果制作缩略图，小缩略图的高度，默认150 */
    private Integer smallThumbHeight = 150;

    /** 如果制作缩略图，大缩略图的宽度默认640 */
    private Integer bigThumbWidth = 640;

    /** 如果制作缩略图，大缩略图的高度，默认480 */
    private Integer bigThumbHeight = 480;

    /** 上传后的图片是否带水印 */
    private boolean makeWatermark = false;

    /** 图片的水印 */
    private Watermark watermark;

    /** 是否制作图片标题 */
    private boolean makeCaption = false;

    /** 图片标题信息 */
    private Caption caption;

    // =========================================================================

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public Long getMinSize() {
        return minSize;
    }

    public void setMinSize(Long minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public boolean isMakeThumb() {
        return makeThumb;
    }

    public void setMakeThumb(boolean makeThumb) {
        this.makeThumb = makeThumb;
    }

    public boolean isKeepAspectRatio() {
        return keepAspectRatio;
    }

    public void setKeepAspectRatio(boolean keepAspectRatio) {
        this.keepAspectRatio = keepAspectRatio;
    }

    public Rename getRename() {
        return rename;
    }

    public void setRename(Rename rename) {
        this.rename = rename;
    }

    public Integer getSmallThumbWidth() {
        return smallThumbWidth;
    }

    public void setSmallThumbWidth(Integer smallThumbWidth) {
        this.smallThumbWidth = smallThumbWidth;
    }

    public Integer getSmallThumbHeight() {
        return smallThumbHeight;
    }

    public void setSmallThumbHeight(Integer smallThumbHeight) {
        this.smallThumbHeight = smallThumbHeight;
    }

    public Integer getBigThumbWidth() {
        return bigThumbWidth;
    }

    public void setBigThumbWidth(Integer bigThumbWidth) {
        this.bigThumbWidth = bigThumbWidth;
    }

    public Integer getBigThumbHeight() {
        return bigThumbHeight;
    }

    public void setBigThumbHeight(Integer bigThumbHeight) {
        this.bigThumbHeight = bigThumbHeight;
    }

    public boolean isMakeWatermark() {
        return makeWatermark;
    }

    public void setMakeWatermark(boolean makeWatermark) {
        this.makeWatermark = makeWatermark;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public boolean isMakeCaption() {
        return makeCaption;
    }

    public void setMakeCaption(boolean makeCaption) {
        this.makeCaption = makeCaption;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

}
