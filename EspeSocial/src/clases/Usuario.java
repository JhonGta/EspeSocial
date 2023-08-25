package clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    public String nombre;
    public List<Usuario> conexiones;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.conexiones = new ArrayList<>();
    }
}