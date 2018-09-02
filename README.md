# basic-microservice
Crazy basic microservice, for simple experimentation

The microservice will
- wait for simple character "events"
  - e.g. when an event of Alphabet letter <A> occurs, the microservice will emit an "event" of <A+1>
	-> this is the "business logic"
	- ideally, I will just paramaterize the input letter and resulting output letter

The implementation will initially work using NATS
- later I would like to switch the implementation to use RabbitMQ / Kafka etc
- will aim to keep the implementation detail away from the "business logic"

I need to orchestrate up multiple instances of the microservices
	- let's say just spin up 5 instances for starters
	- from 2 instances first...

I would like to orchestrate this with docker first
- then later minikube?
- also look for a chance to "use" Chef / Terraform too
