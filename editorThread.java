/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.awt.MouseInfo;
import java.awt.Point;

/**
 *
 * @author danny
 */
public class editorThread implements Runnable{
    private String name="Thread";
    private boardEditor bE;
    private Thread t;
    public editorThread(boardEditor be){
        bE=be;
        t=new Thread(this,name);
        t.start();
    }
    
    @Override
    public void run(){
        
        try{
            boolean b1=bE.getBool();
            while(b1){
                Point p=MouseInfo.getPointerInfo().getLocation();
                Point p1=bE.getLocationOnScreen();
                int x1= (int) p.getX();
                x1-=p1.getX();
                int y1=(int)p.getY();
                y1-=p1.getY();
                if(((x1>=0)&&(x1<Board.PLACE_SIZE*8))&&((y1>=0)&&(y1<Board.PLACE_SIZE*8))){
                    int r=y1/Board.PLACE_SIZE;
                    int c=x1/Board.PLACE_SIZE;
                    
                    if(bE.getMode().equals("T")){
                        bE.getB()[r][c].setPiece(null);
                    }else if(!(bE.getMode().equals("S"))){
                        bE.getB()[r][c].setPiece(new Piece(bE.getModeSide(),Place.RCtoP(r, c),bE.getMode()+"1"+bE.getModeSide()));
                        if(bE.getMode().equals("p")){
                            if((bE.getModeSide()==1)&&(r==1)){
                                bE.getB()[r][c].getPiece().setMoved(1);
                            }else if((bE.getModeSide()==0)&&(r==6)){
                                bE.getB()[r][c].getPiece().setMoved(1);
                            }
                        }
                    }
                }
                
                b1=bE.getBool();
                bE.repaint();
                
                bE.update();
                Thread.sleep(1);
            }
            
        }catch(InterruptedException e){
            System.out.println(" FAIL ");
        }
    }
}