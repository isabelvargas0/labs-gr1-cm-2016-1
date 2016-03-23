package co.edu.udea.compumovil.gr1.lab2apprun.activities;

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

import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Event;

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
        setContentView(R.layout.activity_add_race);
        etName = (EditText) findViewById(R.id.et_name);
        etDescription = (EditText) findViewById(R.id.et_description);
        etDistance = (EditText) findViewById(R.id.et_distance);
        etPlace = (EditText) findViewById(R.id.et_place);
        dpDate = (DatePicker) findViewById(R.id.date);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        ivImage = (ImageView) findViewById(R.id.iv_choose_race);
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
        Double distance = Double.valueOf(etDistance.getText().toString());
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
        Event event = new Event(name, description, distance, place, date, phone, email, imagePath);
        dbManager = new DbManager(this);
        dbManager.insertEvent(event);
        finish();
    }


}
