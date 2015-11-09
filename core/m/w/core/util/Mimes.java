package m.w.core.util;

import java.io.File;
import java.util.Collection;

import eu.medsea.mimeutil.MimeUtil;

public abstract class Mimes {
    static {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
    }

    public static String mime(File file) {
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);
        if(mimeTypes != null && mimeTypes.size() > 0){
            return mimeTypes.iterator().next().toString();
        }
        return "";
    }

}
