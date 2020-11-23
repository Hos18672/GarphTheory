

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

    Graph g = new Graph();
    private JPanel AdjPanel, AdjPanelInfo, DistPanel, DistPanelInfo, WegPanel, WegPanelInfo, InfoPanel, InfoPanel1, mainPanel;
    private JPanel panel;
    private int laenge;
    private JButton[][] btn;
    private JButton[][] btn1;
    private JButton[][] btn2;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu elementMenu;
    Font newButtonFont;

    public  View() {
        g.initAll();
        setlaenge(g.readCSVFile().length);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        addComponents();
        addDistMatrix();
        addWegMatrix();
        addAdjMatrix();
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

        btn   = new JButton[laenge()][laenge];
        btn1  = new JButton[laenge][laenge];
        btn2  = new JButton[laenge][laenge];
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
        JLabel radius = new JLabel();
        JLabel Info = new JLabel("Info");
        Font f = radius.getFont();
        JLabel Bridge = new JLabel();
        JLabel durchmesser = new JLabel();
        JLabel extrencität = new JLabel();
        JLabel zentrum = new JLabel();
        JLabel Komponents = new JLabel();
        JLabel KomponentsOutput = new JLabel();
        JLabel Zusammenhengend = new JLabel();
        JLabel Articulation = new JLabel();
        radius.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        extrencität.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        KomponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Zusammenhengend.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        Bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

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
        JLabel radius = new JLabel("Radius: ");
        Font f = radius.getFont();
        radius.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel durchmesser = new JLabel("Durchmesser: ");
        durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel extrencitaet = new JLabel("Extrencicität: ");
        extrencitaet.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel zentrum = new JLabel("Zentrum: ");
        zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Komponents = new JLabel("KomponentsNum: ");
        Komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel KomponentsOutput = new JLabel("Komponents: ");
        KomponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Zusammenhengend = new JLabel("Zusammenhengend: ");
        Zusammenhengend.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Articulation = new JLabel("Articulation: ");
        Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Bridge = new JLabel("Brücken: ");
        Bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        JLabel radius1 = new JLabel("  " + g.getRadius());
        Font f1 = radius.getFont();
        radius.setFont(f1.deriveFont(f.getStyle() | Font.BOLD));
        JLabel durchmesser1 = new JLabel("  " + g.getDurchmesser());
        durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel extrencitaet1 = new JLabel("  " + g.exzentrizitaet());
        extrencitaet.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel zentrum1 = new JLabel("  " + g.getZentrum());
        zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Komponents1 = new JLabel("  " + g.komponentenanzahl());
        Komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel KomponentsOutput1 = new JLabel("  " + g.komponenteAusgeben());
        KomponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Zusammenhengend1 = new JLabel("  "+g.isZusammenhaengend());
        Zusammenhengend.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Articulation1 = new JLabel("  " +g.getApPrint());
        Articulation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        JLabel Bridge1 = new JLabel("  " + g.getBridgePrint());
        Bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

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
        panel.add(Bridge);
        panel.add(Bridge1);
        panel.add(Articulation);
        panel.add(Articulation1);
        panel.add(Komponents);
        panel.add(Komponents1);
        panel.add(KomponentsOutput);
        panel.add(KomponentsOutput1);
        panel.add(Zusammenhengend);
        panel.add(Zusammenhengend1);

        // Add Panel of Infos to the Infopanel
        InfoPanel.add(panel, BorderLayout.WEST);
        //Create Matrix of buttons
        add(mainPanel);
        setJMenuBar(menuBar); // Add the menu bar to the window
    }
    public void addAdjMatrix() {
        for (int row = 0; row < laenge; row++) {
            for (int col = 0; col < laenge; col++) {
                if (row == col) {
                    //Adjatrix
                    String m = g.getAdjacencyMatrix2()[row][col].toString();
                    btn[row][col] = new JButton(m);
                    btn[row][col].setFont(new Font("Dialog",Font.PLAIN, 20));
                    AdjPanel.add(btn[row][col]);
                    btn[row][col].setBackground(Color.GRAY);
                } else {
                    //AdjMatrix
                    String m = g.getAdjacencyMatrix2()[row][col].toString();
                    btn[row][col] = new JButton(m);
                    btn[row][col].setFont(new Font("Dialog",Font.PLAIN, 20));
                    //AdjMatrix
                    int finalCol = col;
                    int finalRow = row;
                    btn[row][col].addActionListener(e -> {
                        if (e.getSource() == btn[finalRow][finalCol] && btn[finalRow][finalCol].getText() == "1") {
                            btn[finalRow][finalCol].setText("0");
                            btn[finalCol][finalRow].setText("0");
                            System.out.println("Hallooooooooooo");

                        } else {
                            btn[finalRow][finalCol].setText("1");
                            btn[finalCol][finalRow].setText("1");
                            System.out.println("Hallooooooooooo");
                        }

                        int num = 0;
                        num = Integer.parseInt(btn[finalRow][finalCol].getText());
                        Integer[][] num2 = new Integer[laenge][laenge];
                        num2[finalRow][finalCol] = num;
                        Graph g1 = new Graph();
                        g1.initialize2(btn);
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
                    btn[row][col].setBackground(Color.cyan);
                    AdjPanel.add(btn[row][col]);
                    //mainPanel.remove(DistPanel);

                }

            }
        }
    }



    public void addDistMatrix() {
        for (int row = 0; row < laenge; row++) {
            for (int col = 0; col < laenge; col++) {
                if (row == col) {
                    //DistanceMatrix
                    String m1 = g.getDistanceMatix()[row][col].toString();
                    btn1[row][col] = new JButton(m1);
                    btn1[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                    btn1[row][col].setBackground(Color.LIGHT_GRAY);
                    DistPanel.add(btn1[row][col]);
                } else {
                    //DistanceMatrix
                    String m1 = g.getDistanceMatix()[row][col].toString();
                    btn1[row][col] = new JButton(m1);
                    btn1[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                    btn1[row][col].setBackground(Color.WHITE);
                    DistPanel.add(btn1[row][col]);

                }
            }
        }
    }
    public void addWegMatrix() {
        for (int row = 0; row < laenge; row++) {
            for (int col = 0; col < laenge; col++) {
                if (row == col) {
                    //WegMatrix
                    String m2 = g.getWegmatrix()[row][col].toString();
                    btn2[row][col] = new JButton(m2);
                    btn2[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                    btn2[row][col].setBackground(Color.LIGHT_GRAY);
                    WegPanel.add(btn2[row][col]);

                } else {
                    //WegMatrix
                    String m2 = g.getWegmatrix()[row][col].toString();
                    btn2[row][col] = new JButton(m2);
                    btn2[row][col].setFont(new Font("Dialog",Font.PLAIN, 17));
                    btn2[row][col].setBackground(Color.WHITE);
                    WegPanel.add(btn2[row][col]);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        new View();
    }


}
