package co.edu.udea.compumovil.gr1.lab2apprun.fragments;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.ImagesHandler;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.RacesCursorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceDetailFragment extends Fragment {

    private String raceId;
    private Cursor cursor;
    private DbManager dbManager;
    private RacesCursorAdapter racesCursorAdapter;
    private ImagesHandler imagesHandler;

    public RaceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_race_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            imagesHandler = new ImagesHandler();
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_image_detail);
            TextView tvName = (TextView) view.findViewById(R.id.tv_detail_name);
            TextView tvDescr = (TextView) view.findViewById(R.id.tv_detail_description);
            TextView tvDist = (TextView) view.findViewById(R.id.tv_detail_distance);
            TextView tvPlace = (TextView) view.findViewById(R.id.tv_detail_place);
            TextView tvDate = (TextView) view.findViewById(R.id.tv_detail_date);
            TextView tvPhone = (TextView) view.findViewById(R.id.tv_detail_phone);
            TextView tvEmail = (TextView) view.findViewById(R.id.tv_detail_email);
            dbManager = new DbManager(getActivity());
            cursor = dbManager.getRaceById(raceId);
            if (cursor != null)
                cursor.moveToFirst();
            String image = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_IMAGE));
            Bitmap selectedImage = null;
            try {
                ImagesHandler handler = new ImagesHandler();
                Uri imageUri = Uri.parse(image);
                selectedImage = handler.decodeImagePath(getActivity(), imageUri);
            } catch (NullPointerException e) {

            }
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_NAME));
            String descr = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_DESCRIPTION));
            String dist = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_DISTANCE));
            String place = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_PLACE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_DATE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_PHONE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_EMAIL));

            imageView.setImageBitmap(selectedImage);
            tvName.setText(name);
            tvDescr.setText(descr);
            tvDist.setText(dist);
            tvPlace.setText(place);
            tvDate.setText(date);
            tvPhone.setText(phone);
            tvEmail.setText(email);
        }
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }
}
