package cn.xukai.java.kafka.kafka_1_0.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kaixu on 2017/12/18.
 */
public class ConsumerDemo implements Runnable {
    private KafkaConsumer kafkaConsumer;
    private int id;
    private List<String> topics;

    public ConsumerDemo(int id, String groupId, List<String> topics) {
        Properties props = new Properties();
        // 偏移量写入zk中。
        //props.put("zookeeper.connect","192.168.107.128:2181");
        //偏移量写入kafka __consumers_offsets 中
        props.put("bootstrap.servers", "192.168.107.128:9092");
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "false");
        props.put("exclude.internal.topics","false");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", "2");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer(props);
        this.topics = topics;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            kafkaConsumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(3000);
                System.out.println("records : " + records.count());
                for (ConsumerRecord<String, String> record : records) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("partition", record.partition());
                    data.put("offset", record.offset());
                    data.put("value", record.value());
                    System.out.println(this.id + ": " + data);
                }
                kafkaConsumer.commitAsync();
            }
        } catch (WakeupException e) {

        } finally {
            kafkaConsumer.close();
        }
    }

    public void shutdown() {
        kafkaConsumer.wakeup();
    }

    public static void main(String[] args) {
        int numConsumers = 3;
        String groupId = "xk-1";
        List<String> topics = Arrays.asList("xk");

        ExecutorService executorService = Executors.newFixedThreadPool(numConsumers);
        final List<ConsumerDemo> consumers = new ArrayList<>();
        for (int i = 0; i < numConsumers; i++) {
            ConsumerDemo consumerDemo = new ConsumerDemo(i, groupId, topics);
            consumers.add(consumerDemo);
            executorService.submit(consumerDemo);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            consumers.forEach(e -> e.shutdown());
            executorService.shutdown();
            try {
                executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ));
    }
}
