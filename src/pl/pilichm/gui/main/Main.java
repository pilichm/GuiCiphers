package pl.pilichm.gui.main;

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

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
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

        btnCaesar.addActionListener(e -> displayCaesarCipher());
        btnRot13.addActionListener(e -> displayROT13Cipher());
        btnAutoKey.addActionListener(e -> displayAutoKeyCipher());
        btnAffine.addActionListener(e -> displayAffine());
        btnHill.addActionListener(e -> displayHill());

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

        btnColumnarTranspositionCipher.addActionListener(e -> displayColumnarTransposition());
        btnRailFenceCipher.addActionListener(e -> displayRailFence());

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

    private void displayROT13Cipher(){
        System.out.println("Caesar ROT13 cipher.");

        JPanel [] panels = Utils.getPanelsForROT13();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

    private void displayAutoKeyCipher(){
        System.out.println("Caesar Auto key cipher.");

        JPanel [] panels = Utils.getPanelsForAutoKey();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

    private void displayAffine(){
        System.out.println("Affine cipher.");

        JPanel [] panels = Utils.getPanelsForAffine();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

    private void displayHill(){
        System.out.println("Hill cipher.");

        JPanel [] panels = Utils.getPanelsForHill();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

    private void displayColumnarTransposition(){
        System.out.println("Columnar transposition cipher.");

        JPanel [] panels = Utils.getPanelsForColumnarTransposition();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

    private void displayRailFence(){
        System.out.println("Rail fence cipher.");

        JPanel [] panels = Utils.getPanelsForRailsFence();

        cipherInputOutputPanel.removeAll();

        for (JPanel panel : panels){
            cipherInputOutputPanel.add(panel);
        }

        refresh();
    }

}