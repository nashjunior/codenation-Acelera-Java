package com.suite;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CompanyTest extends TableWithIdNameSlugTest {

    public static final String COMPANY = "company";
    public static final String CANDIDATE = "candidate";

    @Override
    public String getTableName() {
        return COMPANY;
    }

    @Override
    protected int manyToOnes() {
        return 0;
    }

    @Override
    protected int oneToManys() {
        return 1;
    }

    @Test
    public void hasOneToManyCandidates() {
        boolean result = hasOneToManyWithEntityName(CANDIDATE);

        assertThat(result, equalTo(true));
    }

}
