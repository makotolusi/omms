package m.w.chart.fcf.ms;

import m.w.chart.fcf.Chartable;

public class MSColumn2D extends MultiSeriesChart<MSColumn2D> implements Chartable{
    public static MSColumn2D born(){
        return new MSColumn2D();
    }

    public MSColumn2D alternateHGridAlpha(Integer alternateHGridAlpha){
        attr.put("alternateHGridAlpha", alternateHGridAlpha);
        return this;
    }

    public MSColumn2D alternateHGridColor(String alternateHGridColor){
        attr.put("alternateHGridColor", alternateHGridColor);
        return this;
    }

    public MSColumn2D alternateVGridAlpha(Integer alternateVGridAlpha){
        attr.put("alternateVGridAlpha", alternateVGridAlpha);
        return this;
    }

    public MSColumn2D alternateVGridColor(String alternateVGridColor){
        attr.put("alternateVGridColor", alternateVGridColor);
        return this;
    }

    public MSColumn2D canvasBgAlpha(Integer canvasBgAlpha){
        attr.put("canvasBgAlpha", canvasBgAlpha);
        return this;
    }

    public MSColumn2D canvasBorderColor(String canvasBorderColor){
        attr.put("canvasBorderColor", canvasBorderColor);
        return this;
    }

    public MSColumn2D canvasBorderThickness(Integer canvasBorderThickness){
        attr.put("canvasBorderThickness", canvasBorderThickness);
        return this;
    }

    public MSColumn2D numVDivLines(Number numVDivLines){
        attr.put("numVDivLines", numVDivLines);
        return this;
    }

    public MSColumn2D rotateNames(Boolean rotateNames){
        attr.put("rotateNames", rotateNames);
        return this;
    }

    public MSColumn2D showAlternateHGridColor(Boolean showAlternateHGridColor){
        attr.put("showAlternateHGridColor", showAlternateHGridColor);
        return this;
    }

    public MSColumn2D showAlternateVGridColor(Boolean showAlternateVGridColor){
        attr.put("showAlternateVGridColor", showAlternateVGridColor);
        return this;
    }

    public MSColumn2D showColumnShadow(Boolean showColumnShadow){
        attr.put("showColumnShadow", showColumnShadow);
        return this;
    }

    public MSColumn2D VDivLineAlpha(Integer VDivLineAlpha){
        attr.put("VDivLineAlpha", VDivLineAlpha);
        return this;
    }

    public MSColumn2D VDivLineColor(String VDivLineColor){
        attr.put("VDivLineColor", VDivLineColor);
        return this;
    }

    public MSColumn2D VDivLineThickness(Integer VDivLineThickness){
        attr.put("VDivLineThickness", VDivLineThickness);
        return this;
    }

    public MSColumn2D yAxisMaxvalue(Number yAxisMaxvalue){
        attr.put("yAxisMaxvalue", yAxisMaxvalue);
        return this;
    }

    public MSColumn2D yAxisMinvalue(Number yAxisMinvalue){
        attr.put("yAxisMinvalue", yAxisMinvalue);
        return this;
    }

    public MSColumn2D yAxisName(String yAxisName){
        attr.put("yAxisName", yAxisName);
        return this;
    }

    public MSColumn2D zeroPlaneThickness(Integer zeroPlaneThickness){
        attr.put("zeroPlaneThickness", zeroPlaneThickness);
        return this;
    }
}
