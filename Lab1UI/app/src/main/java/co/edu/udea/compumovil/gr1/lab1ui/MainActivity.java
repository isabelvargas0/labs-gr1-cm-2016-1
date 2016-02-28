package co.edu.udea.compumovil.gr1.lab1ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView textPais = (AutoCompleteTextView) findViewById(R.id.autoPais);

        String[] paises = getResources().getStringArray(R.array.arrayPaises);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        textPais.setAdapter(adapter);
    }

    public void mostrar(View view) {

        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        EditText txtApellido = (EditText) findViewById(R.id.txtApellido);
        EditText txtTel = (EditText) findViewById(R.id.txtTel);
        EditText txtDir = (EditText) findViewById(R.id.txtDir);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        TextView txtDatos = (TextView) findViewById(R.id.txtDatos);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioSexo = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        DatePicker dateNacimiento = (DatePicker) findViewById(R.id.dateNacimiento);
        AutoCompleteTextView autoPaises = (AutoCompleteTextView) findViewById(R.id.autoPais);
        Spinner spinHobbies = (Spinner) findViewById(R.id.spinHobbies);
        CheckBox checkFavorito = (CheckBox) findViewById(R.id.checkFavorito);

        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String tel = txtTel.getText().toString();
        String dir = txtDir.getText().toString();
        String email = txtEmail.getText().toString();
        String datos;
        int sexoId = radioGroup.getCheckedRadioButtonId();
        String sexo = radioSexo.getText().toString();

        Integer anho = dateNacimiento.getYear();
        Integer mes = dateNacimiento.getMonth();
        Integer dia = dateNacimiento.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(anho.toString()).append("-").append(mes.toString()).append("-")
                .append(dia.toString());
        String fechaNac = sb.toString();
        String pais = autoPaises.getText().toString();
        String hobby = spinHobbies.getSelectedItem().toString();
        String favorito = checkFavorito.getText().toString();

        sb.setLength(0);
        sb.append("Nombre: ").append(nombre).append("\n")
                .append("Apellido: ").append(apellido).append("\n")
                .append("Teléfono: ").append(tel).append("\n")
                .append("Dirección: ").append(dir).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Sexo: ").append(sexo).append("\n")
                .append("Fecha: ").append(fechaNac).append("\n")
                .append("Pais: ").append(pais).append("\n")
                .append("Hobby: ").append(hobby).append("\n")
                .append("Favorito: ").append(favorito).append("\n");
        datos = sb.toString();
        txtDatos.setText(datos);

    }
}
