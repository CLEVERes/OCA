package es.studium;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

public class VistaJuegoOCA extends Frame
{
	private static final long serialVersionUID = 1L;

	Image imgTablero = Toolkit.getDefaultToolkit().getImage("tablero.jpg");
	Image imgJugador1 = Toolkit.getDefaultToolkit().getImage("jugador1.png");
	Image dadoSeleccionado = Toolkit.getDefaultToolkit().getImage("dado6.png");;
	
	Button btnDado = new Button("Lanzar dado");
	
	
	int posJ1 = 0;

	int[][] casilla =
	{
			{ 195, 670 }, { 275, 690 }, { 350, 690 }, { 430, 690 }, { 510, 690 },
			{ 585, 690 }, { 660, 700 }, { 700, 650 }, { 690, 580 }, { 690, 515 },
			{ 690, 445 }, { 690, 375 }, { 690, 300 }, { 690, 230 }, { 690, 160 },
			{ 700, 85 },  { 650, 50 },  { 580, 55 },  { 515, 55 },  { 445, 55 },
			{ 370, 55 },  { 300, 55 },  { 230, 55 },  { 160, 55 },  { 90, 55 },
			{ 45, 105 },  { 60, 170 },  { 60, 235 },  { 60, 300 },  { 60, 365 },
			{ 60, 430 },  { 60, 495 },  { 55, 565 },  { 100, 605 }, { 175, 605 },
			{ 240, 605 }, { 305, 605 }, { 370, 605 }, { 435, 605 }, { 500, 605 },
			{ 565, 605 }, { 610, 550 }, { 600, 490 }, { 600, 430 }, { 600, 370 },
			{ 600, 310 }, { 600, 250 }, { 600, 175 }, { 555, 135 }, { 480, 145 },
			{ 410, 145 }, { 340, 145 }, { 260, 145 }, { 190, 145 }, { 145, 195 },
			{ 155, 265 }, { 155, 335 }, { 155, 405 }, { 150, 475 }, { 200, 515 },
			{ 275, 510 }, { 345, 510 }, { 340, 380 }
	};

	Image bufferImagen = null;
	Graphics bufferGraphics = null;
	
	Canvas cnvTablero = new Canvas()
	{
		private static final long serialVersionUID = 1L;

		@Override
		public void update(Graphics g)
		{
			paint(g);
		}
		
		@Override
		public void paint(Graphics g)
		{
			if (bufferImagen == null)
			{
				bufferImagen = createImage(800, 800);
				bufferGraphics = bufferImagen.getGraphics();
			}
			
			bufferGraphics.drawImage(imgTablero, 0, 0, 800, 800, this);
			int jx = casilla[posJ1][0];
			int jy = casilla[posJ1][1];
			bufferGraphics.drawImage(imgJugador1, jx, jy, 50, 50, this);
			
			g.drawImage(bufferImagen, 0, 0, this);
		}
	};
	
