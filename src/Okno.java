import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;

public class Okno extends JFrame {
    private JButton btn;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    public Okno() {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vyberSouboru();
            }
        });
    }
    public void vyberSouboru(){
        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showOpenDialog(this);
        if (result == fileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String radek = reader.readLine();
                while(radek != null){
                    textArea.append(radek + "\n");
                    radek = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            textArea.setText("Soubor nebyl vybrán");
        }
    }
    public static void main(String[] args) {
        Okno okno = new Okno();
        JMenuBar menuBar = new JMenuBar();
        okno.setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Open");
        menu.add(menuItem);
        menuItem.addActionListener(e-> okno.vyberSouboru());
        okno.setContentPane(okno.mainPanel);
        okno.setBounds(600,200,500,400);
        okno.setTitle("Výběr souboru");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
}
