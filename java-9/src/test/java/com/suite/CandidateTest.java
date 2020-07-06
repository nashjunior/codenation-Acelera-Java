package com.suite;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CandidateTest extends TableWithEmbeddedIdTest {

    public static final String CANDIDATE = "candidate";
    public static final String USER = "user";
    public static final String ACCELERATION = "acceleration";
    public static final String COMPANY = "company";
    public static final String STATUS = "status";

    @Override
    public String getTableName() {
        return CANDIDATE;
    }

    @Override
    protected int manyToOnes() {
        return 0;
    }

    @Override
    protected int oneToManys() {
        return 0;
    }

    @Override
    public List<String> getPrimaryKeys() {
        return Stream.of(USER, ACCELERATION, COMPANY).collect(toList());
    }

    @Test
    public void hasColumnStatus() {
        Optional<Field> field = findByFieldNameOrColumnName(STATUS);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnStatusNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(STATUS);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

}
