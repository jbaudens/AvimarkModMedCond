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
public class ClinicalComplaintsJPanel  extends JPanel implements ActionListener, ClipboardOwner{
   private ClinicalComplaints clinicalComplaints;
   private JButton newPatientJButton;
   private List<ClinicalComplaintUI> listOfClinicalComplaintUIs;   

   private JButton toClipboardJButton;    
   public ClinicalComplaintsJPanel(ClinicalComplaints clinicalComplaints) {
        this.clinicalComplaints = clinicalComplaints;
        double numberOfNeededRows = 0.0;
        for(ClinicalComplaint cc : clinicalComplaints.getListOfClinicalComplaints()){
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
        listOfClinicalComplaintUIs = new ArrayList<>();
        int row = 1;
        for (ClinicalComplaint cc : this.clinicalComplaints.getListOfClinicalComplaints()){
            ClinicalComplaintUI clinicalComplaint = new ClinicalComplaintUI(cc);
            listOfClinicalComplaintUIs.add(clinicalComplaint);
            c.gridy = row;
            c.gridx = 0;
            c.insets = new Insets(0, 10, 0, 0);
            c.weightx = 0.0;
            add(clinicalComplaint.getClinicalComplaintNameJLabel(),c);
            row++;
            c.gridx = 1;
            c.weightx = 0.0;
            add(clinicalComplaint.getAskQuestionsJButton(),c);
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
       StringBuilder result = new StringBuilder();
       /*HashSet<String> setOfCategories = new HashSet<>();
       for (ClinicalComplaintUI ccui : listOfClinicalComplaintUIs){
           for (Question q : ccui.getClinicalComplaint().getListOfQuestions()){
               setOfCategories.add(q.getCategory());
           }
       }        
               
       for (String s : setOfCategories){
            result.append(s+":\n");
            for (ClinicalComplaintUI ccui : listOfClinicalComplaintUIs){
                result.append(ccui.toString(s));
            }
       }*/
       
       for (ClinicalComplaintUI ccui : listOfClinicalComplaintUIs){
           result.append(ccui.toString());
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
        for (ClinicalComplaintUI ccui : listOfClinicalComplaintUIs){
            ccui.reset();
        }
    }
}
