package com.suite;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ChallengeTest extends TableWithIdNameSlugTest {

    public static final String CHALLENGE = "challenge";
    public static final String ACCELERATION = "acceleration";
    public static final String SUBMISSION = "submission";

    @Override
    public String getTableName() {
        return CHALLENGE;
    }

    @Override
    protected int manyToOnes() {
        return 0;
    }

    @Override
    protected int oneToManys() {
        return 2;
    }

    @Test
    public void hasOneToManyAcceleration() {
        boolean result = hasOneToManyWithEntityName(ACCELERATION);

        assertThat(result, equalTo(true));
    }

    @Test
    public void hasOneToManySubmission() {
        boolean result = hasOneToManyWithEntityName(SUBMISSION);

        assertThat(result, equalTo(true));
    }

}

