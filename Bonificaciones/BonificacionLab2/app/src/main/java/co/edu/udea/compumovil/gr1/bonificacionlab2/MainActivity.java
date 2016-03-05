package co.edu.udea.compumovil.gr1.bonificacionlab2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaseManager manager;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private TextView tv;
    private ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        listView = (ListView) findViewById(R.id.listView);

        btn = (ImageButton) findViewById(R.id.imageButton);
        tv = (EditText) findViewById(R.id.editText);

        btn.setOnClickListener(this);

//        manager.insertar("Jaime", "11111111");
//        manager.insertar2("Juan", "22222222");
//        manager.insertar("Ana", "3333333");
//        manager.eliminar("juan");
//        manager.modificarTelefono("Ana", "55555555");

        String[] from = new String[]{manager.CN_NAME, manager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        cursor = manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        listView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageButton){
            Cursor c = manager.buscarContacto(tv.getText().toString());
            adapter.changeCursor(c);
        }
    }
}
