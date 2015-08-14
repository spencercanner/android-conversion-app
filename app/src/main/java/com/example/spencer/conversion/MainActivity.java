package com.example.spencer.conversion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    private TextView tspTextView, tbsTextView, cupTextView, ozTextView, pintTextView, quartTextView, gallonTextView,
    poundTextView, mlTextView, literTextView, mgTextView, kgTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();
        addListenerToNumberEntry();
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initializeTextViews();
    }

    public void initializeTextViews(){
        tspTextView = (TextView) findViewById(R.id.tsp_text_view);
        tbsTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ozTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        mlTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        mgTextView = (TextView) findViewById(R.id.mg_text_view);
        kgTextView = (TextView) findViewById(R.id.kg_text_view);
    }

    public void addItemsToUnitTypeSpinner(){

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.conversion_types, android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner(){

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                checkIfConvertingFromTsp(itemSelectedInSpinner);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addListenerToNumberEntry(){

        EditText amountTextView = (EditText) findViewById(R.id.amount_text_view);
        amountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Spinner spinner = (Spinner)findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Spinner spinner = (Spinner)findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text);
            }
            @Override
            public void afterTextChanged(Editable s) {
                Spinner spinner = (Spinner)findViewById(R.id.unit_type_spinner);
                String text = spinner.getSelectedItem().toString();
                checkIfConvertingFromTsp(text);
            }
        });


    }

    public void checkIfConvertingFromTsp(String currUnit){
        if(currUnit.equals("tsp")){
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        }
        else {
            if (currUnit.equals("tbs")){
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
            }
            else if(currUnit.equals("cup")){
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            }
            else if(currUnit.equals("oz")){
                updateUnitTypeUsingOther(Quantity.Unit.oz);
            }
            else if(currUnit.equals("pint")){
                updateUnitTypeUsingOther(Quantity.Unit.pint);
            }
            else if(currUnit.equals("quart")){
                updateUnitTypeUsingOther(Quantity.Unit.quart);
            }
            else if(currUnit.equals("gallon")){
                updateUnitTypeUsingOther(Quantity.Unit.gallon);
            }
            else if(currUnit.equals("pound")){
                updateUnitTypeUsingOther(Quantity.Unit.pound);
            }
            else if(currUnit.equals("ml")){
                updateUnitTypeUsingOther(Quantity.Unit.ml);
            }
            else if(currUnit.equals("liter")){
                updateUnitTypeUsingOther(Quantity.Unit.liter);
            }
            else if(currUnit.equals("mg")){
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            }
            else {
                updateUnitTypeUsingOther(Quantity.Unit.kg);
            }
        }
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit){
        double doubleToConvert;
        if(amountTextView.getText().toString().trim().length() == 0) {
            doubleToConvert = 0;
        }
        else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        String teaspoonValueAndUnit = doubleToConvert + " tsp";
        tspTextView.setText(teaspoonValueAndUnit);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.tbs, tbsTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.oz, ozTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.ml, mlTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.mg, mgTextView);
        updateUnitTextUsingTsp(doubleToConvert, Quantity.Unit.kg, kgTextView);
    }

    public void updateUnitTextUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo, TextView text){
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        text.setText(tempUnit);
    }


    public void updateUnitTypeUsingOther(Quantity.Unit unit){
        double doubleToConvert;
        if(amountTextView.getText().toString().trim().length() == 0) {
            doubleToConvert = 0;
        }
        else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, unit);
        String valueInTeaspoons = currentQuantitySelected.to(Quantity.Unit.tsp).toString();
        tspTextView.setText(valueInTeaspoons);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.tbs, tbsTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.oz, ozTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.ml, mlTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.mg, mgTextView);
        updateUnitTextFieldsUsingTsp(doubleToConvert, unit, Quantity.Unit.kg, kgTextView);

        if(unit.name().equals(currentQuantitySelected.unit.name())){
            String currentUnitTextView = doubleToConvert + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";
            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextView);
        }
    }

    public void updateUnitTextFieldsUsingTsp (double doubleToConvert, Quantity.Unit currUnit, Quantity.Unit prefUnit, TextView target ){
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currUnit);
        String tempTextView = currentQuantitySelected.to(Quantity.Unit.tsp).to(prefUnit).toString();
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