	Canvas cnvDado = new Canvas()
	{
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g)
		{
			g.drawImage(dadoSeleccionado, 0, 0, 50, 50, this);
		}
	};
	
	Label lblNombreJ1 = new Label("");
	Label lblTiradasJ1 = new Label("Tiradas: 0");
	
	Panel pnl1 = new Panel();
	Panel pnl2 = new Panel();
	Panel pnl3 = new Panel();

	Dialog dlgSalir = new Dialog(this, "Salir?", true);
	Label lblDlgSalir = new Label("Deseas salir del juego?");
	Button btnSiSalir = new Button("Si");
	Button btnNoSalir = new Button("No");
	Panel pnlDlgSalir1 = new Panel();
	Panel pnlDlgSalir2 = new Panel();
	
	Dialog dlgEvento = new Dialog(this, "", true);
	Label lblDlgEvento = new Label("");
	Button btnContinuarEvento = new Button("Continuar");
	
	Dialog dlgFin = new Dialog(this, "Partida finalizada", true);
	Label lblDlgFin = new Label("Deseas jugar denuevo?");
	Button btnSiFin = new Button("Si");
	Button btnNoFin = new Button("No");


	// Paleta de colores globales
	Color colorFondo = new Color(44, 62, 80);       // Azul oscuro
	Color colorTexto = new Color(236, 240, 241);     // Blanco tiza
	Color colorBotonAccion = new Color(39, 174, 96); // Verde
	Color colorBotonPeligro = new Color(192, 57, 43); // Rojo
	Color colorFondoDialogo = new Color(52, 73, 94);  // Gris azulado

	public VistaJuegoOCA(int cantidadJugador)
	{
		// 1. Configuración de la ventana principal
		setTitle("El Juego de la OCA");
		setBackground(colorFondo);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

		// 2. Estilos del panel de control superior
		btnDado.setFont(new Font("Arial", Font.BOLD, 16));
		btnDado.setBackground(colorBotonAccion);
		btnDado.setForeground(colorTexto);

		

		Panel pnlControles = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		pnlControles.add(btnDado);
		pnlControles.add(cnvDado);
		cnvDado.setSize(50,50);
		
		add(pnlControles);
		
		// 3. Agregar el tablero
		cnvTablero.setSize(800, 800);
		pnl2.add(cnvTablero);
		add(pnl2);
		
		lblNombreJ1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombreJ1.setForeground(colorTexto);
		pnl3.add(lblNombreJ1);
		add(pnl3);
		
		lblTiradasJ1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTiradasJ1.setForeground(colorTexto);
		pnl3.add(lblTiradasJ1);
		add(pnl3);
		
		setSize(860, 1000);
		setResizable(false);
		setLocationRelativeTo(null);

		// =========================================================================
		// CONFIGURACIÓN DIRECTA DEL DIÁLOGO: SALIR
		// =========================================================================
		dlgSalir.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		dlgSalir.setBackground(colorFondoDialogo);

		lblDlgSalir.setFont(new Font("Arial", Font.BOLD, 14));
		lblDlgSalir.setForeground(colorTexto);

		btnSiSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSiSalir.setBackground(colorBotonAccion);
		btnSiSalir.setForeground(colorTexto);

		btnNoSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnNoSalir.setBackground(colorBotonPeligro);
		btnNoSalir.setForeground(colorTexto);

		btnSiSalir.setPreferredSize(new java.awt.Dimension(75,25));
		btnNoSalir.setPreferredSize(new java.awt.Dimension(75,25));
		pnlDlgSalir1.add(lblDlgSalir);
		dlgSalir.add(pnlDlgSalir1);
		pnlDlgSalir2.add(btnSiSalir);
		pnlDlgSalir2.add(btnNoSalir);
		dlgSalir.add(pnlDlgSalir2);

		dlgSalir.setSize(250, 150);
		dlgSalir.setResizable(false);
		dlgSalir.setLocationRelativeTo(this);
		dlgSalir.setVisible(false);
		
		// =========================================================================
		// CONFIGURACIÓN DIRECTA DEL DIÁLOGO: FIN DE PARTIDA
		// =========================================================================
		dlgFin.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		dlgFin.setBackground(colorFondoDialogo);

		lblDlgFin.setFont(new Font("Arial", Font.BOLD, 14));
		lblDlgFin.setForeground(colorTexto);

		btnSiFin.setFont(new Font("Arial", Font.BOLD, 12));
		btnSiFin.setBackground(colorBotonAccion);
		btnSiFin.setForeground(colorTexto);

		btnNoFin.setFont(new Font("Arial", Font.BOLD, 12));
		btnNoFin.setBackground(colorBotonPeligro);
		btnNoFin.setForeground(colorTexto);

		btnSiFin.setPreferredSize(new java.awt.Dimension(75,20));
		btnNoFin.setPreferredSize(new java.awt.Dimension(75,20));
		
		dlgFin.add(lblDlgFin);
		dlgFin.add(btnSiFin);
		dlgFin.add(btnNoFin);

		dlgFin.setSize(250, 120);
		dlgFin.setResizable(false);
		dlgFin.setLocationRelativeTo(this);
		dlgFin.setVisible(false);
		
		//DLG de oca en oca
		
		dlgEvento.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		dlgEvento.setBackground(colorFondoDialogo);

		lblDlgEvento.setFont(new Font("Arial", Font.BOLD, 14));
		lblDlgEvento.setForeground(colorTexto);

		btnContinuarEvento.setFont(new Font("Arial", Font.BOLD, 12));
		btnContinuarEvento.setBackground(colorBotonAccion);
		btnContinuarEvento.setForeground(colorTexto);

		btnContinuarEvento.setPreferredSize(new java.awt.Dimension(150,25));
		
		dlgEvento.add(lblDlgEvento);
		dlgEvento.add(btnContinuarEvento);

		dlgEvento.setSize(250, 150);
		dlgEvento.setResizable(false);
		dlgEvento.setLocationRelativeTo(this);
		dlgEvento.setVisible(false);

		setVisible(true);
	}
}