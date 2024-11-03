package com.dev.notebook.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        StringBuilder message = new StringBuilder();

        String domain = "http://localhost:8089/api/v1";
        message.append("Hi ").append(name).append(",\n\n")
                .append("Thanks for signing up on my blog! ")
                .append("Just click the link below to activate your account:\n")
                .append(domain).append("/users/account/verify?confirmationKey=").append(token).append("\n\n")
                .append("You can also visit our site at: ").append(host).append("\n\n") // Sử dụng host ở vị trí khác
                .append("If you didn’t sign up, feel free to ignore this message.\n\n")
                .append("Cheers,\n")
                .append("X Blogger");

        return message.toString();
    }


    public static String getResetPasswordMessage(String name, String host, String token) {
        StringBuilder message = new StringBuilder();
        message.append("Hi ").append(name).append(",\n\n")
                .append("It looks like you requested a password reset. ")
                .append("Click the link below to set a new password:\n")
                .append(host).append("/reset-password?token=").append(token).append("\n\n")
                .append("If you didn’t make this request, no worries – just ignore this email.\n\n")
                .append("Best,\n")
                .append("X Blogger");
        return message.toString();
    }

    public static String getVerificationUrl(String host, String token){
        return host + "/verify/account?token=" + token;
    }
}
