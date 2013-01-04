/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import com.tulskiy.keymaster.common.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author jbaudens
 */
public class AvimarkModMedCond{

    private static File f;
    private static FileChannel channel;
    private static FileLock lock;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //try{
            /*String userHome = System.getProperty("user.home");
            
            f = new File(userHome+"/AviMod.lock");
            // Check if the lock exist
            if (f.exists()) {
                // if exist try to delete it
                f.delete();
            }
            // Try to get the lock
            channel = new RandomAccessFile(f, "rw").getChannel();
            lock = channel.tryLock();
            System.out.println("Lock: " + lock);
            if(lock == null)
            {
                // File is lock by other application
                channel.close();
                JOptionPane.showMessageDialog(null, "There alreasy is a instance running for this user");
                System.exit(3);
            }
            // Add shutdown hook to release lock when application shutdown
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);*/
            
            Provider provider = Provider.getCurrentProvider(true);

            if (1 == args.length){
                File f = new File(args[0]);


                if(!f.exists()) {
                    JOptionPane.showMessageDialog(null, "The file provided as an argument does not exist");
                    System.exit(1);
                }
                if(!f.canRead()){
                    JOptionPane.showMessageDialog(null, "The file provided as an argument cannot be read");
                    System.exit(2);
                }
                MyHotKeyListener mhkl= new MyHotKeyListener(args[0]);


                provider.register(KeyStroke.getKeyStroke(mhkl.getToggleVisibility()), mhkl);

                try {
                    while(true){
                        Thread.sleep(1000000);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(AvimarkModMedCond.class.getName()).log(Level.SEVERE, null, ex);
                }

                provider.reset();
                provider.stop();
            }
            else{
                JOptionPane.showMessageDialog(null, "A valid yamlfile must be provided as an argument to the program");
            }
        //}
        /*catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "There was a problem with the logfile. Contact the administrator.");
            
        }*/
    }

    public static void unlockFile() {
        // release and delete file lock
        try {
            if(lock != null) {
                lock.release();
                channel.close();
                f.delete();
            }
        } catch(IOException e) {
            Logger.getLogger(AvimarkModMedCond.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    static class ShutdownHook extends Thread {

        public void run() {
            unlockFile();
        }
    }
    
    
}
