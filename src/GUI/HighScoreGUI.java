package GUI;

import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo on 03.04.2017.
 */
public class HighScoreGUI extends JFrame{


        public HighScoreGUI() {
            initComponents();
            this.setResizable(false);
        }

        private void initComponents() {
            Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            int taskBarHeight = scrnSize.height - winSize.height;


            //Gesamtbreite und Gesamthöhe bestimmen
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
            int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;


            //Größe, Location und Methode zum Schliessen setzen
            this.setSize(width, height);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);


            //Ein Panel für den ganzen Bildschirm erstellen (mit Layout, Background-Farbe und Größe
            JPanel gesamtPanel = new JPanel();
            gesamtPanel.setBackground(Color.white);
            gesamtPanel.setLayout(null);
            gesamtPanel.setSize(width, height);


            //Das Gesamtpanel in einen Container hinzufügen
            Container cont = this.getContentPane();
            cont.setLayout(new BorderLayout());
            cont.add(gesamtPanel, BorderLayout.CENTER);


            //Label für die Überschrift erstelen und dieses dann formatieren
            JLabel ueberschrift = new JLabel();
            ueberschrift.setText("HighScore");
            ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
            ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()-ueberschrift.getHeight()/10));
            ueberschrift.setForeground(Color.yellow);
            ueberschrift.setHorizontalAlignment(JLabel.CENTER);
            gesamtPanel.add(ueberschrift);


            //Label um das Hintergrundbild hinzuzufügen
            JLabel gesamtLabel = new JLabel();
            gesamtLabel.setSize(width, height);
            gesamtLabel.setOpaque(true);
            Image img = null;
            try {
                img = ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(dimg);
            gesamtLabel.setIcon(icon);
            gesamtPanel.add(gesamtLabel);


            //Button um dieses Fenster zu schliessen
            JButton btSchliessen = new JButton();
            btSchliessen.setText("Schließen");
            btSchliessen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
            btSchliessen.setLocation(gesamtPanel.getWidth()/2+btSchliessen.getWidth()-btSchliessen.getWidth()/3, gesamtPanel.getHeight()-2*btSchliessen.getHeight());
            btSchliessen.addActionListener(e -> {
                try {
                    onExit();
                } catch (Exception ex) {
                    System.out.println("Programm konnte nicht beendet werden");
                }
            });
            btSchliessen.setFont(new Font("Arial", Font.BOLD, btSchliessen.getHeight()/3));
            gesamtLabel.add(btSchliessen);


            //Panel um eine Tabelle einzufügen
            JPanel anzeige = new JPanel(new GridLayout(1,1));
            anzeige.setBorder(new TitledBorder("Highscore"));
            anzeige.setBackground(Color.lightGray);
            anzeige.setSize(gesamtPanel.getWidth()/2, gesamtPanel.getHeight()/2+gesamtPanel.getHeight()/10);
            anzeige.setLocation(gesamtPanel.getWidth()/2-anzeige.getWidth()/2, gesamtPanel.getHeight()/2-gesamtPanel.getHeight()/3);
            gesamtLabel.add(anzeige);


            //Tabelle in der die HighScores angezeigt werden, wird dann in das Panel hinzugefügt
            JTable highscore = new JTable(10,2);
            highscore.setRowHeight(anzeige.getHeight()/11);
            highscore.setOpaque(true);
            anzeige.add(highscore);
        }

        private void onExit() {
            this.dispose();
        }


        public static void main(String[] args) {
            new HighScoreGUI().setVisible(true);

        }
    }


