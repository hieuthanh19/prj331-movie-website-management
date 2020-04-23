/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.media.TrackType;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author ThanhKH
 */
public class MediaPlayerFrame extends javax.swing.JFrame {

    private boolean stopped = false;
    private boolean paused = false;
    private static EmbeddedMediaPlayerComponent emMediaPlayer;
    MainMenu main = null;
    String mrl;

    //icon
    ImageIcon play = new ImageIcon("src/img/play.png");
    ImageIcon pause = new ImageIcon("src/img/pause.png");
    Image appLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/group10_logo.png"));

    /**
     * Create new form MdeiaPlayer
     *
     * @param mainMenu MainMenu object
     * @param mrl link to video
     */
    public MediaPlayerFrame(MainMenu mainMenu, String mrl) {
        this.main = mainMenu;
        this.mrl = mrl;
        this.setIconImage(appLogo);
        initComponents();
        setUpComponents();
        setUpNavigationButt();
         emMediaPlayer.mediaPlayer().media().play(mrl);
        //prepareAndPlayMRL();

        //emMediaPlayer.mediaPlayer().controls().setPause(true);      
    }

    public void setUpComponents() {
        emMediaPlayer = new EmbeddedMediaPlayerComponent();
        emMediaPlayer.videoSurfaceComponent().setPreferredSize(new Dimension(1050, 450));
        emMediaPlayer.mediaPlayer().controls().setRepeat(true);
        pnlMediaPlayer.setLayout(new BorderLayout());
        pnlMediaPlayer.add(emMediaPlayer, BorderLayout.CENTER);
    }

    public void prepareAndPlayMRL() {
        //prepare return true if success
        boolean check = emMediaPlayer.mediaPlayer().media().prepare(mrl);
        // System.out.println(check);
        // System.out.println(emMediaPlayer.mediaPlayer().status().isPlayable());
        if (emMediaPlayer.mediaPlayer().status().isPlayable()) {
            //output error messages
            JOptionPane.showMessageDialog(this, "Error: Can't play video with given MRL", "Play Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showConfirmDialog(this, "You will be transnfered back to Main Menu", "Return to Main Menu", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
            //stop all current media
            emMediaPlayer.mediaPlayer().controls().stop();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    emMediaPlayer.release();
                }
            });
            /*//display main menu
            main.setEnabled(true);
            this.dispose(); */
        } else {
            emMediaPlayer.mediaPlayer().media().play(mrl);

            System.out.println("check1:" + emMediaPlayer.mediaPlayer().status().videoOutputs());
            System.out.println("duration:" + emMediaPlayer.mediaPlayer().media().info().duration());
            // System.out.println(emMediaPlayer.mediaPlayer().media().play(mrl));
            // emMediaPlayer.mediaPlayer().media().s

        }

    }

    /**
     * Set up navigation buttons
     */
    public void setUpNavigationButt() {

        emMediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                // System.out.println("Playing\n");
                lblStartPause.setIcon(pause);
                stopped = false;
                // System.out.println("Finish: " + finished);
                // System.out.println("Paused:" + paused);
                System.out.println("Play: " + emMediaPlayer.mediaPlayer().status().isPlayable());
                System.out.println("duration:" + emMediaPlayer.mediaPlayer().media().info().duration());
            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {
                //  System.out.println("Stop\n");
                lblStartPause.setIcon(play);
                stopped = true;
                System.out.println("Stop: " + emMediaPlayer.mediaPlayer().status().isPlayable());
                  System.out.println("duration:" +emMediaPlayer.mediaPlayer().media().info().duration());

            }

            @Override
            public void paused(MediaPlayer mediaPlayer) {
                //  System.out.println("Paused\n");
                paused = true;
                lblStartPause.setIcon(play);
                stopped = false;
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                System.out.println("Error");
            }

        });
        lblStartPause.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                //   System.out.println("Click");
                //System.out.println("Finish: " + finished);
                //  System.out.println("Paused:" + paused);
                if (stopped) {
                    emMediaPlayer.mediaPlayer().media().play(mrl);
                } else {
                    emMediaPlayer.mediaPlayer().controls().pause();
                }

            }
        }
        );

        lblRewind.addMouseListener(
                new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent me
            ) {
                emMediaPlayer.mediaPlayer().controls().skipTime(-10000);
            }

        }
        );

        lblForward.addMouseListener(
                new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent me
            ) {
                emMediaPlayer.mediaPlayer().controls().skipTime(10000);
            }

        }
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNavigation = new javax.swing.JPanel();
        pnlMediaPlayer = new javax.swing.JPanel();
        lblRewind = new javax.swing.JLabel();
        lblStartPause = new javax.swing.JLabel();
        lblForward = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Media Player");
        setBackground(new java.awt.Color(34, 34, 34));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1100, 550));
        getContentPane().setLayout(null);

        pnlNavigation.setBackground(new java.awt.Color(20, 20, 20));
        pnlNavigation.setPreferredSize(new java.awt.Dimension(1100, 600));

        pnlMediaPlayer.setBackground(new java.awt.Color(255, 153, 0));
        pnlMediaPlayer.setPreferredSize(new java.awt.Dimension(1000, 700));

        javax.swing.GroupLayout pnlMediaPlayerLayout = new javax.swing.GroupLayout(pnlMediaPlayer);
        pnlMediaPlayer.setLayout(pnlMediaPlayerLayout);
        pnlMediaPlayerLayout.setHorizontalGroup(
            pnlMediaPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMediaPlayerLayout.setVerticalGroup(
            pnlMediaPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        lblRewind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10sRewind.png"))); // NOI18N

        lblStartPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pause.png"))); // NOI18N

        lblForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10sForward.png"))); // NOI18N

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/backArrowActive.png"))); // NOI18N
        lblBack.setToolTipText("Back");
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlNavigationLayout = new javax.swing.GroupLayout(pnlNavigation);
        pnlNavigation.setLayout(pnlNavigationLayout);
        pnlNavigationLayout.setHorizontalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavigationLayout.createSequentialGroup()
                .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNavigationLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblBack)
                        .addGap(259, 259, 259)
                        .addComponent(lblRewind)
                        .addGap(142, 142, 142)
                        .addComponent(lblStartPause, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(lblForward)
                        .addGap(0, 397, Short.MAX_VALUE))
                    .addGroup(pnlNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMediaPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNavigationLayout.setVerticalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRewind)
                    .addGroup(pnlNavigationLayout.createSequentialGroup()
                        .addComponent(pnlMediaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlNavigationLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(lblBack))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNavigationLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStartPause, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblForward, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(pnlNavigation);
        pnlNavigation.setBounds(0, -7, 1100, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        emMediaPlayer.mediaPlayer().controls().stop();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                emMediaPlayer.release();
            }
        });
        main.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_lblBackMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblForward;
    private javax.swing.JLabel lblRewind;
    private javax.swing.JLabel lblStartPause;
    private javax.swing.JPanel pnlMediaPlayer;
    private javax.swing.JPanel pnlNavigation;
    // End of variables declaration//GEN-END:variables
}
