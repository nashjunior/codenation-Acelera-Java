package com.suite;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SubmissionTest extends TableWithEmbeddedIdTest {

    public static final String SUBMISSION = "submission";
    public static final String SCORE = "score";
    public static final String CHALLENGE = "challenge";
    public static final String USER = "user";

    @Override
    public List<String> getPrimaryKeys() {
        return Stream.of(CHALLENGE, USER).collect(Collectors.toList());
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
    public String getTableName() {
        return SUBMISSION;
    }

    @Test
    public void hasColumnScore() {
        Optional<Field> field = findByFieldNameOrColumnName(SCORE);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnScoreNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(SCORE);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

}
