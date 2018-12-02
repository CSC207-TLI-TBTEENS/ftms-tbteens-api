package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Notification;
import com.ftms.ftmsapi.model.User;
import com.ftms.ftmsapi.payload.ApiResponse;
import com.ftms.ftmsapi.payload.NotificationService;
import com.ftms.ftmsapi.repository.JobRepository;
import com.ftms.ftmsapi.repository.NotificationRepository;
import com.ftms.ftmsapi.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private JobRepository jobRepository;

    /**
     * Return all the notifications for a certain employee with ID id in a list
     *
     * @param id The ID of the employee to be checked.
     * @return A list containing all notifications for this employee.
     */
    @GetMapping("/{id}")
    public List<Notification> getNotificationByUserId(@PathVariable Long id) {
        System.out.println("Hello");
        return notificationService.findByUserId(id);
    }

    /**
     * Create the nofinication for an employee notifying him/her that a job info has been assigned to him/her,
     * and return the system response entity.
     *
     * @param info The job to be assigned.
     * @return The response entity from the system.
     */
    @PostMapping("/jobassigned")
    public ResponseEntity<?> createNewNotificationJobAssign(@Valid @RequestBody String info) {
        ArrayList<Object> validationResult = validateInfo(info);
        return getJobNotification(validationResult, ") has been assigned to you.", "JOB_ASSIGN");
    }

    /**
     * Create the notification for an employee notifying him/her that a job info has been removed from him/her,
     * and return the system response entity.
     *
     * @param info The job to be deleted.
     * @return The response entity from the system.
     */
    @PostMapping("/jobdeleted")
    public ResponseEntity<?> createNewNotificationJobDeleted(@Valid @RequestBody String info) {
        ArrayList<Object> validationResult = validateInfo(info);
        return getJobNotification(validationResult, ") has been removed from your queue.", "JOB_DELETE");
    }

    /**
     * Returns the job notification of the validated job validationResult over its userID and the action action of
     * notification type notifType, if the validated job is not an error message. Returns the notification for an
     * error elsewise.
     *
     * @param validationResult The validated job.
     * @param action The action of notification.
     * @param notifType The notification type.
     * @return The job notification if the validationResult is an validated job, or an error message elsewise.
     * @throws JSONException
     */
    private ResponseEntity<?> getJobNotification(ArrayList<Object> validationResult, String action, String notifType)
            throws JSONException {
        // Look at the function for validationResult for what this is
        // If validationResult's size is only 1, this means there is an error -> go straight to return the error
        if (validationResult.size() > 1) {
            // If there are goodies inside:

            // at 0: the user, at 1: the job, at 2: the job name, at 3: the HTTP response
            Long userID = (Long) validationResult.get(0);
            Long jobID = (Long) validationResult.get(1);
            String jobName = (String) validationResult.get(2);
            String message = "A new job (" + jobName + action;

            // create the notification
            notificationService.createNotification(message, userID, notifType, jobID);

            // return http response
            return (ResponseEntity) validationResult.get(3);
        }

        return (ResponseEntity) validationResult.get(0);
    }

    /**
     * Return a notification stating the status (approved/rejected) of job info after review if info is a valid job,
     * or an error message elsewise.
     * @param info The reviewed job to nofity.
     * @return A notification stating the status of info after review if info is valid, or a error message elsewise.
     */
    @PostMapping("/jobreviewed")
    public ResponseEntity<?> createNewNotificationJobReviewed(@Valid @RequestBody String info) {
        // Similar to the above function
        ArrayList<Object> validationResult = validateInfo(info);
        if (validationResult.size() > 1) {
            Long userID = (Long) validationResult.get(0);
            Long jobID = (Long) validationResult.get(1);
            JSONObject parser = new JSONObject(info);

            // the JSON for a job-review notification also has a component indicating
            // whether or not the job is approved
            boolean approved = parser.get("approved").equals(true);
            String jobName = (String) validationResult.get(2);

            // if approved is true status is approved, else rejected
            String status = approved ? "approved" : "rejected";

            String message = "Your timesheet for the job " + jobName + " has been " + status + ".";
            String type = "JOB_REVIEW";

            // create the notification
            notificationService.createNotification(message, userID, type, jobID);

            // return http response
            return (ResponseEntity) validationResult.get(3);
        }
        return (ResponseEntity) validationResult.get(0);
    }

    /**
     * Returns a list containing
     * * userID
     * * jobID
     * * Job Name
     * * Success Message
     * if the information info has valid userID and jobID. Return a list with error message elsewise.
     *
     * @param info The information to be validated.
     * @return A list containing userID, jobID, Job Name and Success Message.
     */
    private ArrayList<Object> validateInfo(String info) {
        ArrayList<Object> result = new ArrayList<>();

        // Parse the string <info> into a JSON
        JSONObject notification = new JSONObject(info);

        // Get the values from the keys
        Long userID = Long.parseLong(notification.get("userid").toString());
        Long jobID = Long.parseLong(notification.get("jobid").toString());

        // Make sure that the user exists (look for the user by ID)
        try {
            userRepository.getOne(userID);
        } catch (EntityNotFoundException error) {
            result.add(new ResponseEntity<Object>(new ApiResponse(false, "User not found."),
                    HttpStatus.BAD_REQUEST));
            return result;
        }


        // Make sure that the job exists (look for the job by ID)
        try {
            jobRepository.getOne(jobID);
        } catch (EntityNotFoundException error) {
            result.add(new ResponseEntity<Object>(new ApiResponse(false, "Job not found."),
                    HttpStatus.BAD_REQUEST));
            return result;
        }

        // (ABOVE) If there is any error, the array list returned will simply have the error Response Entity
        // encoding the error

        // If there are no errors above, add the user, job and job's name to the return list
        // Also, add the successful http response to the end of the list for higher level functions to access
        result.add(userID);
        result.add(jobID);
        result.add(notification.get("jobname"));
        result.add(new ResponseEntity<Object>(new ApiResponse(true,
                "Notification for this job assignment to employee #" + userID + " successfully created"),
                HttpStatus.ACCEPTED));
        return result;
    }

    /**
     * Delete the notification of ID id, and then return the system response entity.
     *
     * @param id The ID of the notification to be deleted.
     * @return The system response entity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable Long id) {
        try {
            Notification notification = notificationService.getRepository().getOne(id);
            notificationService.getRepository().delete(notification);
            return new ResponseEntity<Object>(new ApiResponse(true, "Deletion of notification #" + id
                    + " successfully completed!"), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException error) {
            return new ResponseEntity<Object>(new ApiResponse(false, "User not found."),
                    HttpStatus.BAD_REQUEST);
        }
    }


}
