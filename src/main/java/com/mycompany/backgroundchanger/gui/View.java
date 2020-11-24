package com.mycompany.backgroundchanger.gui;

import app.App;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JFrame {

    JButton bChange = new JButton("Change");
    JComboBox<String> combo = new JComboBox<>();
    App backgroundChanger = new App();
    Map<String, String> sources = backgroundChanger.getSources();

    public View() {
        JPanel panel = new JPanel();

        loadSources();

        panel.add(combo);
        panel.add(bChange);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        bChange.addActionListener((ActionEvent e) -> {
            change();
        });

        this.setSize(700, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loadSources() {
        sources.keySet().forEach(str -> {
            combo.addItem(str);
        });
    }

    private void change() {
        bChange.setEnabled(false);
        bChange.setText("Please wait...");
        String sel = combo.getSelectedItem().toString();

        System.out.println(sel);

        try {
            backgroundChanger.change(sel);
            bChange.setEnabled(true);
            bChange.setText("Change");
            JOptionPane.showMessageDialog(this,
                    "Your Background has been changed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this,
                    e.toString(), "Error", JOptionPane.ERROR);
        }
    }
}
