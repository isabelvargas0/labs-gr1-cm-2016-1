package co.edu.udea.compumovil.gr1.lab2apprun.classes;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.activities.MainActivity;

/**
 * Created by felipe on 22/03/16.
 */
public class RacesCursorAdapter extends CursorAdapter {
    public RacesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    // custom CursorAdapter ...

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.race_row_layout, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.imageIcon = (ImageView) rowView.findViewById(R.id.image_icon);
        holder.raceName = (TextView) rowView.findViewById(R.id.tv_race_name);
        holder.raceDescription = (TextView) rowView.findViewById(R.id.tv_race_description);
        rowView.setTag(holder);
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        // Extract properties from cursor
        String image = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_IMAGE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_DESCRIPTION));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_NAME));

        ImagesHandler handler = new ImagesHandler();
        Uri imageUri = Uri.parse(image);
        Bitmap selectedImage = handler.decodeImagePath(context, imageUri);

        // Populate fields with extracted properties
        holder.imageIcon.setImageBitmap(selectedImage);
        holder.raceName.setText(name);
        holder.raceDescription.setText(description);
    }

    private static class ViewHolder {
        ImageView imageIcon;
        TextView raceName;
        TextView raceDescription;
    }
}
