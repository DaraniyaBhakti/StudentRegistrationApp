package com.tatvasoft.tatvasoftassignment5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText editTextName,editTextBirthDate,editTextContactNo,editTextAddress,editTextEmail;
    RadioButton radioButtonMale,radioButtonFemale;
    CheckBox checkBoxReading,checkBoxTravelling,checkBoxCooking,checkBoxBlogging,checkBoxHiking,checkBoxGardening;
    Button nextButton,backButton,saveButton;
    TextView textViewSectionHeading,percentageTextView,cgpaTextView;
    AutoCompleteTextView schoolDropDown,graduationDropDown,countryDropDown;
    Slider percentageSlider,cgpaSlider;
    LinearLayout linearLayoutPersonal,linearLayoutEducation;
    TextInputLayout birthDate,textName,textContact,textEmail,textAddress;
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
             datePicker.show(getSupportFragmentManager(),"date picker");
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


                percentageTextView.setText(" "+slider.getValue() +"%");

            }
        });

        cgpaSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                cgpaTextView.setText(" "+slider.getValue());
            }
        });

    }
    public void bindId()
    {
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextBirthDate = (EditText)findViewById(R.id.editTextBirthDate);
        editTextContactNo = (EditText)findViewById(R.id.editTextContactNo);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale=(RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxReading = (CheckBox)findViewById(R.id.checkBoxReading);
        checkBoxTravelling = (CheckBox)findViewById(R.id.checkBoxTravelling);
        checkBoxCooking = (CheckBox)findViewById(R.id.checkBoxCooking);
        checkBoxBlogging = (CheckBox)findViewById(R.id.checkBoxBlogging);
        checkBoxHiking = (CheckBox)findViewById(R.id.checkBoxHiking);
        checkBoxGardening = (CheckBox)findViewById(R.id.checkBoxGardening);
        nextButton = (Button)findViewById(R.id.nextButton);
        backButton = (Button)findViewById(R.id.backButton);
        saveButton = (Button)findViewById(R.id.saveButton);
        linearLayoutPersonal =(LinearLayout)findViewById(R.id.linearLayoutPersonal);
        linearLayoutEducation = (LinearLayout)findViewById(R.id.linearLayoutEducation);
        textViewSectionHeading = (TextView)findViewById(R.id.textViewSectionHeading);
        schoolDropDown=(AutoCompleteTextView) findViewById(R.id.schoolDropDown);
        graduationDropDown = (AutoCompleteTextView) findViewById(R.id.graduationDropDown);
        countryDropDown = (AutoCompleteTextView)findViewById(R.id.countryDropDown);
        percentageSlider = (Slider)findViewById(R.id.percentageSlider);
        cgpaSlider = (Slider)findViewById(R.id.cgpaSlider);
        percentageTextView=(TextView)findViewById(R.id.percentageTextView);
        cgpaTextView =(TextView)findViewById(R.id.cgpaTextView);
        birthDate = (TextInputLayout)findViewById(R.id.textBirthDate);
        textName= (TextInputLayout)findViewById(R.id.textName);
        textContact= (TextInputLayout)findViewById(R.id.textContact);
        textEmail= (TextInputLayout)findViewById(R.id.textEmail);
        textAddress= (TextInputLayout)findViewById(R.id.textAddress);

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
        }



        if(TextUtils.isEmpty(editTextAddress.getText().toString())){
            isValid=false;
            editTextAddress.setError(getString(R.string.err_address));
        }

        if(TextUtils.isEmpty(editTextBirthDate.getText().toString())){
            isValid=false;
            editTextBirthDate.setError(getString(R.string.err_dob));
        }


        if(TextUtils.isEmpty(editTextEmail.getText().toString())){
            isValid=false;
            editTextEmail.setError(getString(R.string.err1_email));
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches())
        {
            isValid=false;
            editTextEmail.setError(getString(R.string.err2_email));
        }

        if(TextUtils.isEmpty(editTextContactNo.getText().toString())){
                isValid=false;
                editTextContactNo.setError(getString(R.string.err1_contact));
        }
        else if(editTextContactNo.getText().toString().length()!=10){
                isValid=false;
                editTextContactNo.setError(getString(R.string.err2_contact));
        }

        if((!radioButtonMale.isChecked()) && (!radioButtonFemale.isChecked()))
        {
            isValid=false;
            Toast.makeText(getApplicationContext(),getString( R.string.err_gender),Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(countryDropDown.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),getString(R.string.err_country) ,Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!checkBoxReading.isChecked()){
            if(!checkBoxCooking.isChecked()){
                if(!checkBoxTravelling.isChecked()){
                    if(!checkBoxBlogging.isChecked()){
                        if(!checkBoxHiking.isChecked()){
                            if(!checkBoxGardening.isChecked()){
                                Toast.makeText(getApplicationContext(), getString(R.string.err_hobby),Toast.LENGTH_SHORT).show();
                                isValid = false;
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
        }

        if(TextUtils.isEmpty(graduationDropDown.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), getString(R.string.err_graduation),Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if(TextUtils.isEmpty(percentageTextView.getText().toString())){
            Toast.makeText(getApplicationContext(),getString(R.string.err_percent) ,Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(TextUtils.isEmpty(cgpaTextView.getText().toString())){
            Toast.makeText(getApplicationContext(), getString(R.string.err_percent),Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }
}