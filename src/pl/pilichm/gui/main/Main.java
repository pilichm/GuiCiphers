package pl.pilichm.gui.main;

import pl.pilichm.ciphers.substitution.CaesarCipher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    private JPanel listOfCiphersPanel;

    private JPanel cipherInputOutputPanel;
    private JFrame frame;

    public static void main(String[] args) {
        System.out.println("main() - start.");

        new Main().run();

        System.out.println("main() - end.");
    }

    public void run(){
        frame = new JFrame("Classical cryptography");

        JPanel typeOfCipherPanel = new JPanel();
        typeOfCipherPanel.setLayout(new BoxLayout(typeOfCipherPanel, BoxLayout.X_AXIS));
        typeOfCipherPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JButton btnSquareCiphers = new JButton("Square ciphers");
        JButton btnSubstitutionCiphers = new JButton("Substitution ciphers");
        JButton btnTranspositionCiphers = new JButton("Transposition ciphers");

        btnSubstitutionCiphers.addActionListener(e -> displaySubstitutionCiphers());
        btnTranspositionCiphers.addActionListener(e -> displayTranspositionCiphers());
        btnSquareCiphers.addActionListener(e -> displayTSquareCiphers());

        typeOfCipherPanel.add(btnSubstitutionCiphers);
        typeOfCipherPanel.add(btnTranspositionCiphers);
        typeOfCipherPanel.add(btnSquareCiphers);

        listOfCiphersPanel = new JPanel();
        listOfCiphersPanel.setLayout(new BoxLayout(listOfCiphersPanel, BoxLayout.X_AXIS));
        listOfCiphersPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        cipherInputOutputPanel = new JPanel();
        cipherInputOutputPanel.setLayout(new BoxLayout(cipherInputOutputPanel, BoxLayout.Y_AXIS));
        cipherInputOutputPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        frame.setLayout(new GridLayout(0, 1));
        frame.add(typeOfCipherPanel);
        frame.add(listOfCiphersPanel);
        frame.add(cipherInputOutputPanel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void refresh(){
        frame.invalidate();
        frame.validate();
        frame.pack();
        frame.repaint();
    }

    private void displaySubstitutionCiphers(){
        System.out.println("Substitution ciphers.");

        JButton btnAffine = new JButton("Affine");
        JButton btnAutoKey = new JButton("Auto key");
        JButton btnCaesar = new JButton("Caesar");
        JButton btnHill = new JButton("Hill");
        JButton btnRot13 = new JButton("ROT13");

        btnCaesar.addActionListener(e -> {displayCaesarCipher();});

        listOfCiphersPanel.removeAll();
        listOfCiphersPanel.add(btnAffine);
        listOfCiphersPanel.add(btnAutoKey);
        listOfCiphersPanel.add(btnCaesar);
        listOfCiphersPanel.add(btnHill);
        listOfCiphersPanel.add(btnRot13);

        refresh();
    }

    private void displayTranspositionCiphers(){
        System.out.println("Transposition ciphers.");

        JButton btnColumnarTranspositionCipher = new JButton("Columnar transposition");
        JButton btnRailFenceCipher = new JButton("Rail fence");

        listOfCiphersPanel.removeAll();
        listOfCiphersPanel.add(btnColumnarTranspositionCipher);
        listOfCiphersPanel.add(btnRailFenceCipher);

        refresh();
    }

    private void displayTSquareCiphers(){
        System.out.println("Square ciphers.");

        JButton btnPlayfairCipher = new JButton("Playfair");
        JButton btnTwoSquareCipher = new JButton("Two square");
        JButton btnFourSquareCipher = new JButton("Four square");

        listOfCiphersPanel.removeAll();
        listOfCiphersPanel.add(btnPlayfairCipher);
        listOfCiphersPanel.add(btnTwoSquareCipher);
        listOfCiphersPanel.add(btnFourSquareCipher);

        refresh();
    }

    private void displayCaesarCipher(){
        System.out.println("Caesar cipher.");

        JPanel [] panels = Utils.getPanelsForCaesar();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }
}