import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
//package net.codejava.swing;

import javax.swing.JTextArea;

public class project {
    private JFrame mainframe;
    private JPanel northPanel;
    private JPanel enterPanel;
    private JPanel southPanel;
    private JPanel bigPanel;
    private JButton enterButton;
    private JButton clearButton;
    public JTextArea URLbox;
    public JTextArea searchBox;
    public JTextArea results;

    public String inputLink;
    public String keyword;
    public String link;
    public String link2;

    public String mainLink;
    //public String linksForTextArea;

    public project(){
        prepareGUI();
    }

    public static void main(String[] args) {
        project myLayoutLinksProject=new project();
    }

    public void prepareGUI(){
        JFrame mainframe=new JFrame();
        mainframe.setBounds(0,0,800,500);
        mainframe.setLayout(new BorderLayout());


        JPanel northpanel=new JPanel();
        northpanel.setLayout(new GridLayout(1,1));
        JPanel leftTopPanel=new JPanel();
        leftTopPanel.setLayout(new BorderLayout());

        JLabel url=new JLabel("URL: ");
        URLbox=new JTextArea();
        leftTopPanel.add(url, BorderLayout.WEST);
        leftTopPanel.add(URLbox, BorderLayout.CENTER);
        northpanel.add(leftTopPanel);

        JPanel enterPanel=new JPanel();
        enterPanel.setLayout(new BorderLayout());
        JButton enterButton=new JButton("Ok");
        enterPanel.add(enterButton, BorderLayout.EAST);
        JLabel search=new JLabel("search for: ");
        searchBox=new JTextArea ();
        enterPanel.add(search, BorderLayout.WEST);
        enterPanel.add(searchBox, BorderLayout.CENTER);

        northpanel.add(enterPanel);
        mainframe.add(northpanel, BorderLayout.NORTH);

        JPanel resultsPanel=new JPanel(new BorderLayout());
       results=new JTextArea();

        JScrollPane scroll = new JScrollPane(results);
       // scroll.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // scroll.add(results);
        scroll.setViewportView(results);
        resultsPanel.add(scroll, BorderLayout.CENTER);
        mainframe.add(resultsPanel, BorderLayout.CENTER);

       // results.setEditable(false);


        JPanel southPanel=new JPanel();
        southPanel.setLayout(new BorderLayout());
        JButton clearButton=new JButton("clear");
        southPanel.add(clearButton, BorderLayout.EAST);


        enterButton.setActionCommand("OK");


        enterButton.addActionListener(new ButtonClickListener());


        mainframe.add(southPanel, BorderLayout.SOUTH);

        mainframe.setVisible(true);
    }


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {

                results.setText(null);

                mainLink= URLbox.getText();
                System.out.println("The link we're using is: "+mainLink);

                keyword= searchBox.getText();
                System.out.println(keyword);

                if(keyword.isEmpty()==true) {
                    results.append("\n"+"NO KEYWORD FOUND. PRINTING ALL LINKS"+ "\n"+ "\n");
                }
                else{
                    results.append("\n"+"SEARCHING FOR LINKS WITH KEYWORD "+ keyword+"\n"+ "\n");
                }

                System.out.println("Pressed");
                Results();
                //keyWord();
            }
        }
    }

    public void Results(){
        try {
            System.out.println();
            URL url = new URL(mainLink);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                //using to find links (href)
                // indexOf to find lines
                //substring to extract quotation from line
                //contains
                //indexOf(String href);
                if (line.contains("http")) {
                    int start = line.indexOf("http");
                    int end = line.indexOf("\"", start);
                    int end2 = line.indexOf("\'", start);
                    if (end != -1) {
                        System.out.println(line.substring(start, end));
                        if(keyword==null) {
                            results.append(line.substring(start, end) + "\n");
                        }
                        link=line.substring(start,end);
                        if (link != null && keyword != null) {
                            if (link.contains(keyword)) {
                                results.append(link + "\n");
                            }
                         /*   if(link.contains(keyword)==false){
                                results.append("No links found for that keyword within this website" + "\n");
                            } */
                        }
                    } else {
                        System.out.println(line.substring(start, end2));
                        if(keyword==null) {
                            results.append(line.substring(start, end2) + "\n");
                        }
                        link2=line.substring(start,end2);
                        if (link != null && keyword != null) {
                            if (link.contains(keyword)) {
                                results.append(link + "\n");
                            }
                           /* if(link.contains(keyword)==false){
                                results.append("No links found for that keyword within this website" + "\n");
                            } */
                        }
                    }
                }


            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }


    }

    public void keyWord() {

        if (link != null && keyword != null) {
            if (link.contains(keyword)) {
                results.append(link + "\n");
            }
        }
    }


}


