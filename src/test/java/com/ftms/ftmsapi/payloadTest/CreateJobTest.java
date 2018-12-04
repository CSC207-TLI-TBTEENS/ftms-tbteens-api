package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.CreateJob;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateJobTest {

    @Test
    public void testJobTitle() {
        CreateJob newJob = new CreateJob();
        newJob.setJobTitle("Superintendent");
        assertEquals(newJob.getJobTitle(), "Superintendent");
    }

    @Test
    public void testDescription() {
        CreateJob newJob = new CreateJob();
        newJob.setDescription("Check this description");
        assertEquals(newJob.getDescription(), "Check this description");
    }

    @Test
    public void testSiteName() {
        CreateJob newJob = new CreateJob();
        newJob.setSiteName("Robart's");
        assertEquals(newJob.getSiteName(), "Robart's");
    }

}