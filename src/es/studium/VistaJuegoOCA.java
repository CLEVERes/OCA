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
import java.util.ArrayList;

public class VistaJuegoOCA extends Frame
{
	private static final long serialVersionUID = 1L;

	Image imgTablero = Toolkit.getDefaultToolkit().getImage("tablero.jpg");
	Image imgJugador1 = Toolkit.getDefaultToolkit().getImage("jugador1.png");
	Image imgJugador2 = Toolkit.getDefaultToolkit().getImage("jugador2.png");
	Image imgJugador3 = Toolkit.getDefaultToolkit().getImage("jugador3.png");
	Image imgJugador4 = Toolkit.getDefaultToolkit().getImage("jugador4.png");
	Image dadoSeleccionado = Toolkit.getDefaultToolkit().getImage("dado1.png");

	Button btnDado = new Button("Lanzar dado");

	int cantidadJugadorIMG;
	int posJ1 = 0;
	int posJ2 = 0;
	int posJ3 = 0;
	int posJ4 = 0;

	int[][] casilla =
	{
			{ 195, 670 },
			{ 275, 690 },
			{ 350, 690 },
			{ 430, 690 },
			{ 510, 690 },
			{ 585, 690 },
			{ 660, 700 },
			{ 700, 650 },
			{ 690, 580 },
			{ 690, 515 },
			{ 690, 445 },
			{ 690, 375 },
			{ 690, 300 },
			{ 690, 230 },
			{ 690, 160 },
			{ 700, 85 },
			{ 650, 50 },
			{ 580, 55 },
			{ 515, 55 },
			{ 445, 55 },
			{ 370, 55 },
			{ 300, 55 },
			{ 230, 55 },
			{ 160, 55 },
			{ 90, 55 },
			{ 45, 105 },
			{ 60, 170 },
			{ 60, 235 },
			{ 60, 300 },
			{ 60, 365 },
			{ 60, 430 },
			{ 60, 495 },
			{ 55, 565 },
			{ 100, 605 },
			{ 175, 605 },
			{ 240, 605 },
			{ 305, 605 },
			{ 370, 605 },
			{ 435, 605 },
			{ 500, 605 },
			{ 565, 605 },
			{ 610, 550 },
			{ 600, 490 },
			{ 600, 430 },
			{ 600, 370 },
			{ 600, 310 },
			{ 600, 250 },
			{ 600, 175 },
			{ 555, 135 },
			{ 480, 145 },
			{ 410, 145 },
			{ 340, 145 },
			{ 260, 145 },
			{ 190, 145 },
			{ 145, 195 },
			{ 155, 265 },
			{ 155, 335 },
			{ 155, 405 },
			{ 150, 475 },
			{ 200, 515 },
			{ 275, 510 },
			{ 345, 510 },
			{ 340, 380 } };

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

			if (cantidadJugadorIMG == 1)
			{
				int j1x = casilla[posJ1][0];
				int j1y = casilla[posJ1][1];
				bufferGraphics.drawImage(imgJugador1, j1x, j1y, 50, 50, this);
			} else if (cantidadJugadorIMG == 2)
			{
				int j1x = casilla[posJ1][0];
				int j1y = casilla[posJ1][1];
				bufferGraphics.drawImage(imgJugador1, j1x, j1y, 50, 50, this);
				int j2x = casilla[posJ2][0];
				int j2y = casilla[posJ2][1];
				bufferGraphics.drawImage(imgJugador2, j2x, j2y, 50, 50, this);
			} else if (cantidadJugadorIMG == 3)
			{
				int j1x = casilla[posJ1][0];
				int j1y = casilla[posJ1][1];
				bufferGraphics.drawImage(imgJugador1, j1x, j1y, 50, 50, this);
				int j2x = casilla[posJ2][0];
				int j2y = casilla[posJ2][1];
				bufferGraphics.drawImage(imgJugador2, j2x, j2y, 50, 50, this);
				int j3x = casilla[posJ3][0];
				int j3y = casilla[posJ3][1];
				bufferGraphics.drawImage(imgJugador3, j3x, j3y, 50, 50, this);
			} else if (cantidadJugadorIMG == 4)
			{
				int j1x = casilla[posJ1][0];
				int j1y = casilla[posJ1][1];
				bufferGraphics.drawImage(imgJugador1, j1x, j1y, 50, 50, this);
				int j2x = casilla[posJ2][0];
				int j2y = casilla[posJ2][1];
				bufferGraphics.drawImage(imgJugador2, j2x, j2y, 50, 50, this);
				int j3x = casilla[posJ3][0];
				int j3y = casilla[posJ3][1];
				bufferGraphics.drawImage(imgJugador3, j3x, j3y, 50, 50, this);
				int j4x = casilla[posJ4][0];
				int j4y = casilla[posJ4][1];
				bufferGraphics.drawImage(imgJugador4, j4x, j4y, 50, 50, this);
			}

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

