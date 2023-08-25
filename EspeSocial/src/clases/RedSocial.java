package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RedSocial {
    public Map<String, Usuario> usuarios;

    public RedSocial() {
        usuarios = new HashMap<>();
    }

    public void agregarUsuario(String nombre) {
        usuarios.put(nombre, new Usuario(nombre));
    }

    public void agregarConexion(String usuario1, String usuario2) {
        if (usuarios.containsKey(usuario1) && usuarios.containsKey(usuario2)) {
            Usuario u1 = usuarios.get(usuario1);
            Usuario u2 = usuarios.get(usuario2);
            u1.conexiones.add(u2);
            u2.conexiones.add(u1);
        }
    }

    public List<String> encontrarNombresAmigosEnComun(String usuario1, String usuario2) {
        List<String> nombresAmigosEnComun = new ArrayList<>();
        if (usuarios.containsKey(usuario1) && usuarios.containsKey(usuario2)) {
            Usuario u1 = usuarios.get(usuario1);
            Usuario u2 = usuarios.get(usuario2);
            for (Usuario u : u1.conexiones) {
                if (u2.conexiones.contains(u)) {
                    nombresAmigosEnComun.add(u.nombre);
                }
            }
        }
        return nombresAmigosEnComun;
    }

    public double calcularCentralidadDeGrado(String nombreUsuario) {
        if (usuarios.containsKey(nombreUsuario)) {
            Usuario usuario = usuarios.get(nombreUsuario);
            return (double) usuario.conexiones.size();
        }
        return 0.0;
    }
    public void visualizarComunidades(List<List<Usuario>> comunidades) {
        for (List<Usuario> comunidad : comunidades) {
            System.out.print("Comunidad: ");
            for (Usuario usuario : comunidad) {
                System.out.print(usuario.nombre + ", ");
            }
            System.out.println();
        }
    }

    public List<List<Usuario>> encontrarComunidades() {
        List<List<Usuario>> comunidades = new ArrayList<>();
        Set<Usuario> visitados = new HashSet<>();

        for (Usuario usuario : usuarios.values()) {
            if (!visitados.contains(usuario)) {
                List<Usuario> comunidad = new ArrayList<>();
                dfs(usuario, visitados, comunidad);
                comunidades.add(comunidad);
            }
        }

        return comunidades;
    }

    private void dfs(Usuario usuario, Set<Usuario> visitados, List<Usuario> comunidad) {
        visitados.add(usuario);
        comunidad.add(usuario);
        for (Usuario amigo : usuario.conexiones) {
            if (!visitados.contains(amigo)) {
                dfs(amigo, visitados, comunidad);
            }
        }
    }

    public void visualizarRed() {
        for (Usuario usuario : usuarios.values()) {
            System.out.print(usuario.nombre + " -> ");
            for (Usuario amigo : usuario.conexiones) {
                System.out.print(amigo.nombre + ", ");
            }
            System.out.println();
        }
    }

    public void eliminarConexion(String usuario1, String usuario2) {
        if (usuarios.containsKey(usuario1) && usuarios.containsKey(usuario2)) {
            Usuario u1 = usuarios.get(usuario1);
            Usuario u2 = usuarios.get(usuario2);
            u1.conexiones.remove(u2);
            u2.conexiones.remove(u1);
        }
    }
}