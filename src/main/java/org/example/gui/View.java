package org.example.gui;


import org.example.logic.Operations;
import org.example.model.Polynomial;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class View extends JFrame implements ActionListener{
    JLabel l1;
    JTextField t1,t2;
    JLabel tx1, tx2, result, Result;
    JButton b1, b2, b3, b4, b5, b6;
    private Operations operations=new Operations();
Polynomial polynomial=new Polynomial();
    Controller controller= new Controller(this);

    public View(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        l1=new JLabel("Calculator");
        l1.setFont(new Font("Comic Sans", Font.BOLD, 30));
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setForeground(Color.magenta);
        l1.setBounds(200,10,200,30);

        add(l1);

        tx1=new JLabel("Polynomial 1:");
        tx2=new JLabel("Polynomial 2:");
        result= new JLabel();
        Result=new JLabel("RESULT:");
        tx1.setFont(new Font("Comic Sans", Font.PLAIN, 17));
        tx2.setFont(new Font("Comic Sans", Font.PLAIN, 17));
        result.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        Result.setFont(new Font("Comic Sans", Font.BOLD, 20));
        Result.setForeground(Color.magenta );
        Result.setBounds(550, 50, 100, 30);
        result.setBounds(450, 100, 200, 20);
        result.setHorizontalTextPosition(JLabel.CENTER);
        result.setBackground(Color.GRAY);


        t1 = new JTextField(60);
        t2 = new JTextField(60);
        b1=new JButton("Add");
        b2=new JButton("Subtract");
        b3=new JButton("Multiply");
        b4=new JButton("Divide");
        b5=new JButton("Derivative");
        b6=new JButton("Integrate");

        add(tx1);
        add(tx2);
        add(Result);
        add(result);
        tx1.setBounds(100, 60, 120, 30);
        tx2.setBounds(100, 100, 120, 30);

        t1.setBounds(245,60,150,30);
        t2.setBounds(245, 100,150,30);

        b1.setBounds(100, 150, 95,30);
        b2.setBounds(100, 200, 95,30);
        b3.setBounds(200, 150, 95,30);
        b4.setBounds(200, 200, 95,30);
        b5.setBounds(300, 150, 95,30);
        b6.setBounds(300, 200, 95,30);

        add(t1);
        add(t2);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e)
    {
        Polynomial result = null;

        if(e.getSource()==b1|| e.getSource() == b2 || e.getSource() == b3 || e.getSource() ==b4 || e.getSource() == b5 || e.getSource() == b6) {
            Polynomial firstPolynomial = controller.parsePolynomial(t1.getText());
            Polynomial secondPolynomial = controller.parsePolynomial(t2.getText());

            if (e.getSource()==b1) {
                result = operations.add(firstPolynomial, secondPolynomial);
            }
            if (e.getSource()==b2) {
                result = operations.subtract(firstPolynomial, secondPolynomial);
            }
            if (e.getSource()==b3) {
                result = operations.multiply(firstPolynomial, secondPolynomial);
            }
            if (e.getSource()==b4) {
                result = operations.divide(firstPolynomial, secondPolynomial);
            }
            if (e.getSource()==b5) {
                result = operations.derivative(firstPolynomial);
            }
            if (e.getSource()==b6) {
                result = operations.integrate(firstPolynomial);
            }
            //System.out.println(result);
            setResult(result.toString());
        }
    }

    public JButton getB1(){return b1;}
    public JButton getB2(){return b2;}
    public JButton getB3(){return b3;}
    public JButton getB4(){return b4;}
    public JButton getB5(){return b5;}
    public JButton getB6(){return b6;}
    public JTextField getT1(){return t1;}
    public JTextField getT2(){return t2;}
    public void setResult(String text){result.setText(text);}
}