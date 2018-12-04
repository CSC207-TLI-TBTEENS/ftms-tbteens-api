package com.ftms.ftmsapi.modelTest;

import com.ftms.ftmsapi.model.Task;
import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.model.Job;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void testTaskTimesheet(){

        Task task = new Task();

        Timesheet timesheet = new Timesheet();

        task.setTimesheet(timesheet);

        assertEquals(task.getTimesheet(), timesheet );
    }
    @Test
    public void testTaskName(){

        Task task = new Task();

        task.setName("Julie");

        assertEquals(task.getName(),"Julie" );
    }
    @Test
    public void testTaskDescription(){

        Task task = new Task();

        task.setDescription("Work on CSC207! QA must work harder :).");

        assertEquals(task.getDescription(),"Work on CSC207! QA must work harder :)." );
    }
}

