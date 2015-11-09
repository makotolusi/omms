package m.w.chart.fcf.stacked;

import m.w.chart.fcf.Chartable;

public class StackedArea2D extends StackedChart<StackedArea2D> implements Chartable {
    public StackedArea2D alternateHGridAlpha(Integer alternateHGridAlpha){
        attr.put("alternateHGridAlpha", alternateHGridAlpha);
        return this;
    }

    public StackedArea2D alternateHGridColor(String alternateHGridColor){
        attr.put("alternateHGridColor", alternateHGridColor);
        return this;
    }

    public StackedArea2D alternateVGridAlpha(Integer alternateVGridAlpha){
        attr.put("alternateVGridAlpha", alternateVGridAlpha);
        return this;
    }

    public StackedArea2D alternateVGridColor(String alternateVGridColor){
        attr.put("alternateVGridColor", alternateVGridColor);
        return this;
    }

    public StackedArea2D areaAlpha(Integer areaAlpha){
        attr.put("areaAlpha", areaAlpha);
        return this;
    }

    public StackedArea2D areaBorderColor(String areaBorderColor){
        attr.put("areaBorderColor", areaBorderColor);
        return this;
    }

    public StackedArea2D areaBorderThickness(Integer areaBorderThickness){
        attr.put("areaBorderThickness", areaBorderThickness);
        return this;
    }

    public StackedArea2D canvasBgAlpha(Integer canvasBgAlpha){
        attr.put("canvasBgAlpha", canvasBgAlpha);
        return this;
    }

    public StackedArea2D canvasBorderColor(String canvasBorderColor){
        attr.put("canvasBorderColor", canvasBorderColor);
        return this;
    }

    public StackedArea2D canvasBorderThickness(Integer canvasBorderThickness){
        attr.put("canvasBorderThickness", canvasBorderThickness);
        return this;
    }

    public StackedArea2D numVDivLines(Integer numVDivLines){
        attr.put("numVDivLines", numVDivLines);
        return this;
    }

    public StackedArea2D rotateNames(Boolean rotateNames){
        attr.put("rotateNames", rotateNames);
        return this;
    }

    public StackedArea2D showAlternateHGridColor(Boolean showAlternateHGridColor){
        attr.put("showAlternateHGridColor", showAlternateHGridColor);
        return this;
    }

    public StackedArea2D showAlternateVGridColor(Boolean showAlternateVGridColor){
        attr.put("showAlternateVGridColor", showAlternateVGridColor);
        return this;
    }

    public StackedArea2D showAreaBorder(Boolean showAreaBorder){
        attr.put("showAreaBorder", showAreaBorder);
        return this;
    }

    public StackedArea2D VDivLineAlpha(Integer VDivLineAlpha){
        attr.put("VDivLineAlpha", VDivLineAlpha);
        return this;
    }

    public StackedArea2D VDivLineColor(String VDivLineColor){
        attr.put("VDivLineColor", VDivLineColor);
        return this;
    }

    public StackedArea2D VDivLineThickness(Integer VDivLineThickness){
        attr.put("VDivLineThickness", VDivLineThickness);
        return this;
    }

    public StackedArea2D zeroPlaneThickness(Integer zeroPlaneThickness){
        attr.put("zeroPlaneThickness", zeroPlaneThickness);
        return this;
    }

}
