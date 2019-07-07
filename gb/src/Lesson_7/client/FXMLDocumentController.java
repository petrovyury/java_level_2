package Lesson_7.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import java.net.*;
import java.io.*;

public class FXMLDocumentController implements Initializable {

    private Socket socket;     //чтобы общатьс¤ с серваком

    private DataInputStream  in; //поток ввода, используетс¤ UTF-кодировка
    private DataOutputStream out;//поток вывода, используетс¤ UTF-кодировка

    @FXML
    private Button sendButton;

    @FXML
    private TextArea TextArea;

    @FXML
    private TextField TextField;

    @FXML
    private void sendMsg(ActionEvent event) {
        String str = TextField.getText();
        try
        {
            out.writeUTF(str);//сообщение пошло на сервер
            TextField.clear();
            TextField.requestFocus();//фокус ввода на поле ввода сообщени¤
        }
        catch (IOException ex)
        {
            ex.getStackTrace();
        }

    }

    @Override//будет выполн¤тьс¤ при запуске приложени¤
    public void initialize(URL url, ResourceBundle rb) {
        //URL              - доступ к удалЄнному *.fxml
        //ResourceBundle   - доступ к ресурсам, упакованным в jar, если они есть



        try
        {
            //“ри кита при работе в сети:
            socket = new Socket("localhost", 12345);
            in  = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(() -> {
                try {

                    while(true)
                    {
                        String str = in.readUTF();
                        //System.out.println(str);
                        TextArea.appendText(str + "\n");
                    }
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }).start();

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}