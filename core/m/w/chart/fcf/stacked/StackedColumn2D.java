package m.w.chart.fcf.stacked;

import m.w.chart.fcf.Chartable;

public class StackedColumn2D extends StackedChart<StackedColumn2D> implements Chartable {
    public StackedColumn2D alternateHGridAlpha(Integer alternateHGridAlpha){
        attr.put("alternateHGridAlpha", alternateHGridAlpha);
        return this;
    }

    public StackedColumn2D alternateHGridColor(String alternateHGridColor){
        attr.put("alternateHGridColor", alternateHGridColor);
        return this;
    }

    public StackedColumn2D canvasBgAlpha(Integer canvasBgAlpha){
        attr.put("canvasBgAlpha", canvasBgAlpha);
        return this;
    }

    public StackedColumn2D canvasBorderColor(String canvasBorderColor){
        attr.put("canvasBorderColor", canvasBorderColor);
        return this;
    }

    public StackedColumn2D canvasBorderThickness(Integer canvasBorderThickness){
        attr.put("canvasBorderThickness", canvasBorderThickness);
        return this;
    }

    public StackedColumn2D rotateNames(Boolean rotateNames){
        attr.put("rotateNames", rotateNames);
        return this;
    }

    public StackedColumn2D showAlternateHGridColor(Boolean showAlternateHGridColor){
        attr.put("showAlternateHGridColor", showAlternateHGridColor);
        return this;
    }

    public StackedColumn2D showColumnShadow(Boolean showColumnShadow){
        attr.put("showColumnShadow", showColumnShadow);
        return this;
    }

    public StackedColumn2D zeroPlaneThickness(Integer zeroPlaneThickness){
        attr.put("zeroPlaneThickness", zeroPlaneThickness);
        return this;
    }

}
