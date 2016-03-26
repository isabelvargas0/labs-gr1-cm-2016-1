package co.edu.udea.compumovil.gr1.lab2apprun.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Event;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.User;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Validator;

public class RegisterActivity extends AppCompatActivity {

    DbManager dbManager;
    EditText etUser;
    EditText etPass;
    EditText etEmail;
    EditText etConfPass;
    ImageView ivImage;
    String imagePath;

    private static final int SELECT_PHOTO = 100;
    private static final String LOG_TAG = RegisterActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = (EditText) findViewById(R.id.edit_register_user);
        etPass = (EditText) findViewById(R.id.edit_register_pass);
        etEmail = (EditText) findViewById(R.id.edit_register_email);
        etConfPass = (EditText) findViewById(R.id.edit_confirm_pass);
        ivImage = (ImageView) findViewById(R.id.image_view_choose);

        //Data validation

        validateTextFields(etUser, "Please enter the User's name");
        validateTextFields(etPass, "Please enter the Password");
        validateTextFields(etEmail, "Please enter the Email");
        validateTextFields(etConfPass, "Please enter the Confimation");


    }

    public void register(View view) {
        String name = etUser.getText().toString();
        String pass = etPass.getText().toString();
        String email = etEmail.getText().toString();
        String confPass = etConfPass.getText().toString();

        if (name.length() == 0 || pass.length() == 0
                || email.length() == 0 || confPass.length() == 0) {

            Toast.makeText(this, "Todos los campos son obligatorios, por favor diligencielos.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.equals(confPass)) {
            User user = new User(name, email, pass, imagePath);
            dbManager = new DbManager(this);
            dbManager.insertUser(user);
            Toast.makeText(RegisterActivity.this, "Successfully registered",
                    Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, "Password and Confirm pass are not the same",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void loadImage(View view) {
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
                    ImageView imageView = (ImageView) findViewById(R.id.image_view_choose);
                    imageView.setImageBitmap(yourSelectedImage);
                }
        }
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
