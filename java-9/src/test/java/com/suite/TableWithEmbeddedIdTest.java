package com.suite;

import org.junit.Test;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class TableWithEmbeddedIdTest extends ClassTest {

    public abstract List<String> getPrimaryKeys();

    @Test
    public void hasEmbeddedId() {
        Optional<Field> field = findFieldAnnotatedBy(EmbeddedId.class);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void checkEmbeddedIdIsEmbeddable() {
        Boolean result = findFieldAnnotatedBy(EmbeddedId.class)
                .map(f -> f.getType().isAnnotationPresent(Embeddable.class))
                .orElse(false);

        assertThat(result, equalTo(true));
    }

    @Test
    public void checkPrimaryKeys() {
        Field field = findFieldAnnotatedBy(EmbeddedId.class)
                .orElseThrow(() -> new RuntimeException("Not embeddedId"));

        getPrimaryKeys().forEach(key -> {
            assertThat(key, hasFieldWithEntityName(field.getType(), key), equalTo(true));
        });
    }

    @Test
    public void checkPrimaryKeysWithManyToOne() {
        Field field = findFieldAnnotatedBy(EmbeddedId.class)
                .orElseThrow(() -> new RuntimeException("Not embeddedId"));

        getPrimaryKeys().forEach(key -> {
            assertThat(key, hasManyToOneWithEntityName(field.getType(), key), equalTo(true));
        });
    }

}
