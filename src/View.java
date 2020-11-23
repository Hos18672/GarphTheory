

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.Font;

import java.util.ArrayList;

/**
 * Created by Neo on 4.02.2017.
 */
class View extends JFrame
{

    private Graph g = new Graph();
    private JPanel AdjPanel, AdjPanelInfo, DistPanel, DistPanelInfo,
            WegPanel, WegPanelInfo, InfoPanel, InfoPanel1, mainPanel;
    private JLabel radius,durchmesser,extrencitaet,zentrum,bridge,info,
        komponents,komponentsOutput,Zusammenhaenged, Articulation;
    private JLabel radius1,durchmesser1,extrencitaet1,zentrum1,bridge1,
            komponents1,komponentsOutput1,Zusammenhaenged1, Articulation1;
    private JPanel panel;
    private int laenge, num;;
    private JButton[][] AdjBtn;
    private JButton[][] DistBtn;
    private JButton[][] WegBtn;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu elementMenu;


    public  View() {
        g.initAll();
        setlaenge(g.readCSVFile().length);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        addComponents();
        setTitle("The-Program");
        pack();
        setLocationRelativeTo(null);
        setTitle("Testing");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public int laenge() {
        return laenge = laenge;
    }
    public void setlaenge(int laenge) {
        this.laenge = laenge;
    }
    public void initComponents() {
        panel = new JPanel();

        AdjBtn   = new JButton[laenge()][laenge];
        DistBtn  = new JButton[laenge][laenge];
        WegBtn  = new JButton[laenge][laenge];
        menuBar  = new JMenuBar();
        fileMenu = new JMenu("File"); // Create File menu
        elementMenu = new JMenu("Weiter"); // Create Elements men
        AdjPanel = new JPanel();
        AdjPanel.setLayout(new GridLayout(laenge, laenge));
        DistPanel = new JPanel();
        DistPanel.setLayout(new GridLayout(laenge, laenge));
        WegPanel = new JPanel();
        WegPanel.setLayout(new GridLayout(laenge, laenge));
        AdjPanelInfo = new JPanel(new BorderLayout());
        DistPanelInfo = new JPanel(new BorderLayout());
        WegPanelInfo = new JPanel(new BorderLayout());
        InfoPanel = new JPanel(new BorderLayout());
        InfoPanel1 = new JPanel(new GridLayout(2,1));
        mainPanel = new JPanel(new GridLayout(2, 2));

        AdjPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
        DistPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
        WegPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
        panel.setLayout(new GridLayout(9,2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        AdjPanelInfo.setSize(20, 10);
        DistPanelInfo.setSize(20, 10);
        WegPanelInfo.setSize(20, 10);
        InfoPanel.setSize(20, 10);
        mainPanel.setSize(600, 900);

        // create Labels for Infos
         radius = new JLabel();
         info = new JLabel("Info");
         Font f = radius.getFont();
         bridge = new JLabel();
         durchmesser = new JLabel();
         extrencitaet = new JLabel();
         zentrum = new JLabel();
         komponents = new JLabel();
         komponentsOutput = new JLabel();
         Zusammenhaenged = new JLabel();
         Articulation = new JLabel();
         radius.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         extrencitaet.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         komponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         Zusammenhaenged.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
         bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    }
    public void addComponents() {

        fileMenu.add(g.getNewMenuItem());
        menuBar.add(fileMenu); // Add the file menu
        menuBar.add(elementMenu); // Add the element menu
        AdjPanelInfo.add(new JScrollPane(AdjPanel));
        // AdjPanelInfo.add(new JLabel("AdjMatrix", JLabel.CENTER), BorderLayout.PAGE_START);
        JLabel lableInfo = new JLabel("Info",JLabel.CENTER);
        lableInfo.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));

        DistPanelInfo.add(new JScrollPane(DistPanel));
        AdjPanelInfo.add(new JLabel("AdjMatrix", JLabel.CENTER), BorderLayout.PAGE_START);
        DistPanelInfo.add(new JLabel("DistMatrix", JLabel.CENTER), BorderLayout.PAGE_START);
        WegPanelInfo.add(new JScrollPane(WegPanel));
        WegPanelInfo.add(new JLabel("WegMatrix", JLabel.CENTER), BorderLayout.PAGE_START);
        InfoPanel.add(InfoPanel1, BorderLayout.PAGE_START);
        InfoPanel.add(lableInfo, BorderLayout.PAGE_START);

        mainPanel.add(AdjPanelInfo);
        mainPanel.add(new JScrollPane(InfoPanel));
        mainPanel.add(DistPanelInfo);
        mainPanel.add(WegPanelInfo);

        // create Labels for Infos
        radius = new JLabel("Radius: ");
        Font f = radius.getFont();
        radius.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        durchmesser = new JLabel("Durchmesser: ");
        durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        extrencitaet = new JLabel("Extrencicität: ");
        extrencitaet.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        zentrum = new JLabel("Zentrum: ");
        zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        komponents = new JLabel("KomponentsNum: ");
        komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        komponentsOutput = new JLabel("Komponents: ");
        komponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Zusammenhaenged = new JLabel("Zusammenhengend: ");
        Zusammenhaenged.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Articulation = new JLabel("Articulation: ");
        Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        bridge = new JLabel("Brücken: ");
        bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        //-----------------------------------------------------
        radius1 = new JLabel("  " + g.getRadius());
        Font f1 = radius.getFont();
        radius.setFont(f1.deriveFont(f.getStyle() | Font.BOLD));
        durchmesser1 = new JLabel("  " + g.getDurchmesser());
        durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        extrencitaet1 = new JLabel("  " + g.exzentrizitaet());
        extrencitaet.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        zentrum1 = new JLabel("  " + g.getZentrum());
        zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        komponents1 = new JLabel("  " + g.komponentenanzahl());
        komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        komponentsOutput1 = new JLabel("  " + g.komponenteAusgeben());
        komponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Zusammenhaenged1 = new JLabel("  "+g.isZusammenhaengend());
        Zusammenhaenged1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Articulation1 = new JLabel("  " +g.getApPrint());
        Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        bridge1 = new JLabel("  " + g.getBridgePrint());
        bridge1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        // Create Panels
        // Add all Info into Panel Info
        // constraints.insets = new Insets(0,0,2,0);
        panel.add(extrencitaet);
        panel.add(extrencitaet1);
        panel.add(durchmesser);
        panel.add(durchmesser1);
        panel.add(radius);
        panel.add(radius1);
        panel.add(zentrum);
        panel.add(zentrum1);
        panel.add(bridge1);
        panel.add(bridge1);
        panel.add(Articulation);
        panel.add(Articulation1);
        panel.add(komponents);
        panel.add(komponents1);
        panel.add(komponentsOutput);
        panel.add(komponentsOutput1);
        panel.add(Zusammenhaenged1);
        panel.add(Zusammenhaenged1);

        // Add Panel of Infos to the Infopanel
        InfoPanel.add(panel, BorderLayout.WEST);
        //Create Matrix of buttons
        add(mainPanel);
        setJMenuBar(menuBar); // Add the menu bar to the window
        addDistMatrix();
        addWegMatrix();
        addAdjMatrix();
    }
    public void addAdjMatrix() {
        try {
            for (int row = 0; row < laenge; row++) {
                for (int col = 0; col < laenge; col++) {
                    if (row == col) {
                        //Adjatrix
                        String m = g.getAdjacencyMatrix2()[row][col].toString();
                        AdjBtn[row][col] = new JButton(m);
                        AdjBtn[row][col].setFont(new Font("Dialog", Font.PLAIN, 20));
                        AdjPanel.add(AdjBtn[row][col]);
                        AdjBtn[row][col].setBackground(Color.GRAY);
                    } else {
                        //AdjMatrix
                        String m = g.getAdjacencyMatrix2()[row][col].toString();
                        AdjBtn[row][col] = new JButton(m);
                        AdjBtn[row][col].setFont(new Font("Dialog", Font.PLAIN, 20));
                        //AdjMatrix
                        int finalCol = col;
                        int finalRow = row;
                        AdjBtn[row][col].addActionListener(e -> {
                            if (e.getSource() == AdjBtn[finalRow][finalCol] && AdjBtn[finalRow][finalCol].getText() == "1") {
                                AdjBtn[finalRow][finalCol].setText("0");
                                AdjBtn[finalCol][finalRow].setText("0");
                                System.out.println("Hallooooooooooo");

                            } else {
                                AdjBtn[finalRow][finalCol].setText("1");
                                AdjBtn[finalCol][finalRow].setText("1");
                                System.out.println("Hallooooooooooo");
                            }
                            num = Integer.parseInt(AdjBtn[finalRow][finalCol].getText());
                            Integer[][] num2 = new Integer[laenge][laenge];
                            num2[finalRow][finalCol] = num;
                            Graph g1 = new Graph();
                            g1.initialize2(AdjBtn);
                            g1.multiply();
                            g1.ermittle();
                            g1.exzentrizitaet();
                            g1.radiusUndDurchmesser();
                            //addDistancMatrix2(g1.getDistanceMatix());
                            ArrayList<ArrayList<Integer>> adjListArray = g1.convert(g1.getAdjacencyMatrix2());
                            g1.printArrayList(adjListArray);
                            System.out.print("Articulation: ");
                            g1.AP();
                            System.out.println("\nBirdge: ");
                            g1.bridge();
                            g1.printGraph();

                        });
                        AdjBtn[row][col].setBackground(Color.cyan);
                        AdjPanel.add(AdjBtn[row][col]);
                        //mainPanel.remove(DistPanel);

                    }

                }
            }
;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addDistMatrix() {
        try{
            for (int row = 0; row < laenge; row++) {
                for (int col = 0; col < laenge; col++) {
                    if (row == col) {
                        //DistanceMatrix
                        String m1 = g.getDistanceMatix()[row][col].toString();
                        DistBtn[row][col] = new JButton(m1);
                        DistBtn[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                        DistBtn[row][col].setBackground(Color.LIGHT_GRAY);
                        DistPanel.add(DistBtn[row][col]);
                    } else {
                        //DistanceMatrix
                        String m1 = g.getDistanceMatix()[row][col].toString();
                        DistBtn[row][col] = new JButton(m1);
                        DistBtn[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                        DistBtn[row][col].setBackground(Color.WHITE);
                        DistPanel.add(DistBtn[row][col]);
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addWegMatrix() {
        try{
            for (int row = 0; row < laenge; row++) {
                for (int col = 0; col < laenge; col++) {
                    if (row == col) {
                        //WegMatrix
                        String m2 = g.getWegmatrix()[row][col].toString();
                        WegBtn[row][col] = new JButton(m2);
                        WegBtn[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                        WegBtn[row][col].setBackground(Color.LIGHT_GRAY);
                        WegPanel.add(WegBtn[row][col]);

                    } else {
                        //WegMatrix
                        String m2 = g.getWegmatrix()[row][col].toString();
                        WegBtn[row][col] = new JButton(m2);
                        WegBtn[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                        WegBtn[row][col].setBackground(Color.WHITE);
                        WegPanel.add(WegBtn[row][col]);
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        new View();
    }


}
