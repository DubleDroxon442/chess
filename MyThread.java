/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess2;

/**
 *
 * @author danny
 */
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class MyThread implements Runnable{
    private String name;
    private String process;
    private Thread t;
    
    private int fmrp=0;
    private int turn=1;
    private boardPlace[][] bOG=new boardPlace[8][8];
    private ArrayList<boardPlace[][]> positionList=new ArrayList<boardPlace[][]>();
    private boardPlace[][] b=new boardPlace[8][8];
    private ArrayList<Piece> a1=new ArrayList<Piece>();
    private ArrayList<Piece> b1=new ArrayList<Piece>();
    private boardAnalyzer ba;
    private boolean done=false;
    private boolean done1=false;
    private ArrayList<String> moveList=new ArrayList<String>();
    private int moveNum=1;
    private Board board;
    public MyThread(String name1, String process1, Board board1){
        board=board1;
        fmrp=board.getFMRP();
        turn=board.getTurn();
        bOG=board.getbOG();
        positionList=board.getPositionList();
        b=board.getB();
        a1=board.getA1();
        b1=board.getB1();
        ba=new boardAnalyzer(b);
        done=board.getDone();
        moveList=board.getMoveList();
        moveNum=board.getMoveNum();
        
        
        name=name1;
        process=process1;
        t=new Thread(this,name);
        t.start();
    }
    @Override
    public void run(){
        try{
            if(process.equals("test")){
                ArrayList<String> temp=board.possibleMoves();
                while(!done1){
                    board.lockBoard();
                    while((!done)&&(!(temp.isEmpty()))){
                        Random rand=new Random();
                        int i=rand.nextInt(temp.size());
                       
                        board.move(temp.get(i));
                        done=(board.checkDeadGame()||board.FFRP()||board.FMRP());
                        
                        temp=board.possibleMoves();
                        board.updateMovePane();
                        board.repaint();
                        done1=board.getDone1();
                        if(done1){
                            board.unlockBoard();
                            break;
                        }
                        Thread.sleep(5);
                    }
                    if(done1){
                        board.unlockBoard();
                        break;
                    }
                    board.setGames(board.getGames()+1);
                    if(temp.isEmpty()){
                        if(board.getTurn()==1){
                            if(board.getBA().whiteKingDanger(board.getB())){
                                board.setBlackScore(board.getBlackScore()+1);
                                board.concatMovePaneText("BLACK WINS: CHECKMATE\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                                
                            }else{
                                board.concatMovePaneText("DRAW: STALEMATE\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                            }
                            
                        }else{
                            if(board.getBA().blackKingDanger(board.getB())){
                                board.setWhiteScore(board.getWhiteScore()+1);
                                board.concatMovePaneText("WHTIE WINS: CHECKMATE\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                                
                            }else{
                                board.concatMovePaneText("DRAW: STALEMATE\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                            }
                        }
                    }else if(board.FFRP()){
                        board.concatMovePaneText("DRAW: FIVEFOLD REPETITION\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                    }else if(board.FMRP()){
                        board.concatMovePaneText("DRAW: FIFTY MOVE RULE\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                    }else if(board.checkDeadGame()){
                        board.concatMovePaneText("DRAW: DEAD GAME (NO POSSIBLE MATES)\n SCORE: "+board.getWhiteScore()+", "+board.getBlackScore()+"\n");
                    }
                    board.concatMovePaneText("\n");
                    board.updateMovePane();
                    board.restart();
                    done1=board.getDone1();
                    done=board.getDone();
                    temp=board.possibleMoves();
                    board.updateMovePane();
                    Thread.sleep(1000);
                }
                
            }else if(process.equals("paint")){
                boolean done1=board.getDone1();
                while(!done1){
                    board.repaint();
                    done1=board.getDone1();
                    Thread.sleep(1);
                }
            }else if(process.equals("scroll")){
                boolean done1=board.getDone1();
                while(!done1){
                    done1=board.getDone1();
                    Thread.sleep(1);
                }
                
            }
            
        }catch(InterruptedException e){
            System.out.println(name+": interrupted :P");
        }
        System.out.println("DONE");
    }
}