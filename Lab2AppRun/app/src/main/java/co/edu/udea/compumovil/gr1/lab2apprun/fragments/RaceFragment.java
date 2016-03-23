package co.edu.udea.compumovil.gr1.lab2apprun.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.activities.AddRaceActivity;
import co.edu.udea.compumovil.gr1.lab2apprun.activities.RegisterActivity;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.Event;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.RacesCursorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceFragment extends ListFragment {

    private DbManager dbManager;
    private RacesCursorAdapter racesCursorAdapter;
    private Cursor cursor;

    public RaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_race, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* dbManager = new DbManager(getActivity());
        cursor = dbManager.raceCursor();
        racesCursorAdapter = new RacesCursorAdapter(getActivity(), cursor, 0);
        ListView listView = getListView();
        listView.setAdapter(racesCursorAdapter);*/

    }

    @Override
    public void onResume() {
        super.onResume();
        dbManager = new DbManager(getActivity());
        cursor = dbManager.raceCursor();
        racesCursorAdapter = new RacesCursorAdapter(getActivity(), cursor, 0);
        ListView listView = getListView();
        listView.setAdapter(racesCursorAdapter);
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.race, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_race) {
            Intent intent = new Intent(getActivity(), AddRaceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
