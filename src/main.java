import Model.ResponseModel;
import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class main {
    public Container Main;
    private JButton submitButton;
    private JTextField textFieldStatus;
    private JTextField textFieldComment;
    private JTextField textFieldMsg;
    private JButton exitButton;
    private JPanel root;

    public main() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectURI koneksisaya = new ConnectURI();
                URL myAddress = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
                String response = null;
                try {
                    response = koneksisaya.getResponseFromHttpUrl(myAddress);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(response);

                JSONArray responseJSON = new JSONArray(response);
                ArrayList<ResponseModel> responseModel = new ArrayList<>();
                for (int i = 0; i < responseJSON.length(); i++) {
                    ResponseModel resModel = new ResponseModel();
                    JSONObject myJSONObject = responseJSON.getJSONObject(i);
                    resModel.setMsg(myJSONObject.getString("message"));
                    resModel.setComment(myJSONObject.getString("status"));
                    resModel.setStatus(myJSONObject.getString("comment"));
                    responseModel.add(resModel);
                }

                System.out.println("Response are: ");
                main Main = new main();
                for (int index = 0; index < responseModel.size(); index++) {
                    textFieldStatus.setText(responseModel.get(index).getStatus());
                    textFieldComment.setText(responseModel.get(index).getComment());
                    textFieldMsg.setText(responseModel.get(index).getMsg());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldStatus.setText("");
                textFieldComment.setText("");
                textFieldMsg.setText("");
            }
        });
    }

        public static void main (String[]args){
            JFrame frame = new JFrame("main");
            frame.setContentPane(new main().root);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
    }