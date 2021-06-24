/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author danny
 */
public class Board extends JPanel  implements ActionListener, MouseListener{
    //<editor-fold defaultstate="collapsed" desc="--instance variable declarations--">
    private int whiteScore=0; 
    private int blackScore=0;
    private boardPlace bpS1=null;
    private boardPlace bpS2=null;
    private int fmrp=0;
    private int turn=1;
    public static final int PLACE_SIZE=90;
    private boardPlace[][] bOG=new boardPlace[8][8];
    private ArrayList<boardPlace[][]> positionList=new ArrayList<boardPlace[][]>();
    
    private boardPlace[][] b=new boardPlace[8][8];
    private ArrayList<Piece> a1=new ArrayList<Piece>();
    
    private ArrayList<Piece> b1=new ArrayList<Piece>();
    
    private boolean isFun = false;
    
    
    private ArrayList<boardPlace> flaggedPlaces=new ArrayList<boardPlace>();
    private boardAnalyzer ba;
    private JFrame f;
    
    private boolean done=false;
    private boolean done1=false;
    private ArrayList<String> moveList=new ArrayList<String>();
    
    private JButton restartButton;
    private JButton queenProm;
    private JButton knightProm;
    private JButton bishopProm;
    private JButton rookProm;
   
    private int games=1;
    private int moveNum=1;
    private boardPlace selected = null;
    private JEditorPane movePane;
    private JScrollPane movePane2;
    private String movePaneText;
    private JButton testButton;
    private JButton stopTest;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="--constructor--">
    public Board(boardPlace[][] bpb){
       
        //f=fr;
        
       
        
        testButton=new JButton();
        testButton.setText("TEST");
        testButton.setBounds(90,730,100,50);
        testButton.addActionListener(this);
        testButton.setVisible(true);
        super.add(testButton);
        
        stopTest=new JButton();
        stopTest.setText("STOP");
        stopTest.setBounds(200,730,100,50);
        stopTest.addActionListener(this);
        stopTest.setVisible(true);
        super.add(stopTest);
        
        movePaneText="Game: "+games+" \n";
        
        queenProm=new JButton();
        queenProm.setText("QUEEN");
        queenProm.addActionListener(this);
        queenProm.setBounds(10,730,100,50);
        queenProm.setVisible(false);
        super.add(queenProm);
        
        knightProm=new JButton();
        knightProm.setText("KNIGHT");
        knightProm.addActionListener(this);
        knightProm.setBounds(120,730,100,50);
        knightProm.setVisible(false);
        super.add(knightProm);
        
        rookProm=new JButton();
        rookProm.setText("ROOK");
        rookProm.addActionListener(this);
        rookProm.setBounds(230,730,100,50);
        rookProm.setVisible(false);
        super.add(rookProm);
        
        bishopProm=new JButton();
        bishopProm.setText("BISHOP");
        bishopProm.addActionListener(this);
        bishopProm.setBounds(340,730,100,50);
        bishopProm.setVisible(false);
        super.add(bishopProm);
        
        restartButton=new JButton();
        restartButton.setText("RESTART");
        restartButton.addActionListener(this);
        restartButton.setBounds(10,840,100,50);
        restartButton.setVisible(false);
        super.add(restartButton);
        
        
        
        movePane=new JEditorPane();
        movePane.setBounds(0,0,200,PLACE_SIZE*8);
        movePane.setEditable(false);
        movePane.setText(movePaneText);
        movePane.setVisible(true);
        
        movePane2=new JScrollPane(movePane);
        movePane2.setBounds(720,0,200,PLACE_SIZE*8);
        movePane2.setVisible(true);
        
        super.add(movePane2);
        b=bpb;
        bOG=bpb;
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                
                b[r][c].addActionListener(this);
                b[r][c].addMouseListener(this);
                super.add(b[r][c]);
                
                b4=!b4;
            }
            b4=!b4;
        }
        
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                boardPlace place=b[r][c];
                if(place.getPiece()!=null){
                    if(place.getPieceSide()==1){
                        a1.add(place.getPiece());
                    }else{
                        b1.add(place.getPiece());
                    }
                }
            }
        }
        
        
        ba=new boardAnalyzer(b);
        
        //boardPlace[][] temp=ba.move("Ke1d1");
        //System.out.println("Danger"+ba.whiteKingDanger(temp));
        positionList.add(b);
        
        super.setPreferredSize(new Dimension(PLACE_SIZE*8,PLACE_SIZE*8));
        super.setBackground(new Color(224, 224, 224));    
        super.setLayout(null);
        super.setVisible(true);
        super.repaint();
        
        if(checkDeadGame()){
            lockBoard();
            restartButton.setVisible(true);
            
        }
        
    }
    public void testButtonVisible(boolean b){
        testButton.setVisible(b);
        stopTest.setVisible(b);
    }
    public void updateMovePane(){
        movePane.setText(movePaneText); 
        movePane2.getVerticalScrollBar().setValue(movePane2.getVerticalScrollBar().getMaximum());
    }
    public void concatMovePaneText(String s){
        movePaneText+=s;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public int getBlackScore(){
        return blackScore;
    }
    public void setBlackScore(int i){
        blackScore=i;
    }
    public int getWhiteScore(){
        return whiteScore;
    }
    public void setWhiteScore(int i){
        whiteScore=i;
    }
    public int getMoveNum(){
        return moveNum;
    }
    public boolean getDone(){
        return done;
    }
    
    public int getFMRP(){
        return fmrp;
    }
    public int getTurn(){
        return turn;
    }
    public boardPlace[][] getbOG(){
        return bOG;
    }
    public ArrayList<boardPlace[][]> getPositionList(){
        return positionList;
    }
    
    public ArrayList<String>  getMoveList(){
        return moveList;
    }
    public boardPlace[][] getB(){
        return b;
    }
    public boolean getDone1(){
        return done1;
    }
    public void setDone1(boolean b1){
        done1=b1;
    }
    public void setB(boardPlace[][] b11){
        b=b11;
        a1=new ArrayList<Piece>();
        b1=new ArrayList<Piece>();
        bOG=b11;
        boolean b4=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                
                b[r][c].addActionListener(this);
                b[r][c].addMouseListener(this);
                super.add(b[r][c]);
                
                b4=!b4;
            }
            b4=!b4;
        }
        
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                boardPlace place=b[r][c];
                if(place.getPiece()!=null){
                    if(place.getPieceSide()==1){
                        a1.add(place.getPiece());
                    }else{
                        b1.add(place.getPiece());
                    }
                }
            }
        }
        ba=new boardAnalyzer(b);
        positionList=new ArrayList<boardPlace[][]>();
        positionList.add(b);
        fmrp=0;
        games=1;
        moveNum=1;
    }
    public ArrayList<Piece> getA1(){
        return a1;
    }
    
    public ArrayList<Piece> getB1(){
        return b1;
    }
    public int getGames(){
        return games;
    }
    public void setGames(int i){
        games=i;
    }
    public boardAnalyzer getBA(){
        return ba;
    }
    public JScrollPane getMovePane2(){
        return movePane2;
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="possibleMoves">
    public ArrayList<String> possibleMoves(){
        if(turn==1){
            
            return ba.whitePosMoves();
        }else{
           
            return ba.blackPosMoves();
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="update">
    public void update(){
        if(checkDeadGame()||FFRP()||FMRP()){
            done=true;
        }
        if(turn==1){
            movePaneText+="         ";
            turn=0;
        }else{
            movePaneText+="\n";
            turn=1;
        }
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c].addActionListener(this);
                b[r][c].addMouseListener(this);
                super.add(b[r][c]);
                
            }
        }
        movePane.setText(movePaneText);
        super.repaint();
    }
    

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="move">
    public void move(String m){
                    
            //System.out.println("ISDEAD: "+checkDeadGame());
                    
            //System.out.println("GAME: "+games);
            //System.out.println("MOVE NUMBER: "+moveNum);
            //System.out.println(a1);
            //System.out.println(b1);
            moveList.add(turn+": "+m);
            movePaneText+=turn+": "+m;
            //System.out.println(possibleMoves());
            //System.out.println("MOVE: "+m);
            //printBoard(ba.move(m));
            boolean fmrpb=false;
            boolean fmrbpt=true;
            for(int i=0; i<m.length(); i++){
                if(m.substring(i,i+1).equals("x")){
                    fmrpb=true;
                }
            }
            String s=m.substring(0,1);
            if(s.equals("K")){
                fmrbpt=false;
            }
            if(s.equals("Q")){
                fmrbpt=false;
            }
            if(s.equals("R")){
                fmrbpt=false;
            }
            if(s.equals("N")){
                fmrbpt=false;
            }
            if(s.equals("B")){
                fmrbpt=false;
            }
            if(fmrpb||fmrbpt){
                fmrp=0;
            }else{
                fmrp++;
            }
            
            //System.out.println(fmrp);
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    super.remove(b[r][c]);
                }
            }
            boardPlace[][] moved=ba.move(m);
            boardPlace[][] res=new boardPlace[8][8];
            boolean b4=true;
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(moved[r][c].getPiece()==null){
                        boardPlace temp=new boardPlace(r,c,b4);
                        temp.setColor(moved[r][c].getColor());
                        res[r][c]=temp;
                    }else{
                        boardPlace temp=new boardPlace(r,c,b4);
                        temp.setColor(moved[r][c].getColor());
                        String sv=moved[r][c].getPieceSV();
                        String place=Place.RCtoP(r, c);
                        int side=moved[r][c].getPieceSide();
                        int moves=moved[r][c].getPiece().getMoved();
                        Piece tempPiece=new Piece(side,place,sv);
                        tempPiece.setMoved(moves);
                        temp.setPiece(tempPiece);
                        res[r][c]=temp;
                    }
                    b4=!b4;
                }
                b4=!b4;
            }
        
            b=res;
            ArrayList<Piece> at=new ArrayList<Piece>();
            ArrayList<Piece> bt=new ArrayList<Piece>();
            for(int r=0; r<8; r++){
                for(int c=0; c<8; c++){
                    if(b[r][c].getPiece()!=null){
                        if(b[r][c].getPieceSide()==1){
                            at.add(b[r][c].getPiece());
                        }else{
                            bt.add(b[r][c].getPiece());
                        }
                    }
                }
            }
            a1=at;
            b1=bt;
            ArrayList<String> places=Place.decifer(m);
            int[] cords=Place.toRC(places.get(1));
            int r=cords[0];
            int c=cords[1];
            b[r][c].getPiece().moveInc();
            moveNum++;
            
            
            
            
            boolean b6=false;
            boolean b7=false;
            for(int c1=0; c1<8; c1++){
                if(b[7][c1].getPiece()!=null){
                    if(b[7][c1].getPieceSV().substring(0,1).equals("p")){
                        b6=true;
                    }
                }
            }
            for(int c1=0; c1<8; c1++){
                if(b[0][c1].getPiece()!=null){
                    if(b[0][c1].getPieceSV().substring(0,1).equals("p")){
                        b7=true;
                    }
                }
            }
            if(!(b6||b7)){
                ba=new boardAnalyzer(b);
            }else{
                lockBoard();
                queenProm.setVisible(true);
                bishopProm.setVisible(true);
                rookProm.setVisible(true);
                knightProm.setVisible(true);
            }
            
            update();
            for(int r1=0; r1<8; r1++){
                for(int c1=0; c1<8; c1++){
                    b[r][c].repaint();
                }
            }
            positionList.add(res);
            movePane2.getVerticalScrollBar().setValue(movePane2.getVerticalScrollBar().getMaximum());
            super.repaint();
        
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="isEqual">
    public boolean isEqual(boardPlace[][] bpb1, boardPlace[][] bpb2){
        boolean res=true;
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(!(bpb1[r][c].toString().equals(bpb2[r][c].toString()))){
                    res=false;
                }
            }
        }
        return res;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="FFRP">
    public boolean FFRP(){
        int r=1;
        boardPlace[][] bpb1=positionList.get(positionList.size()-1);
        for(int i=positionList.size()-2; i>=0; i--){
            if(isEqual(bpb1,positionList.get(i))){
                r++;
            }
        }
        //System.out.println("REPEATS: "+r);
        //System.out.println("FFR: "+(r>=5));
        return (r>=5);
    }
    //</editor-fold>
    
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
    //<editor-fold defaultstate="collapsed" desc="FMRP">
    public boolean FMRP(){
        return (fmrp>=50);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="restart">
    public void restart(){
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                super.remove(b[r][c]);
            }
        }
        boardPlace[][] res=new boardPlace[8][8];
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                if(bOG[r][c].getPiece()==null){
                    Color C=bOG[r][c].getColor();
                    boardPlace temp=new boardPlace(r,c,false);
                    temp.setColor(C);
                    res[r][c]=temp;
                }else{
                    boardPlace temp=new boardPlace(r,c,false);
                    temp.setColor(bOG[r][c].getColor());
                    String sv=bOG[r][c].getPieceSV();
                    String place=Place.RCtoP(r, c);
                    int side=bOG[r][c].getPieceSide();
                    int moves=bOG[r][c].getPiece().getMoved();
                    Piece tempPiece=new Piece(side,place,sv);
                    tempPiece.setMoved(moves);
                    temp.setPiece(tempPiece);
                    res[r][c]=temp;
                }
            }
        }
        
        b=res;
        ba=new boardAnalyzer(b);
        turn=1;
        moveList=new ArrayList<String>();
        positionList=new ArrayList<boardPlace[][]>();
        positionList.add(b);
        done=false;
        fmrp=0;
        restartButton.setVisible(false);
        //System.out.println(games);
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c].addActionListener(this);
                b[r][c].addMouseListener(this);
                super.add(b[r][c]);
            }
        }
        
        moveNum=1;
        unlockBoard(); 
        if(checkDeadGame()){
            restartButton.setVisible(true);
            lockBoard();
        }
        movePaneText+="Game: "+games+"\n";
        movePane.setText(movePaneText);
        super.repaint();
        
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="select">
    public void select(boardPlace bp){
        if(bpS1==null){
            bp.select();
            bpS1=bp;
            
        }else{
            bp.select();
            bpS2=bp;
        }
    }
    //</editor-fold>
    
   
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==restartButton){
            restart();
            if(isFun){
                testButton.setVisible(true);
                stopTest.setVisible(true);
            }
        }
        if(e.getSource()==queenProm){
            if(turn==1){
                for(int c=0; c<8; c++){
                    if((b[7][c].getPiece()!=null)&&(b[7][c].getPieceSide()==0)&&(b[7][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[7][c].getPieceSV();
                        String sv1="Q"+sv.substring(1,sv.length());
                        b[7][c].getPiece().setSV(sv);
                        Piece p=new Piece(b[7][c].getPieceSide(),Place.RCtoP(7, c),sv1);
                        b[7][c].setPiece(p);
                    }
                }
            }else{
                for(int c=0; c<8; c++){
                    if((b[0][c].getPiece()!=null)&&(b[0][c].getPieceSide()==1)&&(b[0][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[0][c].getPieceSV();
                        String sv1="Q"+sv.substring(1,sv.length());
                        
                        Piece p=new Piece(b[0][c].getPieceSide(),Place.RCtoP(0, c),sv1);
                        b[0][c].setPiece(p);
                        
                    }
                }
            }
            ba=new boardAnalyzer(b);
            String s=moveList.remove(moveList.size()-1);
            moveList.add(s+"=Q");
            positionList.remove(positionList.size()-1);
            positionList.add(b);
            queenProm.setVisible(false);
            knightProm.setVisible(false);
            rookProm.setVisible(false);
            bishopProm.setVisible(false);
            if(isFun){
                testButton.setVisible(true);
                stopTest.setVisible(true);
            }
            unlockBoard();
        }
        if(e.getSource()==knightProm){
            if(turn==1){
                for(int c=0; c<8; c++){
                    if((b[7][c].getPiece()!=null)&&(b[7][c].getPieceSide()==0)&&(b[7][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[7][c].getPieceSV();
                        String sv1="N"+sv.substring(1,sv.length());
                        b[7][c].getPiece().setSV(sv);
                        Piece p=new Piece(b[7][c].getPieceSide(),Place.RCtoP(7, c),sv1);
                        b[7][c].setPiece(p);
                    }
                }
            }else{
                for(int c=0; c<8; c++){
                    if((b[0][c].getPiece()!=null)&&(b[0][c].getPieceSide()==1)&&(b[0][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[0][c].getPieceSV();
                        String sv1="N"+sv.substring(1,sv.length());
                        
                        Piece p=new Piece(b[0][c].getPieceSide(),Place.RCtoP(0, c),sv1);
                        b[0][c].setPiece(p);
                        
                    }
                }
            }
            ba=new boardAnalyzer(b);
            String s=moveList.remove(moveList.size()-1);
            moveList.add(s+"=N");
            positionList.remove(positionList.size()-1);
            positionList.add(b);
            queenProm.setVisible(false);
            knightProm.setVisible(false);
            rookProm.setVisible(false);
            bishopProm.setVisible(false);
            if(isFun){
                testButton.setVisible(true);
                stopTest.setVisible(true);
            }
            unlockBoard();
        }
        if(e.getSource()==rookProm){
            if(turn==1){
                for(int c=0; c<8; c++){
                    if((b[7][c].getPiece()!=null)&&(b[7][c].getPieceSide()==0)&&(b[7][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[7][c].getPieceSV();
                        String sv1="R"+sv.substring(1,sv.length());
                        b[7][c].getPiece().setSV(sv);
                        Piece p=new Piece(b[7][c].getPieceSide(),Place.RCtoP(7, c),sv1);
                        b[7][c].setPiece(p);
                    }
                }
            }else{
                for(int c=0; c<8; c++){
                    if((b[0][c].getPiece()!=null)&&(b[0][c].getPieceSide()==1)&&(b[0][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[0][c].getPieceSV();
                        String sv1="R"+sv.substring(1,sv.length());
                        
                        Piece p=new Piece(b[0][c].getPieceSide(),Place.RCtoP(0, c),sv1);
                        b[0][c].setPiece(p);
                        
                    }
                }
            }
            ba=new boardAnalyzer(b);
            String s=moveList.remove(moveList.size()-1);
            moveList.add(s+"=R");
            positionList.remove(positionList.size()-1);
            positionList.add(b);
            queenProm.setVisible(false);
            knightProm.setVisible(false);
            rookProm.setVisible(false);
            bishopProm.setVisible(false);
            if(isFun){
                testButton.setVisible(true);
                stopTest.setVisible(true);
            }
            unlockBoard();
        }
        if(e.getSource()==bishopProm){
            if(turn==1){
                for(int c=0; c<8; c++){
                    if((b[7][c].getPiece()!=null)&&(b[7][c].getPieceSide()==0)&&(b[7][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[7][c].getPieceSV();
                        String sv1="B"+sv.substring(1,sv.length());
                        b[7][c].getPiece().setSV(sv);
                        Piece p=new Piece(b[7][c].getPieceSide(),Place.RCtoP(7, c),sv1);
                        b[7][c].setPiece(p);
                    }
                }
            }else{
                for(int c=0; c<8; c++){
                    if((b[0][c].getPiece()!=null)&&(b[0][c].getPieceSide()==1)&&(b[0][c].getPieceSV().substring(0,1).equals("p"))){
                        String sv=b[0][c].getPieceSV();
                        String sv1="B"+sv.substring(1,sv.length());
                        
                        Piece p=new Piece(b[0][c].getPieceSide(),Place.RCtoP(0, c),sv1);
                        b[0][c].setPiece(p);
                        
                    }
                }
            }
            ba=new boardAnalyzer(b);
            String s=moveList.remove(moveList.size()-1);
            moveList.add(turn+": "+s+"=B");
            positionList.remove(positionList.size()-1);
            positionList.add(b);
            queenProm.setVisible(false);
            knightProm.setVisible(false);
            rookProm.setVisible(false);
            bishopProm.setVisible(false);
            if(isFun){
                testButton.setVisible(true);
                stopTest.setVisible(true);
            }
            unlockBoard();
        }
        if(e.getSource()==testButton){
            done1=false;
            new MyThread("test","test",this);
        }
        if(e.getSource()==stopTest){
            done1=true;
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="--toString--">      
    @Override
    public String toString(){
        String res="";
        for(int r=0; r<8; r++){
            String s1="";
            for(int c=0; c<8; c++){
                s1+=b[r][c].toString()+" ";
            }
            s1+="\n";
            res+=s1;
        }
     
        
        return res;
    }
    //</editor-fold>
    public void wipe(){
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c].unselect();
            }
        }
        for(int i=0; i<flaggedPlaces.size(); i++){
            flaggedPlaces.get(i).setFlag(false);
            
        }
        flaggedPlaces=new ArrayList<boardPlace>();
    }
    public ArrayList<String> getPieceMoves(boardPlace bp1){
        ArrayList<String> res=new ArrayList<String>();
        ArrayList<String> mvs=this.possibleMoves();
        String pl=bp1.getPiece().getPlace();
        for(int i=0; i<mvs.size(); i++){
            ArrayList<String> places=Place.decifer(mvs.get(i));
            if(places.get(0).equals(pl)){
                res.add(mvs.get(i));
            }
        }
        if(bp1.getPieceSV().substring(0,1).equals("K")){
            ArrayList<String> step1=new ArrayList<String>();
            for(int i=0; i<res.size(); i++){
                String s1=mvs.get(i);
                String res1="";
                boolean b1=false;
                for(int k=0; k<s1.length(); k++){
                    if(s1.substring(k,k+1).equals("O")){
                        b1=true;
                    }
                }
                if(b1){
                 
                    ArrayList<String> places=Place.decifer(s1);
                    String p1=places.get(1);
                    
                    if(p1.substring(0,1).equals("a")){
                        p1="b"+p1.substring(1,p1.length());
                    }else if(p1.substring(0,1).equals("h")){
                        p1="g"+p1.substring(1,p1.length());
                    }
                    s1="K"+places.get(0)+"O"+p1;
                    res.set(i,s1);
                }
            }
        }else{
            boolean b2=false;
            for(int i=0; i<res.size(); i++){
                for(int j=0; j<res.get(i).length(); j++){
                    if(res.get(i).substring(j,j+1).equals("=")){
                        
                        b2=true;
                    }
                }
                if(b2){
                    String s1=res.get(i);
                    String res1=s1.substring(0,s1.length()-2);
                    res.set(i,res1);
                }
            }
        }
        return res;
    }
    
    @Override 
    public void mousePressed(MouseEvent e){
        
        boardPlace bp = (boardPlace) e.getSource();
        if(bp.isEnabled()){
        if(selected!=null){
            ArrayList<String> mvs=getPieceMoves(selected);
            boolean b4=bp.isEnabled();
        }
        if(e.getModifiers()==InputEvent.BUTTON3_MASK){
            
            bp.flag();
        }
        if(e.getModifiers()==InputEvent.BUTTON1_MASK){
            if(bp.getPiece()!=null){
                wipe();
                if(bp.getPieceSide()==turn){
                    if(bp.equals(selected)){
                        wipe();
                        int r=bp.getR();
                        int c=bp.getC();
                        b[r][c].unselect();
                        selected=null;
                    
                        
                    }else{
                                
                        ArrayList<String> mvs=getPieceMoves(bp);
                        for(int i=0; i<mvs.size(); i++){
                            ArrayList<String> places=Place.decifer(mvs.get(i));
                            int[] cords=Place.toRC(places.get(1));
                            
                            int r=cords[0];
                            int c=cords[1];
                            b[r][c].setFlag(true);
                            flaggedPlaces.add(b[r][c]);
                        
                        }
                        bp.select();
                        selected=bp;
                    }
                    
                }else if(bp.getPieceSide()!=turn){
                    if(selected!=null){
                        ArrayList<String> mvs=getPieceMoves(selected);
                        boolean b=false;
                        String res="";
                        
                   
                        String p=Place.RCtoP(bp.getR(), bp.getC());
                        for(int i=0; i<mvs.size(); i++){
                            ArrayList<String> places=Place.decifer(mvs.get(i));
                            if(p.equals(places.get(1))){
                                b=true;
                                res=mvs.get(i);
                                break;
                            }
                        }
                        if(b){
                            wipe();
                            move(res);
                            selected=null;
                        }
                    }
                }
            }else{
                
                if(selected!=null){
                    ArrayList<String> mvs=getPieceMoves(selected);
                    boolean b1=false;
                    String res="";
                    
                   
                    String p=Place.RCtoP(bp.getR(), bp.getC());
                    for(int i=0; i<mvs.size(); i++){
                        ArrayList<String> places=Place.decifer(mvs.get(i));
                        if(p.equals(places.get(1))){
                            b1=true;
                            res=mvs.get(i);
                            break;
                        }
                    }
                    if(b1){
                        if(res.substring(0,1).equals("K")){
                            boolean b2=false;
                            for(int i=0; i<res.length(); i++){
                                if(res.substring(i,i+1).equals("O")){
                                    b2=true;
                                } 
                            }
                            if(b2){
                                ArrayList<String> places=Place.decifer(res);
                                String p1=places.get(1);
                                if(p1.substring(0,1).equals("b")){
                                    p1="a"+p1.substring(1,p1.length());
                                }else if(p1.substring(0,1).equals("g")){
                                    p1="h"+p1.substring(1,p1.length());
                                }
                                res="K"+places.get(0)+"O"+p1;
                            }
                        }
                        wipe();
                        move(res);
                        if(turn==1){
                            boolean b2=false;
                            for(int c=0; c<8; c++){
                                if(b[7][c].getPiece()!=null){
                                    if(b[7][c].getPieceSide()==0){
                                        if(b[7][c].getPieceSV().substring(0,1).equals("p")){
                                            b2=true;
                                        }
                                    }
                                }
                            }
                            if(b2){
                                lockBoard();
                                queenProm.setVisible(true);
                                bishopProm.setVisible(true);
                                rookProm.setVisible(true);
                                knightProm.setVisible(true);
                                if((testButton.isVisible())||(stopTest.isVisible())){
                                    isFun=true;
                                    testButton.setVisible(false);
                                    stopTest.setVisible(false);
                                }
                            }
                        }else{
                            boolean b2=false;
                            for(int c=0; c<8; c++){
                                if(b[0][c].getPiece()!=null){
                                    System.out.println(b[7][c].getPieceSV());
                                    if(b[0][c].getPieceSide()==1){
                                        if(b[0][c].getPieceSV().substring(0,1).equals("p")){
                                            b2=true;
                                        }
                                    }
                                }
                            }
                            if(b2){
                                lockBoard();
                                queenProm.setVisible(true);
                                bishopProm.setVisible(true);
                                rookProm.setVisible(true);
                                knightProm.setVisible(true);
                                if((testButton.isVisible())||(stopTest.isVisible())){
                                    isFun=true;
                                    testButton.setVisible(false);
                                    stopTest.setVisible(false);
                                }
                            }
                        }
                        selected=null;
                    }
                }
            }
            boolean whiteMate=false;
            boolean blackMate=false;
            boolean Mate=false;
            if(this.possibleMoves().size()==0){
                done=true;
                if(turn==1){
                    Mate=true;
                    if(ba.whiteKingDanger(b)){
                        whiteMate=true;
                    }
                }else{
                    Mate=true;
                    if(ba.blackKingDanger(b)){
                        blackMate=true;
                    }
                }
            }
            if(done||FFRP()||FMRP()||checkDeadGame()){
                lockBoard();
                restartButton.setVisible(true);
                if((testButton.isVisible())||(stopTest.isVisible())){
                    isFun=true;
                    testButton.setVisible(false);
                    stopTest.setVisible(false);
                }
                games++;
                movePaneText+="\n";
                if(done){
                    if(whiteMate){
                        blackScore++;
                        movePaneText+="BLACK WINS: CHECKMATE\n SCORE: "+whiteScore+", "+blackScore+"\n";
                    }else if(blackMate){
                        whiteScore++;
                        movePaneText+="WHITE WINS: CHECKMATE\n SCORE: "+whiteScore+", "+blackScore+"\n";
                    }else if(Mate){
                       movePaneText+="DRAW: STALEMATE\n SCORE: "+whiteScore+", "+blackScore+"\n"; 
                    }
                }else if(FFRP()){
                    movePaneText+="DRAW: FIVEFOLD REPETITION\n SCORE: "+whiteScore+", "+blackScore+"\n"; 
                }else if(FMRP()){
                    movePaneText+="DRAW: FIFTY MOVE RULE\n SCORE: "+whiteScore+", "+blackScore+"\n"; 
                }else if(checkDeadGame()){
                    movePaneText+="DRAW: DEAD GAME (NO POSSIBLE MATES)\n SCORE: "+whiteScore+", "+blackScore+"\n"; 
                }
                movePaneText+="\n";
                movePane.setText(movePaneText);
                
            }
        }
        }
        super.repaint();
    }
    public void lockBoard(){
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c].setEnabled(false);
            }
        }
    }
    public void unlockBoard(){
        for(int r=0; r<8; r++){
            for(int c=0; c<8; c++){
                b[r][c].setEnabled(true);
            }
        }
    }
  @Override public void mouseClicked(MouseEvent e){/*non-implemented method*/}
  @Override public void mouseReleased(MouseEvent e){/*non-implemented method*/}
  @Override public void mouseEntered(MouseEvent e){/*non-implemented method*/}
  @Override public void mouseExited(MouseEvent e){/*non-implemented method*/}
  //<editor-fold defaultstate="collapsed" desc="--printBoard--">  
  public static void printBoard(boardPlace[][] bpb){
        String res="";
        for(int r=0; r<8; r++){
            String s1="";
            for(int c=0; c<8; c++){
                s1+=bpb[r][c].toString()+" ";
            }
            s1+="\n";
            res+=s1;
        }
        System.out.println(res);
    }
  //</editor-fold>
  
  
}