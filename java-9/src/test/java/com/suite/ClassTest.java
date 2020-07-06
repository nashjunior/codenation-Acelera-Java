package com.suite;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public abstract class ClassTest {

    public static final String CREATED_AT = "created_at";
    public static final String UNDERSCORE = "_";
    public static final String EMPTY = "";
    public static final String EMPTY_CONSTRUCTOR_NOTFOUND_MESSAGE = "Não foi possível encontrar construtor vazio";
    public static final String DEFAULT_PACKAGE = "com.challenge.entity.";

    private Object object = null;

    protected abstract int manyToOnes();

    protected abstract int oneToManys();

    public String getClassName() {
        return newObject().getClass().getSimpleName();
    }

    public abstract String getTableName();

    @Test
    public void isEntity() {
        assertAnnotationClass(Entity.class);
    }

    @Test
    public void isEntityListener() {
        assertAnnotationClass(EntityListeners.class);
    }

    @Test
    public void checkManyToOneFieldsQuantity() {
        List<Field> manyToOneFields = findFieldsAnnotatedBy(ManyToOne.class);

        assertThat(manyToOneFields, hasSize(manyToOnes()));
    }

    @Test
    public void oneToManyFieldsFieldsAreCollection() {
        findFieldsAnnotatedBy(OneToMany.class).forEach(f -> {
            assertThat(Collection.class.isAssignableFrom(f.getType()), equalTo(true));
        });
    }

    @Test
    public void verifyTableName() {
        if (getClassName().toLowerCase().equals(getTableName()))
            return;

        if (isAnnotationPresent())
            assertThat(newObject().getClass().getAnnotation(Entity.class).name(), equalTo(getTableName()));
        else
            Assert.assertThat(true, Matchers.equalTo(false));
    }

    @Test
    public void checkOneToManyFieldsQuantity() {
        List<Field> oneToManyFields = findFieldsAnnotatedBy(OneToMany.class);

        assertThat(oneToManyFields, hasSize(oneToManys()));
    }

    @Test
    public void hasColumnCreatedAt() {
        Optional<Field> field = findByFieldNameOrColumnName(CREATED_AT);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnCreatedAtWithAnnotationCreatedDate() {
        Optional<Field> field = findByFieldNameOrColumnName(CREATED_AT);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(field.get().isAnnotationPresent(CreatedDate.class), equalTo(true));
    }

    protected Boolean fieldIsNotNull(Optional<Field> field) {
        return field.map(f -> f.isAnnotationPresent(NotNull.class)).orElse(false);
    }

    protected Boolean fieldHasSize(Optional<Field> field, int size) {
        return field.map(f -> f.getAnnotation(Size.class))
                .map(a -> a.max() == size).orElse(false);
    }

    protected Optional<Field> findByFieldNameOrColumnName(String columnName) {
        return Optional.ofNullable(findFieldByName(columnName.replace(UNDERSCORE, EMPTY))
                .orElseGet(() -> findFieldByColumnName(columnName)
                        .orElse(null)));
    }

    protected void verifyIdWithCorretStrategy() {
        Optional<GeneratedValue> generatedValue = findFieldAnnotatedBy(GeneratedValue.class)
                .map(f -> f.getAnnotation(GeneratedValue.class));

        assertThat(generatedValue.isPresent(), notNullValue());
        assertThat(generatedValue.map(GeneratedValue::strategy).orElse(null), equalTo(GenerationType.IDENTITY));
    }

    private boolean isAnnotationPresent() {
        return newObject().getClass().isAnnotationPresent(Entity.class);
    }

    protected Optional<Field> findFieldByName(String fieldName) {
        return Stream.of(newObject().getClass().getDeclaredFields())
                .filter(field -> field.getName().equalsIgnoreCase(fieldName)).findFirst();
    }

    protected <T extends Annotation> Optional<Field> findFieldAnnotatedBy(Class<T> annotation) {
        return Stream.of(newObject().getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation)).findFirst();
    }

    protected <T extends Annotation> List<Field> findFieldsAnnotatedBy(Class<T> annotation) {
        return Stream.of(newObject().getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation)).collect(Collectors.toList());
    }

    protected <T extends Annotation> List<Annotation> findAnnotationFieldsAnnotatedBy(Class<T> annotation) {
        return Stream.of(newObject().getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .map(field -> field.getAnnotation(annotation))
                .collect(Collectors.toList());
    }

    private <T extends Annotation> void assertAnnotationClass(Class<T> annotation) {
        assertThat(newObject().getClass().isAnnotationPresent(annotation), equalTo(true));
    }

    protected Optional<Field> findFieldByColumnName(String columnName) {
        return findFieldsAnnotatedBy(Column.class).stream()
                .filter(a -> a.getAnnotation(Column.class).name().equalsIgnoreCase(columnName))
                .findFirst();
    }

    protected Boolean isClassnameOrEntityWithName(Class clazz, String name) {
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        return clazz.getSimpleName().equalsIgnoreCase(name) || entity.name().equalsIgnoreCase(name);
    }

    public Object newObject() {
        if (object == null) {
            try {
                object = Stream.of(getClassByNameOrTableName(getTableName()).getDeclaredConstructors())
                        .filter(constructor -> constructor.getGenericParameterTypes().length == 0)
                        .findFirst().map(newInstance())
                        .orElseThrow(() ->
                                new InvocationTargetException(null, EMPTY_CONSTRUCTOR_NOTFOUND_MESSAGE));
                return object;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else
            return object;
    }

    protected <T extends Annotation> List<Field> findFieldsAnnotatedBy(Class clazz, Class<T> annotation) {
        return Stream.of(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation)).collect(Collectors.toList());
    }

    protected boolean hasManyToOneWithEntityName(String name) {
        return findFieldsAnnotatedBy(ManyToOne.class).stream()
                .anyMatch(f -> isClassnameOrEntityWithName(f.getType(), name));
    }

    protected boolean hasManyToOneWithEntityName(Class clazz, String name) {
        return findFieldsAnnotatedBy(clazz, ManyToOne.class).stream()
                .anyMatch(f -> isClassnameOrEntityWithName(f.getType(), name));
    }

    protected boolean hasFieldWithEntityName(Class clazz, String name) {
        return Stream.of(clazz.getDeclaredFields())
                .anyMatch(f -> isClassnameOrEntityWithName(f.getType(), name));
    }

    protected boolean hasOneToManyWithEntityName(String name) {
        return findFieldsAnnotatedBy(OneToMany.class).stream()
                .map(f -> getClassOfListField(f))
                .anyMatch(clazz -> {
                    boolean isClassName = clazz.getSimpleName().toLowerCase().contains(name);
                    boolean isEntityName = clazz.getAnnotation(Entity.class).name().equalsIgnoreCase(name);
                    return isClassName || isEntityName;
                });
    }

    private Function<Constructor<?>, ?> newInstance() {
        return c -> {
            try {
                return c.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                new RuntimeException(e);
            }
            return null;
        };
    }

    protected Class<?> getClassOfListField(Field f) {
        return (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
    }

    protected Class<?> getClassByNameOrTableName(String className) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE);
        Set<Class<? extends Object>> entities = reflections.getTypesAnnotatedWith(Entity.class);
        try {
            return entities.stream()
                    .filter(c -> c.getSimpleName().equalsIgnoreCase(className) || c.getAnnotation(Entity.class).name().equalsIgnoreCase(className))
                    .findFirst().orElseThrow(() -> new ClassNotFoundException());
        } catch (ClassNotFoundException e) {
            new RuntimeException(e);
        }
        return null;
    }

}
