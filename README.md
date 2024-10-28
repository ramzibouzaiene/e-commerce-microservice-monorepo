# Application WorkFlow

![spring-ms drawio](https://github.com/user-attachments/assets/6259e395-c462-4117-84c3-c7d0613fe9c6)

# E-Commerce Microservice Application

## Overview

This repository contains a microservice application built with **Spring Boot** and **Spring Cloud**, demonstrating various distributed patterns such as:

- **Configuration Server** for centralized configuration management
- **Discovery Server (Eureka)** for service registration and discovery
- **API Gateway** for routing requests and aggregating responses
- **Asynchronous Communication** using **Kafka** for event-driven architecture
- **Synchronous Communication** with **OpenFeign** and **RestTemplate** for RESTful service calls
- **Deployment** with Kubernetes resources using the **Jib Maven Plugin**

## Architecture

The application is structured as a monorepo containing several microservices, each responsible for distinct functionalities within the e-commerce platform. The architecture follows best practices for microservice design and communication.

### Microservices Included

- **Configuration Server**: Manages application configuration settings.
- **Discovery Service**: Registers and discovers services using Eureka.
- **API Gateway**: Serves as the entry point for client requests, routing them to appropriate microservices.
- **Order Service**: Handles order processing and management.
- **Payment Service**: Manages payment processing and transactions.
- **Customer Service**: Manages customer data and interactions.
- **Product Service**: Manages product information and inventory.
- **Notification Service**: Sends notifications to users (email, SMS, etc.).
- **Zookeeper**: Optional service for distributed coordination (if used).

## Technologies Used

- **Spring Boot**: For building microservices
- **Spring Cloud**: For cloud-native features and service orchestration
- **Kafka**: For message brokering and asynchronous communication
- **Eureka**: For service discovery
- **OpenFeign**: For declarative REST client
- **RestTemplate**: For synchronous RESTful communication
- **Kubernetes**: For container orchestration and deployment
- **Jib**: For building Docker images directly from Maven
- **Helm**: For managing Kubernetes applications

## Helm Chart

A Helm chart is included in this project to facilitate the deployment of the microservices to a Kubernetes cluster. The chart can be found in the `k8s/` directory. 
