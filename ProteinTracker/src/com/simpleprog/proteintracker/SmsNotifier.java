package com.simpleprog.proteintracker;

import java.io.IOException;

public class SmsNotifier implements Notifier {
    private String userName;
    private String password;
    private String numberToMessage;

    public SmsNotifier(String userName, String password, String numberToMessage) {
        this.userName = userName;
        this.password = password;
        this.numberToMessage = numberToMessage;
    }

    @Override
    public boolean send(String message) {
//        try {
//            Voice voice = new Voice(userName, password);
//            voice.sendSMS(numberToMessage, message);
//        } catch (IOException e) {
//            return false;
//        }

        return true;
    }


}
