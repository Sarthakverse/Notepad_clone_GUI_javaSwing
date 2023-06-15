
package com.mycompany.notepadapp;

import javax.swing.*;
import java.awt.*;

public class About  extends JFrame
{
   About()
   {
       setBounds(100,100,500,400);//100 from Left ,100 from top , 500 is width , 400 is height
       setTitle("about notepad application");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(null);
       
       JLabel iconlabel = new JLabel();
       iconlabel.setBounds(100,50,80,80);
       
       ImageIcon icon = new ImageIcon("C:\\Users\\rsart\\Music\\notepadcloneicon.png");
       setIconImage(icon.getImage());
       iconlabel.setIcon(icon);
       add(iconlabel);
       
       //br tag is for new line.
       JLabel textLabel = new JLabel("<html>Welcome To notepad<br>Notepad is a simple text editor for Microsoft Windows and a basic text editing programme which enables computer users to create documents <br>All right reserved @2023</html>");
       textLabel.setBounds(60,50,350,300);
       textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
       add(textLabel);
   
   
   
   }
   
   public static void main(String arg[])
   {
       About ab=new About();
       ab.setVisible(true);
   }
}
