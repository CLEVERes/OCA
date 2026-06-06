package es.studium;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;

public class VistaOpcionesOCA extends Frame
{
	private static final long serialVersionUID = 1L;

	// Componentes principales
	Label lblOpciones = new Label("Selecciona el número de jugadores", Label.CENTER);
	
	CheckboxGroup chbGrp = new CheckboxGroup();
	Checkbox chbUno = new Checkbox("1 Jugador", true, chbGrp);
	Checkbox chbDos = new Checkbox("2 Jugadores", false, chbGrp);
	Checkbox chbTres = new Checkbox("3 Jugadores", false, chbGrp);
	Checkbox chbCuatro = new Checkbox("4 Jugadores", false, chbGrp);
	
	Button btnJugar = new Button("¡A JUGAR!");
	
	// Mantenemos la paleta de colores del menú principal para tener coherencia visual
	Color colorFondo = new Color(44, 62, 80);      // Azul oscuro
	Color colorTexto = new Color(236, 240, 241);    // Blanco tiza
	Color colorBotonJugar = new Color(39, 174, 96); // Verde esmeralda
	
	Dialog dlg = new Dialog(this, "Indica tu nombre", true);
	Label lblDlg = new Label("");
	TextField txfDlg = new TextField(15);
	Button btnContinuar = new Button("Continuar");

	Color colorBotonAccion = new Color(39, 174, 96);
	Color colorFondoDialogo = new Color(52, 73, 94);
	public VistaOpcionesOCA() 
	{
		// 1. Configuración de la ventana
		setTitle("Configuración de Partida");
		setBackground(colorFondo);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// 2. Estilos de las fuentes y colores de los componentes
		lblOpciones.setFont(new Font("Arial", Font.BOLD, 20));
		lblOpciones.setForeground(colorTexto);
		
		Font fuenteCheckboxes = new Font("Arial", Font.PLAIN, 15);
		
		chbUno.setFont(fuenteCheckboxes);
		chbUno.setForeground(colorTexto);
		
		chbDos.setFont(fuenteCheckboxes);
		chbDos.setForeground(colorTexto);
		
		chbTres.setFont(fuenteCheckboxes);
		chbTres.setForeground(colorTexto);
		
		chbCuatro.setFont(fuenteCheckboxes);
		chbCuatro.setForeground(colorTexto);
		
		btnJugar.setFont(new Font("Arial", Font.BOLD, 16));
		btnJugar.setBackground(colorBotonJugar);
		btnJugar.setForeground(colorTexto);
		
		// 3. Distribución con GridBagLayout (Fila por fila)
		
		// Fila 0: El título explicativo
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2; // Ocupa el ancho de dos columnas para centrarse bien
		gbc.insets = new Insets(0, 0, 25, 0); // Margen generoso abajo
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(lblOpciones, gbc);
		
		// Reseteamos el ancho de celdas para los Checkboxes (irán de 1 en 1)
		gbc.gridwidth = 1; 
		gbc.fill = GridBagConstraints.NONE;
		
		// Fila 1, Columna 0: Opción 1 jugador
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 20, 15, 20); // Margen izquierdo y derecho para separarlos entre sí
		add(chbUno, gbc);
		
		// Fila 1, Columna 1: Opción 2 jugadores
		gbc.gridx = 1;
		add(chbDos, gbc);
		
		// Fila 2, Columna 0: Opción 3 jugadores
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(chbTres, gbc);
		
		// Fila 2, Columna 1: Opción 4 jugadores
		gbc.gridx = 1;
		add(chbCuatro, gbc);
		
		// Fila 3: El botón de Jugar (centrado abajo)
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2; // Vuelve a ocupar ambas columnas para expandirse en el centro
		gbc.insets = new Insets(25, 0, 0, 0); // Margen arriba para separarlo de los Checkboxes
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(btnJugar, gbc);
		
		// 4. Dimensiones de la ventana de opciones
		setSize(450, 320); // Ajustamos tamaño para que la rejilla se vea holgada y limpia
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		dlg.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		dlg.setBackground(colorFondoDialogo);

		lblDlg.setFont(new Font("Arial", Font.BOLD, 14));
		lblDlg.setForeground(colorTexto);

		btnContinuar.setFont(new Font("Arial", Font.BOLD, 12));
		btnContinuar.setBackground(colorBotonAccion);
		btnContinuar.setForeground(colorTexto);

		btnContinuar.setPreferredSize(new java.awt.Dimension(150,25));
		
		dlg.add(lblDlg);
		dlg.add(txfDlg);
		dlg.add(btnContinuar);

		dlg.setSize(250, 180);
		dlg.setResizable(false);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(false);
	}
}