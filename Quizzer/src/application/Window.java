package application;



import java.io.File;



import javax.swing.JFrame;



public abstract class Window {

	

	//setup is a boolean that tells us if the database has been setup

    protected boolean setup;

    //the JFrame represents a window that has all buttons except setup button disabled until a database is setup.

    public JFrame frame;

    

    public Window() {

    	File chk_exist = new File("quizzer.db");

        setup = chk_exist.exists() && !chk_exist.isDirectory();

        initialize();

    }

    

    public Window(boolean set) {

    	setup = set;

		initialize();

    }

    

    abstract void initialize();



}