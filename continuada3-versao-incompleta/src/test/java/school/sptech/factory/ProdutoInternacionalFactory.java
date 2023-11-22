package school.sptech.factory;

import school.sptech.Produto;
import school.sptech.ProdutoInternacional;
import school.sptech.utils.ProdutoInternacionalUtils;
import school.sptech.utils.ProdutoUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class ProdutoInternacionalFactory {

    public static Object getInstance(
            Object codigoBarra,
            Object nome,
            Object descricao,
            Object categoria,
            Object preco,
            Object quantidade,
            Object paisOrigem
    ) throws ReflectiveOperationException {

        Class<ProdutoInternacional> produtoInternacionalClass = ProdutoInternacional.class;
        Class<Produto> produtoClass = Produto.class;

        Constructor<?> constructor = produtoInternacionalClass.getConstructors()[0];

        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[constructor.getParameterCount()];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType() == double.class) {
                args[i] = 0.0;
            }

            if (parameters[i].getType() == int.class) {
                args[i] = 0;
            }
        }

        Object produto = constructor.newInstance(args);

        Field campoCodigoBarra = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_CODIGO_BARRA);
        Field campoNome = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_NOME);
        Field campoDescricao = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_DESCRICAO);
        Field campoCategoria = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_CATEGORIA);
        Field campoPreco = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_PRECO);
        Field campoQuantidade = produtoClass.getDeclaredField(ProdutoUtils.ATRIBUTO_QUANTIDADE);
        Field campoPaisOrigem = produtoInternacionalClass.getDeclaredField(ProdutoInternacionalUtils.ATRIBUTO_PAIS_ORIGEM);
        Field campoTaxaImportacao = produtoInternacionalClass.getDeclaredField(ProdutoInternacionalUtils.ATRIBUTO_TAXA_IMPORTACAO);

        campoCodigoBarra.setAccessible(true);
        campoNome.setAccessible(true);
        campoDescricao.setAccessible(true);
        campoCategoria.setAccessible(true);
        campoPreco.setAccessible(true);
        campoQuantidade.setAccessible(true);
        campoPaisOrigem.setAccessible(true);
        campoTaxaImportacao.setAccessible(true);

        campoCodigoBarra.set(produto, codigoBarra);
        campoNome.set(produto, nome);
        campoDescricao.set(produto, descricao);
        campoCategoria.set(produto, categoria);
        campoPreco.set(produto, preco);
        campoQuantidade.set(produto, quantidade);
        campoPaisOrigem.set(produto, paisOrigem);

        return produto;

    }
}
