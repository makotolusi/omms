package m.w.chart.fcf.ms;

import m.w.chart.fcf.Chartable;

public class MSBar2D extends MultiSeriesChart<MSBar2D> implements Chartable {
    public MSBar2D alternateHGridAlpha(Integer alternateHGridAlpha){
        attr.put("alternateHGridAlpha", alternateHGridAlpha);
        return this;
    }

    public MSBar2D alternateHGridColor(String alternateHGridColor){
        attr.put("alternateHGridColor", alternateHGridColor);
        return this;
    }

    public MSBar2D alternateVGridAlpha(Integer alternateVGridAlpha){
        attr.put("alternateVGridAlpha", alternateVGridAlpha);
        return this;
    }

    public MSBar2D alternateVGridColor(String alternateVGridColor){
        attr.put("alternateVGridColor", alternateVGridColor);
        return this;
    }

    public MSBar2D canvasBgAlpha(Integer canvasBgAlpha){
        attr.put("canvasBgAlpha", canvasBgAlpha);
        return this;
    }

    public MSBar2D canvasBorderColor(String canvasBorderColor){
        attr.put("canvasBorderColor", canvasBorderColor);
        return this;
    }

    public MSBar2D canvasBorderThickness(Integer canvasBorderThickness){
        attr.put("canvasBorderThickness", canvasBorderThickness);
        return this;
    }

    public MSBar2D hDivLineAlpha(Integer hDivLineAlpha){
        attr.put("hDivLineAlpha", hDivLineAlpha);
        return this;
    }

    public MSBar2D hDivLineColor(String hDivLineColor){
        attr.put("hDivLineColor", hDivLineColor);
        return this;
    }

    public MSBar2D hDivLineThickness(Integer hDivLineThickness){
        attr.put("hDivLineThickness", hDivLineThickness);
        return this;
    }

    public MSBar2D numHDivLines(Number numHDivLines){
        attr.put("numHDivLines", numHDivLines);
        return this;
    }

    public MSBar2D showAlternateHGridColor(Boolean showAlternateHGridColor){
        attr.put("showAlternateHGridColor", showAlternateHGridColor);
        return this;
    }

    public MSBar2D showAlternateVGridColor(Boolean showAlternateVGridColor){
        attr.put("showAlternateVGridColor", showAlternateVGridColor);
        return this;
    }

    public MSBar2D showBarShadow(Boolean showBarShadow){
        attr.put("showBarShadow", showBarShadow);
        return this;
    }

    public MSBar2D yAxisMaxvalue(Number yAxisMaxvalue){
        attr.put("yAxisMaxvalue", yAxisMaxvalue);
        return this;
    }

    public MSBar2D yAxisMinvalue(Number yAxisMinvalue){
        attr.put("yAxisMinvalue", yAxisMinvalue);
        return this;
    }

    public MSBar2D yAxisName(String yAxisName){
        attr.put("yAxisName", yAxisName);
        return this;
    }

    public MSBar2D zeroPlaneThickness(Integer zeroPlaneThickness){
        attr.put("zeroPlaneThickness", zeroPlaneThickness);
        return this;
    }

}
