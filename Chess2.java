/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author danny
 */
public class Chess2 extends JPanel implements ActionListener{
    private JFrame f;
    private JTabbedPane tp;
    private Graphics g2;
    private JLabel l;
    private JPanel p;
    private JButton b, bx, by, b1, b2, b3, b4;
    private JMenuItem i, i1, i2;
    private JMenuBar m,m1;
    private ButtonGroup g;
    private JMenu men;
    private int rect=0;
    private int x=200;
    private int y=200;
    private JTextField xt, yt;
    private JPanel j1;
    private Board board;
    
    private Piece p10, p11, p12, p13, p14, p15, p16, p17;
    private Piece p20, p21, p22, p23, p24, p25, p26, p27;
    
    private Piece N10;
    private Piece N11;
    private Piece N20;
    private Piece N21;
    private Piece b10;
    private Piece b11;
    private Piece b20;
    private Piece b21;
    private Piece r10;
    private Piece r11;
    private Piece r20;
    private Piece r21;
    private Piece k1;
    private Piece k2;
    private Piece q1;
    private Piece q2;
    private Piece p28;
    private Board funBoard;
    private boardEditor be;
    private JButton moveButton;
    private JTextField moveField;
    public Chess2(JFrame fr) {
       
        Font vb12=new Font("Vernana",Font.BOLD,12);
        f=fr;
        super.setOpaque(true);
        super.setSize(new Dimension(1920,1080));
        super.setBackground(Color.WHITE);
        super.setLayout(null);
       
        super.repaint();
       
        ArrayList<Piece> testw=new ArrayList<Piece>();
        ArrayList<Piece> testb=new ArrayList<Piece>();
       
        q1=new Piece(1, "d1", "Q1");
        q2=new Piece(0, "d8", "Q2");
        k1=new Piece(1, "e1", "K1");
        k2=new Piece(0, "e8", "K2");
        testw.add(k1);
        testb.add(k2);
        testw.add(q1);
        testb.add(q2);
            
        N10=new Piece(1, "g1", "N10");
        N11=new Piece(1, "b1", "N11");
        N20=new Piece(0,"g8", "N20");
        N21=new Piece(0, "b8", "N21");
        testw.add(N10);
        testw.add(N11);
        testb.add(N20);
        testb.add(N21);
       
        b10=new Piece(1,"c1", "B10");
        b11=new Piece(1,"f1", "B11");
        b20=new Piece(0,"c8", "B20");
        b21=new Piece(0, "f8", "B21");
        testw.add(b10);
        testw.add(b11);
        testb.add(b20);
        testb.add(b21);

        r10=new Piece(1, "a1", "R10");
        r11=new Piece(1, "h1", "R11");
        r20=new Piece(0, "a8", "R20");
        r21=new Piece(0, "h8", "R21");
        testw.add(r10);
        testw.add(r11);
        testb.add(r20);
        testb.add(r21);
        
        p10=new Piece(1, "a2", "p10");
        p11=new Piece(1, "b2", "p11"); 
        p12=new Piece(1, "c2", "p12");
        p13=new Piece(1, "d2", "p13");
        p14=new Piece(1, "e2", "p14");
        p15=new Piece(1, "f2", "p15");
        p16=new Piece(1, "g2", "p16");
        p17=new Piece(1, "h2", "p17");
        p20=new Piece(0, "a7", "p20");
        p21=new Piece(0, "b7", "p21"); 
        p22=new Piece(0, "c7", "p22");
        p23=new Piece(0, "d7", "p23");
        p24=new Piece(0, "e7", "p24");
        p25=new Piece(0, "f7", "p25");
        p26=new Piece(0, "g7", "p26");
        p27=new Piece(0, "h7", "p27");
        testw.add(p10);
        testw.add(p11);
        testw.add(p12);
        testw.add(p13);
        testw.add(p14);
        testw.add(p15);
        testw.add(p16);
        testw.add(p17);
        testb.add(p20);
        testb.add(p21);
        testb.add(p22);
        testb.add(p23);
        testb.add(p24);
        testb.add(p25);
        testb.add(p26);
        testb.add(p27);
        
                
        //BOARD METHODS
       boardPlace[][] bpb=new boardPlace[8][8];
       boolean bool=true;
       for(int r=0; r<8; r++){
           for(int c=0; c<8; c++){
               bpb[r][c]=new boardPlace(r,c,bool);
               bool=!bool;
           }
           bool=!bool;
       }
       for(int i=0; i<testw.size(); i++){
           Piece p1=testw.get(i);
           int[] cords=Place.toRC(p1.getPlace());
           int r=cords[0];
           int c=cords[1];
           bpb[r][c].setPiece(p1);
           
           
       }
       for(int i=0; i<testb.size(); i++){
           Piece p1=testb.get(i);
           int[] cords=Place.toRC(p1.getPlace());
           int r=cords[0];
           int c=cords[1];
           bpb[r][c].setPiece(p1);
       }
       ArrayList<boardPlace[][]> positionListT=new ArrayList<boardPlace[][]>();
       positionListT.add(bpb);
       boardPlace[][] temp=new boardPlace[8][8];
       bool=true;
       for(int r=0; r<8; r++){
           for(int c=0; c<8; c++){
               temp[r][c]=new boardPlace(r,c,bool);
               bool=!bool;
           }
           bool=!bool;
       }
       for(int i=0; i<testw.size(); i++){
           Piece p1=testw.get(i);
           int[] cords=Place.toRC(p1.getPlace());
           int r=cords[0];
           int c=cords[1];
           temp[r][c].setPiece(p1);
           
           
       }
       for(int i=0; i<testb.size(); i++){
           Piece p1=testb.get(i);
           int[] cords=Place.toRC(p1.getPlace());
           int r=cords[0];
           int c=cords[1];
           temp[r][c].setPiece(p1);
       }
       
       funBoard=new Board(temp);
       funBoard.testButtonVisible(true);
       funBoard.setBounds(0,0,Board.PLACE_SIZE*8+200,Board.PLACE_SIZE*8+200);
       

       
       board = new Board(bpb);
       board.testButtonVisible(false);
       board.setBounds(0,0,Board.PLACE_SIZE*8+200,Board.PLACE_SIZE*8+200);
       
       
       //super.add(board);
       be=new boardEditor();
       
       
       tp=new JTabbedPane();
       tp.setBounds(0,0,1000,1000);
       tp.add("BOARD",board);
       tp.add("BOARD EDITOR",be);
       tp.add("COOL BOARD",funBoard);
       be.getSB().addActionListener(this);
       
       super.add(tp);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==be.getSB()){
            boardPlace[][] bpb=be.getB();
            boardPlace[][] temp=new boardPlace[8][8];
            boolean b4=true;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(bpb[r][c].getPiece()==null){
                        boardPlace bp=new boardPlace(r,c,b4);
                        temp[r][c]=bp;
                    }else{
                        String sv=bpb[r][c].getPieceSV();
                        int side=bpb[r][c].getPieceSide();
                        String place=Place.RCtoP(r, c);
                        int moved=bpb[r][c].getPiece().getMoved();
                        Piece p=new Piece(side,place,sv);
                        p.setMoved(moved);
                        boardPlace bp=new boardPlace(r,c,b4);
                        bp.setPiece(p);
                        temp[r][c]=bp;
                    }
                    b4=!b4;
                }
                b4=!b4;
            }
            
            boardPlace[][] temp1=new boardPlace[8][8];
            b4=true;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(bpb[r][c].getPiece()==null){
                        boardPlace bp=new boardPlace(r,c,b4);
                        temp1[r][c]=bp;
                    }else{
                        String sv=bpb[r][c].getPieceSV();
                        int side=bpb[r][c].getPieceSide();
                        String place=Place.RCtoP(r, c);
                        int moved=bpb[r][c].getPiece().getMoved();
                        Piece p=new Piece(side,place,sv);
                        p.setMoved(moved);
                        boardPlace bp=new boardPlace(r,c,b4);
                        bp.setPiece(p);
                        temp1[r][c]=bp;
                    }
                    b4=!b4;
                }
                b4=!b4;
            }
            
            board=new Board(temp);
            board.testButtonVisible(false);
            funBoard=new Board(temp1);
            funBoard.testButtonVisible(true);
            be=new boardEditor();
            tp.remove(0);
            tp.remove(0);
            tp.remove(0);
            tp.add("BOARD",board);
            tp.add("BOARD EDITOR",be);
            tp.add("COOL BOARD",funBoard);
            be.getSB().addActionListener(this);
            super.repaint();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame fr=new JFrame("Chess2");
        fr.setContentPane(new Chess2(fr));
        fr.setPreferredSize(new Dimension(1000,1000));
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(0,0);
        fr.setResizable(false);
        fr.pack();
        fr.setVisible(true);
    }
    
    
}