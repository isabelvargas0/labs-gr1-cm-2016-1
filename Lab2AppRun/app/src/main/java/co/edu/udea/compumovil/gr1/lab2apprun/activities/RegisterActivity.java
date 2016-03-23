package co.edu.udea.compumovil.gr1.lab2apprun.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr1.lab2apprun.R;

public class RegisterActivity extends AppCompatActivity {
    private static final int SELECT_PHOTO = 100;
    private static final String LOG_TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_image);
        layout.setVisibility(ImageView.VISIBLE);
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
}
