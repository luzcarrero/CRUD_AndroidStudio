package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DAO_contacto dao_contacto;
    Adaptador adaptador;
    ArrayList<Contacto>lista;
    Button agregar;
    Contacto contacto;
     ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao_contacto=new DAO_contacto(this);

        lista=dao_contacto.listarContactos();
        adaptador=new Adaptador(this, lista);
        agregar=(Button)findViewById(R.id.agregar);

        list=(ListView)findViewById(R.id.lista);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Dialog de vista previa de registro de vista.xml
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo de agregar

                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Agregar");
                LayoutInflater inflater=MainActivity.this.getLayoutInflater();
                View vista=inflater.inflate(R.layout.dialog,null);
                dialog.setView(vista);


                //enlazar todos los elementos de la vista del dialogo

                final EditText nombre=(EditText)vista.findViewById(R.id.nombre);
                final EditText telefono=(EditText)vista.findViewById(R.id.telefono);
                final EditText mail=(EditText)vista.findViewById(R.id.mail);
                final EditText edad=(EditText)vista.findViewById(R.id.edad);

               dialog.setPositiveButton("agregar", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dao_contacto.insertar(new Contacto(
                               nombre.getText().toString(),
                               telefono.getText().toString(),
                               mail.getText().toString(),
                               Integer.parseInt(edad.getText().toString())
                       ));
                    //actualizar vista
                      lista=dao_contacto.listarContactos();
                       list.setAdapter(new Adaptador(MainActivity.this,lista));
                   }
               });

              dialog.setNegativeButton("cancelar",null);
                dialog.show();

            }
        });
    }




    public class Adaptador extends ArrayAdapter<Contacto> {

        ArrayList<Contacto> lista;
        AlertDialog.Builder dialog = null;


        public Adaptador(Context contexto , ArrayList lista){
            super(contexto,R.layout.item,lista);
            //lista de contactos
            this.lista=lista;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=((Activity)getContext()).getLayoutInflater();
            View vista=inflater.inflate(R.layout.item,null);


            final Contacto contacto=lista.get(position);
            final TextView nombre=(TextView)vista.findViewById(R.id.nombre);
            final TextView telefono=(TextView)vista.findViewById(R.id.telefono);
            final TextView edad=(TextView)vista.findViewById(R.id.mail);
            final TextView mail=(TextView)vista.findViewById(R.id.edad);
            Button editar=(Button)vista.findViewById(R.id.editar);
            Button borrar=(Button)vista.findViewById(R.id.borrar);

            //llenamos los datos de contacto

            nombre.setText(contacto.getNombre());
            telefono.setText(contacto.getTelefono());
            edad.setText(String.valueOf(contacto.getEdad()));
            mail.setText(contacto.getMail());

            //se editan los botones con el fin de saber la posicion del que estamos pulsando
            editar.setTag(position);
            borrar.setTag(position);

            //DIALOG
            //Dialogo de agregar

            final AlertDialog.Builder dialg=new AlertDialog.Builder(MainActivity.this);
            dialg.setTitle("EDITAR");
            LayoutInflater inflr=MainActivity.this.getLayoutInflater();
            View visa=inflr.inflate(R.layout.dialog,null);
            dialg.setView(visa);

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialg.setPositiveButton("Modifcar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            contacto.setNombre(nombre.getText().toString());
                            contacto.setTelefono(telefono.getText().toString());
                            contacto.setMail(mail.getText().toString());
                            contacto.setEdad(Integer.parseInt(edad.getText().toString()));
                            dao_contacto.modificar(contacto);
                            //actualizar vista

                            lista=dao_contacto.listarContactos();
                            list.setAdapter(new Adaptador(MainActivity.this,lista));

                        }
                    });
                    dialg.show();
                    dialg.setNegativeButton("Cancelar",null);

                }
            });


            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //introducir dialigo para eliminar SI/NO

                }
            });


            return vista;
        }
    }
}
