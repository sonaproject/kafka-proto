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

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerializer;
import com.sktelecom.ci.models.PersonModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * An example of kafka producer which utilizes the protobuf as serde backend.
 */
public class KafkaProtoProducer {

    private void produce() {
        Properties props = new Properties();

        Producer<String, PersonModel.Person> producer = new KafkaProducer<>(props,
                new StringSerializer(),
                new KafkaProtobufSerializer<>());

        producer.send(new ProducerRecord<>("topic", PersonModel.Person.getDefaultInstance()));
    }
}
