package co.edu.udea.compumovil.gr1.lab2apprun.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by felipe on 23/03/16.
 */
public class ImagesHandler {

    public Bitmap decodeImagePath(Context context, Uri imageUri) {
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(imageStream);
    }
}
