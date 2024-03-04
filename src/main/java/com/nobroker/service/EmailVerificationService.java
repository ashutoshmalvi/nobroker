package com.nobroker.service;

import com.nobroker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    static final Map<String, String> emailOtpMapping = new HashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;


    public Map<String, String> verifyOtp(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);
        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                userService.verifyEmail(user);
                emailOtpMapping.remove(email);
                response.put("status", "success");
                response.put("msg", "Email verified successful");
            } else {
              //  logger.error("Invalid OTP received for email:");
                response.put("status", "error");
                response.put("msg", "User not found");
            }
        } else {
            response.put("status", "error");
            response.put("msg", "OTP is Invalid");
        }
        return response;
    }

    public Map<String, String> sendOtpToLogin(String email) {
        if (userService.isEmailVerified(email)) {
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp);
            //send otp to user's email
            emailService.sendOtpEmailToLogin(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return response;
        } else {
            Map<String,String>response = new HashMap<>();
            response.put("status","error");
            response.put("message","Email is not verified");
            return response;
        }
    }

    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {

            emailOtpMapping.remove(email);
            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        } else {
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }
        return response;
    }
}

