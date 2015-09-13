package com.example.spencer.conversion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;
    private Spinner countrySpinner;
    private EditText amountTextView;
    private String location;
    private TextView tspTextView, tbsTextView, cupTextView, ozTextView, pintTextView, quartTextView, gallonTextView,
            mlTextView, literTextView, shotTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemsToUnitTypeSpinner();
        addItemsToCountrySpinner();
        addListenerToUnitTypeSpinner();
        addListenerToCountrySpinner();
        addListenerToNumberEntry();
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initializeTextViews();
    }

    public void initializeTextViews() {
        tspTextView = (TextView) findViewById(R.id.tsp_text_view);
        tbsTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ozTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        mlTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        shotTextView = (TextView) findViewById(R.id.shot_text_view);
    }

    public void addItemsToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.conversion_types, android.R.layout.simple_spinner_item);
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addItemsToCountrySpinner() {
        countrySpinner = (Spinner) findViewById(R.id.country_spinner);
        ArrayAdapter<CharSequence> countrySpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        countrySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countrySpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                Spinner countrySpinner = (Spinner) findViewById(R.id.country_spinner);
                String country = countrySpinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(itemSelectedInSpinner, country);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addListenerToCountrySpinner() {
        countrySpinner = (Spinner) findViewById(R.id.country_spinner);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String countrySelectedInSpinner = parent.getItemAtPosition(position).toString();
                Spinner spinner = (Spinner) findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text, countrySelectedInSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addListenerToNumberEntry() {

        EditText amountTextView = (EditText) findViewById(R.id.amount_text_view);
        amountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Spinner spinner = (Spinner) findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                Spinner countrySpinner = (Spinner) findViewById(R.id.country_spinner);
                String country = countrySpinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text, country);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Spinner spinner = (Spinner) findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                Spinner countrySpinner = (Spinner) findViewById(R.id.country_spinner);
                String country = countrySpinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text, country);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Spinner spinner = (Spinner) findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                Spinner countrySpinner = (Spinner) findViewById(R.id.country_spinner);
                String country = countrySpinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text, country);
            }
        });


    }

    public void checkIfConvertingFromTsp(String currUnit, String country) {
        Log.d("COUNTRY", country);
        if (currUnit.equals("tsp")) {
            if(country.equals("CA"))
                updateCanUnitTypeUsingTsp();
            else
                updateUsaUnitTypeUsingTsp();
        } else {
            if (country.equals("CA")) {
                switch (currUnit) {
                    case "tbs":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.tbs);
                        break;
                    case "cup":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.cup);
                        break;
                    case "oz":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.oz);
                        break;
                    case "pint":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.pint);
                        break;
                    case "quart":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.quart);
                        break;
                    case "gallon":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.gallon);
                        break;
                    case "ml":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.ml);
                        break;
                    case "liter":
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.liter);
                        break;
                    default:
                        updateCanUnitTypeUsingOther(QuantityCAN.Unit.shot);
                        break;
                }
            }
            else {
                switch (currUnit) {
                    case "tbs":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.tbs);
                        break;
                    case "cup":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.cup);
                        break;
                    case "oz":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.oz);
                        break;
                    case "pint":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.pint);
                        break;
                    case "quart":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.quart);
                        break;
                    case "gallon":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.gallon);
                        break;
                    case "ml":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.ml);
                        break;
                    case "liter":
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.liter);
                        break;
                    default:
                        updateUsaUnitTypeUsingOther(QuantityUSA.Unit.shot);
                        break;
                }
            }
        }
    }

    public void updateCanUnitTypeUsingTsp() {
        double doubleToConvert;
        if (amountTextView.getText().toString().trim().length() == 0 || amountTextView.getText().toString().equals(".")) {
            doubleToConvert = 0;
        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String doubleString = df.format(doubleToConvert);
        doubleString = doubleString.indexOf(".") < 0 ? doubleString : doubleString.replaceAll("0*$", "").replaceAll("\\.$", "");
        if (Double.parseDouble(doubleString) > 9007199254740990.0) {
            doubleString = "Overflow Error";
        }
        String teaspoonValueAndUnit = doubleString + " tsp";
        tspTextView.setText(teaspoonValueAndUnit);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.tbs, tbsTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.cup, cupTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.oz, ozTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.pint, pintTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.quart, quartTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.gallon, gallonTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.ml, mlTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.liter, literTextView);
        updateCanUnitTextUsingTsp(doubleToConvert, QuantityCAN.Unit.shot, shotTextView);
    }

    public void updateUsaUnitTypeUsingTsp() {
        double doubleToConvert;
        if (amountTextView.getText().toString().trim().length() == 0 || amountTextView.getText().toString().equals(".")) {
            doubleToConvert = 0;
        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String doubleString = df.format(doubleToConvert);
        doubleString = doubleString.indexOf(".") < 0 ? doubleString : doubleString.replaceAll("0*$", "").replaceAll("\\.$", "");
        if (Double.parseDouble(doubleString) > 9007199254740990.0) {
            doubleString = "Overflow Error";
        }
        String teaspoonValueAndUnit = doubleString + " tsp";
        tspTextView.setText(teaspoonValueAndUnit);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.tbs, tbsTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.cup, cupTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.oz, ozTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.pint, pintTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.quart, quartTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.gallon, gallonTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.ml, mlTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.liter, literTextView);
        updateUsaUnitTextUsingTsp(doubleToConvert, QuantityUSA.Unit.shot, shotTextView);
    }

    public void updateCanUnitTextUsingTsp(double doubleToConvert, QuantityCAN.Unit unitConvertingTo, TextView text) {
        QuantityCAN unitQuantity = new QuantityCAN(doubleToConvert, QuantityCAN.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        text.setText(tempUnit);
    }
    public void updateUsaUnitTextUsingTsp(double doubleToConvert, QuantityUSA.Unit unitConvertingTo, TextView text) {
        QuantityUSA unitQuantity = new QuantityUSA(doubleToConvert, QuantityUSA.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        text.setText(tempUnit);
    }


    public void updateCanUnitTypeUsingOther(QuantityCAN.Unit unit) {
        double doubleToConvert;
        if (amountTextView.getText().toString().trim().length() == 0 || amountTextView.getText().toString().equals(".")) {
            doubleToConvert = 0;
        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        QuantityCAN currentQuantitySelected = new QuantityCAN(doubleToConvert, unit);
        String valueInTeaspoons = currentQuantitySelected.to(QuantityCAN.Unit.tsp).toString();
        tspTextView.setText(valueInTeaspoons);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.tbs, tbsTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.cup, cupTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.oz, ozTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.pint, pintTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.quart, quartTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.gallon, gallonTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.ml, mlTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.liter, literTextView);
        updateCanUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityCAN.Unit.shot, shotTextView);

        if (unit.name().equals(currentQuantitySelected.unit.name())) {
            DecimalFormat df = new DecimalFormat("0.00");
            String doubleString = df.format(doubleToConvert);
            doubleString = doubleString.indexOf(".") < 0 ? doubleString : doubleString.replaceAll("0*$", "").replaceAll("\\.$", "");
            if (Double.parseDouble(doubleString) > 9007199254740990.0) {
                doubleString = "Overflow Error";
            }
            String currentUnitTextView = doubleString + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";
            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextView);
        }
    }

    public void updateUsaUnitTypeUsingOther(QuantityUSA.Unit unit) {
        double doubleToConvert;
        if (amountTextView.getText().toString().trim().length() == 0 || amountTextView.getText().toString().equals(".")) {
            doubleToConvert = 0;
        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        QuantityUSA currentQuantitySelected = new QuantityUSA(doubleToConvert, unit);
        String valueInTeaspoons = currentQuantitySelected.to(QuantityUSA.Unit.tsp).toString();
        tspTextView.setText(valueInTeaspoons);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.tbs, tbsTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.cup, cupTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.oz, ozTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.pint, pintTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.quart, quartTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.gallon, gallonTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.ml, mlTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.liter, literTextView);
        updateUsaUnitTextFieldsUsingTsp(doubleToConvert, unit, QuantityUSA.Unit.shot, shotTextView);

        if (unit.name().equals(currentQuantitySelected.unit.name())) {
            DecimalFormat df = new DecimalFormat("0.00");
            String doubleString = df.format(doubleToConvert);
            doubleString = doubleString.indexOf(".") < 0 ? doubleString : doubleString.replaceAll("0*$", "").replaceAll("\\.$", "");
            if (Double.parseDouble(doubleString) > 9007199254740990.0) {
                doubleString = "Overflow Error";
            }
            String currentUnitTextView = doubleString + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";
            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextView);
        }
    }

    public void updateCanUnitTextFieldsUsingTsp(double doubleToConvert, QuantityCAN.Unit currUnit, QuantityCAN.Unit prefUnit, TextView target) {
        QuantityCAN currentQuantitySelected = new QuantityCAN(doubleToConvert, currUnit);
        String tempTextView = currentQuantitySelected.to(QuantityCAN.Unit.tsp).to(prefUnit).toString();
        target.setText(tempTextView);
    }

    public void updateUsaUnitTextFieldsUsingTsp(double doubleToConvert, QuantityUSA.Unit currUnit, QuantityUSA.Unit prefUnit, TextView target) {
        QuantityUSA currentQuantitySelected = new QuantityUSA(doubleToConvert, currUnit);
        String tempTextView = currentQuantitySelected.to(QuantityUSA.Unit.tsp).to(prefUnit).toString();
        target.setText(tempTextView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
