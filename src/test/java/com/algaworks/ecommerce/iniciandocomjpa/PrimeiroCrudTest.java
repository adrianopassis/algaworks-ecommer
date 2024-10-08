package com.algaworks.ecommerce.iniciandocomjpa;


import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro(){
        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome("Gertrudes da Silva");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
    }

    @Test
    public void buscarResgistro(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals("Pati Maionese", cliente.getNome());
    }

    @Test
    public void atualizarRegistro(){
        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setNome("Jubiraquara Adabiroska");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
        Assertions.assertEquals("Jubiraquara Adabiroska", clienteVerificacao.getNome());
    }

    @Test
    public void removerResgistro(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
        Assertions.assertNotNull(clienteVerificacao);
    }

}
