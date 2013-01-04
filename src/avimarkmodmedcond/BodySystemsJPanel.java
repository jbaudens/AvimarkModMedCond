/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jbaudens
 */
public class BodySystemsJPanel extends JPanel implements ActionListener, ClipboardOwner{
    
   private JButton newPatientJButton;
   private BodySystems bodySystems;
   private List<BodySystemUI> listOfBodySystemUIs;

   private JButton toClipboardJButton;    

    public BodySystemsJPanel(BodySystems bodySystems) {
        this.bodySystems = bodySystems;
        double numberOfNeededRows = 0.0;
        for(BodySystem bs : this.bodySystems.getListOfBodySystems()){
            if(bs.getIsSymmetrical()){
                numberOfNeededRows++;
            }
            numberOfNeededRows++;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = screenSize.width*0.05;
        double y = screenSize.height*0.05;
        double width = screenSize.width*0.9;
        double height = screenSize.height*0.9;
        double numberOfRows = 2.0 + numberOfNeededRows;
        double maxNumberOfRows = 42.0;
        height = numberOfRows/maxNumberOfRows*height;
        setBounds((int)Math.round(x),(int)Math.round(y),(int)Math.round(width),(int)Math.round(height));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 0.0;
        c.gridx = 0; c.gridy = 0; 
        newPatientJButton = new JButton("New Patient");
        newPatientJButton.addActionListener(this);
        add(newPatientJButton,c);
        
        listOfBodySystemUIs = new ArrayList<>();
        int row = 1;
        for (BodySystem bs : this.bodySystems.getListOfBodySystems()){
            BodySystemUI bodySystem = new BodySystemUI(bs);
            listOfBodySystemUIs.add(bodySystem);
            c.gridy = row;
            c.gridx = 0;
            c.insets = new Insets(0, 10, 0, 0);
            c.weightx = 0.0;
            add(bodySystem.getBodySystemNameJLabel(),c);
            c.gridx = 1;
            c.weightx = 0.0;
            add(bodySystem.getNormalRadioButton(),c);
            c.gridx = 2;
            c.weightx = 0.0;
            add(bodySystem.getAbnormalRadioButton(),c);
            c.gridx = 3;
            c.weightx = 0.0;
            add(bodySystem.getNotExaminedRadioButton(),c);
            c.gridx = 4;
            c.weightx = 0.0;
            add(bodySystem.getSymmetryJComboBox(),c);
            c.gridx = 5;
            c.weightx = 0.0;
            add(bodySystem.getPredefinedCommentsJButton(),c);
            c.gridx = 6;
            c.insets = new Insets(0, 0, 0, 10);
            c.weightx = 1.0;
            add(bodySystem.getNotesJTextField(),c);
            
            row++;
            
            if (bs.getIsSymmetrical()){
                c.gridy = row;
                c.gridx = 0;
                c.insets = new Insets(0, 10, 0, 0);
                c.weightx = 0.0;
                add(bodySystem.getBodySystemNameJLabel2(),c);
                c.gridx = 1;
                c.weightx = 0.0;
                add(bodySystem.getNormalRadioButton2(),c);
                c.gridx = 2;
                c.weightx = 0.0;
                add(bodySystem.getAbnormalRadioButton2(),c);
                c.gridx = 3;
                c.weightx = 0.0;
                add(bodySystem.getNotExaminedRadioButton2(),c);
                c.gridx = 4;
                c.weightx = 0.0;
                add(bodySystem.getSymmetryJLabel2(),c);
                c.gridx = 5;
                c.weightx = 0.0;
                add(bodySystem.getPredefinedCommentsJButton2(),c);
                c.gridx = 6;
                c.insets = new Insets(0, 0, 0, 10);
                c.weightx = 1.0;
                add(bodySystem.getNotesJTextField2(),c);
                row++;
            }
        }
        toClipboardJButton = new JButton("To Clipboard");
        toClipboardJButton.addActionListener(this);

        c.gridx = 0;
        c.gridy++;
        c.weightx = 0.0;
        
        add(toClipboardJButton,c);
    }
    
    
   
   @Override
   public void actionPerformed(ActionEvent e) {
       if (e.getSource() == this.toClipboardJButton){ 
           this.textToClipboard();
       }
       else if (e.getSource() == this.newPatientJButton){
           this.reset();
       }
   }
   
    /**
     *
     */
    public void textToClipboard(){
       
       /*ArrayList<String> listOfNormalBodySystems= new ArrayList();
       ArrayList<String> listOfAbnormalBodySystems= new ArrayList();
       ArrayList<String> listOfNotExaminedBodySystems= new ArrayList();

       for (BodySystemUI bsp : listOfBodySystemUIs){
           if(bsp.isNormal()){
            listOfNormalBodySystems.add(bsp.toString(1));
           }
           else if(bsp.isAbnormal()){
            listOfAbnormalBodySystems.add(bsp.toString(1));              
           }
           else if(bsp.isNotExamined()){
            listOfNotExaminedBodySystems.add(bsp.toString(1));              
           }
           if(bsp.isSymmetryActive()){
                if(bsp.isNormal2()){
                 listOfNormalBodySystems.add(bsp.toString(2));
                }
                else if(bsp.isAbnormal2()){
                 listOfAbnormalBodySystems.add(bsp.toString(2));              
                }
                else if(bsp.isNotExamined2()){
                 listOfNotExaminedBodySystems.add(bsp.toString(2));              
                }               
           }
       }

       StringBuilder resultNormal = new StringBuilder();
       StringBuilder resultAbnormal = new StringBuilder();
       StringBuilder resultNotExamined = new StringBuilder();
       
       Joiner joiner;
       if (!listOfAbnormalBodySystems.isEmpty()){
        resultAbnormal.append("Abnormal:\n");
        joiner = Joiner.on("\n").skipNulls();
        resultAbnormal.append(joiner.join(listOfAbnormalBodySystems));
       }
       if (!listOfNormalBodySystems.isEmpty()){
        joiner = Joiner.on(", ").skipNulls();
        resultNormal.append("\nNormal - ");
        resultNormal.append(joiner.join(listOfNormalBodySystems));
       }
       if (!listOfNotExaminedBodySystems.isEmpty()){
        joiner = Joiner.on(", ").skipNulls();
        resultNotExamined.append("\nNot Examined - ");
        resultNotExamined.append(joiner.join(listOfNotExaminedBodySystems));
       }
       
       setClipboardContents( resultAbnormal.toString() + resultNormal.toString() + resultNotExamined.toString());*/
       StringBuilder result = new StringBuilder();
       for (BodySystemUI bsp : listOfBodySystemUIs){
           result.append(bsp);
       } 
       setClipboardContents(result.toString());
   }
   
   /**
    * Place a String on the clipboard, and make this class the
    * owner of the Clipboard's contents.
    * @param aString 
    */
    public void setClipboardContents( String aString ){
        StringSelection stringSelection = new StringSelection( aString );
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents( stringSelection, this );
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void reset() {
        for (BodySystemUI bsui : listOfBodySystemUIs){
            bsui.reset();
        }
    }    
}
