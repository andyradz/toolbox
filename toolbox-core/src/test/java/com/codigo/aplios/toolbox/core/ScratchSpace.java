package com.codigo.aplios.toolbox.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ScratchSpace {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException ex) {
            }

            AnimatedPanel panel = new AnimatedPanel();

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            panel.animate();
        });
    }

    public static class AnimatedPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 6319536148805310404L;

        private float progress = 0.0f; // a number between 0.0 and 1.0

        public AnimatedPanel() {

            this.setPreferredSize(new Dimension(800, 100));
        }

        public void animate() {

            final int animationTime = 1000;
            int framesPerSecond = 60;
            int delay = 1000 / framesPerSecond;
            final long start = System.currentTimeMillis();
            final Timer timer = new Timer(delay, null);
            timer.addActionListener(e -> {

                final long now = System.currentTimeMillis();
                final long elapsed = now - start;

                int width = AnimatedPanel.this.getWidth();
                int height = AnimatedPanel.this.getHeight();
                int oldWidth = (int)(width * AnimatedPanel.this.progress);

                AnimatedPanel.this.progress = (float)elapsed / animationTime;
                int newWidth = (int)(width * AnimatedPanel.this.progress);

                AnimatedPanel.this.repaint(new Rectangle(oldWidth, 0, newWidth - oldWidth, height));
                if (elapsed >= animationTime)
                    timer.stop();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2d = (Graphics2D)g;
            int width = this.getWidth();
            int progressWidth = (int)(width * this.progress);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(0, 0, progressWidth, this.getHeight());
            g2d.setColor(Color.RED);
            g2d.fillRect(progressWidth, 0, width - progressWidth, this.getHeight());
        }

    }
}
