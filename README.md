# Backend Microservice Repository

This repository contains a collection of backend microservices built with Java Spring Boot, Maven, Docker Compose, and Jib build. The microservices included in this repository are:

1. Product Service
2. API Gateway
3. Discovery Server
4. Inventory Service
5. Notification Service
6. Order Service

## Prerequisites

To run the microservices in this repository, you need to have the following tools installed:

- Java Development Kit (JDK)
- Apache Maven
- Docker
- Docker Compose

## Getting Started

Follow the steps below to set up and run the microservices:

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/backend-microservice-repo.git
   cd backend-microservice-repo
   ```

2. Build the microservices using Maven:

   ```shell
   mvn clean install
   ```

3. Build the Docker images using Jib:

   ```shell
   mvn jib:dockerBuild
   ```

4. Start the microservices using Docker Compose:

   ```shell
   docker-compose up
   ```

   This command will start all the microservices along with the required infrastructure services such as the Discovery Server.

5. Access the microservices:

   - Product Service: http://localhost:8081
   - API Gateway: http://localhost:8080
   - Discovery Server: http://localhost:8761
   - Inventory Service: http://localhost:8082
   - Notification Service: http://localhost:8083
   - Order Service: http://localhost:8084

## Configuration

Each microservice has its own configuration files located in their respective directories. You can modify the configuration files according to your requirements.

## Development

If you want to make changes to the microservices or add new features, follow the steps below:

1. Create a new branch for your changes:

   ```shell
   git checkout -b feature/your-feature-name
   ```

2. Make the necessary changes and test them locally.

3. Commit your changes:

   ```shell
   git commit -m "Add your commit message here"
   ```

4. Push your changes to the repository:

   ```shell
   git push origin feature/your-feature-name
   ```

5. Open a pull request on GitHub and describe your changes in detail.

## License

 Feel free to use, modify, and distribute it as per the terms of the license.

## Acknowledgments

- The microservices in this repository are built with Java Spring Boot, which is an open-source framework.
- Docker and Docker Compose are used to containerize the microservices and manage their deployment.
- Jib build is used for building Docker images without the need for writing Dockerfiles.

Please refer to the individual microservice directories for more specific information and documentation on each service.
