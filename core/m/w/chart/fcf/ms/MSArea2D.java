package m.w.chart.fcf.ms;

import m.w.chart.fcf.Chartable;

public class MSArea2D extends MultiSeriesChart<MSArea2D> implements Chartable {
    public MSArea2D alternateHGridAlpha(Integer alternateHGridAlpha){
        attr.put("alternateHGridAlpha", alternateHGridAlpha);
        return this;
    }

    public MSArea2D alternateHGridColor(String alternateHGridColor){
        attr.put("alternateHGridColor", alternateHGridColor);
        return this;
    }

    public MSArea2D alternateVGridAlpha(Integer alternateVGridAlpha){
        attr.put("alternateVGridAlpha", alternateVGridAlpha);
        return this;
    }

    public MSArea2D alternateVGridColor(String alternateVGridColor){
        attr.put("alternateVGridColor", alternateVGridColor);
        return this;
    }

    public MSArea2D areaAlpha(Integer areaAlpha){
        attr.put("areaAlpha", areaAlpha);
        return this;
    }

    public MSArea2D areaBorderColor(String areaBorderColor){
        attr.put("areaBorderColor", areaBorderColor);
        return this;
    }

    public MSArea2D areaBorderThickness(Integer areaBorderThickness){
        attr.put("areaBorderThickness", areaBorderThickness);
        return this;
    }

    public MSArea2D canvasBgAlpha(Integer canvasBgAlpha){
        attr.put("canvasBgAlpha", canvasBgAlpha);
        return this;
    }

    public MSArea2D canvasBorderColor(String canvasBorderColor){
        attr.put("canvasBorderColor", canvasBorderColor);
        return this;
    }

    public MSArea2D canvasBorderThickness(Integer canvasBorderThickness){
        attr.put("canvasBorderThickness", canvasBorderThickness);
        return this;
    }

    public MSArea2D numVDivLines(Number numVDivLines){
        attr.put("numVDivLines", numVDivLines);
        return this;
    }

    public MSArea2D rotateNames(Boolean rotateNames){
        attr.put("rotateNames", rotateNames);
        return this;
    }

    public MSArea2D showAlternateHGridColor(Boolean showAlternateHGridColor){
        attr.put("showAlternateHGridColor", showAlternateHGridColor);
        return this;
    }

    public MSArea2D showAlternateVGridColor(Boolean showAlternateVGridColor){
        attr.put("showAlternateVGridColor", showAlternateVGridColor);
        return this;
    }

    public MSArea2D showAreaBorder(Boolean showAreaBorder){
        attr.put("showAreaBorder", showAreaBorder);
        return this;
    }

    public MSArea2D VDivLineAlpha(Integer VDivLineAlpha){
        attr.put("VDivLineAlpha", VDivLineAlpha);
        return this;
    }

    public MSArea2D VDivLineColor(String VDivLineColor){
        attr.put("VDivLineColor", VDivLineColor);
        return this;
    }

    public MSArea2D VDivLineThickness(Integer VDivLineThickness){
        attr.put("VDivLineThickness", VDivLineThickness);
        return this;
    }

    public MSArea2D yAxisMaxvalue(Number yAxisMaxvalue){
        attr.put("yAxisMaxvalue", yAxisMaxvalue);
        return this;
    }

    public MSArea2D yAxisMinvalue(Number yAxisMinvalue){
        attr.put("yAxisMinvalue", yAxisMinvalue);
        return this;
    }

    public MSArea2D yAxisName(String yAxisName){
        attr.put("yAxisName", yAxisName);
        return this;
    }

    public MSArea2D zeroPlaneThickness(Integer zeroPlaneThickness){
        attr.put("zeroPlaneThickness", zeroPlaneThickness);
        return this;
    }

}
