package es.studium;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorOCA extends WindowAdapter implements ActionListener, KeyListener
{
	ModeloOCA moca;
	VistaInicioOCA vioca;
	VistaOpcionesOCA vooca;
	VistaJuegoOCA vjoca;
	VistaRankingOCA vroca;

	int cantidadJugador;
	int[] casillaOCA = { 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 56 };
	int saltoOCA = 0;
	boolean boolCasillaOCA = false;
	int[] casillaPuente = { 6, 12 };
	int saltoPuente = 0;
	int retrocesoLaberinto = 0;
	int retrocesoMuerte = 1;
	int tipoEvento = 0;
	boolean retroceso = false;
	String nombreGanador;
	ArrayList<Integer> tiradasJugadores = new ArrayList<Integer>();
	int tiradasGanador;
	ArrayList<String> listaRanking = new ArrayList<String>();
	int cantidadLabel;
	int dado;
	String refDado; 
	int contadorJugador;
	String txfJugador;
	int indiceTirador = 0; 

	public ControladorOCA(ModeloOCA moca, VistaInicioOCA vioca)
	{
		this.moca = moca;
		this.vioca = vioca;

		vioca.addWindowListener(this);
		vioca.btnInicio.addActionListener(this);
		vioca.btnRanking.addActionListener(this);
		vioca.btnAyuda.addActionListener(this);
	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void actionPerformed(ActionEvent e)
	{
		if (vioca != null)
		{
			if (e.getSource().equals(vioca.btnAyuda))
			{
				try
		        {
					File archivoPDF = new File("ayuda.pdf");
					Desktop.getDesktop().open(archivoPDF);
		        }
				
				catch (IOException ioe)
		        {
		            // Capturamos cualquier error de entrada/salida
		            ioe.printStackTrace();
		        }
				
			}
			else if (e.getSource().equals(vioca.btnRanking))
			{
				listaRanking = moca.consultarRanking();
				cantidadLabel = listaRanking.size();
				this.vroca = new VistaRankingOCA(cantidadLabel);
				this.vroca.addWindowListener(this);
				this.vroca.btnVolver.addActionListener(this);
				for (int i = 0; i < cantidadLabel; i++)
				{
					vroca.lblList.get(i).setText(listaRanking.get(i));
				}
				vioca.setVisible(false);
			}
			else if (e.getSource().equals(vioca.btnInicio))
			{
				vooca = new VistaOpcionesOCA();
				vooca.addWindowListener(this);
				vooca.dlg.addWindowListener(this);
				vooca.btnJugar.addActionListener(this);
				vooca.btnContinuar.addActionListener(this);
				vioca.setVisible(false);
			}
		}

		if (vroca != null)
		{
			if (e.getSource().equals(vroca.btnVolver))
			{
				vioca.setVisible(true);
				vroca.dispose();
			}
		}

		if (vooca != null)
		{
			if (e.getSource().equals(vooca.btnJugar))
			{
				if (vooca.chbUno.getState() == true) { cantidadJugador = 1; }
				else if (vooca.chbDos.getState() == true) { cantidadJugador = 2; }
				else if (vooca.chbTres.getState() == true) { cantidadJugador = 3; }
				else if (vooca.chbCuatro.getState() == true) { cantidadJugador = 4; }

				tiradasJugadores.clear();
				for (int i = 0; i < cantidadJugador; i++)
				{
					tiradasJugadores.add(0);
				}

				crearVistaJuego();
				vjoca.lblNombreJ.get(0).setForeground(vjoca.colorJActivo);

				vjoca.cantidadJugadorIMG = cantidadJugador;
				contadorJugador = 0;
				vooca.lblDlg.setText("Jugador " + 1);
				vooca.txfDlg.setText("");
				vooca.dlg.setVisible(true);
			}
			else if (e.getSource().equals(vooca.btnContinuar))
			{
				txfJugador = vooca.txfDlg.getText();
				if (txfJugador.trim().equals(""))
				{
					txfJugador = "Jugador " + (contadorJugador + 1);
				}
				vjoca.lblNombreJ.get(contadorJugador).setText(txfJugador);
				contadorJugador++;

				if (contadorJugador < cantidadJugador)
				{
					vooca.lblDlg.setText("Jugador " + (contadorJugador + 1));
					vooca.txfDlg.setText("");
				} 
				else
				{
					vooca.dlg.dispose();
					vooca.dispose();
					vjoca.setVisible(true);
				}
			}
		}

		if (vjoca != null)
		{
			if (e.getSource().equals(vjoca.btnDado))
			{
				dado = moca.lanzarDado();
				refDado = String.valueOf(dado);
				vjoca.dadoSeleccionado = Toolkit.getDefaultToolkit().getImage("dado" + refDado + ".png");

				int tiradaActual = tiradasJugadores.get(indiceTirador) + 1;
				tiradasJugadores.set(indiceTirador, tiradaActual);
				vjoca.lblTiradasJ.get(indiceTirador).setText("Tiradas: " + tiradaActual);
				
				vjoca.lblNombreJ.get(indiceTirador).setForeground(vjoca.colorTexto);

				if (indiceTirador == 0)
				{
					vjoca.posJ1 += dado;
					vjoca.posJ1 = comprobarRebote(vjoca.posJ1);
					int posAntesDeSaltar = vjoca.posJ1; 

					saltoOCA = comprobarCasillaOCA(vjoca.posJ1);
					if (saltoOCA != 0)
					{
						vjoca.posJ1 = saltoOCA;
						realizarCasillaOCA();
					}
					saltoPuente = comprobarCasillaPuente(posAntesDeSaltar);
					if (saltoPuente != 0)
					{
						vjoca.posJ1 = saltoPuente;
						realizarCasillaPuente(posAntesDeSaltar); 
					}
					retrocesoLaberinto = comprobarCasillaLaberinto(posAntesDeSaltar);
					if (retrocesoLaberinto != 0)
					{
						vjoca.posJ1 = retrocesoLaberinto;
						realizarCasillaLaberinto(posAntesDeSaltar);
					}
					retrocesoMuerte = comprobarCasillaMuerte(vjoca.posJ1);
					if (retrocesoMuerte == 0)
					{
						vjoca.posJ1 = retrocesoMuerte;
						realizarCasillaMuerte(vjoca.posJ1);
					}
					comprobarCasillaFinal(vjoca.posJ1);
				}
				else if (indiceTirador == 1)
				{
					vjoca.posJ2 += dado;
					vjoca.posJ2 = comprobarRebote(vjoca.posJ2);
					int posAntesDeSaltar = vjoca.posJ2;

					saltoOCA = comprobarCasillaOCA(vjoca.posJ2);
					if (saltoOCA != 0)
					{
						vjoca.posJ2 = saltoOCA;
						realizarCasillaOCA();
					}
					saltoPuente = comprobarCasillaPuente(posAntesDeSaltar);
					if (saltoPuente != 0)
					{
						vjoca.posJ2 = saltoPuente;
						realizarCasillaPuente(posAntesDeSaltar);
					}
					retrocesoLaberinto = comprobarCasillaLaberinto(posAntesDeSaltar);
					if (retrocesoLaberinto != 0)
					{
						vjoca.posJ2 = retrocesoLaberinto;
						realizarCasillaLaberinto(posAntesDeSaltar);
					}
					retrocesoMuerte = comprobarCasillaMuerte(vjoca.posJ2);
					if (retrocesoMuerte == 0)
					{
						vjoca.posJ2 = retrocesoMuerte;
						realizarCasillaMuerte(vjoca.posJ2);
					}
					comprobarCasillaFinal(vjoca.posJ2);
				}
				else if (indiceTirador == 2)
				{
					vjoca.posJ3 += dado;
					vjoca.posJ3 = comprobarRebote(vjoca.posJ3);
					int posAntesDeSaltar = vjoca.posJ3;

					saltoOCA = comprobarCasillaOCA(vjoca.posJ3);
					if (saltoOCA != 0)
					{
						vjoca.posJ3 = saltoOCA;
						realizarCasillaOCA();
					}
					saltoPuente = comprobarCasillaPuente(posAntesDeSaltar);
					if (saltoPuente != 0)
					{
						vjoca.posJ3 = saltoPuente;
						realizarCasillaPuente(posAntesDeSaltar);
					}
					retrocesoLaberinto = comprobarCasillaLaberinto(posAntesDeSaltar);
					if (retrocesoLaberinto != 0)
					{
						vjoca.posJ3 = retrocesoLaberinto;
						realizarCasillaLaberinto(posAntesDeSaltar);
					}
					retrocesoMuerte = comprobarCasillaMuerte(vjoca.posJ3);
					if (retrocesoMuerte == 0)
					{
						vjoca.posJ3 = retrocesoMuerte;
						realizarCasillaMuerte(vjoca.posJ3);
					}
					comprobarCasillaFinal(vjoca.posJ3);
				}
				else if (indiceTirador == 3)
				{
					vjoca.posJ4 += dado;
					vjoca.posJ4 = comprobarRebote(vjoca.posJ4);
					int posAntesDeSaltar = vjoca.posJ4;

					saltoOCA = comprobarCasillaOCA(vjoca.posJ4);
					if (saltoOCA != 0)
					{
						vjoca.posJ4 = saltoOCA;
						realizarCasillaOCA();
					}
					saltoPuente = comprobarCasillaPuente(posAntesDeSaltar);
					if (saltoPuente != 0)
					{
						vjoca.posJ4 = saltoPuente;
						realizarCasillaPuente(posAntesDeSaltar);
					}
					retrocesoLaberinto = comprobarCasillaLaberinto(posAntesDeSaltar);
					if (retrocesoLaberinto != 0)
					{
						vjoca.posJ4 = retrocesoLaberinto;
						realizarCasillaLaberinto(posAntesDeSaltar);
					}
					retrocesoMuerte = comprobarCasillaMuerte(vjoca.posJ4);
					if (retrocesoMuerte == 0)
					{
						vjoca.posJ4 = retrocesoMuerte;
						realizarCasillaMuerte(vjoca.posJ4);
					}
					comprobarCasillaFinal(vjoca.posJ4);
				}
				
				indiceTirador++; 
				if (indiceTirador >= cantidadJugador)
				{
					indiceTirador = 0; 
				}

				vjoca.lblNombreJ.get(indiceTirador).setForeground(vjoca.colorJActivo);
				vjoca.cnvDado.repaint();
				vjoca.cnvTablero.repaint();
			}
			else if (e.getSource().equals(vjoca.btnNoSalir))
			{
				vjoca.dlgSalir.dispose();
			}
			else if (e.getSource().equals(vjoca.btnSiSalir))
			{
				tiradasGanador = 0;
				indiceTirador = 0;
				vioca.setVisible(true);
				vjoca.dispose();
			}
			else if (e.getSource().equals(vjoca.btnNoFin))
			{
				moca.ranking(nombreGanador, tiradasGanador);
				tiradasGanador = 0;
				indiceTirador = 0;
				vioca.setVisible(true);
				vjoca.dispose();
			}
			else if (e.getSource().equals(vjoca.btnSiFin))
			{
				moca.ranking(nombreGanador, tiradasGanador);
				tiradasGanador = 0;
				indiceTirador = 0;
				vjoca.dispose();
				crearVistaJuego();
			}
			else if (e.getSource().equals(vjoca.btnContinuarEvento))
			{
				saltoOCA = 0;
				saltoPuente = 0;
				retrocesoLaberinto = 0;
				retrocesoMuerte = 1;
				tipoEvento = 0;

				vjoca.cnvTablero.repaint();
				vjoca.dlgEvento.dispose();
			}
		}
	}

	public void windowClosing(WindowEvent e)
	{
		if (vioca != null && e.getSource().equals(vioca)) { System.exit(0); }
		else if (vroca != null && e.getSource().equals(vroca))
		{
			vioca.setVisible(true);
			vroca.dispose();
		}
		else if (vooca != null && e.getSource().equals(vooca))
		{
			vioca.setVisible(true);
			vooca.dispose();
		}
		else if (vooca != null && e.getSource().equals(vooca.dlg)) { vooca.dlg.dispose(); }
		else if (vjoca != null && e.getSource().equals(vjoca)) { vjoca.dlgSalir.setVisible(true); }
		else if (vjoca != null && e.getSource().equals(vjoca.dlgSalir))
		{
			tiradasGanador = 0;
			vjoca.dlgSalir.dispose();
		}
		else if (vjoca != null && e.getSource().equals(vjoca.dlgFin))
		{
			vioca.setVisible(true);
			vjoca.dispose();
		}
		else if (vjoca != null && e.getSource().equals(vjoca.dlgEvento)) { vjoca.dlgEvento.dispose(); }
	}

	public void crearVistaJuego()
	{
		vjoca = new VistaJuegoOCA(cantidadJugador);
		vjoca.addWindowListener(this);
		vjoca.dlgSalir.addWindowListener(this);
		vjoca.dlgFin.addWindowListener(this);
		vjoca.dlgEvento.addWindowListener(this);
		vjoca.btnDado.addActionListener(this);
		vjoca.btnSiSalir.addActionListener(this);
		vjoca.btnNoSalir.addActionListener(this);
		vjoca.btnSiFin.addActionListener(this);
		vjoca.btnNoFin.addActionListener(this);
		vjoca.btnContinuarEvento.addActionListener(this);
	}

	public int comprobarCasillaOCA(int posJ)
	{
		boolCasillaOCA = false;
		for (int i = 0; i < casillaOCA.length; i++)
		{
			if (casillaOCA[i] - 1 == posJ && i < casillaOCA.length - 1 && boolCasillaOCA == false)
			{
				saltoOCA = casillaOCA[i + 1] - 1;
				boolCasillaOCA = true;
			}
		}
		return saltoOCA;
	}

	public void realizarCasillaOCA()
	{
		vjoca.cnvTablero.repaint();
		vjoca.dlgEvento.setTitle("¡¡¡ENHORABUENA!!!");
		vjoca.lblDlgEvento.setText("¡¡¡De OCA en OCA!!!");
		tipoEvento = 1;
		vjoca.dlgEvento.setVisible(true);
	}

	public int comprobarCasillaPuente(int posJ)
	{
		if (posJ == casillaPuente[0] - 1) 
		{
			saltoPuente = casillaPuente[1] - 1;
		}
		else if (posJ == casillaPuente[1] - 1)
		{
			saltoPuente = casillaPuente[0] - 1;
		}
		else
		{
			saltoPuente = 0;
		}
		return saltoPuente;
	}

	public void realizarCasillaPuente(int posJ)
	{
		vjoca.cnvTablero.repaint();
		if (posJ == casillaPuente[0] - 1) 
		{
			vjoca.dlgEvento.setTitle("¡¡¡ENHORABUENA!!!");
			vjoca.lblDlgEvento.setText("¡¡¡Avanzas en el puente!!!");
		}
		else if (posJ == casillaPuente[1] - 1)
		{
			vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
			vjoca.lblDlgEvento.setText("¡¡¡Retrocedes en el puente!!!");
		}
		tipoEvento = 2;
		vjoca.dlgEvento.setVisible(true);
	}

	public int comprobarCasillaLaberinto(int posJ)
	{
		if (posJ == 41)
		{
			retrocesoLaberinto = posJ - 30; 
		}
		else
		{
			retrocesoLaberinto = 0;
		}
		return retrocesoLaberinto;
	}

	public void realizarCasillaLaberinto(int posJ)
	{
		vjoca.cnvTablero.repaint();
		vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
		vjoca.lblDlgEvento.setText("¡¡¡Retrocedes en el laberinto!!!");
		tipoEvento = 3;
		vjoca.dlgEvento.setVisible(true);
	}

	public int comprobarCasillaMuerte(int posJ)
	{
		if (posJ == 57)
		{
			retrocesoMuerte = 0;
		}
		else
		{
			retrocesoMuerte = 1; 
		}
		return retrocesoMuerte;
	}

	public void realizarCasillaMuerte(int posJ)
	{
		vjoca.cnvTablero.repaint();
		vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
		vjoca.lblDlgEvento.setText("¡¡¡Retrocedes al inicio!!!");
		tipoEvento = 4;
		vjoca.dlgEvento.setVisible(true);
	}

	public int comprobarRebote(int posJ)
	{
		retroceso = false;
		if (posJ > 62) 
		{
			posJ -= dado;
			for (int i = 0; i < dado; i++)
			{
				if (posJ == 61) { retroceso = true; }
				if (posJ < 61 && retroceso == false) { posJ++; }
				else { posJ--; }
			}
		}

		if (posJ == 57)
		{
			posJ = 0;
			realizarCasillaMuerte(posJ);
		}
		vjoca.cnvTablero.repaint();
		return posJ;
	}

	public void comprobarCasillaFinal(int posJ)
	{
		if (posJ == 62)
		{
			tiradasGanador = tiradasJugadores.get(indiceTirador);
			nombreGanador = vjoca.lblNombreJ.get(indiceTirador).getText();
			vjoca.dlgFin.setVisible(true);
		}
	}
}