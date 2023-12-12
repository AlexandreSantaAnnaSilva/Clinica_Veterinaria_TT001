package br.unicamp.a248488_atvProjeto;

import java.text.ParseException;
import java.util.Calendar;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.ConsultaDAO;
import model.EspecieDAO;
import model.ExameDAO;
import model.TratamentoDAO;
import model.VeterinarioDAO;

/**
 *
 * @author 248488
 */
public class Main {
    public static void main(String[] args) throws ParseException {
//          Registrando clientes no banco de dados
//          ClienteDAO.getInstance().create("Pedro","Rua Unicamp TT 001","19997164571","9201039","pedro@unicamp.br");;
//          ClienteDAO.getInstance().create("Henrique","Rua Unicamp TT 001","17993772019","9281730","henrique@unicamp.br");
//          ClienteDAO.getInstance().create("Vinicius","Rua YYYXZ TT 001","14991839102","2819313","vinicius@unicamp.br");


//            Registrando animais no banco de dados
//          Nome, Idade, Sexo {0,1}, Especie {1,n}, id_cliente
//          AnimalDAO.getInstance().create("Ted", 6, "Macho", 1, 1);
//          AnimalDAO.getInstance().create("Moah", 8, "Macho", 1, 1);
//          AnimalDAO.getInstance().create("Lina", 3, "Fêmea", 1, 1);
//          AnimalDAO.getInstance().create("Falkir", 2, "Macho", 1, 2);
//          AnimalDAO.getInstance().create("Susan", 5, "Fêmea", 1, 2);
//          AnimalDAO.getInstance().create("Flock", 5, "Macho", 1, 3);


//            Registrando veterinarios no banco de dados
//          VeterinarioDAO.getInstance().create("Luiz Victor Vet", "Rua dos Pouco Bobos", "luiz@veterinario.com.br", "19992301392");
//          VeterinarioDAO.getInstance().create("Nohlan Vet", "Rua dos Talvez Bobos", "nohlan@veterinario.com.br", "19992512909");


//            Registrando especies no banco de dados
//          EspecieDAO.getInstance().create("Cachorro");
//          EspecieDAO.getInstance().create("Gato");

           
//            Registrando exames no banco de dados
//          ExameDAO.getInstance().create("Exame de Sangue", 1);
//          ExameDAO.getInstance().create("Exame de Urina", 1);
//          ExameDAO.getInstance().create("Exame de Sangue", 2);
//           
           
//            Registrando tratamentos no banco de dados
//          TratamentoDAO.getInstance().create(1, "Tratamento 01", Calendar.getInstance().getTime() , Calendar.getInstance().getTime(), 0);
//          TratamentoDAO.getInstance().create(2, "Tratamento 02", Calendar.getInstance().getTime() , Calendar.getInstance().getTime(), 1);

            
//            Registrando consultas no banco de dados
//          ConsultaDAO.getInstance().create(Calendar.getInstance().getTime(), 1, "Animal tranquilo, sem problemas", 1, 1, 1, true);
//          ConsultaDAO.getInstance().create(Calendar.getInstance().getTime(), 2, "Animal problematico", 3, 2, 2, false);
//          Calendar data, int hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou

//            Criando instancias para ClienteDAO e AnimalDAO
//          ClienteDAO clienteDAO = ClienteDAO.getInstance();
//          AnimalDAO animalDAO = AnimalDAO.getInstance();


//            Objetos para clientes recuperados no banco de dados
//          Cliente clienteUm = clienteDAO.retrieveById(1);
//          Cliente clienteDois = clienteDAO.retrieveById(2);
//          Cliente clienteTres = clienteDAO.retrieveById(3);


//           Adicionando os animais relacionados para cada cliente em memória
//          clienteUm.addAnimais(animalDAO.retrieveByClientId(clienteUm.getId()));
//          clienteDois.addAnimais(animalDAO.retrieveByClientId(clienteDois.getId()));
//          clienteTres.addAnimais(animalDAO.retrieveByClientId(clienteTres.getId()));


//           Exibindo os clientes da memoria
//       System.out.println(clienteUm);
//       System.out.println(clienteDois);
//       System.out.println(clienteTres);


//          Exibindo  todos os clientes, animais, veterinarios, especies, tratamentos e consultas registrados
       System.out.println(ClienteDAO.getInstance().retrieveAll());
       System.out.println(AnimalDAO.getInstance().retrieveAll());
       System.out.println(VeterinarioDAO.getInstance().retrieveAll());
       System.out.println(EspecieDAO.getInstance().retrieveAll());
       System.out.println(ExameDAO.getInstance().retrieveAll());
       System.out.println(TratamentoDAO.getInstance().retrieveAll());
       System.out.println(ConsultaDAO.getInstance().retrieveAll());
    }
}
