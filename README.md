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

Instructions:
So far this much is working:
1) start up the nats server as per the doc here
	https://www.nats.io/documentation/tutorials/gnatsd-docker/
	(after the first time, docker start / stop should work)

	- you could run against the public nats demo server

2) run the java application 
	(I am just running it from eclipse so far)

3) telnet to the nats server, and publish messages
	e.g.

	telnet <nats ip> 4222
	pub alphabet.A 1
	A

4) Observe the microservice's behaviour