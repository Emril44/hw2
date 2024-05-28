import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MessageSendTest {
    private Encryptor encryptor;
    private Decryptor decryptor;
    private Processor processor;
    private SampleReceiver receiver;
    private Sender sender;

    @BeforeEach
    public void setup() {
        // TODO: Implement test
        byte[] key = "1028564026592835".getBytes();
        encryptor = new Encryptor(key);
        sender = new Sender();
        processor = new Processor(encryptor, sender);
        decryptor = new Decryptor(key, processor);
        receiver = new SampleReceiver(encryptor, decryptor);
    }

    @Test
    public void testMultithreadMessageSend() throws InterruptedException {
        // Create a thread pool to simulate concurrent message sending
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Submit multiple tasks to the executor service
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    receiver.receiveMessage();
                } catch (Exception e) {
                    fail("Exception occurred while receiving message: " + e.getMessage());
                }
            });
        }

        // Shutdown the executor service and wait for tasks to complete
        executorService.shutdown();
        boolean tasksCompleted = executorService.awaitTermination(30, TimeUnit.SECONDS);

        assertTrue(tasksCompleted, "All tasks should complete within the timeout period");
    }
}
