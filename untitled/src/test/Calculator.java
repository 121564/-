package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class Calculator {
    JFrame jFrame;
    ImageIcon icon;
    JButton[] jButton;
    JPanel jPanel;
    JLabel jLabel;
    JTextField jTextField;
    boolean isLeftAvailable;
    String data="";
    double left,right;
    String prevOperactor="";

    public void total(){
        setjFrame();
        setjTextField();
        setjButton();
        close();
    }

    public void setjFrame(){
        jFrame =new JFrame();
        jFrame.setTitle("计算器");
        jFrame.setLocation(700,150);
        jFrame.setSize(450,540);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
    }
    /*public void setIcon(){

    }*/
    public void setjTextField(){
        jTextField =new JTextField("0");
        jTextField.setBounds(20,15,400,60);
        jTextField.setFont(new Font("黑体",Font.BOLD,35));
        jTextField.setBackground(new Color(230,230,250));
        jFrame.add(jTextField);
    }
    public void setjButton(){
        String[] str={ "del","C","^","/",
                "7","8","9","*",
                "4","5","6","+",
                "1","2","3","-",
                "+/-","0",".","="
        };
        jButton = new JButton[str.length];

        jPanel = new JPanel();
        jPanel.setBounds(20,90,400,350);
        jPanel.setLayout(new GridLayout(5,4,8,8));
        for (int i = 0; i < jButton.length; i++) {
            jButton[i]=new JButton(str[i]);
            jButton[i].setFont(new Font("黑体",Font.CENTER_BASELINE,20));
            jButton[i].setBackground(new Color(242,240,235));
            if (i==jButton.length){
                jButton[i].setBackground(new Color(211,120,129));
            }
            //添加事件
            int idx=i;
            //设置监听器
            jButton[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    click(jButton[idx].getText());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jButton[idx].setFont(new Font("黑体",Font.CENTER_BASELINE,35));
                    jButton[idx].setBackground(new Color(240,255,255));
                    jButton[idx].setForeground(new Color(255,99,71));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jButton[idx].setFont(new Font("黑体",Font.CENTER_BASELINE,20));
                    jButton[idx].setBackground(new Color(242,240,235));
                    jButton[idx].setForeground(new Color(0,0,0));
                }
            });
            jPanel.add(jButton[i]);
        }
        jFrame.add(jPanel);
    }

    public void close(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    public void click(String content){
        String operator="";
        if ("1".equals(content)){
            data+="1";
            jTextField.setText(data);
        }else if ("2".equals(content)){
            data+="2";
            jTextField.setText(data);
        }else if ("3".equals(content)){
            data+="3";
            jTextField.setText(data);
        }else if ("4".equals(content)){
            data+="4";
            jTextField.setText(data);
        }else if ("5".equals(content)){
            data+="5";
            jTextField.setText(data);
        }else if ("6".equals(content)){
            data+="6";
            jTextField.setText(data);
        }else if ("7".equals(content)){
            data+="7";
            jTextField.setText(data);
        }else if ("8".equals(content)){
            data+="8";
            jTextField.setText(data);
        }else if ("9".equals(content)){
            data+="9";
            jTextField.setText(data);
        }else if ("0".equals(content)){
            data+="0";
            jTextField.setText(data);
        }else if (".".equals(content)){
            data+=".";
            jTextField.setText(data);
        }else if ("+/-".equals(content)){
            if (data.indexOf("-")<0){
                data="-"+data;
            }else {
                data=data.substring(1);
            }
            jTextField.setText(data);
        }else if ("^".equals(content)){
            operator="^";
            cal(operator);
        }else if ("+".equals(content)){
            operator="+";
            cal(operator);
        }else if ("-".equals(content)){
            operator="-";
            cal(operator);
        }else if ("*".equals(content)){
            operator="*";
            cal(operator);
        }else if ("/".equals(content)){
            operator="/";
            cal(operator);
        }else if ("=".equals(content)){
            operator="=";
            cal(operator);
        }else if ("del".equals(content)){
            if (data.length()!=0){
                data=data.substring(0,data.length()-1);
            }
            jTextField.setText(data);
        }else if ("C".equals(content)){
            data="";
            jTextField.setText(data);
        }
    }

    public void cal(String operator){
        if ("".equals((operator))){
            if ("=".equals(operator)){
                isLeftAvailable=false;
            }
            return;
        }

        if (isLeftAvailable){
            right=Double.parseDouble(data);
            data="";
            if ("+".equals(prevOperactor)){
                left+=right;
            }else if ("-".equals(prevOperactor)){
                left-=right;
            }else if ("*".equals(prevOperactor)){
                left*=right;
            }else if ("/".equals(prevOperactor)){
                left/=right;
            }else if ("^".equals(prevOperactor)){
                left=Math.pow(left,right);
            }



            if (left==(int)left){
                left=(int)left;
            }
        }else {
            left=Double.parseDouble(data);
            data="";
            isLeftAvailable=true;
        }

        String result=left+"";

        if("=".equals(operator)){
            isLeftAvailable=false;
            operator="";
        }

        prevOperactor=operator;
        jTextField.setText(textFormat(result)+""+operator);
    }

    public String textFormat(String s){
        NumberFormat nf=NumberFormat.getInstance();
        String fomatResult=nf.format(Double.parseDouble(s));
        return fomatResult;
    }

    public static void main(String[] args) {
        new Calculator().total();
    }
}
