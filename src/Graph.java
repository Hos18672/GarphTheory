import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Graph {
    private int size;
    private int radius;
    private int durchmesser =0;
    private String zentrum;
    private Integer[][] AdjacencyMatrix2;
    private Integer[][] distanceMatix;
    private Integer[][] wegmatrix;
    private Integer[][] matrixA;
    private int[] exzentrizitaet;
    private int[] posUnique;
    private int time = 0;
    private static final int NIL = -1;
    private ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<>();
    private String inputLine = "";

    private String apPrint = "";
    private String bridgePrint = "";
    private String filelocation;
    private String path = null;
    private JMenuItem newMenuItem = new JMenuItem("New") {public void menuSelectionChanged(boolean isSelected) {
        super.menuSelectionChanged(isSelected);

        if (isArmed()) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("select File");
            fc.showOpenDialog(null);
            File file = fc.getSelectedFile();
            setPath(file.getAbsolutePath());

            System.out.println("The Path" + file.getPath());
        } else {
            System.out.println("The menu item is no longer selected");
        }
    }};

    public static int getNIL() {
        return NIL;
    }
    //-----------------------Getter-------------------------------------------------

    public Integer[][] getAdjacencyMatrix2() {
        return AdjacencyMatrix2;
    }
    public Integer[][] getDistanceMatix() {
        return distanceMatix;
    }
    public Integer[][] getWegmatrix() {
        return wegmatrix;
    }
    public String getPath() {
        if (getFilelocation() == null) {
            setFilelocation("F:\\Schule\\OneDrive - Erudio School of Art\\Desktop\\input_graph1.csv");
        } else {
            setFilelocation(path);
        }
        return getFilelocation();
    }
    public String getZentrum() {
        return zentrum;
    }
    public int getRadius() {
        return radius;
    }
    public int getDurchmesser() {
        return durchmesser;
    }
    //-----------------------Setter-----------------------------------------------
    public void setSize(int size) {
        this.size = size;
    }
    public void setZentrum(String zentrum) {
        this.zentrum = zentrum;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }
    public void setAdjListArray(ArrayList<ArrayList<Integer>> adjListArray) {
        this.adjListArray = adjListArray;
    }

    public Integer[][] readCSVFile() {
        Integer[][] myArray = null;
        System.out.println("....");
        try{   //setup a scanner
            Scanner scannerIn = new Scanner(new BufferedReader(new FileReader("F:\\Schule\\OneDrive - Erudio School of Art\\Desktop\\input_graph1.csv")));
            int j=0;
            while (scannerIn.hasNextLine())
            {
                // read line in from file
                setInputLine(scannerIn.nextLine().trim());
                if(getInputLine().endsWith(",")) {
                    setInputLine(getInputLine().substring(0, getInputLine().length()-1));
                }
                // split the Inputline into an array at the commas
                String[] inArray = getInputLine().split(",");
                if(myArray == null) {
                    myArray = new Integer[inArray.length][inArray.length];
                }
                //copy the content of the inArray to the myArray
                for (int i =0; i < inArray.length; i++)
                {
                    if(!inArray[i].trim().isEmpty()) {
                        myArray[j][i] = Integer.parseInt(inArray[i].trim());
                    }
                }
                // Increment the row in the array
                j++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return myArray;
    }
    public void printGraph() {
        System.out.println("\n------------ Print AdjacencyMatrix ---------------------");
        for (int i = 0; i < getSize(); i++) {
            System.out.println();
            for (int j = 0; j < getSize(); j++) {
                System.out.print(getAdjacencyMatrix2()[i][j]+ " ");
            }
        }
    }
    public void initAll() {
        initialize();
        printGraph();
        ermittle();
        PrintDistanceMatrix();
        exzentrizitaet();
        radiusUndDurchmesser();
        zentrum();
        komponentenanzahl();
        AP();
        bridge();
    }
    public void initAll2() {
        //printGraph();
        ermittle();
        multiply();
        PrintDistanceMatrix();
        exzentrizitaet();
        radiusUndDurchmesser();
        zentrum();
        komponentenanzahl();
        AP();
        bridge();
    }
    public void initialize() {
        Integer[][] loadedMtrix = readCSVFile();
        setSize(loadedMtrix.length);
        setAdjacencyMatrix2(new Integer[getSize()][getSize()]);
        setDistanceMatix(new Integer[getSize()][getSize()]);
        setWegmatrix(new Integer[getSize()][getSize()]);
        setMatrixA(new Integer[getSize()][getSize()]);
        setExzentrizitaet(new int[getSize()]);
        setPosUnique(new int [getSize()]);

        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                getAdjacencyMatrix2()[i][j] =loadedMtrix[i][j];
                getDistanceMatix()[i][j] =loadedMtrix[i][j];
                getMatrixA()[i][j] =loadedMtrix[i][j];
                if (i != j){
                    if (getDistanceMatix()[i][j]==0){
                        getDistanceMatix()[i][j]= -1;
                        getWegmatrix()[i][j] = 0;
                    }
                    else {
                        getWegmatrix()[i][j] = 1;
                    }

                }
                else {
                    getWegmatrix()[i][j] = 1;
                }
            }
        }
    }
    public void initialize2(JButton[][] loadedMtrix2) {

        setSize(loadedMtrix2.length);
        setAdjacencyMatrix2(new Integer[getSize()][getSize()]);
        setDistanceMatix(new Integer[getSize()][getSize()]);
        setWegmatrix(new Integer[getSize()][getSize()]);
        setMatrixA(new Integer[getSize()][getSize()]);
        setExzentrizitaet(new int[getSize()]);
        setPosUnique(new int [getSize()]);
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                getAdjacencyMatrix2()[i][j] =Integer.parseInt(loadedMtrix2[i][j].getText());
                getDistanceMatix()[i][j] =Integer.parseInt(loadedMtrix2[i][j].getText());
                getMatrixA()[i][j] =Integer.parseInt(loadedMtrix2[i][j].getText());
                if (i != j){
                    if (getDistanceMatix()[i][j]==0){
                        getDistanceMatix()[i][j]= -1;
                        getWegmatrix()[i][j] = 0;
                    }
                    else {
                        getWegmatrix()[i][j] = 1;
                    }
                }
                else {
                    getWegmatrix()[i][j] = 1;
                }
            }
        }
    }
    public void ermittle() {
        int anzMultipliziert = 0;
        while (anzMultipliziert < getSize()) {
            Integer[][] multiply = multiply();
            anzMultipliziert++;
            for (int i = 0; i < getSize(); i++) {
                for (int j = 0; j < getSize(); j++) {
                    if (getDistanceMatix()[i][j] < 0 && multiply[i][j] > 0) {
                        getDistanceMatix()[i][j] = anzMultipliziert + 1;
                    } else if (getWegmatrix()[i][j] == 0 && multiply[i][j] > 0) {
                        getWegmatrix()[i][j] = 1;
                    }
                }
            }
        }
    }

    public Integer[][] multiply() {
        Integer multiply[][] = new Integer[getSize()][getSize()];
        int sum = 0;
        for (int row = 0; row < getSize(); row++) {
            for (int col = 0; col < getSize(); col++) {
                sum =0;
                for (int index = 0; index < getSize(); index++) {
                    sum = sum + getMatrixA()[row][index] * getAdjacencyMatrix2()[index][col];
                }
                multiply[row][col] = sum;
            }
        }
        for(int i = 0; i < getMatrixA().length; i++){
            for(int j = 0; j < getMatrixA().length; j++){
                getMatrixA()[i][j] = multiply[i][j];
            }
        }
        return multiply;
    }
    public void PrintDistanceMatrix() {
        System.out.println("\n\n------------ Print DistanceMatrix---------------------\n");
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print(getDistanceMatix()[i][j] +" ");
            }
            System.out.println();
        }
    }
    public void radiusUndDurchmesser(){
        this.setRadius(999);
        this.setDurchmesser(0);
        for (int i = 0; i < getExzentrizitaet().length; i++){
            if (getExzentrizitaet()[i] < getRadius() && getExzentrizitaet()[i] > -1 ){
                setRadius(getExzentrizitaet()[i]);
                setRadius(getRadius());
            }
            if (getExzentrizitaet()[i] > getDurchmesser()){
                this.setDurchmesser(getExzentrizitaet()[i]);
                setDurchmesser(this.getDurchmesser());
            }
        }
        System.out.println("\n------------ Print Radius ---------------------\n");
        System.out.println("Radius: " + getRadius());
        System.out.println("\n------------ Print Durchmesser ---------------------\n");
        System.out.println("Durchmesser: " + getDurchmesser());
    }
    public String exzentrizitaet(){
        int max = 0;
        String info = "{ ";
        for (int i = 0; i < getDistanceMatix().length; i++){
            for (int j = 0; j < getDistanceMatix().length; j++){
                if (max < getDistanceMatix()[i][j]){
                    max = getDistanceMatix()[i][j];
                }
            }
            getExzentrizitaet()[i] = max;
            max = 0;
        }
        System.out.println("\n------------ Print Exzentrizitaet---------------------\n");

        for(int i = 0; i < getExzentrizitaet().length; i++ )
        {
            info += getExzentrizitaet()[i] + " ";

        }
        info = info+ "} \n";
        System.out.print(info);
        return info;
    }
    public String zentrum(){
        String info;
        int zentrum[];
        zentrum = new int[getExzentrizitaet().length];
        for (int i = 0; i < getExzentrizitaet().length; i++){
            if (getExzentrizitaet()[i] == this.getRadius()){
                zentrum[i] = i+1;
            }
        }
        info = "{";
        for (int i = 0; i < zentrum.length; i++){
            if (zentrum[i] != 0){
                info = info + " " + zentrum[i];
            }
        }
        info = info + " " + " }";
        System.out.println("\n------------ Print Zentrum ---------------------\n");
        System.out.println(info);
        setZentrum( info);
        return info;
    }
    public boolean isZusammenhaengend(){
        int count = 0;
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                if (getWegmatrix()[i][j]==1){
                    count++;
                }
            }
        }
        if (count == getSize() * getSize()){
            return true;
        }
        return false;
    }
    public int komponentenanzahl(){
        int vergleichsArray[] = new int [getSize()];
        int pos[]= new int [getSize()];
        int count = 0;
        int index = 0;
        //Position ermitteln
        while (index < getSize()){
            //Zeile in vergleichsArray speichern
            for (int j = 0; j < getSize(); j++){
                vergleichsArray[j] = getWegmatrix()[index][j];
            }
            //Position speichern
            for (int i = 0; i < getSize(); i++){
                count = 0;
                for (int j = 0; j < getSize(); j++){
                    if (getWegmatrix()[i][j] == vergleichsArray[j]){
                        count++;
                        if (count == getSize()){
                            pos[index] = i+1;
                        }
                    }
                }
            }
            index++;
        }
        //Elemente in pos in setString speichern und dadurch "Duplikate entfernen"
        Set<Integer> setString = new LinkedHashSet<Integer>();
        for(int i=0;i<pos.length;i++){
            setString.add(pos[i]);
        }
        //Für Komponentenbeschreibung //Elemente in setString in posUnique speichern
        int ind = 0;
        for (Iterator<Integer> it = setString.iterator(); it.hasNext(); ) {
            getPosUnique()[ind] = it.next();
            ind++;
        }
        //Komponentenanzahl zurückgeben
        return setString.size();
    }
    public String komponenteAusgeben(){
        String infKompo="";
        int komponent = 1;
        for (int i = 0; i < komponentenanzahl(); i++){
            infKompo+= "K"+komponent+" { ";
            for (int a = 0; a < getWegmatrix().length; a++){
                for (int b = 0; b < getWegmatrix().length; b++){
                    if (getPosUnique()[i]-1 == a){
                        if (getWegmatrix()[a][b]==1){
                            infKompo += b+1 + " ";
                        }
                    }
                }
            }
            infKompo += "}\n";
            komponent++;
        }
        return infKompo;
    }
    //----------------------ArrayList -----------------------------------------------
    public static ArrayList<ArrayList<Integer>> convert(Integer[][] a) {
        // no of vertices
        int l = a[0].length;
        ArrayList<ArrayList<Integer>> adjListArray
                = new ArrayList<ArrayList<Integer>>(l);
        // Create a new list for each
        // vertex such that adjacent
        // nodes can be stored
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }
        int i, j;
        for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a.length; j++) {
                if (a[i][j] == 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }
        return adjListArray;
    }
    public static void printArrayList(ArrayList<ArrayList<Integer>> adjListArray){
        // Print the adjacency list
        for (int v = 0; v < adjListArray.size(); v++) {
            System.out.print(v);
            for (Integer u : adjListArray.get(v)) {
                System.out.print(" -> " + u);
            }
            System.out.println();
        }
    }
    //----------------- Articulation-Point-------------------------------
    public void APUtil(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {
        setAdjListArray(convert(getAdjacencyMatrix2()));
        // Count of children in DFS Tree
        int children = 0;
        // Mark the current node as visited
        visited[u] = true;
        // Initialize discovery time and low value
        disc[u] = low[u] = setTime(getTime() + 1);
        // Go through all vertices aadjacent to this
        Iterator<Integer> i = getAdjListArray().get(u).iterator();
        while (i.hasNext())
        {
            int v = i.next();  // v is current adjacent of u
            // If v is not visited yet, then make it a child of u
            // in DFS tree and recur for it
            if (!visited[v])
            {
                children++;
                parent[v] = u;
                APUtil(v, visited, disc, low, parent, ap);
                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
                low[u]  = Math.min(low[u], low[v]);
                // u is an articulation point in following cases
                // (1) u is root of DFS tree and has two or more chilren.
                if (parent[u] == getNIL() && children > 1)
                    ap[u] = true;
                // (2) If u is not root and low value of one of its child
                // is more than discovery value of u.
                if (parent[u] != getNIL() && low[v] >= disc[u])
                    ap[u] = true;
            }
            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }
    // The function to do DFS traversal. It uses recursive function APUtil()
    public void AP() {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[getSize()];
        int disc[] = new int[getSize()];
        int low[] = new int[getSize()];
        int parent[] = new int[getSize()];
        boolean ap[] = new boolean[getSize()]; // To store articulation points
        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < getSize(); i++)
        {
            parent[i] = getNIL();
            visited[i] = false;
            ap[i] = false;
        }
        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (int i = 0; i < getSize(); i++)
            if (visited[i] == false)
                APUtil(i, visited, disc, low, parent, ap);
        // Now ap[] contains articulation points, print them
        for (int i = 0; i < getSize(); i++)
            if (ap[i] == true) {
                setApPrint(getApPrint() + i + "  ");
                System.out.print(i + " ");
            }
    }
    //---------------------Bridge------------------------------------
    public void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {
        // Mark the current node as visited
        visited[u] = true;
        // Initialize discovery time and low value
        disc[u] = low[u] = setTime(getTime() + 1);
        // Go through all vertices aadjacent to this
        Iterator<Integer> i = getAdjListArray().get(u).iterator();
        while (i.hasNext())
        {
            int v = i.next();  // v is current adjacent of u
            // If v is not visited yet, then make it a child
            // of u in DFS tree and recur for it.
            // If v is not visited yet, then recur for it
            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);
                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[u]  = Math.min(low[u], low[v]);
                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low[v] > disc[u])
                    setBridgePrint(getBridgePrint() + u+"-"+v +"   ");
                System.out.println(u+" "+v);
            }
            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }
    // DFS based function to find all bridges. It uses recursive
    // function bridgeUtil()
    public void bridge() {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[getSize()];
        int disc[] = new int[getSize()];
        int low[] = new int[getSize()];
        int parent[] = new int[getSize()];
        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < getSize(); i++)
        {
            parent[i] = getNIL();
            visited[i] = false;
        }
        // Call the recursive helper function to find Bridges
        // in DFS tree rooted with vertex 'i'
        for (int i = 0; i < getSize(); i++)
            if (visited[i] == false) {
                bridgeUtil(i, visited, disc, low, parent);
            }
    }

    public int getSize() {
        return size;
    }

    public void setAdjacencyMatrix2(Integer[][] adjacencyMatrix2) {
        AdjacencyMatrix2 = adjacencyMatrix2;
    }

    public void setDistanceMatix(Integer[][] distanceMatix) {
        this.distanceMatix = distanceMatix;
    }

    public void setWegmatrix(Integer[][] wegmatrix) {
        this.wegmatrix = wegmatrix;
    }

    public Integer[][] getMatrixA() {
        return matrixA;
    }

    public void setMatrixA(Integer[][] matrixA) {
        this.matrixA = matrixA;
    }

    public int[] getExzentrizitaet() {
        return exzentrizitaet;
    }

    public void setExzentrizitaet(int[] exzentrizitaet) {
        this.exzentrizitaet = exzentrizitaet;
    }

    public int[] getPosUnique() {
        return posUnique;
    }

    public void setPosUnique(int[] posUnique) {
        this.posUnique = posUnique;
    }

    public int getTime() {
        return time;
    }

    public int setTime(int time) {
        this.time = time;
        return time;
    }

    public ArrayList<ArrayList<Integer>> getAdjListArray() {
        return adjListArray;
    }

    public String getInputLine() {
        return inputLine;
    }

    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }

    public String getApPrint() {
        return apPrint;
    }

    public void setApPrint(String apPrint) {
        this.apPrint = apPrint;
    }

    public String getBridgePrint() {
        return bridgePrint;
    }

    public void setBridgePrint(String bridgePrint) {
        this.bridgePrint = bridgePrint;
    }

    public String getFilelocation() {
        return filelocation;
    }

    public void setFilelocation(String filelocation) {
        this.filelocation = filelocation;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JMenuItem getNewMenuItem() {
        return newMenuItem;
    }

    public void setNewMenuItem(JMenuItem newMenuItem) {
        this.newMenuItem = newMenuItem;
    }
}