/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author danny
 */
public class pieceButton extends JButton{
    
    private String sv="";
    private Color c;
    private Color cs=new Color(57, 166, 10);
    private boolean selected=false;
    private int side;
    public pieceButton(String sv1, Color c1, int s1){
        sv=sv1;
        c=c1;
        side=s1;
    }
    public String getSV(){
        return sv;
    }
    public void setSV(String sv1){
        sv=sv1;
    }
    public void setColor(Color c1){
        c=c1;
    }
    public void select(){
        selected=true;
    }
    public void unselect(){
        selected=false;
    }
    public void setSelect(boolean b1){
        selected=b1;
    }
    public boolean getSelect(){
        return selected;
    }
    public int getSide(){
        return side;
    }
    public void setSide(int s1){
        side=s1;
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(c);
        g.fillRect(0,0,Board.PLACE_SIZE,Board.PLACE_SIZE);
        if(selected){
            g.setColor(cs);
            g.fillRect(0,0,Board.PLACE_SIZE,Board.PLACE_SIZE);
        }
        if(side==1){
            g.setColor(Color.WHITE);
        }else{
            g.setColor(Color.BLACK);
        }
        Font f=new Font("Consolas",Font.BOLD,(Board.PLACE_SIZE*4)/5);
        g.setFont(f);
        g.drawString(""+sv,((Board.PLACE_SIZE)*3)/10, (Board.PLACE_SIZE*4)/5);
    }
    
}