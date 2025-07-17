📊 Real-Time Log Analytics System using Apache Kafka
A real-time data pipeline that captures log messages from a Spring Boot producer, sends them to a Kafka topic, and persists them in MongoDB via a Kafka consumer.

🧱 Architecture
          +------------+         +------------+         +-------------+
          |  Producer  |  --->   |   Kafka    |  --->   |  Consumer   |
          | (Spring)   |         |  +Zookeeper|         | (Spring)    |
          +------------+         +------------+         +-------------+
                                                         |
                                                         v
                                                    +----------+
                                                    | MongoDB  |
                                                    +----------+
📦 Tech Stack
Component	Technology
Messaging	Apache Kafka
Orchestration	Docker Compose
Persistence	MongoDB
Producer App	Spring Boot (Java)
Consumer App	Spring Boot (Java)
Coordination	Zookeeper

🧠 How It Works
Producer sends logs to Kafka topic web-logs.

Kafka stores and forwards logs to all consumer groups.

Consumer reads logs and saves them to MongoDB.

Zookeeper manages Kafka broker coordination.

MongoDB stores the log records in real-time for analytics.

🔍 Why Kafka?
Kafka enables:

High throughput & low latency

Scalable consumption (via partitions)

Multiple consumers (each with own group ID)

Replayable messages (durable storage)

⚠️ Key Concepts & Caveats
Concept	Explanation
Topic Partition	Topic is split into partitions. Enables parallel consumption.
Consumer Group	Group of consumers that share the load (each partition → one consumer).
Multiple Consumers	Can have multiple groups for different purposes (e.g., DB saving, alerts).
Zookeeper	Required for Kafka metadata coordination (in older versions of Kafka).
MongoDB	Stores real-time log data persistently for analysis.

🧠 Kafka does not auto-create partitions based on consumers. Partitions are created when the topic is created (default = 1).

🚀 How to Run
1. Clone the project
git clone https://github.com/tanmaymone/real-time-log-analytics.git
cd real-time-log-analytics
2. Start Kafka, Zookeeper, and MongoDB via Docker Compose
docker-compose up -d
3. Run the Producer App
cd producer
./mvnw spring-boot:run
4. Run the Consumer App
cd consumer
./mvnw spring-boot:run
📝 Sample Log Sent
{
  "timestamp": "2025-07-17T12:00:00Z",
  "level": "INFO",
  "message": "User logged in successfully",
  "source": "AuthService"
}
📂 Folder Structure
├── docker-compose.yml
├── consumer
│   └── com.kafka.consumer  # Spring Boot Kafka consumer (MongoDB persistence)
├── producer
│   └── com.kafka.producer  # Spring Boot Kafka log producer
📌 Improvements You Can Add
Expose REST API to fetch logs from MongoDB

Add Elasticsearch + Kibana for log visualization

Use Kafka Streams to filter or aggregate logs

Add retry mechanism for consumer
