package com.suite;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class TableWithIdNameSlugTest extends ClassTest {

    public static final String NAME = "name";
    public static final String SLUG = "slug";

    @Test
    public void hasColumnName() {
        Optional<Field> field = findByFieldNameOrColumnName(NAME);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnNameNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(NAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnNameSized() {
        Optional<Field> field = findByFieldNameOrColumnName(NAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 100), equalTo(true));
    }

    @Test
    public void hasColumnSlug() {
        Optional<Field> field = findByFieldNameOrColumnName(SLUG);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnSlugNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(SLUG);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnSlugSize() {
        Optional<Field> field = findByFieldNameOrColumnName(SLUG);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 50), equalTo(true));
    }

}
