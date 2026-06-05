package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;

public class VistaRankingOCA extends Frame
{
	private static final long serialVersionUID = 1L;

	ArrayList<Label> lblList = new ArrayList<Label>();
	Label lblTitulo = new Label("RANKING - TOP 10", Label.CENTER);
	Button btnVolver = new Button("Volver al Menú");
	
	// Paleta de colores globales (A juego con VistaJuegoOCA)
	Color colorFondo = new Color(44, 62, 80);        // Azul oscuro
	Color colorTexto = new Color(236, 240, 241);     // Blanco tiza
	Color colorTitulo = new Color(241, 196, 15);     // Amarillo Oca / Oro
	Color colorBotonAccion = new Color(39, 174, 96); // Verde elegante
	Color colorFondoFilas = new Color(52, 73, 94);   // Gris azulado para las filas

	public VistaRankingOCA(int cantidadLabel)
	{
		// 1. Configuración de la ventana principal
		setTitle("Clasificación General");
		setBackground(colorFondo);
		// Usamos un FlowLayout con márgenes generosos arriba y abajo
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
		
		// 2. Estilo del Título Principal
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setForeground(colorTitulo);
		lblTitulo.setPreferredSize(new java.awt.Dimension(400, 30));
		add(lblTitulo);
		
		// 3. Contenedor de la lista de jugadores (Diseño vertical tipo cuadrícula)
		// Si no hay jugadores apuntados, le damos un mínimo de 1 fila para que no rompa el diseño
		int filas = (cantidadLabel == 0) ? 1 : cantidadLabel;
		Panel pnlRanking = new Panel(new GridLayout(filas, 1, 0, 6));
		pnlRanking.setPreferredSize(new java.awt.Dimension(360, filas * 28));
		
		// Controlamos si la base de datos está vacía para no dejar la pantalla colgada
		if (cantidadLabel == 0)
		{
			Label lblVacio = new Label("¡Aún no hay partidas registradas!", Label.CENTER);
			lblVacio.setFont(new Font("Arial", Font.ITALIC, 14));
			lblVacio.setForeground(colorTexto);
			pnlRanking.add(lblVacio);
		}
		else
		{
			// Inicializamos y decoramos cada fila del Ranking
			for(int i = 0; i < cantidadLabel; i++)
			{
				Label lblFila = new Label("", Label.LEFT);
				lblFila.setFont(new Font("Consolas", Font.BOLD, 14)); // Letra monoespaciada para alinear los números
				lblFila.setForeground(colorTexto);
				lblFila.setBackground(colorFondoFilas);
				
				lblList.add(lblFila);
				pnlRanking.add(lblFila);
			}
		}
		add(pnlRanking);
		
		// 4. Estilo del Botón Volver
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(colorBotonAccion);
		btnVolver.setForeground(colorTexto);
		btnVolver.setPreferredSize(new java.awt.Dimension(140, 30));
		
		// Panel inferior para separar cómodamente el botón
		Panel pnlBoton = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		pnlBoton.add(btnVolver);
		add(pnlBoton);
		
		// 5. Ajustes de dimensiones y despliegue
		// Escalamos la altura dinámicamente según cuántos jugadores vengan de la BD
		int alturaVentana = 140 + (filas * 34); 
		setSize(420, Math.max(alturaVentana, 250)); // Mínimo de 250px de alto por estética
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}