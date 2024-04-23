/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
//mport java.lang.Object.*;

/**
 *
 * @author putra_000
 */
public class GameTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameTest gtm=new GameTest();
        gtm.start();
    }
    
    JFrame mainwindow;
    BufferStrategy strategy;
    boolean spkey=false;
    double cy=200;
    
    BufferedImage bimage;
    
    
    GameTest(){
        this.mainwindow=new JFrame("GameTest");
        this.mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainwindow.setSize(800,720);
        this.mainwindow.setLocationRelativeTo(null);
        this.mainwindow.setVisible(true);
        this.mainwindow.setResizable(true);
        
        //buffer strategy
        this.mainwindow.setIgnoreRepaint(true);
        this.mainwindow.createBufferStrategy(2);
        this.strategy=this.mainwindow.getBufferStrategy();
        
        //key adapter
        this.mainwindow.addKeyListener(new MyKeyAdapter());
        try {     
            this.bimage=ImageIO.read(new File("spaceship.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void start(){
        Timer t=new Timer();
        t.schedule(new RenderTask(),0,10);
    }
    
    void render(){
        Graphics2D g=(Graphics2D)this.strategy.getDrawGraphics();
        g.setBackground(Color.BLACK);
        g.clearRect(0,0,this.mainwindow.getWidth(),this.mainwindow.getHeight());
        
        if(spkey==true){
            cy -= 1;
        }else{
            cy += 1;
        }
        
        g.drawImage(this.bimage, (int)126, (int)this.cy+24,null);
        
        g.setColor(Color.YELLOW);
        g.draw(new Ellipse2D.Double(100,this.cy,100,100));
        
        g.setColor(Color.RED);
        g.draw(new Rectangle2D.Double(400,400,400,400));
        g.dispose();
        this.strategy.show();
        
    }
    
    class RenderTask extends TimerTask{
        int count=0;
        
        @Override
        public void run(){
            //count++;
            //system.out.print(count+ "\n");
            GameTest.this.render();
        }
    }
    
    class MyKeyAdapter extends KeyAdapter{

       @Override
       public void keyPressed(KeyEvent e){
          if(e.getKeyCode()==KeyEvent.VK_SPACE){
              GameTest.this.spkey=true;
          }
       }
       
       @Override
       public void keyReleased(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
              GameTest.this.spkey=false;
          }
       }
        
    }
}
