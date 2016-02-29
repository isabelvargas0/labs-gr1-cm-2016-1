package co.edu.udea.compumovil.gr1.lab1ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtApellido;
    EditText txtTel;
    EditText txtDir;
    EditText txtEmail;
    TextView txtDatos;
    RadioGroup radioGroup;
    RadioButton radioSexo;
    DatePicker dateNacimiento;
    AutoCompleteTextView autoPaises;
    Spinner spinHobbies;
    CheckBox checkFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView textPais = (AutoCompleteTextView) findViewById(R.id.autoPais);

        String[] paises = getResources().getStringArray(R.array.arrayPaises);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        textPais.setAdapter(adapter);

        //Obtener vistas
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTel = (EditText) findViewById(R.id.txtTel);
        txtDir = (EditText) findViewById(R.id.txtDir);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDatos = (TextView) findViewById(R.id.txtDatos);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioSexo = null;
        dateNacimiento = (DatePicker) findViewById(R.id.dateNacimiento);
        autoPaises = (AutoCompleteTextView) findViewById(R.id.autoPais);
        spinHobbies = (Spinner) findViewById(R.id.spinHobbies);
        checkFavorito = (CheckBox) findViewById(R.id.checkFavorito);

        // Validacion de campos
        if (txtNombre.getText().toString().length() == 0) {
            txtNombre.setError("Por favor ingrese su nombre.");
            txtNombre.addTextChangedListener(new Validator(txtNombre) {
                @Override
                public void validate(TextView textView, String text) {
                    if (txtNombre.getText().toString().length() == 0) {
                        txtNombre.setError("Por favor ingrese su nombre.");
                    }
                }
            });
        }
        if (txtApellido.getText().toString().length() == 0) {
            txtApellido.setError("Por favor ingrese su apellido.");
            txtApellido.addTextChangedListener(new Validator(txtApellido) {
                @Override
                public void validate(TextView textView, String text) {
                    if (txtApellido.getText().toString().length() == 0) {
                        txtApellido.setError("Por favor ingrese su apellido.");
                    }
                }
            });
        }
        if (txtTel.getText().toString().length() == 0) {
            txtTel.setError("Por favor ingrese su teléfono.");
            txtTel.addTextChangedListener(new Validator(txtTel) {
                @Override
                public void validate(TextView textView, String text) {
                    if (txtTel.getText().toString().length() == 0) {
                        txtTel.setError("Por favor ingrese su teléfono.");
                    }
                }
            });
        }
        if (txtDir.getText().toString().length() == 0) {
            txtDir.setError("Por favor ingrese su dirección.");
            txtDir.addTextChangedListener(new Validator(txtDir) {
                @Override
                public void validate(TextView textView, String text) {
                    if (txtDir.getText().toString().length() == 0) {
                        txtDir.setError("Por favor ingrese su dirección.");
                    }
                }
            });
        }
        if (txtEmail.getText().toString().length() == 0) {
            txtEmail.setError("Por favor ingrese su e-mail.");
            txtEmail.addTextChangedListener(new Validator(txtEmail) {
                @Override
                public void validate(TextView textView, String text) {
                    if (txtEmail.getText().toString().length() == 0) {
                        txtEmail.setError("Por favor ingrese su e-mail.");
                    }
                }
            });
        }
        if (autoPaises.getText().toString().length() == 0) {
            autoPaises.setError("Por favor ingrese el país.");
            autoPaises.addTextChangedListener(new Validator(autoPaises) {
                @Override
                public void validate(TextView textView, String text) {
                    if (autoPaises.getText().toString().length() == 0) {
                        autoPaises.setError("Por favor ingrese el país.");
                    }
                }
            });
        }


    }

    public void mostrar(View view) {
        //Captura de datos de cada vista
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String tel = txtTel.getText().toString();
        String dir = txtDir.getText().toString();
        String email = txtEmail.getText().toString();
        String datos;
        //String sexo = radioSexo.getText().toString();
        String sexo = "";
        try {
            radioSexo = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            sexo = radioSexo.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer anho = dateNacimiento.getYear();
        Integer mes = dateNacimiento.getMonth();
        Integer dia = dateNacimiento.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(dia.toString()).append("-").append(mes.toString()).append("-")
                .append(anho.toString());
        String fechaNac = sb.toString();
        String pais = autoPaises.getText().toString();
        String hobby = spinHobbies.getSelectedItem().toString();
        String favorito = checkFavorito.getText().toString();

        //Validacion de cada campo cuando se da click en el boton.
        if (nombre.length() == 0 || apellido.length() == 0 || tel.length() == 0 ||
                dir.length() == 0 || email.length() == 0 || sexo.length() == 0 ||
                fechaNac.length() == 0 || pais.length() == 0 || hobby.length() == 0) {

            Toast.makeText(this, "Todos los campos son obligatorios, por favor diligencielos.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Asignacion de datos al string segun el idioma.
        sb.setLength(0);
        String idioma = Locale.getDefault().getDisplayLanguage();
        if (idioma.equals("es")) {
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
        } else {
            sb.append("Name: ").append(nombre).append("\n")
                    .append("Last Name: ").append(apellido).append("\n")
                    .append("Phone: ").append(tel).append("\n")
                    .append("Address: ").append(dir).append("\n")
                    .append("Email: ").append(email).append("\n")
                    .append("Gender: ").append(sexo).append("\n")
                    .append("DOB: ").append(fechaNac).append("\n")
                    .append("Country: ").append(pais).append("\n")
                    .append("Hobby: ").append(hobby).append("\n")
                    .append("Favorite: ").append(favorito).append("\n");

        }
        datos = sb.toString();
        //Mostrar datos
        txtDatos.setText(datos);
    }
}
