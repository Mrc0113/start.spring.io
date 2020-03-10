/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension.dependency.springcloud;

import io.spring.initializr.metadata.Dependency;
import io.spring.initializr.web.project.ProjectRequest;
import io.spring.start.site.extension.AbstractExtensionTests;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SpringCloudStreamBuildCustomizer}.
 *
 * @author Stephane Nicoll
 */
class SpringCloudStreamBuildCustomizerTests extends AbstractExtensionTests {

	private static final Dependency KAFKA_BINDER = Dependency.withId("cloud-stream-binder-kafka",
			"org.springframework.cloud", "spring-cloud-stream-binder-kafka");

	private static final Dependency KAFKA_STREAMS_BINDER = Dependency.withId("cloud-stream-binder-kafka-streams",
			"org.springframework.cloud", "spring-cloud-stream-binder-kafka-streams");

	private static final Dependency RABBIT_BINDER = Dependency.withId("cloud-stream-binder-rabbit",
			"org.springframework.cloud", "spring-cloud-stream-binder-rabbit");

	private static final Dependency SOLACE_BINDER = Dependency.withId("cloud-stream-binder-solace",
			"com.solace.spring.cloud", "spring-cloud-stream-binder-solace");

	private static final Dependency SCS_TEST = Dependency.withId("cloud-stream-test", "org.springframework.cloud",
			"spring-cloud-stream-test-support", null, Dependency.SCOPE_TEST);

	@Test
	void springCloudStreamWithRabbit() {
		ProjectRequest request = createProjectRequest("cloud-stream", "amqp");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-stream")).hasDependency(getDependency("amqp"))
				.hasDependency(RABBIT_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(6);
	}

	@Test
	void springCloudStreamWithSolace() {
		ProjectRequest request = createProjectRequest("cloud-stream", "solace");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-stream"))
				.hasDependency(getDependency("solace")).hasDependency(SOLACE_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(5);
	}

	@Test
	void springCloudStreamWithKafka() {
		ProjectRequest request = createProjectRequest("cloud-stream", "kafka");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-stream")).hasDependency(getDependency("kafka"))
				.hasDependency(KAFKA_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(6);
	}

	@Test
	void springCloudStreamWithKafkaStreams() {
		ProjectRequest request = createProjectRequest("cloud-stream", "kafka-streams");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-stream"))
				.hasDependency(getDependency("kafka-streams")).hasDependency(KAFKA_STREAMS_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(5);
	}

	@Test
	void springCloudStreamWithAllBinders() {
		ProjectRequest request = createProjectRequest("cloud-stream", "amqp", "kafka", "kafka-streams", "solace");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-stream")).hasDependency(getDependency("amqp"))
				.hasDependency(getDependency("solace")).hasDependency(getDependency("kafka"))
				.hasDependency(getDependency("kafka-streams")).hasDependency(RABBIT_BINDER).hasDependency(SOLACE_BINDER)
				.hasDependency(KAFKA_BINDER).hasDependency(KAFKA_STREAMS_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(13);
	}

	@Test
	void reactiveSpringCloudStreamWithRabbit() {
		ProjectRequest request = createProjectRequest("reactive-cloud-stream", "amqp");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("reactive-cloud-stream"))
				.hasDependency(getDependency("amqp")).hasDependency(RABBIT_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(6);
	}

	@Test
	void reactiveSpringCloudStreamWithSolace() {
		ProjectRequest request = createProjectRequest("reactive-cloud-stream", "solace");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("reactive-cloud-stream"))
				.hasDependency(getDependency("solace")).hasDependency(SOLACE_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(5);
	}

	@Test
	void reactiveSpringCloudStreamWithKafka() {
		ProjectRequest request = createProjectRequest("reactive-cloud-stream", "kafka");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("reactive-cloud-stream"))
				.hasDependency(getDependency("kafka")).hasDependency(KAFKA_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(6);
	}

	@Test
	void reactiveSpringCloudStreamWithKafkaStreams() {
		ProjectRequest request = createProjectRequest("reactive-cloud-stream", "kafka-streams");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("reactive-cloud-stream"))
				.hasDependency(getDependency("kafka-streams")).hasDependency(KAFKA_STREAMS_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(5);
	}

	@Test
	void reactiveSpringCloudStreamWithAllBinders() {
		ProjectRequest request = createProjectRequest("reactive-cloud-stream", "amqp", "kafka", "kafka-streams",
				"solace");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("reactive-cloud-stream"))
				.hasDependency(getDependency("amqp")).hasDependency(getDependency("solace"))
				.hasDependency(getDependency("kafka")).hasDependency(getDependency("kafka-streams"))
				.hasDependency(RABBIT_BINDER).hasDependency(SOLACE_BINDER).hasDependency(KAFKA_BINDER)
				.hasDependency(KAFKA_STREAMS_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(SCS_TEST).hasDependenciesSize(13);
	}

	@Test
	void springCloudBusWithRabbit() {
		ProjectRequest request = createProjectRequest("cloud-bus", "amqp");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-bus")).hasDependency(getDependency("amqp"))
				.hasDependency(RABBIT_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(5);
	}

	@Test
	void springCloudBusWithSolace() {
		ProjectRequest request = createProjectRequest("cloud-bus", "solace");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-bus")).hasDependency(getDependency("solace"))
				.hasDependency(SOLACE_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(4);
	}

	@Test
	void springCloudBusWithKafka() {
		ProjectRequest request = createProjectRequest("cloud-bus", "amqp");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-bus")).hasDependency(getDependency("amqp"))
				.hasDependency(RABBIT_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(5);
	}

	@Test
	void springCloudBusWithAllBinders() {
		ProjectRequest request = createProjectRequest("cloud-bus", "amqp", "kafka", "kafka-streams", "solace");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-bus")).hasDependency(getDependency("amqp"))
				.hasDependency(getDependency("solace")).hasDependency(getDependency("kafka"))
				.hasDependency(getDependency("kafka-streams")).hasDependency(RABBIT_BINDER).hasDependency(SOLACE_BINDER)
				.hasDependency(KAFKA_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(11);
	}

	@Test
	void springCloudTurbineStreamWithRabbit() {
		ProjectRequest request = createProjectRequest("cloud-turbine-stream", "amqp");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-turbine-stream"))
				.hasDependency(getDependency("amqp")).hasDependency(RABBIT_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(5);
	}

	@Test
	void springCloudTurbineStreamWithSolace() {
		ProjectRequest request = createProjectRequest("cloud-turbine-stream", "solace");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-turbine-stream"))
				.hasDependency(getDependency("solace")).hasDependency(SOLACE_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(4);
	}

	@Test
	void springCloudTurbineStreamWithKafka() {
		ProjectRequest request = createProjectRequest("cloud-turbine-stream", "kafka");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-turbine-stream"))
				.hasDependency(getDependency("kafka")).hasDependency(KAFKA_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(5);
	}

	@Test
	void springCloudTurbineStreamWithAllBinders() {
		ProjectRequest request = createProjectRequest("cloud-turbine-stream", "amqp", "kafka", "kafka-streams",
				"solace");
		request.setBootVersion("2.0.0.RELEASE");
		assertThat(mavenPom(request)).hasDependency(getDependency("cloud-turbine-stream"))
				.hasDependency(getDependency("amqp")).hasDependency(getDependency("solace"))
				.hasDependency(getDependency("kafka")).hasDependency(getDependency("kafka-streams"))
				.hasDependency(RABBIT_BINDER).hasDependency(SOLACE_BINDER).hasDependency(KAFKA_BINDER)
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(11);
	}

}
