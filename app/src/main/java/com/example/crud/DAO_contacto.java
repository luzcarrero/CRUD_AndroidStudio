package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Key;
import java.util.ArrayList;

public class DAO_contacto extends SQLiteOpenHelper {

    SQLiteDatabase database;
    DAO_contacto DAO_contacto;
    String tabla="contacto";

    ArrayList lista=new ArrayList();
    Context ct;
    Contacto contacto;
    //nombre de la base de datos
   //creacion de la tabla
   String query="CREATE TABLE IF NOT EXISTS contacto(id integer primary key autoincrement , nombre text , telefono text, mail text, edad int)";

    public DAO_contacto(Context con){
        super(con,"BDcontactos",null,1);
        onCreate(getWritableDatabase());
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertar(Contacto contacto){
        this.database=getWritableDatabase();

        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",contacto.getNombre());
        contenedor.put("telefono",contacto.getTelefono());
        contenedor.put("mail",contacto.getMail());
        contenedor.put("edad",contacto.getEdad());
        long resultado=this.database.insert(tabla,null,contenedor);
        this.database.close();
        return resultado;
    }

    public void modificar(Contacto contacto){

        this.database=getWritableDatabase();
        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",contacto.getNombre());
        contenedor.put("telefono",contacto.getTelefono());
        contenedor.put("mail",contacto.getMail());
        contenedor.put("edad",contacto.getEdad());

        this.database.update(tabla,contenedor, "id=?", new String[]{String.valueOf(contacto.getId())});
        this.database.close();
    }

    public boolean eliminar(Contacto contacto){
        this.database=getWritableDatabase();
        this.database.delete(tabla,"id=?",new String[]{String.valueOf(contacto.getId())});
        this.database.close();
        return true;
    }
    public ArrayList<Contacto> listarContactos(){
        String query="SELECT * FROM "+tabla;
        ArrayList<Contacto>lista=new ArrayList<>();
        Cursor cursor=getWritableDatabase().rawQuery(query,null);

        while(cursor.moveToNext()){

            lista.add(new Contacto(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getString(cursor.getColumnIndex("telefono")),
                    cursor.getString(cursor.getColumnIndex("mail")),
                    cursor.getInt(cursor.getColumnIndex("edad"))
                    ));
        }
        return lista ;
    }

    public Contacto verUno (int id){
        return contacto;
    }


}
