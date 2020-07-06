package com.suite;

import org.junit.Test;

import javax.validation.constraints.Email;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserTest extends TableWithIdTest {

    public static final String FULLNAME = "fullname";
    public static final String EMAIL = "email";
    public static final String NICKNAME = "nickname";
    public static final String PASSWORD = "password";
    public static final String SUBMISSION = "submission";
    public static final String CANDIDATE = "candidate";

    @Override
    protected int manyToOnes() {
        return 0;
    }

    @Override
    protected int oneToManys() {
        return 2;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Test
    public void hasColumnFullName() {
        Optional<Field> field = findByFieldNameOrColumnName(FULLNAME);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnFullNameNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(FULLNAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnFullNameSize() {
        Optional<Field> field = findByFieldNameOrColumnName(FULLNAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 100), equalTo(true));
    }

    @Test
    public void hasColumnEmail() {
        Optional<Field> field = findByFieldNameOrColumnName(EMAIL);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnEmailNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(EMAIL);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnEmailSize() {
        Optional<Field> field = findByFieldNameOrColumnName(EMAIL);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 100), equalTo(true));
    }

    @Test
    public void hasColumnEmailWithEmailAnnotation() {
        Optional<Field> field = findByFieldNameOrColumnName(EMAIL);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(field.get().isAnnotationPresent(Email.class), equalTo(true));
    }

    @Test
    public void hasColumnNickname() {
        Optional<Field> field = findByFieldNameOrColumnName(NICKNAME);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnNicknameNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(NICKNAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnNicknameSize() {
        Optional<Field> field = findByFieldNameOrColumnName(NICKNAME);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 50), equalTo(true));
    }

    @Test
    public void hasColumnPassword() {
        Optional<Field> field = findByFieldNameOrColumnName(PASSWORD);

        assertThat(field.isPresent(), equalTo(true));
    }

    @Test
    public void hasColumnPasswordNotNull() {
        Optional<Field> field = findByFieldNameOrColumnName(PASSWORD);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldIsNotNull(field), equalTo(true));
    }

    @Test
    public void hasColumnPasswordSize() {
        Optional<Field> field = findByFieldNameOrColumnName(PASSWORD);

        assertThat(field.isPresent(), equalTo(true));
        assertThat(fieldHasSize(field, 255), equalTo(true));
    }

    @Test
    public void hasOneToManySubmissions() {
        boolean result = hasOneToManyWithEntityName(SUBMISSION);

        assertThat(result, equalTo(true));
    }

    @Test
    public void hasOneToManyCandidates() {
        boolean result = hasOneToManyWithEntityName(CANDIDATE);

        assertThat(result, equalTo(true));
    }

}
