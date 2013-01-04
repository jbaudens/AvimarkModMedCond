package avimarkmodmedcond;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/** adapted from comment section of ListCellRenderer api */
class CheckComboRenderer implements ListCellRenderer
{
    JCheckBox checkBox;
 
    public CheckComboRenderer()
    {
        checkBox = new JCheckBox();
    }
    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        
        CheckComboStore store = (CheckComboStore)value;
        checkBox.setText(store.id);
        checkBox.setSelected(store.state);
        return checkBox;
    }
    
}
 
class CheckComboStore
{
    String id;
    Boolean state;
 
    public CheckComboStore(String id, Boolean state)
    {
        this.id = id;
        this.state = state;
    }
}
