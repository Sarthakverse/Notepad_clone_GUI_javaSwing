package com.mycompany.notepadapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.*;

public class NotepadApp extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar(); //will create a bar for placing menu items inside it
    JMenu file = new JMenu("File"); //such as file 
    JMenu edit = new JMenu("Edit");// such as edit
    JMenu help = new JMenu("Help");//and such as help

    //creating menu items for file JMenu
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    //creating menu items for edit Jmenu
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("SelectAll");

    //creating menu item for help Jmenu
    JMenuItem about = new JMenuItem("About");

    //text area
    JTextArea textarea = new JTextArea();

    public NotepadApp() {
        setTitle("Notepad Application"); //this is name of my app
        setBounds(100, 100, 800, 600); //setting the boundaries of the application

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// the appn will be exited when clicked on 'x' on appn window

        //to set image of notepad icon..
        //copy png file from file manager
        //paste inside sourcepackage of this project
        //and then do this
        ImageIcon icon = new ImageIcon("C:\\Users\\rsart\\Music\\notepadcloneicon.png");
        setIconImage(icon.getImage());

        //add(menubar); //this may create a problem where its components will not be at top..they will be somewhere in frame
        setJMenuBar(menubar); //this is the right method 
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        //adding the (menuitems)<--(different menubars such as file,help and edit)
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(about);

        help.add(about);

        JScrollPane scrollpane = new JScrollPane(textarea);
        add(scrollpane);//will make text area dynamic

        //if u want that while writing text when u reach end point of frame then automatically
        //new line should come in action...otherwise horizontall scroll bar will get created
        //then do this 👇
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());

        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);
        
        //set shortcut keys
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));
               

    }

    public static void main(String arg[]) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        NotepadApp np = new NotepadApp();
        np.setVisible(true); // making the frame visible

    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equalsIgnoreCase("New")) {
        textarea.setText(null);
    } else if (e.getActionCommand().equalsIgnoreCase("open")) {
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter textfilter = new FileNameExtensionFilter("only text files (.txt)", "txt");
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(textfilter);

        int action = filechooser.showOpenDialog(null);

        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                textarea.read(reader, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    } else if (e.getActionCommand().equalsIgnoreCase("save")) {
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter textfilter = new FileNameExtensionFilter("only text files (.txt)", "txt");
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(textfilter);

        int action = filechooser.showSaveDialog(null);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        } else {
            String fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
            if (!fileName.contains(".txt")) {
                fileName = fileName + ".txt";
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                textarea.write(writer);
                writer.close(); // Close the writer after writing the file
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    } else if (e.getActionCommand().equalsIgnoreCase("print")) {
        try{textarea.print(); }
        catch(PrinterException ex){Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE,null,ex);}

    } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
        System.exit(0);

    } else if (e.getActionCommand().equalsIgnoreCase("cut")) {
        textarea.cut();

    } else if (e.getActionCommand().equalsIgnoreCase("copy")) {
        textarea.copy();

    } else if (e.getActionCommand().equalsIgnoreCase("paste")) {
        textarea.paste();

    } else if (e.getActionCommand().equalsIgnoreCase("selectall")) {
        textarea.selectAll();

    } else if (e.getActionCommand().equalsIgnoreCase("about")) {
        new About() .setVisible(true);

    }
}
}
