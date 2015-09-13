package com.example.spencer.conversion;

import java.text.DecimalFormat;

public class QuantityUSA {

    final double value;
    final Unit unit;

    public static enum Unit{

        tsp(1.0d), tbs(0.33333333333d), cup(0.0208377361680544d), oz(0.1667230300980724d),
        pint(0.0104188680840272d), quart(0.0052094890896603d), gallon(0.0013023688319099d),
        ml(4.93d), liter(0.0049300d), shot(0.1111486867320482667d);

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

    public QuantityUSA(double value, Unit unit){

        super();
        this.value = value;
        this.unit = unit;
    }

    public QuantityUSA to(Unit newUnit){

        Unit oldUnit = this.unit;
        return new QuantityUSA(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)), newUnit);

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
