package com.example.mini_project_resister_app;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
//    int currentBand = 0;
//    int resistorValue = 0;
    Spinner band1Spinner, band2Spinner, multiplierSpinner, toleranceSpinner;
    TextView display;
    Button calculateButton, resetButton;

    HashMap<String, Integer> bandValues;
    HashMap<String, Double> multiplierValues;
    HashMap<String, String> toleranceValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.tvDisplay);

        // Fetch color array from resources
        int[] colors = getResources().getIntArray(R.array.androidcolors);

        // Define Spinner items
        String[] bandOptions = getResources().getStringArray(R.array.band12_options);

        // Set up Spinners
        band1Spinner = findViewById(R.id.band1);
        band2Spinner = findViewById(R.id.band2);

        // Create and set adapters for each Spinner
        ColorSpinnerAdapter adapter = new ColorSpinnerAdapter(this, bandOptions, colors);
        band1Spinner.setAdapter(adapter);
        band2Spinner.setAdapter(adapter);

        // Repeat for the multiplier and tolerance spinners as needed
        multiplierSpinner = findViewById(R.id.multiplyBand);
        toleranceSpinner = findViewById(R.id.toleranceBand);

        // Apply the same adapter logic
        multiplierSpinner.setAdapter(new ColorSpinnerAdapter(this, getResources().getStringArray(R.array.multiplier_options), colors));
        toleranceSpinner.setAdapter(new ColorSpinnerAdapter(this, getResources().getStringArray(R.array.tolerance_options), colors));

        setupColorMappings();

        calculateButton = findViewById(R.id.buttonCalculate);
        // Set the calculate button listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResistorValue();
            }
        });
        //Set the reset button listener
        resetButton = findViewById(R.id.buttonReset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDisplay();
            }
        });
    }
    private void calculateResistorValue() {
        String band1Value = band1Spinner.getSelectedItem().toString();
        String band2Value = band2Spinner.getSelectedItem().toString();
        String multiplierValue = multiplierSpinner.getSelectedItem().toString();
        String toleranceValue = toleranceSpinner.getSelectedItem().toString();

        // Retrieve values based on selected colors
        int firstDigit = bandValues.get(band1Value);
        int secondDigit = bandValues.get(band2Value);
        double multiplier = multiplierValues.get(multiplierValue);
        String tolerance = toleranceValues.get(toleranceValue);

        // Calculate the resistor value
        int significantFigures = Integer.parseInt(firstDigit + "" + secondDigit);
        double resistorValue = significantFigures * multiplier;

        // Update the "Display" TextView with the calculated value
        String result = "Resistor Value: " + resistorValue + " Ω " + tolerance;
        display.setText(result);
    }

    private void setupColorMappings() {
        // Initialize the HashMaps with color codes and corresponding values

        // Band 1 and Band 2 mappings
        bandValues = new HashMap<>();
        bandValues.put("Black (0)", 0);
        bandValues.put("Brown (1)", 1);
        bandValues.put("Red (2)", 2);
        bandValues.put("Orange (3)", 3);
        bandValues.put("Yellow (4)", 4);
        bandValues.put("Green (5)", 5);
        bandValues.put("Blue (6)", 6);
        bandValues.put("Violet (7)", 7);
        bandValues.put("Gray (8)", 8);
        bandValues.put("White (9)", 9);

        // Multiplier mappings
        multiplierValues = new HashMap<>();
        multiplierValues.put("Black (1 Ω)", 1.0);
        multiplierValues.put("Brown (10 Ω)", 10.0);
        multiplierValues.put("Red (100 Ω)", 100.0);
        multiplierValues.put("Orange (1 kΩ)", 1000.0);
        multiplierValues.put("Yellow (10 kΩ)", 10000.0);
        multiplierValues.put("Green (100 kΩ)", 100000.0);
        multiplierValues.put("Blue (1 MΩ)", 1000000.0);
        multiplierValues.put("Gold (0.1 Ω)", 0.1);
        multiplierValues.put("Silver (0.01 Ω)", 0.01);

        // Tolerance mappings
        toleranceValues = new HashMap<>();
        toleranceValues.put("Brown (±1%)", "±1%");
        toleranceValues.put("Red (±2%)", "±2%");
        toleranceValues.put("Green (±0.5%)", "±0.5%");
        toleranceValues.put("Blue (±0.25%)", "±0.25%");
        toleranceValues.put("Violet (±0.1%)", "±0.1%");
        toleranceValues.put("Gray (±0.05%)", "±0.05%");
        toleranceValues.put("Gold (±5%)", "±5%");
        toleranceValues.put("Silver (±10%)", "±10%");
    }
    private void resetDisplay() {
        // Reset the "Display" TextView
        display.setText("Resistor Value");

        //reset the spinners
        band1Spinner.setSelection(0);
        band2Spinner.setSelection(0);
        multiplierSpinner.setSelection(0);
        toleranceSpinner.setSelection(0);
    }
//    public void doBand1 (View view) {
//        currentBand = 1;
//    }
//    public void doBand2 (View view) {
//        currentBand = 2;
//    }
//    public void doBand3 (View view) {
//        currentBand = 3;
//    }
//    public void doRed (View view) {
//        if (currentBand == 1) {
//            resistorValue = 2;
//        } else if (currentBand == 2) {
//            resistorValue = resistorValue * 10 + 2;
//        } else if (currentBand == 3) {
//            resistorValue = resistorValue * 100;
//        }
//    }
//    public void doOrange(View view) {
//        if (currentBand == 1) {
//            resistorValue = 3;
//        } else if (currentBand == 2) {
//            resistorValue = resistorValue * 10 + 3;
//        } else if (currentBand == 3) {
//            resistorValue = resistorValue * 1000;
//        }
//    }
//    public void doYellow (View view) {
//        if (currentBand == 1) {
//            resistorValue = 4;
//        } else if (currentBand == 2) {
//            resistorValue = resistorValue * 10 + 4;
//        } else if (currentBand == 3) {
//            resistorValue = resistorValue * 10000;
//        }
//    }
//    public void doCalculate (View view) {
//        display.setText(String.valueOf(resistorValue));
//    }
}