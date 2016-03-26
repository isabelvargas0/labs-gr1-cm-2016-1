package co.edu.udea.compumovil.gr1.lab2apprun.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by felipe on 21/03/16.
 */
public class DbManager {
    public static final String TABLE_USERS = "users";
    public static final String TABLE_EVENTS = "events";
    public static final String CN_USERNAME = "user";
    public static final String CN_PASSWORD = "pass";
    public static final String CN_EMAIL = "email";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_DESCRIPTION = "description";
    public static final String CN_DISTANCE = "distance";
    public static final String CN_PLACE = "place";
    public static final String CN_DATE = "date";
    public static final String CN_PHONE = "phone";
    public static final String CN_IMAGE = "image_path";

    public static final String CREATE_TABLE_USERS = "create table " + TABLE_USERS + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_USERNAME + " text not null unique,"
            + CN_EMAIL + " text not null,"
            + CN_PASSWORD + " text not null,"
            + CN_IMAGE + " text);";

    public static final String CREATE_TABLE_EVENTS = "create table if not exists "
            + TABLE_EVENTS + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_DESCRIPTION + " text not null,"
            + CN_DISTANCE + " real not null,"
            + CN_PLACE + " text not null,"
            + CN_DATE + " text not null,"
            + CN_PHONE + " text not null,"
            + CN_EMAIL + " text not null,"
            + CN_IMAGE + " text);";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    private ContentValues usersContentValues(String username, String email, String password,
                                             String imagePath) {
        ContentValues values = new ContentValues();
        values.put(CN_USERNAME, username);
        values.put(CN_EMAIL, email);
        values.put(CN_PASSWORD, password);
        values.put(CN_IMAGE, imagePath);
        return values;
    }

    private ContentValues eventsContentValues(String name, String description, double distance,
                                              String place, String date, String phone, String email,
                                              String imagePath) {
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_DESCRIPTION, description);
        values.put(CN_DISTANCE, distance);
        values.put(CN_PLACE, place);
        values.put(CN_DATE, date);
        values.put(CN_PHONE, phone);
        values.put(CN_EMAIL, email);
        values.put(CN_IMAGE, imagePath);
        return values;
    }

    public void insertUser(User user) {
        db.insert(TABLE_USERS, null, usersContentValues(user.getUserName(), user.getEmail(),
                user.getPassword(), user.getImagePath()));
    }

    public void insertEvent(Event event) {
        db.insert(TABLE_EVENTS, null, eventsContentValues(event.getName(), event.getDescription(),
                Double.valueOf(event.getDistance()), event.getPlace(), event.getDate(), event.getPhone(),
                event.getEmail(), event.getImagePath()));
    }

    public void deleteEvent(Event event) {
        //bd.delete (Tabla, Claúsula Where, Argumentos Where)
        db.delete(TABLE_EVENTS, CN_NAME + "=?", new String[]{event.getName()});
    }

    public Cursor raceCursor() {
        String[] columns = new String[]{CN_ID, CN_NAME, CN_DESCRIPTION, CN_IMAGE};
        return db.query(TABLE_EVENTS, columns, null, null, null, null, null);
    }

    public Cursor getRaceById(String id) {
        db = dbHelper.getReadableDatabase();
        String whereClause = CN_ID + " = ?";
        String[] columns = new String[]{CN_ID, CN_NAME, CN_DESCRIPTION, CN_DISTANCE, CN_PLACE,
                CN_DATE, CN_PHONE, CN_EMAIL, CN_IMAGE};
        String[] whereArgs = new String[]{id};

        return db.query(TABLE_EVENTS, columns, whereClause, whereArgs, null, null, null);
    }

    public Cursor getUserByUserName(String userName) {
        db = dbHelper.getReadableDatabase();
        String whereClause = CN_USERNAME + " = ?";
        String[] columns = new String[]{CN_USERNAME, CN_EMAIL, CN_PASSWORD, CN_IMAGE};
        String[] whereArgs = new String[]{userName};

        return db.query(TABLE_USERS, columns, whereClause, whereArgs, null, null, null);
    }


}
