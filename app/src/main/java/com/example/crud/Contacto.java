package com.example.crud;

/**
 * esta clase representa la table de la base de datos que vamos a utilizar
 * cada vez que queramos insertar un registro o eliminarlo voy
 * a interactuar con esta clase, basicamente es mi modelo
 */
public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String mail;
    private int edad;

    public Contacto() {

    }

    public Contacto(int id, String nombre, String telefono, String mail, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.edad = edad;
    }

    public Contacto( String nombre, String telefono, String mail, int edad) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMail() {
        return mail;
    }

    public int getEdad() {
        return edad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Contacto: " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                ", edad=" + edad ;
    }
}
