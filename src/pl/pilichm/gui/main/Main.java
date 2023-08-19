package pl.pilichm.gui.main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("main() - start.");

        JFrame frame = new JFrame("Classical cryptography");

        /*
        Buttons for selecting type of cipher.
         */

        JPanel typeOfCipherPanel = new JPanel();
        BoxLayout boxlayoutTypeOfCipher = new BoxLayout(typeOfCipherPanel, BoxLayout.X_AXIS);
        typeOfCipherPanel.setLayout(boxlayoutTypeOfCipher);
        typeOfCipherPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JButton btnSquareCiphers = new JButton("Square ciphers");
        JButton btnSubstitutionCiphers = new JButton("Substitution ciphers");
        JButton btnTranspositionCiphers = new JButton("Transposition ciphers");

        typeOfCipherPanel.add(btnSubstitutionCiphers);
        typeOfCipherPanel.add(btnTranspositionCiphers);
        typeOfCipherPanel.add(btnSquareCiphers);

        /*
         * Buttons for substitution ciphers.
         */
        JPanel substitutionPanel = new JPanel();
        BoxLayout boxlayoutSubstitutionBtn = new BoxLayout(substitutionPanel, BoxLayout.X_AXIS);
        substitutionPanel.setLayout(boxlayoutSubstitutionBtn);
        substitutionPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JButton btnAffine = new JButton("Affine");
        JButton btnAutoKey = new JButton("Auto key");
        JButton btnCaesar = new JButton("Caesar");
        JButton btnHill = new JButton("Hill");
        JButton btnRot13 = new JButton("ROT13");

        substitutionPanel.add(btnAffine);
        substitutionPanel.add(btnAutoKey);
        substitutionPanel.add(btnCaesar);
        substitutionPanel.add(btnHill);
        substitutionPanel.add(btnRot13);

        frame.setLayout(new GridLayout(0, 1));
        frame.add(typeOfCipherPanel);
        frame.add(substitutionPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("main() - end.");
    }

}