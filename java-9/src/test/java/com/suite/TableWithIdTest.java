package com.suite;

import org.junit.Test;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class TableWithIdTest extends ClassTest {

    public static final String ID = "id";

    @Test
    public void hasColumnId() {
        Optional<Field> field = findFieldByName(ID);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasIdWithAnnotationId() {
        Optional<Field> field = findFieldAnnotatedBy(Id.class);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasIdWithAnnotationGeneratedValueWithStrategyIdentity() {
        verifyIdWithCorretStrategy();
    }

    @Test
    public void hasIdWithAnnotationGeneratedValue() {
        Optional<Field> field = findFieldAnnotatedBy(GeneratedValue.class);

        assertThat(field.isPresent(), equalTo(true));
    }
}

