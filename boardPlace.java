/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author danny
 */
public class boardPlace extends JButton{
    private Color C;
    private int r;
    private int c;
    private Piece p=null;
    private boolean b1;
    private boolean is_Selected;
    private boolean is_Flagged;
    private boolean is_Possible=false;
    public boardPlace(int r1, int c1, boolean b){
        if(b){
            C=Color.GRAY;
        }else{
            C=Color.DARK_GRAY;
        }
        is_Flagged=false;
        is_Selected=false;
        r=r1;
        c=c1;
        b1=false;
        super.setBounds(c*Board.PLACE_SIZE,r*Board.PLACE_SIZE,Board.PLACE_SIZE,Board.PLACE_SIZE);
    }
    public Color getColor(){
        return C;
    }
    public void setColor(Color c){
        C=c;
    }
    public void flag(){
        is_Flagged=!(is_Flagged);
    }
    public void setFlag(boolean b){
        is_Flagged=b;
    }
    
    
    public void setPiece(Piece p1){
        
        p=p1;
        b1=true;
        
    }
    public void removePiece(){
        Piece temp=p;
        p=null;
        
    }
    public int getRow(){
        return r;
    }
    public int getCol(){
        return c;
    }
    public Piece getPiece(){
        return p;
    }
    public int getR(){
        return r;
    }
    public int getC(){
        return c;
    }
    public boolean update(){
        if(p!=null){
            b1=true;
            return true;
        }else{
            b1=false;
            return false;
        }
    }
    public void switchHasPiece(){
        b1=!b1;
    }
    public boolean getHasPiece(){
        return b1;
    }
    public int getPieceSide(){
        if(p==null){
            return -1;
        }else{
            return p.getSide();
        }
    }
    public String getPieceSV(){
        if(p==null){
            return "#";
        }else{
            return p.getSV();
        }
    }
    public boolean selected(){
        return is_Selected;
    }
    
    public void select(){
        is_Selected=true;
    }
    public void unselect(){
        is_Selected=false;
    }
    @Override 
    public String toString(){
        if(this.getPieceSide()==1){
            return this.getPieceSV().substring(0,1).toUpperCase();
        }else{
            return this.getPieceSV().substring(0,1).toLowerCase();
        }
        
    }
    
    @Override 
    public void paintComponent(Graphics g){
        g.setColor(C);
        g.fillRect(0, 0, Board.PLACE_SIZE, Board.PLACE_SIZE);
        if(is_Flagged){
            g.setColor(Color.PINK);
            g.fillRect(0, 0, Board.PLACE_SIZE, Board.PLACE_SIZE);
        }else if(this.selected()){
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, Board.PLACE_SIZE, Board.PLACE_SIZE);
        }
        
        if(p!=null){
            Font f=new Font("Consolas",Font.BOLD,(Board.PLACE_SIZE*4)/5);
            g.setFont(f);
            if(this.getPieceSide()==1){
                g.setColor(Color.WHITE);
            }else{
                g.setColor(Color.BLACK);
            }

            if(this.getPiece()!=null){
                g.drawString(""+this.getPiece().getSV().substring(0,1),((Board.PLACE_SIZE)*3)/10, (Board.PLACE_SIZE*4)/5);
            }        
        }
    }
    
}