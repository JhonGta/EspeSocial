package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.RedSocial;
import clases.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {

    private JPanel contentPane;
    private RedSocial redSocial;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfaz frame = new Interfaz();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Interfaz() {
        redSocial = new RedSocial();  // Crea una instancia de la clase RedSocial

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 576, 481);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTextoEspeSocial = new JLabel("ESPE SOCIAL");
        lblTextoEspeSocial.setForeground(Color.WHITE);
        lblTextoEspeSocial.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTextoEspeSocial.setBounds(83, 23, 142, 42);
        contentPane.add(lblTextoEspeSocial);

        // Agregar botones al panel
        JButton agregarUsuarioButton = new JButton("Agregar usuario");
        agregarUsuarioButton.setBounds(10, 350, 142, 23);
        contentPane.add(agregarUsuarioButton);
        JButton agregarConexionButton = new JButton("Agregar conexión");
        agregarConexionButton.setBounds(10, 384, 142, 23);
        contentPane.add(agregarConexionButton);
        JButton encontrarAmigosButton = new JButton("Encontrar amigos en común");
        encontrarAmigosButton.setBounds(161, 350, 183, 23);
        contentPane.add(encontrarAmigosButton);
        JButton calcularCentralidadButton = new JButton("Cantidad de amigos en comun");
        calcularCentralidadButton.setBounds(161, 384, 183, 23);
        contentPane.add(calcularCentralidadButton);
        JButton encontrarComunidadesButton = new JButton("Encontrar comunidades");
        encontrarComunidadesButton.setBounds(354, 350, 170, 23);
        contentPane.add(encontrarComunidadesButton);
        JButton visualizarRedButton = new JButton("Visualizar red");
        visualizarRedButton.setBounds(354, 382, 170, 23);
        contentPane.add(visualizarRedButton);
        JButton salirButton = new JButton("Salir");
        salirButton.setBounds(249, 418, 82, 23);
        contentPane.add(salirButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(Interfaz.class.getResource("/imagen/logo.png")));
        lblNewLabel_1.setBounds(5, 5, 565, 480);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Interfaz.class.getResource("/imagen/EspeSocial.jpg")));
        lblNewLabel.setBounds(5, 5, 555, 456);
        contentPane.add(lblNewLabel);

        // Agregar listeners a los botones
        agregarUsuarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = JOptionPane.showInputDialog("Ingresa el nombre del usuario:");
                redSocial.agregarUsuario(nombreUsuario);
                JOptionPane.showMessageDialog(null, "Usuario agregado: " + nombreUsuario);
            }
        });

        agregarConexionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario1 = JOptionPane.showInputDialog("Ingresa el nombre del primer usuario:");
                String usuario2 = JOptionPane.showInputDialog("Ingresa el nombre del segundo usuario:");
                redSocial.agregarConexion(usuario1, usuario2);
                JOptionPane.showMessageDialog(null, "Conexión agregada entre " + usuario1 + " y " + usuario2);
            }
        });

        encontrarAmigosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usr1 = JOptionPane.showInputDialog("Ingresa el nombre del primer usuario:");
                String usr2 = JOptionPane.showInputDialog("Ingresa el nombre del segundo usuario:");
                java.util.List<String> nombresAmigosComunes = redSocial.encontrarNombresAmigosEnComun(usr1, usr2);
                String amigosComunes = String.join(", ", nombresAmigosComunes);
                JOptionPane.showMessageDialog(null, "Amigos en común entre " + usr1 + " y " + usr2 + ": " + amigosComunes);
            }
        });

        calcularCentralidadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usr = JOptionPane.showInputDialog("Ingresa el nombre del usuario:");
                double centralidad = redSocial.calcularCentralidadDeGrado(usr);
                JOptionPane.showMessageDialog(null, "Amigos en comun de " + usr + ": " + centralidad);
            }
        });

        encontrarComunidadesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.List<java.util.List<Usuario>> comunidades = redSocial.encontrarComunidades();
                StringBuilder comunidadesText = new StringBuilder("Comunidades encontradas:\n");
                for (java.util.List<Usuario> comunidad : comunidades) {
                    String usuariosComunidad = comunidad.stream()
                            .map(usuario -> usuario.nombre)
                            .reduce("", (acc, nombre) -> acc + nombre + ", ");
                    comunidadesText.append("Comunidad: ").append(usuariosComunidad).append("\n");
                }
                JOptionPane.showMessageDialog(null, comunidadesText.toString());
            }
        });

        visualizarRedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder redText = new StringBuilder("Red social:\n");
                for (Usuario usuario : redSocial.usuarios.values()) {
                    String amigos = usuario.conexiones.stream()
                            .map(amigo -> amigo.nombre)
                            .reduce("", (acc, nombre) -> acc + nombre + ", ");
                    redText.append(usuario.nombre).append(" -> ").append(amigos).append("\n");
                }
                JOptionPane.showMessageDialog(null, redText.toString());
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saliendo...");
                System.exit(0);
            }
        });
    }
}
