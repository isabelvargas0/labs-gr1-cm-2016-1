package co.edu.udea.compumovil.gr1.lab2apprun.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Event;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Validator;

public class AddRaceActivity extends AppCompatActivity {

    DbManager dbManager;
    EditText etName;
    EditText etDescription;
    EditText etDistance;
    EditText etPlace;
    DatePicker dpDate;
    EditText etPhone;
    EditText etEmail;
    ImageView ivImage;
    String imagePath;
    private static final int SELECT_PHOTO = 100;
    private static final String LOG_TAG = AddRaceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_race);
        etName = (EditText) findViewById(R.id.et_name);
        etDescription = (EditText) findViewById(R.id.et_description);
        etDistance = (EditText) findViewById(R.id.et_distance);
        etPlace = (EditText) findViewById(R.id.et_place);
        dpDate = (DatePicker) findViewById(R.id.date);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        ivImage = (ImageView) findViewById(R.id.iv_choose_race);

        //Fields Validation
        validateTextFields(etName, "Please enter the Race's name");
        validateTextFields(etDescription, "Please enter the Race's description");
        validateTextFields(etDistance, "Please enter the Race's distance");
        validateTextFields(etPlace, "Please enter the Race's place");
        validateTextFields(etPhone, "Please enter the Race's phone");
        validateTextFields(etEmail, "Please enter the Race's email");
    }

    public void addImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    imagePath = selectedImage.toString();
                    // Toast.makeText(RegisterActivity.this, selectedImage.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, selectedImage.toString());
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    ivImage.setImageBitmap(yourSelectedImage);
                }
        }
    }

    public void addRace(View view) {
        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        Double distance = 0.0;
        try {
            distance = Double.valueOf(etDistance.getText().toString());
        } catch (Exception e) {

        }
        String place = etPlace.getText().toString();
        Integer year = dpDate.getYear();
        Integer month = dpDate.getMonth();
        Integer day = dpDate.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(day.toString()).append("-").append(month.toString()).append("-")
                .append(year.toString());
        String date = sb.toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        if (name.length() == 0 || description.length() == 0 ||
                etDistance.getText().toString().length() == 0 || place.length() == 0 ||
                date.length() == 0 || phone.length() == 0 || email.length() == 0) {

            Toast.makeText(this, "Todos los campos son obligatorios, por favor diligencielos.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Event event = new Event(name, description, distance, place, date, phone, email, imagePath);
        dbManager = new DbManager(this);
        dbManager.insertEvent(event);
        finish();
    }

    public void validateTextFields(EditText textView, final String textError) {
        if (textView.getText().toString().length() == 0) {
            textView.setError(textError);
            textView.addTextChangedListener(new Validator(textView) {
                @Override
                public void validate(TextView textView, String text) {
                    if (textView.getText().toString().length() == 0) {
                        textView.setError(textError);
                    }
                }
            });
        }
    }


}
