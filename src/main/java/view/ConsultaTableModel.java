/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.VeterinarioDAO;

/**
 *
 * @author Alexandre Silva | RA: 248488 | TT001
 */
public class ConsultaTableModel extends GenericTableModel {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ConsultaTableModel(List vDados) {
        super(vDados, new String[]{"Data", "Hora", "Cliente", "Animal", "Veterinário", "Obs", "Fim"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        Animal animal;
        
        switch (columnIndex) {
            case 0:
                return dateFormat.format(consulta.getData().getTime());
            case 1:
                return consulta.getHora();
            case 2:
                animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                if (animal != null) {
                    return ClienteDAO.getInstance().retrieveById(animal.getIdCliente()).getNome();
                }
            case 3:
                animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                if (animal != null) {
                    return animal.getNome();
                }
            case 4:
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVet()).getNome();
            case 5:
                return consulta.getComentarios();
            case 6:
                return consulta.isTerminou();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(dateFormat.parse((String)aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(ConsultaTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                consulta.setData(cal);
                break;
            case 1:
                consulta.setHora((Integer)aValue);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                consulta.setComentarios((String)aValue);
                break;
            case 6:
                consulta.setTerminou((Boolean)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ConsultaDAO.getInstance().update(consulta);
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if ((columnIndex<2) || (columnIndex>4)) {
            return true;
        } else {
            return false;
        }
    }
}
