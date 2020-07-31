package sdc.bsnl.co.in.srm.firebase;

import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sdc.bsnl.co.in.srm.model.PushNotificationRequest;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);


//    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
//            throws InterruptedException, ExecutionException {
//        Message message = getPreconfiguredMessageWithData(data, request);
//        String response = sendAndGetResponse(message);
//        logger.info("sent message with data. Topic : ", request.getTopic() + "," + response);
//    }
//
//    public void sendMessageWithoutData(PushNotificationRequest request) throws ExecutionException, InterruptedException {
//        Message message = getPreconfiguredMessageWithOutData(request);
//        String response = sendAndGetResponse(message);
//        logger.info("sent message with out data. Topic : ", request.getTopic() + "," + response);
//    }

    public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response);
    }

    private String sendAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
    }

//    private Message getPreconfiguredMessageWithOutData(PushNotificationRequest request) {
//        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
//    }
//
//    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
//        return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic()).build();
//    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        return Message.builder()
                .setAndroidConfig(androidConfig)
                .setNotification(new Notification(request.getTitle(), request.getMessage()));

    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }
}
