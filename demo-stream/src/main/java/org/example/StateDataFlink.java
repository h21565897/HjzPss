package org.example;


import com.alibaba.fastjson.JSON;
import com.junzhou.infop.handler.enums.AnchorInfo;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class StateDataFlink {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<String> sources = KafkaSource.<String>builder().setBootstrapServers("kafka-cluster:9092").setTopics("infopLog").setGroupId("infopLogGroup").setStartingOffsets(OffsetsInitializer.latest()).setValueOnlyDeserializer(new SimpleStringSchema()).build();

        DataStreamSource<String> kafkaLogSource = env.fromSource(sources, WatermarkStrategy.noWatermarks(), "kafkaLogSource");
        SingleOutputStreamOperator<AnchorInfo> flatedMap = kafkaLogSource.map(new MapFunction<String, AnchorInfo>() {

            @Override
            public AnchorInfo map(String value) throws Exception {
                return JSON.parseObject(value, AnchorInfo.class);
            }
        });
        System.out.println("executejjjjjjj");
        flatedMap.addSink(new DataSink()).name("sink1");
        env.execute();
    }
}