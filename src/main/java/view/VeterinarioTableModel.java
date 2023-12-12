/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import model.Veterinario;
import model.VeterinarioDAO;

/**
 *
 * @author Alexandre Silva | RA: 248488 | TT001
 */
public class VeterinarioTableModel extends GenericTableModel {
    
    public VeterinarioTableModel(List vDados) {
        super(vDados, new String[]{"Nome", "Email", "Telefone"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario veterinario  = (Veterinario) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return veterinario.getNome();
            case 1:
                return veterinario.getEmail();
            case 2:
                return veterinario.getTelefone();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
                    
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                veterinario.setNome((String) aValue);
                break;
            case 1:
                veterinario.setEmail((String) aValue);
                break;
            case 2:
                veterinario.setTelefone((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        VeterinarioDAO.getInstance().update(veterinario);
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
}
