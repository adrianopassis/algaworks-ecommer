package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOpercaoComBancoDeDados(){
        Produto produto = entityManager.find(Produto.class,1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle", produtoVerificacao.getNome());
    }


    @Test
    public void mostrartDiferencaPersisteMerge() {//Como é uym registro novo, todos os campos são preenchidos.
        Produto produtoPersist = new Produto();

//        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone two plus");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assertions.assertNotNull(produtoVerificacaoPersist);

        Produto produtoMerge = new Produto();

       // produtoMerge.setId(5);
        produtoMerge.setNome("Notebook Dell ");
        produtoMerge.setDescricao("O melhor da categoria");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assertions.assertNotNull(produtoVerificacaoMerge);
    }

    @Test
    public void inseriObjetoComMerge() {//Como é uym registro novo, todos os campos são preenchidos.
        Produto produto = new Produto();

        //produto.setId(4);
        produto.setNome("Microfone Rode Videomic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        Produto produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
        Assertions.assertNotNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjetoGerenciado(){//Neste metodo eu só altero o atributo do objeto que eu realmente quero alterar.
        Produto produto = entityManager.find(Produto.class,1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());
    }


    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();

       // produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        Produto produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
        Assertions.assertNotNull(produtoVerificacao);
        Assertions.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }


    @Test
    public void removerObjeto(){
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        //entityManager.clear(); Não é necessário na asserção para operação de remoção

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerificacao);

    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();

      //  produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao);
    }

    @Test
    public void abrirEFecharATransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erros.

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}