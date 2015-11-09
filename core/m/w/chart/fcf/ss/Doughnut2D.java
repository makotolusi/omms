package m.w.chart.fcf.ss;

import m.w.chart.fcf.Chartable;

public class Doughnut2D extends SingleSeriesChart<Doughnut2D> implements Chartable {
    public Doughnut2D animation(Boolean animation){
        attr.put("animation", animation);
        return this;
    }

    public Doughnut2D nameTBDistance(Integer nameTBDistance){
        attr.put("nameTBDistance", nameTBDistance);
        return this;
    }

    public Doughnut2D pieBorderAlpha(Integer pieBorderAlpha){
        attr.put("pieBorderAlpha", pieBorderAlpha);
        return this;
    }

    public Doughnut2D pieBorderThickness(Integer pieBorderThickness){
        attr.put("pieBorderThickness", pieBorderThickness);
        return this;
    }

    public Doughnut2D pieFillAlpha(Integer pieFillAlpha){
        attr.put("pieFillAlpha", pieFillAlpha);
        return this;
    }

    public Doughnut2D pieRadius(Number pieRadius){
        attr.put("pieRadius", pieRadius);
        return this;
    }

    public Doughnut2D shadowColor(String shadowColor){
        attr.put("shadowColor", shadowColor);
        return this;
    }

    public Doughnut2D showPercentageInLabel(Boolean showPercentageInLabel){
        attr.put("showPercentageInLabel", showPercentageInLabel);
        return this;
    }

    public Doughnut2D showPercentageValues(Boolean showPercentageValues){
        attr.put("showPercentageValues", showPercentageValues);
        return this;
    }

    public Doughnut2D showShadow(Boolean showShadow){
        attr.put("showShadow", showShadow);
        return this;
    }

    public Doughnut2D showValues(Boolean showValues){
        attr.put("showValues", showValues);
        return this;
    }

    public Doughnut2D slicingDistance(Integer slicingDistance){
        attr.put("slicingDistance", slicingDistance);
        return this;
    }

}
