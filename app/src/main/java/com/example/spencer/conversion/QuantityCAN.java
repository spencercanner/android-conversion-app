package com.example.spencer.conversion;

import java.text.DecimalFormat;

public class QuantityCAN {

    final double value;
    final Unit unit;


    public static enum Unit{

        tsp(1.0d), tbs(0.33333333333d), cup(0.02d), oz(0.1759943681802182d),
        pint(0.0087987892865942d), quart(0.0043993946432971d), gallon(0.0010998462414954d),
        ml(5.0000000d), liter(0.0050000d), shot(0.1173295787868121333d);


        final static Unit baseUnit = tsp;

        final double byBaseUnit;

        private Unit(double inTsp){
            this.byBaseUnit = inTsp;
        }

        public double toBaseUnit(double value){

            return value / byBaseUnit;
        }

        public double fromBaseUnit(double value){
            return value * byBaseUnit;
        }
    }



    public QuantityCAN(double value, Unit unit){

        super();
        this.value = value;
        this.unit = unit;
    }

    public QuantityCAN to(Unit newUnit){

        Unit oldUnit = this.unit;
        return new QuantityCAN(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)), newUnit);

    }
    @Override
    public String toString(){

        DecimalFormat df = new DecimalFormat("0.00");
        String dString = df.format(value);
        dString = dString.indexOf(".") < 0 ? dString : dString.replaceAll("0*$", "").replaceAll("\\.$", "");
        if (Double.parseDouble(dString) > 9007199254740990.0){
            dString = "Overflow Error";
        }
        return dString + " " + unit.name();
    }
}
