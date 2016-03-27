package co.edu.udea.compumovil.gr1.lab2apprun.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr1.lab2apprun.R;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.DbManager;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.ImagesHandler;
import co.edu.udea.compumovil.gr1.lab2apprun.classes.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private ImagesHandler imagesHandler;
    private DbManager dbManager;
    private Cursor cursor;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ActionBar actionBar = getActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void login(View view) {
        if (view != null) {
            imagesHandler = new ImagesHandler();
            TextView tvUser = (TextView) findViewById(R.id.edit_user);
            TextView tvPass = (TextView) findViewById(R.id.edit_pass);
            String userText = tvUser.getText().toString();
            String userPassText = tvPass.getText().toString();

            dbManager = new DbManager(this);
            cursor = dbManager.getUserByUserName(userText);
            try {

                if (cursor != null)
                    cursor.moveToFirst();

                String image = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_IMAGE));
                String userName = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_USERNAME));
                String userPass = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_PASSWORD));
                String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.CN_EMAIL));

                if (userText.equals(userName) && userPassText.equals(userPass)) {
                    session = new SessionManager(this);
                    session.createLoginSession(image, userName, userEmail);
               /* Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.USER, userName);
                returnIntent.putExtra(MainActivity.EMAIL, userEmail);
                returnIntent.putExtra(MainActivity.IMAGE, image);
                setResult(RESULT_OK, returnIntent);*/
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Invalid username and/or password.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
