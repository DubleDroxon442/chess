/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import static chess2.Board.PLACE_SIZE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author danny
 */
public class boardEditor extends JPanel implements ActionListener, MouseListener{
    private editorThread t1;
    boolean Bool=false;
    private boardPlace[][] b=new boardPlace[8][8];
    private String mode="S";
    private int modeSide=-1;
    private pieceButton bPawn,bBishop,bRook,bKnight,bQueen,bKing,wPawn,wBishop,wRook,wKnight,wQueen,wKing, trash, selectButton;
    private JButton clearBoard;
    private JButton setBoard;
    private ArrayList<pieceButton> bList=new ArrayList<pieceButton>();
    
    //<editor-fold defaultstate="collapsed" desc="constructor">
    public boardEditor(){
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c]=new boardPlace(r,c,b4);
                b[r][c].addActionListener(this);
                b[r][c].addMouseListener(this);
                b4=!b4;
                super.add(b[r][c]);
            }
            b4=!b4;
        }
        Color temp=Color.GRAY;
        Color temp1=Color.DARK_GRAY;
        
        //b[7][0].setPiece(new Piece(1,Place.RCtoP(7,0),"K"));
        //b[6][1].setPiece(new Piece(0,Place.RCtoP(6,1),"Q"));
        //b[5][2].setPiece(new Piece(0,Place.RCtoP(5,2),"K"));
        
        clearBoard=new JButton();
        clearBoard.addMouseListener(this);
        clearBoard.addActionListener(this);
        clearBoard.setBounds(730,0,150,50);
        clearBoard.setText("CLEAR BOARD");
        super.add(clearBoard);
        
        setBoard=new JButton();
        setBoard.setBounds(730,60,150,50);
        setBoard.setText("Play From Here");
        super.add(setBoard);
        
        bPawn=new pieceButton("p",temp,0);
        bPawn.setBounds(0,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bPawn.addActionListener(this);
        bPawn.addMouseListener(this);
        bList.add(bPawn);
        super.add(bPawn);
        
        bBishop=new pieceButton("B",temp,0);
        bBishop.setBounds(90,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bBishop.addActionListener(this);
        bBishop.addMouseListener(this);
        bList.add(bBishop);
        super.add(bBishop);
        
        bKnight=new pieceButton("N",temp,0);
        bKnight.setBounds(180,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bKnight.addActionListener(this);
        bKnight.addMouseListener(this);
        bList.add(bKnight);
        super.add(bKnight);
        
        bRook=new pieceButton("R",temp,0);
        bRook.setBounds(270,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bRook.addActionListener(this);
        bRook.addMouseListener(this);
        bList.add(bRook);
        super.add(bRook);
        
        bQueen=new pieceButton("Q",temp,0);
        bQueen.setBounds(360,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bQueen.addActionListener(this);
        bQueen.addMouseListener(this);
        bList.add(bQueen);
        super.add(bQueen);
        
        bKing=new pieceButton("K",temp,0);
        bKing.setBounds(450,730,Board.PLACE_SIZE,Board.PLACE_SIZE);
        bKing.addActionListener(this);
        bKing.addMouseListener(this);
        bList.add(bKing);
        super.add(bKing);
        
        wPawn=new pieceButton("p",temp,1);
        wPawn.setBounds(0,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wPawn.addActionListener(this);
        wPawn.addMouseListener(this);
        bList.add(wPawn);
        super.add(wPawn);
        
        wBishop=new pieceButton("B",temp,1);
        wBishop.setBounds(90,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wBishop.addActionListener(this);
        wBishop.addMouseListener(this);
        bList.add(wBishop);
        super.add(wBishop);
        
        wKnight=new pieceButton("N",temp,1);
        wKnight.setBounds(180,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wKnight.addActionListener(this);
        wKnight.addMouseListener(this);
        bList.add(wKnight);
        super.add(wKnight);
        
        wRook=new pieceButton("R",temp,1);
        wRook.setBounds(270,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wRook.addActionListener(this);
        wRook.addMouseListener(this);
        bList.add(wRook);
        super.add(wRook);
        
        wQueen=new pieceButton("Q",temp,1);
        wQueen.setBounds(360,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wQueen.addActionListener(this);
        wQueen.addMouseListener(this);
        bList.add(wQueen);
        super.add(wQueen);
        
        wKing=new pieceButton("K",temp,1);
        wKing.setBounds(450,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        wKing.addActionListener(this);
        wKing.addMouseListener(this);
        bList.add(wKing);
        super.add(wKing);
        
        trash=new pieceButton("T",temp,1);
        trash.setBounds(540,820,Board.PLACE_SIZE,Board.PLACE_SIZE);
        trash.addActionListener(this);
        trash.addMouseListener(this);
        bList.add(trash);
        super.add(trash);
        
        
        
        update();
        
        
        
        super.setPreferredSize(new Dimension(PLACE_SIZE*10,PLACE_SIZE*10));
        super.setBackground(new Color(224, 224, 224));    
        super.setLayout(null);
        super.setVisible(true);
        super.repaint();
    }
    
    public ArrayList<ArrayList<Piece>> getLists(){
        ArrayList<ArrayList<Piece>> res=new ArrayList<ArrayList<Piece>>();
        ArrayList<Piece> w1=new ArrayList<Piece>();
        ArrayList<Piece> b1=new ArrayList<Piece>();
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(b[r][c].getPiece()!=null){
                    if(b[r][c].getPieceSide()==1){
                        w1.add(b[r][c].getPiece());
                    }else{
                        b1.add(b[r][c].getPiece());
                    }
                }
            }
        }
        res.add(w1);
        res.add(b1);
        return res;
    }
    //<editor-fold defaultstate="collapsed" desc="checkDeadGame">
    public boolean checkDeadGame(){
        boolean res=false;
        ArrayList<Piece> at=new ArrayList<Piece>();
        ArrayList<Piece> bt=new ArrayList<Piece>();
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(b[r][c].getPiece()!=null){
                    if(b[r][c].getPieceSV().substring(0,1).equals("K")){
                        if(b[r][c].getPieceSide()==1){
                            at.add(b[r][c].getPiece());
                        }else{
                            bt.add(b[r][c].getPiece());
                        }
                    }
                }
            }
        }
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(b[r][c].getPiece()!=null){
                    if(!(b[r][c].getPieceSV().substring(0,1).equals("K"))){
                        if(b[r][c].getPieceSide()==1){
                            at.add(b[r][c].getPiece());
                        }else{
                            bt.add(b[r][c].getPiece());
                        }
                    }
                }
            }
        }
        int aS=at.size();
        int bS=bt.size();
        if((aS==1)&&(bS==1)){
            res=true;
        }
        if((aS==1)&&(bS==2)){
            if((bt.get(1).getSV().substring(0,1).equals("N"))){
                res=true;
            }
            if((bt.get(1).getSV().substring(0,1).equals("B"))){
                res=true;
            }
            
        }
        if((aS==2)&&(bS==1)){
            if((at.get(1).getSV().substring(0,1).equals("N"))){
                res=true;
            }
            if((at.get(1).getSV().substring(0,1).equals("B"))){
                res=true;
            }
            
        }
        if((aS==2)&&(bS==2)){
            if((at.get(1).getSV().substring(0,1).equals("B"))&&(bt.get(1).getSV().substring(0,1).equals("B"))){
                String p0=bt.get(1).getPlace();
                String p1=at.get(1).getPlace();
                int[] cords0=Place.toRC(p0);
                int[] cords1=Place.toRC(p1);
                int r0=cords0[0];
                int c0=cords0[1];
                int r1=cords1[0];
                int c1=cords1[1];
                boardPlace bp0=b[r0][c0];
                boardPlace bp1=b[r1][c1];
                if(bp0.getColor().equals(bp1.getColor())){
                    res=true;
                }
            }
        }
        return res;
        
    }
    //</editor-fold>
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public editorThread getT1(){
        return t1;
    }
    public JButton getSB(){
        return setBoard;
    }
    public void setMode(String s){
        mode=s;
    }
    public String getMode(){
        return mode;
    }
    public int getModeSide(){
        return modeSide;
    }
    public boolean getBool(){
        return Bool;
    }
    public void setBool(boolean b1){
        Bool=b1;
    }
    public boardPlace[][] getB(){
        return b;
    }
    //</editor-fold>
    public void update(){
        boolean allGood=!checkDeadGame();
        int whiteKingCt=0;
        int blackKingCt=0;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(b[r][c].getPiece()!=null){
                    if((r==0)||(r==7)){
                        if(b[r][c].getPieceSV().substring(0,1).equals("p")){
                            allGood=false;
                        }
                    }
                    if(b[r][c].getPieceSV().substring(0,1).equals("K")){
                        if(b[r][c].getPieceSide()==1){
                            whiteKingCt++;
                        }else if(b[r][c].getPieceSide()==0){
                            blackKingCt++;
                        }
                    }
                }
            }
        }
        
                
        if((whiteKingCt!=1)||(blackKingCt!=1)){
            allGood=false;
        }else{
            boardAnalyzer ba=new boardAnalyzer(b);
            ArrayList<String> wmvs=ba.whitePosMoves();
            ArrayList<String> bmvs=ba.blackPosMoves();
            if((wmvs.size()==0)||(bmvs.size()==0)){
                allGood=false;
            }
            
        }
        
        //allGood=!checkDeadGame();
        if(allGood){
            setBoard.setEnabled(true);
        }else{
            setBoard.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //<editor-fold defaultstate="collapsed" desc="pieceButtons">
        if(e.getSource()==bPawn){
            mode=bPawn.getSV();
            modeSide=bPawn.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bPawn.select();
        }
        if(e.getSource()==bBishop){
            mode=bBishop.getSV();
            modeSide=bBishop.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bBishop.select();
        }
        if(e.getSource()==bKnight){
            mode=bKnight.getSV();
            modeSide=bKnight.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bKnight.select();
        }
        if(e.getSource()==bRook){
            mode=bRook.getSV();
            modeSide=bRook.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bRook.select();
        }
        if(e.getSource()==bQueen){
            mode=bQueen.getSV();
            modeSide=bQueen.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bQueen.select();
        }
        if(e.getSource()==bKing){
            mode=bKing.getSV();
            modeSide=bKing.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            bKing.select();
        }
        if(e.getSource()==wPawn){
            mode=wPawn.getSV();
            modeSide=wPawn.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wPawn.select();
        }
        if(e.getSource()==wBishop){
            mode=wBishop.getSV();
            modeSide=wBishop.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wBishop.select();
        }
        if(e.getSource()==wRook){
            mode=wRook.getSV();
            modeSide=wRook.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wRook.select();
        }
        if(e.getSource()==wKnight){
            mode=wKnight.getSV();
            modeSide=wKnight.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wKnight.select();
        }
        if(e.getSource()==wQueen){
            mode=wQueen.getSV();
            modeSide=wQueen.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wQueen.select();
        }
        if(e.getSource()==wKing){
            mode=wKing.getSV();
            modeSide=wKing.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            wKing.select();
        }
        if(e.getSource()==trash){
            mode=trash.getSV();
            modeSide=trash.getSide();
            for(int i=0; i<bList.size(); i++){
                if(bList.get(i).getSelect()){
                    bList.get(i).setSelect(false);
                }    
            }
            trash.select();
        }
        
        //</editor-fold>
        if(e.getSource()==clearBoard){
            boardPlace[][] temp1=b;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    temp1[r][c].setPiece(null);
                }
            }
        }
        super.repaint();
    }
    @Override public void mousePressed(MouseEvent e){
        Bool=true;
        new editorThread(this);
        
        super.repaint();
    }
    @Override public void mouseClicked(MouseEvent e){
        Bool=false;
        boardPlace[][] temp1=b;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(e.getSource()==temp1[r][c]){
                    if(!(mode.equals("S"))){
                        if(mode.equals("T")){
                            temp1[r][c].setPiece(null);
                        }else{
                            temp1[r][c].setPiece(new Piece(modeSide,Place.RCtoP(temp1[r][c].getR(), temp1[r][c].getC()),mode+"1"+modeSide));
                            if(mode.equals("p")){
                                if((modeSide==1)&&(r==1)){
                                    temp1[r][c].getPiece().setMoved(1);
                                }else if((modeSide==0)&&(r==6)){
                                    temp1[r][c].getPiece().setMoved(1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @Override public void mouseReleased(MouseEvent e){
        Bool=false;        
    }
    @Override public void mouseEntered(MouseEvent e){/*non-implemented method*/}
    @Override public void mouseExited(MouseEvent e){/*non-implemented method*/}
    
}