package com.daniel.resistorapp;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

//Daniel Ranieri
//131600214
//28/10/2016

//This is a program that will allow the user to determine the ohms
//passing through a resistor depending on the bands that are selected from
//the scroll wheels. The user is able to chose each colour from the four bands.
//The user is then able to chose colour background, text and the text size.
public class MainActivity extends Activity {
	//Global variables that will be called later on
	TextView resistorValue;
	ImageView bandOne, bandTwo, bandThree, tolerance;
	RelativeLayout layoutGG;

	int oneBand = 0;
	int twoBand = 0;
	int threeBand = 0;
	int fourBand = 0;

	String multipleName = "";
	String oneTwoBand;
	String ResistorText = "Resistor Calculation";
	String decimalR = "";
	int fourNumber = 0;
	
	int backColour = Color.WHITE;
	int fontColour = Color.BLACK;
	int fontSize = 20;
	
	//initialization of each band, colours and font size
	final String BAND_ONE = "BandOne";
	final String BAND_TWO = "BandTwo";
	final String BAND_THREE = "BandThree";
	final String BAND_FOUR = "BandFour";
	final String FONT_COLOUR = "FontColour";
	final String FONT_SIZE = "FontSize";
	final String BACK_COLOUR = "BackColour";

	//getting the bands, colours and sizes after the new state is loaded.
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(BAND_ONE, oneBand);
		savedInstanceState.putInt(BAND_TWO, twoBand);
		savedInstanceState.putInt(BAND_THREE, threeBand);
		savedInstanceState.putInt(BAND_FOUR, fourBand);
		savedInstanceState.putInt(FONT_COLOUR, fontColour);
		savedInstanceState.putInt(FONT_SIZE, fontSize);
		savedInstanceState.putInt(BACK_COLOUR, backColour);
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState != null) {
			// Restore value of members from saved state
			oneBand = savedInstanceState.getInt(BAND_ONE);
			twoBand = savedInstanceState.getInt(BAND_TWO);
			threeBand = savedInstanceState.getInt(BAND_THREE);
			fourBand = savedInstanceState.getInt(BAND_FOUR);
			fontColour = savedInstanceState.getInt(FONT_COLOUR);
			fontSize = savedInstanceState.getInt(FONT_SIZE);
			backColour = savedInstanceState.getInt(BACK_COLOUR);
			
		} else {
		}

		//Initialize all the parts from the layout and setting the picker values.
		bandOne = (ImageView) findViewById(R.id.colorOne);
		bandTwo = (ImageView) findViewById(R.id.colorTwo);
		bandThree = (ImageView) findViewById(R.id.colorThree);
		tolerance = (ImageView) findViewById(R.id.Tolerance);
		resistorValue = (TextView) findViewById(R.id.resValue);

		NumberPicker pickerOne = (NumberPicker) findViewById(R.id.bandOne);
		NumberPicker pickerTwo = (NumberPicker) findViewById(R.id.bandTwo);
		NumberPicker pickerThree = (NumberPicker) findViewById(R.id.bandThree);
		NumberPicker pickerTolerance = (NumberPicker) findViewById(R.id.bandTolerance);

		pickerOne.setMinValue(0);
		pickerOne.setMaxValue(9);
		pickerOne.setValue(oneBand);
		pickerTwo.setMinValue(0);
		pickerTwo.setMaxValue(9);
		pickerTwo.setValue(twoBand);
		pickerThree.setMinValue(0);
		pickerThree.setMaxValue(9);
		pickerThree.setValue(threeBand);
		pickerTolerance.setMinValue(0);
		pickerTolerance.setMaxValue(2);
		pickerTolerance.setValue(fourBand);
		
		//set all the colours, fonts, texts, and bands on the resistor.
		layoutGG = (RelativeLayout) findViewById(R.id.layoutGG);
		layoutGG.setBackgroundColor(backColour);
		resistorValue.setTextColor(fontColour);
		resistorValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
		switchOne(oneBand);
		switchTwo(twoBand);
		switchThree(threeBand);
		switchFour(fourBand);
		setResText();

		//set all the picker values.
		pickerOne
				.setDisplayedValues(new String[] { "Black", "Brown", "Red",
						"Orange", "Yellow", "Green", "Blue", "Violet", "Grey",
						"White" });
		pickerTwo
				.setDisplayedValues(new String[] { "Black", "Brown", "Red",
						"Orange", "Yellow", "Green", "Blue", "Violet", "Grey",
						"White" });
		pickerThree
				.setDisplayedValues(new String[] { "Black", "Brown", "Red",
						"Orange", "Yellow", "Green", "Blue", "Violet", "Gold",
						"Silver" });
		pickerTolerance.setDisplayedValues(new String[] { "Gold", "Silver",
				"None" });

		pickerOne
				.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					@Override
					public void onValueChange(NumberPicker picker, int oldVal,
							int newVal) {
						// TODO Auto-generated method stub
						oneBand = newVal;
						switchOne(newVal);
						setResText();
					}
				});

		pickerTwo
				.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					@Override
					public void onValueChange(NumberPicker picker, int oldVal,
							int newVal) {
						// TODO Auto-generated method stub
						twoBand = newVal;
						switchTwo(newVal);
						setResText();
					}
				});

		pickerThree
				.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					@Override
					public void onValueChange(NumberPicker picker, int oldVal,
							int newVal) {
						// TODO Auto-generated method stub
						threeBand = newVal;
						switchThree(newVal);
						setResText();
					}
				});

		pickerTolerance
				.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					@Override
					public void onValueChange(NumberPicker picker, int oldVal,
							int newVal) {
						fourBand = newVal;
						switchFour(newVal);
						setResText();
					}
				});
	}

	//Switch statement to change the band colour when you turn the first scroll.
	public void switchOne(int newValue) {
		switch (newValue) {
		case 0:
			bandOne.setImageResource(R.drawable.black);
			break;
		case 1:
			bandOne.setImageResource(R.drawable.brown);
			break;
		case 2:
			bandOne.setImageResource(R.drawable.red);
			break;
		case 3:
			bandOne.setImageResource(R.drawable.orange);
			break;
		case 4:
			bandOne.setImageResource(R.drawable.yellow);
			break;
		case 5:
			bandOne.setImageResource(R.drawable.green);
			break;
		case 6:
			bandOne.setImageResource(R.drawable.blue);
			break;
		case 7:
			bandOne.setImageResource(R.drawable.violet);
			break;
		case 8:
			bandOne.setImageResource(R.drawable.grey);
			break;
		case 9:
			bandOne.setImageResource(R.drawable.white);
			break;
		}
	}

	//Switch statement to change the band colour when you turn the second scroll.	
	public void switchTwo(int newValue) {
		switch (newValue) {
		case 0:
			bandTwo.setImageResource(R.drawable.black);
			break;
		case 1:
			bandTwo.setImageResource(R.drawable.brown);
			break;
		case 2:
			bandTwo.setImageResource(R.drawable.red);
			break;
		case 3:
			bandTwo.setImageResource(R.drawable.orange);
			break;
		case 4:
			bandTwo.setImageResource(R.drawable.yellow);
			break;
		case 5:
			bandTwo.setImageResource(R.drawable.green);
			break;
		case 6:
			bandTwo.setImageResource(R.drawable.blue);
			break;
		case 7:
			bandTwo.setImageResource(R.drawable.violet);
			break;
		case 8:
			bandTwo.setImageResource(R.drawable.grey);
			break;
		case 9:
			bandTwo.setImageResource(R.drawable.white);
			break;
		}
	}

	//Switch statement to change the band colour when you turn the third scroll.
	//Change the formatting of the resistor calculation.
	public void switchThree(int newValue) {
		switch (newValue) {
		case 0:
			bandThree.setImageResource(R.drawable.black);
			multipleName = "";
			decimalR = "";
			break;
		case 1:
			bandThree.setImageResource(R.drawable.brown);
			multipleName = "0";
			decimalR = "";
			break;
		case 2:
			bandThree.setImageResource(R.drawable.red);
			multipleName = " K";
			decimalR = ".";
			break;
		case 3:
			bandThree.setImageResource(R.drawable.orange);
			multipleName = " K";
			decimalR = "";
			break;
		case 4:
			bandThree.setImageResource(R.drawable.yellow);
			multipleName = "0 K";
			decimalR = "";
			break;
		case 5:
			bandThree.setImageResource(R.drawable.green);
			multipleName = " M";
			decimalR = ".";
			break;
		case 6:
			bandThree.setImageResource(R.drawable.blue);
			multipleName = " M";
			decimalR = "";
			break;
		case 7:
			bandThree.setImageResource(R.drawable.violet);
			multipleName = "0 M";
			decimalR = "";
			break;
		case 8:
			bandThree.setImageResource(R.drawable.gold);
			multipleName = "";
			decimalR = ".";
			break;
		case 9:
			bandThree.setImageResource(R.drawable.silver);
			multipleName = "";
			oneTwoBand = "0." + String.valueOf(oneBand) + ""
					+ String.valueOf(twoBand);
			break;
		}
	}
	
	//Switch statement to change the band colour when you turn the fourth scroll.
	public void switchFour(int newValue) {
		switch (newValue) {
		case 0:
			tolerance.setImageResource(R.drawable.gold);
			fourNumber = 5;
			break;
		case 1:
			tolerance.setImageResource(R.drawable.silver);
			fourNumber = 10;
			break;
		case 2:
			tolerance.setImageResource(R.drawable.spacer);
			fourNumber = 0;
		}
	}

	//Create the textview in the correct formatting.
	public void setResText() {
		if (threeBand == 8) {
			ResistorText = String.valueOf(oneBand) + "."
					+ String.valueOf(twoBand) + multipleName + " Ohms "
					+ fourNumber + "%";
			resistorValue.setText(ResistorText);
		} else if (threeBand == 9) {
			ResistorText = "0."
					+ (String.valueOf(oneBand) + "" + String.valueOf(twoBand))
					+ multipleName + " Ohms " + fourNumber + "%";
			resistorValue.setText(ResistorText);
		} else {
			ResistorText = (String.valueOf(oneBand) + decimalR + String
					.valueOf(twoBand))
					+ multipleName
					+ " Ohms "
					+ fourNumber
					+ "%";
			resistorValue.setText(ResistorText);
		}

	}

	//Set what each menu selection will do.
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.day:
			backColour = getResources().getColor(R.color.day_background);
			fontColour = getResources().getColor(R.color.day_text);
			layoutGG.setBackgroundColor(backColour);
			resistorValue.setTextColor(fontColour);
			return true;
		case R.id.construction:
			backColour = getResources().getColor(R.color.construction_background);
			fontColour = getResources().getColor(R.color.construction_text);
			layoutGG.setBackgroundColor(backColour);
			resistorValue.setTextColor(fontColour);
			return true;
		case R.id.disco:
			backColour = getResources().getColor(R.color.disco_background);
			fontColour = getResources().getColor(R.color.disco_text);
			layoutGG.setBackgroundColor(backColour);
			resistorValue.setTextColor(fontColour);
			return true;
		case R.id.night:
			backColour = getResources().getColor(R.color.night_background);
			fontColour = getResources().getColor(R.color.night_text);
			layoutGG.setBackgroundColor(backColour);
			resistorValue.setTextColor(fontColour);
			return true;
		case R.id.business:
			backColour = getResources().getColor(R.color.business_background);
			fontColour = getResources().getColor(R.color.business_text);
			layoutGG.setBackgroundColor(backColour);
			resistorValue.setTextColor(fontColour);
			return true;
		case R.id.smalltext:
			fontSize = 15;
			resistorValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
			return true;
		case R.id.mediumtext:
			fontSize = 25;
			resistorValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);;
			return true;
		case R.id.largetext:
			fontSize = 40;
			resistorValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		}

	//Create the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}