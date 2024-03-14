package org.example;

import org.example.gui.View;

import java.awt.*;

public class App {
public static void main(String[] args)
{
    View c = new View();
    c.getContentPane().setBackground(Color.pink);
    c.setBounds(400, 200, 800, 300);
    c.setVisible(true);
}
}
