/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author quocdunginfo
 */
public class UTL_Listener {
    public static KeyListener TextField_DenyQuote = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField tmp = (JTextField)e.getSource();
            if(UTL_String.CheckSingleQuote(tmp.getText()))
            {
                tmp.setText(tmp.getText().replace("\'", ""));
                Debug.WriteLine("TextField Quote Detected!");
            }
        }
    };
    public static KeyListener TextField_OnlyInt = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField tmp = (JTextField)e.getSource();
            if(!UTL_String.CheckIntOnly(tmp.getText()))
            {
                tmp.setText(UTL_String.IntOnly(tmp.getText()));
                Debug.WriteLine("TextField Int Only!");
            }
        }
    };
    public static KeyListener TextField_OnlyDouble = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField tmp = (JTextField)e.getSource();
            if(!UTL_String.CheckDoubleOnly(tmp.getText()))
            {
                tmp.setText(UTL_String.DoubleOnly(tmp.getText()));
                Debug.WriteLine("TextField Double Only!");
            }
        }
    };
    public static void Map_TextField_To_Button(JTextField tf, final JButton bt)
    {
        tf.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    bt.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
    }
    public static void Map_TextField_To_Button(JPasswordField tf, final JButton bt)
    {
        tf.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    bt.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
    }
}
