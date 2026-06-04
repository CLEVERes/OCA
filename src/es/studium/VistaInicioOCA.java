package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Insets;

public class VistaInicioOCA extends Frame
{
	private static final long serialVersionUID = 1L;

	// Componentes principales
	Label lblInicio = new Label("¡¡¡ LA OCA !!!", Label.CENTER); // Centramos el texto
	Button btnInicio = new Button("Iniciar Juego");
	Button btnAyuda = new Button("Ayuda / Reglas");

	// Paleta de colores "Gaming / Tablero"
	Color colorFondo = new Color(44, 62, 80);      // Azul oscuro elegante
	Color colorTexto = new Color(236, 240, 241);    // Blanco tiza moderno
	Color colorBoton = new Color(39, 174, 96);     // Verde esmeralda para botones
	Color colorBotonAyuda = new Color(211, 84, 0); // Naranja para la ayuda

	public VistaInicioOCA()
	{
		// 1. Configuración de la ventana principal
		setTitle("Juego de la Oca - Menú Principal");
		setBackground(colorFondo);
		
		// Usamos GridBagLayout para centrar todo vertical y horizontalmente en la pantalla
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// 2. Estilo del Título
		lblInicio.setFont(new Font("Arial", Font.BOLD, 36)); // Letra grande y negrita
		lblInicio.setForeground(colorTexto);
		
		// 3. Estilo del Botón de Inicio
		btnInicio.setFont(new Font("Arial", Font.BOLD, 16));
		btnInicio.setBackground(colorBoton);
		btnInicio.setForeground(colorTexto);
		
		// 4. Estilo del Botón de Ayuda
		btnAyuda.setFont(new Font("Arial", Font.BOLD, 14));
		btnAyuda.setBackground(colorBotonAyuda);
		btnAyuda.setForeground(colorTexto);

		// 5. Distribución de los componentes en la pantalla (Fila por fila)
		
		// Fila 0: El Título
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 30, 0); // Margen de 30 píxeles abajo para separar de los botones
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(lblInicio, gbc);

		// Fila 1: Botón Iniciar
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 15, 0); // Margen de 15 píxeles abajo
		add(btnInicio, gbc);

		// Fila 2: Botón Ayuda
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 0);  // Sin margen abajo
		add(btnAyuda, gbc);
		
		// 6. Dimensiones del menú
		setSize(450, 300); // Lo hacemos un pelín más alto para que respire el diseño
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}