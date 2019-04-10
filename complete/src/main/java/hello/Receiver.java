package hello;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;
    private StringRedisTemplate template;

    @Autowired
    public Receiver(CountDownLatch latch, StringRedisTemplate template) {
        this.latch = latch;
        this.template = template;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");

        String testdata = template.opsForValue().get("testdata");
        LOGGER.info("Get <" + testdata + ">");
        
        latch.countDown();
    }
}
