package co.edu.udea.compumovil.gr1.lab2apprun.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.ImagesHandler;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.SessionManager;
import co.edu.udea.compumovil.gr1.lab2apprun.fragments.AboutFragment;
import co.edu.udea.compumovil.gr1.lab2apprun.fragments.ProfileFragment;
import co.edu.udea.compumovil.gr1.lab2apprun.fragments.RaceFragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    private static final int RESULT = 100;
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String IMAGE = "image";
    private View headerView;
    private CircleImageView profileImage;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddRaceActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);
        profileImage = (CircleImageView) headerView.findViewById(R.id.profile_image);

        session = new SessionManager(this);
        session.checkLogin();
       /* if (savedInstanceState == null) {
            Fragment fragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_content, fragment)
                    .commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }*/

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fm = getSupportFragmentManager();
                String stackName = null;
//                int backCount = fm.getBackStackEntryCount();
//                if (backCount == 0) {
//                    finish();
//                }
                for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
                    stackName = fm.getBackStackEntryAt(entry).getName();
                    Log.i("BC", "stackEntry" + entry);
                }
                if (stackName != null) {
                    if (stackName.equals("RaceFragment")) {
                        navigationView.setCheckedItem(R.id.nav_race);
                    } else if (stackName.equals("AboutFragment")) {
                        navigationView.setCheckedItem(R.id.nav_about);
                    } else {
                        navigationView.setCheckedItem(R.id.nav_profile);
                    }
                } else {
                    navigationView.setCheckedItem(R.id.nav_profile);
                }
            }
        });

       /* profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(intent, RESULT);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String image = user.get(SessionManager.KEY_IMAGE);

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        Bitmap selectedImage = null;
        try {
            ImagesHandler handler = new ImagesHandler();
            Uri imageUri = Uri.parse(image);
            selectedImage = handler.decodeImagePath(this, imageUri);
        } catch (NullPointerException e) {

        }

        TextView tvUserName = (TextView) headerView.findViewById(R.id.tv_profile_name);
        TextView tvUserEmail = (TextView) headerView.findViewById(R.id.tv_profile_email);

        profileImage.setImageBitmap(selectedImage);
        tvUserName.setText(name);
        tvUserEmail.setText(email);
        navigationView.setCheckedItem(R.id.nav_race);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_logout:
                session.logoutUser();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;

        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_race:
                fragmentClass = RaceFragment.class;
                break;
            case R.id.nav_about:
                fragmentClass = AboutFragment.class;
                break;
            default:
                fragmentClass = ProfileFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT:
                if (resultCode == RESULT_OK) {
                    String userName = data.getStringExtra(USER);
                    String userEmail = data.getStringExtra(EMAIL);
                    String userImage = data.getStringExtra(IMAGE);
                    Bitmap selectedImage = null;
                    try {
                        ImagesHandler handler = new ImagesHandler();
                        Uri imageUri = Uri.parse(userImage);
                        selectedImage = handler.decodeImagePath(this, imageUri);
                    } catch (NullPointerException e) {

                    }

                    TextView tvUserName = (TextView) headerView.findViewById(R.id.tv_profile_name);
                    TextView tvUserEmail = (TextView) headerView.findViewById(R.id.tv_profile_email);

                    profileImage.setImageBitmap(selectedImage);
                    tvUserName.setText(userName);
                    tvUserEmail.setText(userEmail);

                }
        }
    }
}

