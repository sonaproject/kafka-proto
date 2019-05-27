/*
 * Copyright 2019-present SK Telecom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sktelecom.ci.example;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufDeserializer;
import com.sktelecom.ci.models.PersonModel;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * An example of kafka consumer which utilizes the protobuf as serde backend.
 */
public class KafkaProtoConsumer {

    private void consume() {
        Properties props = new Properties();

        Consumer<String, PersonModel.Person> consumer = new KafkaConsumer<>(props,
                new StringDeserializer(),
                new KafkaProtobufDeserializer(PersonModel.Person.parser()));

        consumer.subscribe(Collections.singleton("topic"));
        ConsumerRecords<String, PersonModel.Person>
                records = consumer.poll(Duration.ofMillis(100));

        records.forEach(record -> {
            String key = record.key();
            PersonModel.Person value = record.value();
        });
    }
}
