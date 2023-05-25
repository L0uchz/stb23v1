package fr.univrouen.stb23.client;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import fr.univrouen.stb23.util.TransformXML;

public class Application {

    private JFrame frmXmlRequestManager;
    private JTextField requestTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                                 | UnsupportedLookAndFeelException e1) {
                            // TODO Auto-generated catch block

                        }
                        JFrame.setDefaultLookAndFeelDecorated(true);
                        Application window = new Application();
                        window.frmXmlRequestManager.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public Application() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {
        frmXmlRequestManager = new JFrame();
        frmXmlRequestManager.setTitle("XML request manager");
        frmXmlRequestManager.setResizable(false);
        frmXmlRequestManager.getContentPane().setForeground(Color.LIGHT_GRAY);
        frmXmlRequestManager.setBounds(100, 100, 1280, 800);
        frmXmlRequestManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmXmlRequestManager.getContentPane().setLayout(null);

        Dimension maximumSize = new Dimension(375, 400);

        JLabel lblTypeYourRequest = new JLabel("Type your request here");
        lblTypeYourRequest.setBounds(22, 6, 196, 15);
        frmXmlRequestManager.getContentPane().add(lblTypeYourRequest);

        requestTextField = new JTextField();
        requestTextField.setBounds(22, 27, 869, 36);
        frmXmlRequestManager.getContentPane().add(requestTextField);
        requestTextField.setColumns(10);

        JButton btnSend = new JButton("SEND");

        btnSend.setBounds(1088, 26, 117, 36);
        frmXmlRequestManager.getContentPane().add(btnSend);

        JComboBox methodComboBox = new JComboBox();
        methodComboBox.setModel(new DefaultComboBoxModel(new String[] { "GET", "POST" , "DELETE" }));
        methodComboBox.setSelectedIndex(0);
        methodComboBox.setBounds(937, 26, 126, 36);
        frmXmlRequestManager.getContentPane().add(methodComboBox);

        JScrollPane responseScrollPane = new JScrollPane();
        responseScrollPane.setBounds(22, 105, 600, 600);
        responseScrollPane.setMaximumSize(maximumSize);
        frmXmlRequestManager.getContentPane().add(responseScrollPane);

        JTextPane responseTextArea = new JTextPane();
        responseTextArea.setEditable(false);
        responseScrollPane.setViewportView(responseTextArea);

        JLabel lblResponseArriveHere = new JLabel("Response arrive here : ");
        lblResponseArriveHere.setBounds(22, 88, 216, 15);
        frmXmlRequestManager.getContentPane().add(lblResponseArriveHere);

        JScrollPane fileScrollPane = new JScrollPane();
        fileScrollPane.setBounds(658, 105, 600, 600);
        frmXmlRequestManager.getContentPane().add(fileScrollPane);

        JTextArea fileTextArea = new JTextArea();
        fileTextArea.setEditable(false);
        fileScrollPane.setViewportView(fileTextArea);

        JLabel lblPasteYourFile = new JLabel("Paste your file here :");
        lblPasteYourFile.setBounds(658, 88, 175, 15);
        frmXmlRequestManager.getContentPane().add(lblPasteYourFile);

        JCheckBox chckbxSendFilepost = new JCheckBox("Send file (POST ONLY)");
        chckbxSendFilepost.setBounds(668, 717, 208, 23);
        chckbxSendFilepost.setEnabled(false);
        frmXmlRequestManager.getContentPane().add(chckbxSendFilepost);

        btnSend.addActionListener(new ActionListener() {
            boolean isXml;
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Object> res = null;
                if(requestTextField.getText().contains("xml") || requestTextField.getText().contains("insert")) {
                    responseTextArea.setContentType("text/plain");
                    isXml = true;
                }
                else {
                    responseTextArea.setContentType("text/html");
                    isXml = false;
                }
                if (methodComboBox.getSelectedItem() == "GET") {
                    try {
                        res = sendGetRequest(requestTextField.getText());
                        String response = (String) res.get(1);
                        int responseCode = (int) res.get(0);
                        responseTextArea.setText("");
                        HTMLDocument doc = (HTMLDocument)responseTextArea.getDocument();
                        doc.putProperty("IgnoreCharsetDirective", true);
                        HTMLEditorKit editorKit = (HTMLEditorKit)responseTextArea.getEditorKit();
                        editorKit.insertHTML(doc, doc.getLength(), response, 0, 0, null);
                    } catch (Exception e) {
                        if(requestTextField.getText().contains(e.getMessage())) {
                            responseTextArea.setText("ERROR 404 please check your request");
                        }
                        else {
                            responseTextArea.setText("ERROR :" + e.getMessage());
                        }

                    }
                } else if (methodComboBox.getSelectedItem() == "POST") {
                    try {
                        res = sendPostRequest(requestTextField.getText(), fileTextArea.getText());
                        String response = (String) res.get(1);
                        int responseCode = (int) res.get(0);
                        responseTextArea.setText("Response code : " + responseCode + "<br>" + response);
                    } catch (IOException e) {
                        if(requestTextField.getText().contains(e.getMessage())) {
                            responseTextArea.setText("ERROR 404 please check your request");
                        }
                        else {
                            responseTextArea.setText("ERROR :" + e.getMessage());
                        }
                    }
                }
                else {

                    try {
                        res = sendDeleteRequest(requestTextField.getText());
                        String response = (String) res.get(1);
                        int responseCode = (int) res.get(0);
                        responseTextArea.setText("Response code : " + responseCode + "<br>" + response);
                    } catch (IOException e) {
                        if(requestTextField.getText().contains(e.getMessage())) {
                            responseTextArea.setText("ERROR 404 please check your request");
                        }
                        else {
                            responseTextArea.setText("ERROR :" + e.getMessage());
                        }
                    }
                }

            }
        });
        chckbxSendFilepost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (chckbxSendFilepost.isSelected()) {
                    fileTextArea.setEditable(true);
                } else {
                    fileTextArea.setEditable(false);
                    fileTextArea.setText("");

                }

            }
        });
        methodComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (methodComboBox.getSelectedItem() != "POST") {
                    fileTextArea.setText("");
                    fileTextArea.setEditable(false);
                    chckbxSendFilepost.setEnabled(false);
                    chckbxSendFilepost.setSelected(false);
                } else {
                    chckbxSendFilepost.setEnabled(true);
                }
            }
        });

    }

    private ArrayList<Object> sendGetRequest(String url) throws Exception {
        RequestSender rs = new RequestSender();
        return rs.sendGet(url);
    }

    private ArrayList<Object> sendPostRequest(String url, String args) throws IOException {
        RequestSender rs = new RequestSender();
        return rs.sendPost(url, args);
    }
    private ArrayList<Object> sendDeleteRequest(String url) throws IOException {
        RequestSender rs = new RequestSender();
        return rs.sendDelete(url);
    }

}
