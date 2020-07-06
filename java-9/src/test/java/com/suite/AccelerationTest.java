package com.suite;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccelerationTest extends TableWithIdNameSlugTest {

    public static final String ACCELERATION = "acceleration";
    public static final String CHALLENGE = "challenge";
    public static final String CANDIDATE = "candidate";

    @Override
    public String getTableName() {
        return ACCELERATION;
    }

    @Override
    protected int manyToOnes() {
        return 1;
    }

    @Override
    protected int oneToManys() {
        return 1;
    }

    @Test
    public void hasManyToOneChallenge() {
        Boolean is = hasManyToOneWithEntityName(CHALLENGE);

        assertThat(is, equalTo(true));
    }

    @Test
    public void hasOneToManyCandidates() {
        boolean result = hasOneToManyWithEntityName(CANDIDATE);

        assertThat(result, equalTo(true));
    }

}
