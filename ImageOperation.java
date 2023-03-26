import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.plaf.FileChooserUI;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

abstract public class ImageOperation implements ActionListener
{
    public static void operate(int key)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        //! FileInputStream
        try{
                FileInputStream fis=new FileInputStream(file);

                byte []data=new byte[fis.available()];
                fis.read(data);
                int i=0;
                for(byte b:data)
                {
                    data[i]=(byte)(b^key);
                    i++;
                }
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(data);
                fos.close();
                fis.close();
                JOptionPane.showMessageDialog(null,"Done");

        }catch(Exception e)
        {

        }
    }

    public static void main (String args[])
    {
        //? creating frame
        JFrame fr = new JFrame();
        fr.setTitle("Image Operation");
        fr.setSize(400, 400);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //? Font
        Font font = new Font("Roboto", Font.BOLD, 25);

        
        //! creating button
        JButton btn = new JButton();
        btn.setText("Open Image");
        btn.setFont(font);


        //! creating textField
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        //? using lamda function
        btn.addActionListener(e->{
            String text = textField.getText();
            int temp = Integer.parseInt(text);

            operate(temp);
        });


        fr.setLayout(new FlowLayout());
        fr.add(btn);
        fr.add(textField);
        fr.setVisible(true);
    }
}
