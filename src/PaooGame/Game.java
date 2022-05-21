package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.States.*;
import PaooGame.entity.Entity;
import PaooGame.entity.Isaac;
import PaooGame.tile1.AssetSetter;
import PaooGame.tile1.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;
/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game extends JPanel implements Runnable
{
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    public int screenWidth;
    public int screenHeight;
    public int actualScreenWidth=1551;
    public int actualScreenHeight=873;
    //actual screen width = 1536
    //actual screen height = 864
    public int tileSize;
    public int rows=18;
    public int cols=32;
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Graphics        g;          /*!< Referinta catre un context grafic.*/

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    // setam pozitia default a playerului
    public AssetSetter aSet;
    //public KeyHandler keyH = new KeyHandler(this);
    public KeyHandler keyH=KeyHandler.getInstance(this);
    public Isaac isaac;
    public int personaj=0; // 0 = isaac, default; 1 = azazel;
    public int nrPersonaje=2;

    public int nivel=0;
    public int nivelVechi=0;
    public int camera=0;
    public int cameraVeche=0;
    public boolean nivelComplet=false;

    public TileManager tileM;
    public CheckCollision cChecker=new CheckCollision(this);
    //GAME STATE

    public State titleState,playState,deathState,pauseState;
    public UIStates uiStates;
    /*public int gameState;
    public final int playState = 1;
    public final int titleState = 0;
    public final int pauseState = 2;
    public final int deathState = 3;*/

    public Entity []obj = new Entity[100];
    public Entity []monsters = new Entity[100];

    public ArrayList<Entity> projectileList=new ArrayList<>();


    public Connection c = null;

    public Game(String title, int width, int height)
    {
            /// Obiectul GameWindow este creat insa fereastra nu este construita
            /// Acest lucru va fi realizat in metoda init() prin apelul
            /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        screenHeight=height;
        screenWidth=width;
            /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    public void setNewGame(){
        nivel=0;
        camera=0;
        Arrays.fill(tileM.nivComplet, 0);
        isaac.setDefaultValues();
        tileM.drawBasement();
        aSet.setObjects();
    }
    public void setDataBase(){
        Statement s = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bazadedate.db");
//            c.setAutoCommit(false);
            s=c.createStatement();
            String sql = null;
            try {
                sql = "CREATE TABLE \"player\" (\n" +
                        "\t\"ID\"\tINTEGER,\n" +
                        "\t\"x\"\tINTEGER,\n" +
                        "\t\"y\"\tINTEGER,\n" +
                        "\t\"speed\"\tINTEGER,\n" +
                        "\t\"health\"\tINTEGER,\n" +
                        "\t\"maxLife\"\tINTEGER,\n" +
                        "\t\"keys\"\tINTEGER,\n" +
                        "\t\"coins\"\tINTEGER,\n" +
                        "\t\"nivel\"\tINTEGER,\n" +
                        "\t\"camera\"\tINTEGER,\n" +
                        "\t\"nivelTerminat\"\tINTEGER,\n" +
                        "\tPRIMARY KEY(\"ID\")\n" +
                        ");";
                System.out.println("TABLE CREATE PLAYER" + s.execute(sql));
            }
            catch (Exception e){
//                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Tabela player a fost deja creata");
            }
            try {
                sql = "CREATE TABLE \"monstri\" (\n" +
                        "\t\"ID\"\tINTEGER,\n" +
                        "\t\"x\"\tINTEGER,\n" +
                        "\t\"y\"\tINTEGER,\n" +
                        "\t\"health\"\tINTEGER,\n" +
                        "\t\"name\"\tTEXT,\n" +
                        "\tPRIMARY KEY(\"ID\")\n" +
                        ");";
                System.out.println("TABLE CREATE PLAYER" + s.execute(sql));
            }
            catch (Exception e){
                // System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Tabela monstri a fost deja creata");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setupGame(){
        aSet.setMonsters();
        aSet.setObjects();
        //Instantiem starile jocului
        playState=new PlayState(this);
        pauseState=new PauseState(this);
        deathState=new DeathState(this);
        titleState=new TitleState(this);
    }
    private void InitGame() {
        tileSize = 48;
        //cols=15;
        //rows=13;
        wnd = new GameWindow("The binding of isaac", screenWidth, screenHeight);

        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        /// Se incarca toate elementele grafice (dale)
        wnd.setKeyH(keyH);

        setDataBase();

        aSet = new AssetSetter(this);
        tileM = new TileManager(this);
        isaac = new Isaac(this);
        //isaac.save();
        setupGame();
        // SET GAME STATE
//        gameState = titleState;
        uiStates = new UIStates(this);
        // DATA BASE


    }
    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run()
    {
            /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

            /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
            /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

            /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
                /// Se obtine timpul curent
            curentTime = System.nanoTime();
                /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
                /// Se actualizeaza flagul de stare a threadului
            runState = true;
                /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
                /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
                /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
                /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
                /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    public boolean monstersNull=true;
    public int nivelTerminat=0;
    private void Update()
    {
        wnd.GetCanvas().setFocusable(false);
        wnd.GetCanvas().setFocusable(true);
        keyH.update();
        uiStates.state.update();
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Draw()
    {
            /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
            /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
                /// Se executa doar la primul apel al metodei Draw()
            try
            {
                    /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                    /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
            /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
            /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        uiStates.state.draw(g);

            // end operatie de desenare
            /// Se afiseaza pe ecran
        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }
}

