package testes;

import model.beans.Cliente;
import model.dao.ClienteDAO;

import java.util.List;

public class TesteInserir {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNome("Teste lala lll");
        
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente.setCodigo(1);
        clienteDAO.alterar(cliente);
       
        System.out.println("Sucesso!");
        
        

      
    }
}
