package m.w.chart.fcf;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

public class FusionChartsFreeView implements View {
    private static final String BOM = new String(new byte[]{ (byte)0xEF, (byte)0xBB, (byte)0xBF });
    private static final String UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp, Object obj)
            throws Throwable {
        if(obj instanceof Chartable){
            resp.setHeader("Cache-Control", "no-cache");
            resp.setContentType("application/xml");
            
            Chartable chart = (Chartable)obj;
            PrintWriter writer = resp.getWriter();
            writer.write(BOM);
            writer.write(UTF8);
            writer.write(chart.toXml());
            resp.flushBuffer();
        }
    }
}
