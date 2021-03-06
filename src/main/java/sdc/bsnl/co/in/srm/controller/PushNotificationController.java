package sdc.bsnl.co.in.srm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sdc.bsnl.co.in.srm.model.PushNotificationRequest;
import sdc.bsnl.co.in.srm.model.PushNotificationResponse;
import sdc.bsnl.co.in.srm.service.PushNotificationService;

@RestController
public class PushNotificationController {
    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
//
//    @PostMapping("/notification/topic")
//    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotificationWithoutData(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }



//    @PostMapping("/notification/data")
//    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotification(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }
//
//    @GetMapping("/notification")
//    public ResponseEntity sendSampleNotification() {
//        pushNotificationService.sendSamplePushNotification();
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }
}
