package com.challenge.testes;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class ReflectionTest {

    private static final String METHOD_SOMAR = "somar";
    private static final String METHOD_SUBTRAIR = "subtrair";
    private static final String METHOD_TOTALIZAR = "totalizar";
    private static final String CALCULADOR_DE_CLASSES_PATH = "com.challenge.desafio.CalculadorDeClasses";
    private static final String CALCULAVEL_PATH = "com.challenge.interfaces.Calculavel";

    @Test
    public void whenAdd() {
        Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_SOMAR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(40)));
    }

    @Test
    public void whenAddWithouAnnotation() {
        AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_SOMAR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(0)));
    }

    @Test
    public void whenSubtract() {
        Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_SUBTRAIR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(15)));
    }

    @Test
    public void whenSubtractAllWithouAnnotation() {
        AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_SUBTRAIR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(0)));
    }

    private BigDecimal execute(String method, Object object) {
        try {
            Object obj = getCalculadorClassesClass().newInstance();
            return (BigDecimal) obj.getClass().getMethod(method, Object.class).invoke(obj, object);
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return null;
    }

    @Test
    public void whenResult() {
        Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_TOTALIZAR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(25)));
    }

    @Test
    public void whenResultWithoutAnnotation() {
        AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5));
        salario.setPlanoSaude(BigDecimal.valueOf(10));
        salario.setValeRefeicao(BigDecimal.valueOf(15));
        salario.setSalarioBruto(BigDecimal.valueOf(25));

        BigDecimal result = execute(METHOD_TOTALIZAR, salario);

        Assert.assertThat(result, Matchers.equalTo(new BigDecimal(0)));
    }

    @Test
    public void shouldImplementsCalculavel() {
        boolean isSameClass = Stream.of(getCalculadorClassesClass().getInterfaces())
                .anyMatch(i -> i.equals(getCalculavelClass()));

        Assert.assertThat(isSameClass, Matchers.is(true));
    }

    @Test
    public void shouldHaveAddMethod() {
        boolean temSoma = Stream.of(getCalculavelClass().getMethods()).anyMatch(m -> m.getName().equals(METHOD_SOMAR));

        Assert.assertThat(temSoma, Matchers.is(true));
    }

    private Class<?> getCalculavelClass() {
        try {
            return Class.forName(CALCULAVEL_PATH);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getCalculadorClassesClass() {
        try {
            return Class.forName(CALCULADOR_DE_CLASSES_PATH);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldHaveSubtractMethod() {
        boolean temSoma = Stream.of(getCalculavelClass().getMethods())
                .anyMatch(m -> m.getName().equals(METHOD_SUBTRAIR));

        Assert.assertThat(temSoma, Matchers.is(true));
    }

    @Test
    public void shouldHaveResultMethod() {
        boolean temSoma = Stream.of(getCalculavelClass().getMethods())
                .anyMatch(m -> m.getName().equals(METHOD_TOTALIZAR));

        Assert.assertThat(temSoma, Matchers.is(true));
    }

}
