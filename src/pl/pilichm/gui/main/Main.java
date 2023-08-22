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
        CaesarCipher cc = new CaesarCipher();

        JTextField txtTextIn = new JTextField("Text for processing");
        JTextField offsetIn = new JTextField("1");
        JLabel lblResult = new JLabel("Result: ");
        JLabel labelOffset = new JLabel("Offset: ");
        JCheckBox encryption = new JCheckBox("Encryption");
        JCheckBox decryption = new JCheckBox("Decryption");
        JButton btnRunCipher = new JButton("run");

        offsetIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        txtTextIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        lblResult.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        encryption.setSelected(true);

        JPanel panelEncryptionDecryption = new JPanel();
        panelEncryptionDecryption.setLayout(new BoxLayout(panelEncryptionDecryption, BoxLayout.X_AXIS));
        panelEncryptionDecryption.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelOffset = new JPanel();
        panelOffset.setLayout(new BoxLayout(panelOffset, BoxLayout.X_AXIS));
        panelOffset.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelEncryptionDecryption.add(encryption);
        panelEncryptionDecryption.add(decryption);
        panelOffset.add(labelOffset);
        panelOffset.add(offsetIn);

        encryption.addItemListener(e -> decryption.setSelected(!encryption.isSelected()));
        decryption.addItemListener(e -> encryption.setSelected(!decryption.isSelected()));

        cipherInputOutputPanel.removeAll();

        cipherInputOutputPanel.add(txtTextIn);
        cipherInputOutputPanel.add(lblResult);
        cipherInputOutputPanel.add(panelEncryptionDecryption);
        cipherInputOutputPanel.add(panelOffset);
        cipherInputOutputPanel.add(btnRunCipher);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            int currentOffset = 1;

            try {
                currentOffset = Integer.parseInt(offsetIn.getText());
            } catch (Exception exc){
                System.out.println("Invalid offset value!");
                offsetIn.setForeground(Color.RED);
                labelOffset.setForeground(Color.RED);
                return;
            }

            String result;
            cc.setOffset(currentOffset);

            if (encryption.isSelected()){
                result = cc.encode(textToProcess);
            } else {
                result = cc.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        refresh();
    }
}