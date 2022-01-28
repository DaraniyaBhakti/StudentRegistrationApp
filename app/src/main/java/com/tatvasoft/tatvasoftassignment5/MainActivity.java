package com.tatvasoft.tatvasoftassignment5;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.slider.Slider;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText editTextName,editTextBirthDate,editTextContactNo,editTextAddress,editTextEmail;
    private RadioButton radioButtonMale,radioButtonFemale;
    private CheckBox checkBoxReading,checkBoxTravelling,checkBoxCooking,checkBoxBlogging,checkBoxHiking,checkBoxGardening;
    private TextView textViewSectionHeading,percentageTextView,cgpaTextView;
    private AutoCompleteTextView schoolDropDown,graduationDropDown,countryDropDown;
    private Slider percentageSlider,cgpaSlider;
    private LinearLayout linearLayoutPersonal,linearLayoutEducation;
    private static Resources resources;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.user_profile));
        resources=getResources();
        bindId();
        setEducationDropDown();
        setSliders();

        editTextBirthDate.setKeyListener(null);

        editTextBirthDate.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                DialogFragment datePicker = new com.tatvasoft.tatvasoftassignment5.DatePicker();
             datePicker.show(getSupportFragmentManager(),getString(R.string.tag_datePicker));
             editTextBirthDate.setError(null);
            }
            return false;
        });

    }

    public static Resources getRes()
    {
        return resources;
    }

    public void setEducationDropDown()
    {

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, resources.getStringArray(R.array.countryArray));
        countryDropDown.setAdapter(countryAdapter);

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, resources.getStringArray(R.array.schoolArray));
        schoolDropDown.setAdapter(schoolAdapter);

        ArrayAdapter<String> graduationAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, resources.getStringArray(R.array.graduationArray));
        graduationDropDown.setAdapter(graduationAdapter);
    }

    public void setSliders()
    {
        percentageSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {


                percentageTextView.setText(String.format(" %s%%", slider.getValue()));

            }
        });

        cgpaSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                cgpaTextView.setText(String.format(" %s", slider.getValue()));
            }
        });

    }
    public void bindId()
    {
        editTextName = findViewById(R.id.editTextName);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextContactNo = findViewById(R.id.editTextContactNo);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale= findViewById(R.id.radioButtonFemale);
        checkBoxReading = findViewById(R.id.checkBoxReading);
        checkBoxTravelling = findViewById(R.id.checkBoxTravelling);
        checkBoxCooking = findViewById(R.id.checkBoxCooking);
        checkBoxBlogging = findViewById(R.id.checkBoxBlogging);
        checkBoxHiking = findViewById(R.id.checkBoxHiking);
        checkBoxGardening = findViewById(R.id.checkBoxGardening);
        linearLayoutPersonal = findViewById(R.id.linearLayoutPersonal);
        linearLayoutEducation = findViewById(R.id.linearLayoutEducation);
        textViewSectionHeading = findViewById(R.id.textViewSectionHeading);
        schoolDropDown= findViewById(R.id.schoolDropDown);
        graduationDropDown = findViewById(R.id.graduationDropDown);
        countryDropDown = findViewById(R.id.countryDropDown);
        percentageSlider = findViewById(R.id.percentageSlider);
        cgpaSlider = findViewById(R.id.cgpaSlider);
        percentageTextView= findViewById(R.id.percentageTextView);
        cgpaTextView = findViewById(R.id.cgpaTextView);

    }

    public void nextBtnCLick(View view) {

        if(isValidPersonal())
        {
            linearLayoutPersonal.setVisibility(View.GONE);
            linearLayoutEducation.setVisibility(View.VISIBLE);
            textViewSectionHeading.setText(getString(R.string.educational_details));
        }

    }

    public void backButtonClick(View view) {
        linearLayoutEducation.setVisibility(View.GONE);
        linearLayoutPersonal.setVisibility(View.VISIBLE);
        textViewSectionHeading.setText(getString(R.string.personal_details));
    }

    public void saveButtonClick(View view) {

        if(isValidEducation())
        {
            Toast.makeText(getApplicationContext(), getString(R.string.valid_data),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);

        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK).format(calendar.getTime());
        editTextBirthDate.setText(currentDateString);
    }



    private boolean isValidPersonal(){
    boolean isValid=true;


        if(TextUtils.isEmpty(editTextName.getText().toString())) {
            isValid = false;
            editTextName.setError(getString(R.string.err_name));
            editTextName.requestFocus();
        }



        if(TextUtils.isEmpty(editTextAddress.getText().toString())){
            isValid=false;
            editTextAddress.setError(getString(R.string.err_address));
            editTextAddress.requestFocus();
        }

        if(TextUtils.isEmpty(editTextBirthDate.getText().toString())){
            isValid=false;
            editTextBirthDate.setError(getString(R.string.err_dob));
            editTextBirthDate.requestFocus();
        }


        if(TextUtils.isEmpty(editTextEmail.getText().toString())){
            isValid=false;
            editTextEmail.setError(getString(R.string.err1_email));
            editTextEmail.requestFocus();

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches())
        {
            isValid=false;
            editTextEmail.setError(getString(R.string.err2_email));
            editTextEmail.requestFocus();
        }

        if(TextUtils.isEmpty(editTextContactNo.getText().toString())){
                isValid=false;
                editTextContactNo.setError(getString(R.string.err1_contact));
            editTextContactNo.requestFocus();
        }
        else if(editTextContactNo.getText().toString().length()!=10){
                isValid=false;
                editTextContactNo.setError(getString(R.string.err2_contact));
            editTextContactNo.requestFocus();
        }

        if((!radioButtonMale.isChecked()) && (!radioButtonFemale.isChecked()))
        {
            isValid=false;
            Toast.makeText(getApplicationContext(),getString( R.string.err_gender),Toast.LENGTH_SHORT).show();
            radioButtonMale.requestFocus();
        }

        if(TextUtils.isEmpty(countryDropDown.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),getString(R.string.err_country) ,Toast.LENGTH_SHORT).show();
            isValid = false;
            countryDropDown.requestFocus();
        }

        if(!checkBoxReading.isChecked()){
            if(!checkBoxCooking.isChecked()){
                if(!checkBoxTravelling.isChecked()){
                    if(!checkBoxBlogging.isChecked()){
                        if(!checkBoxHiking.isChecked()){
                            if(!checkBoxGardening.isChecked()){
                                Toast.makeText(getApplicationContext(), getString(R.string.err_hobby),Toast.LENGTH_SHORT).show();
                                isValid = false;
                                checkBoxReading.requestFocus();
                            }
                        }
                    }
                }
            }
        }


        return isValid;
    }

    private boolean isValidEducation()
    {
        boolean isValid = true;
        if(TextUtils.isEmpty(schoolDropDown.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), getString(R.string.err_school),Toast.LENGTH_SHORT).show();
            isValid = false;
            schoolDropDown.requestFocus();
        }

        if(TextUtils.isEmpty(graduationDropDown.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), getString(R.string.err_graduation),Toast.LENGTH_SHORT).show();
            isValid = false;
            graduationDropDown.requestFocus();
        }
        if(TextUtils.isEmpty(percentageTextView.getText().toString())){
            Toast.makeText(getApplicationContext(),getString(R.string.err_percent) ,Toast.LENGTH_SHORT).show();
            isValid = false;
            percentageSlider.requestFocus();
        }

        if(TextUtils.isEmpty(cgpaTextView.getText().toString())){
            Toast.makeText(getApplicationContext(), getString(R.string.err_percent),Toast.LENGTH_SHORT).show();
            isValid = false;
            cgpaSlider.requestFocus();
        }

        return isValid;
    }
}