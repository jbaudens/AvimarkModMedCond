/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avimarkmodmedcond;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jbaudens
 */
public class QuestionJPanel extends JPanel{
    Question question;
    
    
    
    public QuestionJPanel(Question q){
        question = q;
        
        this.setLayout(new GridLayout(1,2));
        
        JLabel questionJLabel = new JLabel(q.getQuestion());
        
        JTextField answerJTextField = new JTextField(40);
        
        this.add(questionJLabel);
        this.add(answerJTextField);
    }    
    
}