	ArrayList<Label> lblNombreJ = new ArrayList<Label>();
	ArrayList<Label> lblTiradasJ = new ArrayList<Label>();

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

	Color colorFondo = new Color(44, 62, 80);
	Color colorTexto = new Color(236, 240, 241);
	Color colorJActivo = new Color(0, 128, 0);
	Color colorBotonAccion = new Color(39, 174, 96);
	Color colorBotonPeligro = new Color(192, 57, 43);
	Color colorFondoDialogo = new Color(52, 73, 94);

	public VistaJuegoOCA(int cantidadJugador)
	{
		setTitle("El Juego de la OCA");
		setBackground(colorFondo);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

		btnDado.setFont(new Font("Arial", Font.BOLD, 16));
		btnDado.setBackground(colorBotonAccion);
		btnDado.setForeground(colorTexto);

		Panel pnlControles = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		pnlControles.add(btnDado);
		pnlControles.add(cnvDado);
		cnvDado.setSize(50, 50);

		add(pnlControles);

		cnvTablero.setSize(800, 800);
		pnl2.add(cnvTablero);
		add(pnl2);

		for (int i = 0; i < cantidadJugador; i++)
		{
			Label lblNombre = new Label();
			lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
			lblNombre.setForeground(colorTexto);
			lblNombreJ.add(lblNombre);
			pnl3.add(lblNombre);

			Label lblTiradas = new Label("Tiradas: 0");
			lblTiradas.setFont(new Font("Arial", Font.BOLD, 16));
			lblTiradas.setForeground(colorTexto);
			lblTiradasJ.add(lblTiradas);
			pnl3.add(lblTiradas);
		}
		add(pnl3);

		setSize(860, 1000);
		setResizable(false);
		setLocationRelativeTo(null);

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

		btnSiSalir.setPreferredSize(new java.awt.Dimension(75, 25));
		btnNoSalir.setPreferredSize(new java.awt.Dimension(75, 25));
		pnlDlgSalir1.add(lblDlgSalir);
		dlgSalir.add(pnlDlgSalir1);
		pnlDlgSalir2.add(btnSiSalir);
		pnlDlgSalir2.add(btnNoSalir);
		dlgSalir.add(pnlDlgSalir2);

		dlgSalir.setSize(250, 150);
		dlgSalir.setResizable(false);
		dlgSalir.setLocationRelativeTo(this);
		dlgSalir.setVisible(false);

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

		btnSiFin.setPreferredSize(new java.awt.Dimension(75, 20));
		btnNoFin.setPreferredSize(new java.awt.Dimension(75, 20));

		dlgFin.add(lblDlgFin);
		dlgFin.add(btnSiFin);
		dlgFin.add(btnNoFin);

		dlgFin.setSize(250, 120);
		dlgFin.setResizable(false);
		dlgFin.setLocationRelativeTo(this);
		dlgFin.setVisible(false);

		dlgEvento.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		dlgEvento.setBackground(colorFondoDialogo);

		lblDlgEvento.setFont(new Font("Arial", Font.BOLD, 14));
		lblDlgEvento.setForeground(colorTexto);

		btnContinuarEvento.setFont(new Font("Arial", Font.BOLD, 12));
		btnContinuarEvento.setBackground(colorBotonAccion);
		btnContinuarEvento.setForeground(colorTexto);

		btnContinuarEvento.setPreferredSize(new java.awt.Dimension(150, 25));

		dlgEvento.add(lblDlgEvento);
		dlgEvento.add(btnContinuarEvento);

		dlgEvento.setSize(250, 150);
		dlgEvento.setResizable(false);
		dlgEvento.setLocationRelativeTo(this);
		dlgEvento.setVisible(false);

		setVisible(false);
	}
}