package com.example.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {

	private Spinner bldgrpSpinner;
	Button btnSubmit;
	EditText firstName, lastName, mobileNumber, city, pincode, email, state;
	String txtFirstName, txtLastName, txtMobileNumber, txtCity, txtPincode,
			txtEmail, txtState;
	private static final String[] bloodgGroups = { "O +", "O -", "A +", "A -",
			"B +", "B -", "AB +", "AB -" };
	boolean displayActivity2 = false;

	SharedPreferences sharedpreferences;
	public static final String LOGINPREFERENCES = "LoginPREF";

	public static final String FirstName = "firstName";
	public static final String LastName = "lastName";
	public static final String MobileNo = "mobileNo";
	public static final String BloodGroup = "bloodGroup";
	public static final String EmailAddress = "emailAddes";
	public static final String State = "state";
	public static final String City = "city";
	public static final String Pincode = "pinCode";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.reg_layout);
		sharedpreferences = getSharedPreferences(LOGINPREFERENCES,
				Context.MODE_PRIVATE);
		initUI();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_spinner_item,
				bloodgGroups);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bldgrpSpinner.setAdapter(adapter);
		bldgrpSpinner.setOnItemSelectedListener(this);

	}
	
	@Override
	public void onStart() {
		super.onStart();
		if(getSharedPrefData()) {
			Intent activity2 = new Intent(this,MainActivity2.class);
			startActivity(activity2);
			}
	}

	public void initUI() {
		bldgrpSpinner = (Spinner) findViewById(R.id.bloodGroup);
		btnSubmit = (Button) findViewById(R.id.register);
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		mobileNumber = (EditText) findViewById(R.id.phoneNumber);
		city = (EditText) findViewById(R.id.fieldCity);
		state = (EditText) findViewById(R.id.fieldState);
		pincode = (EditText) findViewById(R.id.pinCode);
		email = (EditText) findViewById(R.id.email);

		// get the string textfield
		txtFirstName = firstName.getText().toString();
		txtLastName = lastName.getText().toString();
		txtMobileNumber = mobileNumber.getText().toString();

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean save = true;
				String strFirstName = firstName.getText().toString();
				String strLastName = lastName.getText().toString();
				String strMobileNo = mobileNumber.getText().toString();
				String strCity = city.getText().toString();
				String strState = state.getText().toString();
				String strPinCode = pincode.getText().toString();
				String strEmail = email.getText().toString();
				String strBloodGroup = bldgrpSpinner.getSelectedItem()
						.toString();
				if (TextUtils.isEmpty(strFirstName)) {
					firstName.setError("Enter First Name");
					return;
				}
				if (TextUtils.isEmpty(strLastName)) {
					lastName.setError("Enter Last Name");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strMobileNo)) {
					mobileNumber.setError("Enter Mobile number");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strCity)) {
					city.setError("Enter Mobile number");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strState)) {
					state.setError("Enter Mobile number");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strEmail)) {
					email.setError("Enter Mobile number");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strPinCode)) {
					pincode.setError("Enter Mobile number");
					save = false;
					return;
				}
				if (TextUtils.isEmpty(strBloodGroup)) {
					save = false;
					Toast.makeText(getApplicationContext(),
							"Select Blood group", Toast.LENGTH_SHORT).show();
				}

				Editor editor = sharedpreferences.edit();
				if (save) {
					editor.putString(FirstName, strFirstName);
					editor.putString(LastName, strLastName);
					editor.putString(MobileNo, strMobileNo);
					editor.putString(State, strState);
					editor.putString(City, strCity);
					editor.putString(BloodGroup, strBloodGroup);
					editor.putString(EmailAddress, strEmail);
					editor.putString(Pincode, strPinCode);
					editor.commit();

					Toast.makeText(getApplicationContext(), "Submitted",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter correct values", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
	}
	
	private boolean getSharedPrefData() {
		String  firstname = sharedpreferences.getString(FirstName, "");
		String  lastname = sharedpreferences.getString(LastName, "");
		String  mobileNo = sharedpreferences.getString(MobileNo, "");
		String  bloodGroup = sharedpreferences.getString(BloodGroup, "");
		String  city = sharedpreferences.getString(City, "");
		String  state = sharedpreferences.getString(State, "");
		String  pincode = sharedpreferences.getString(Pincode, "");
		if(firstname.isEmpty()) {
			displayActivity2 = false;
		}
		if(firstname.isEmpty()) {
			displayActivity2 = false;
		}
		else if(lastname.isEmpty()) {
			displayActivity2 = false;
		}
		else if(mobileNo.isEmpty()) {
			displayActivity2 = false;
		}
		else if(bloodGroup.isEmpty()) {
			displayActivity2 = false;
		}
		else if(city.isEmpty()) {
			displayActivity2 = false;
		}
		else if(state.isEmpty()) {
			displayActivity2 = false;
		}
		else if(pincode.isEmpty()) {
			displayActivity2 = false;
		}
		else {
			displayActivity2 = true;
			
		}
		return displayActivity2;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
