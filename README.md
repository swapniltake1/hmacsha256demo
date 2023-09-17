
# HMAC-SHA256 Authentication Example

This repository contains a simple example of HMAC-SHA256-based authentication in a Spring Boot application. HMAC-SHA256 (Hash-based Message Authentication Code using the SHA-256 hash function) is a cryptographic technique used for verifying the integrity and authenticity of data.

## Overview

The application includes an `AuthenticationController` that provides two endpoints:

1. `/api/generate-secret-key`: This endpoint generates a new secret key for HMAC-SHA256 and returns it as a Base64-encoded string. This key is used for signature validation during login.

2. `/api/login`: This endpoint validates a user's login attempt by comparing the received HMAC-SHA256 signature with the stored secret key.

## Usage

To use this application, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/swapniltake1/hmacsha256demo.git
   ```

2. Build and run the Spring Boot application using your preferred IDE or with Maven:

   ```bash
   mvn spring-boot:run
   ```

3. Access the following endpoints:

   - Generate a new secret key: `http://localhost:8080/api/generate-secret-key`
   - Validate a login: `http://localhost:8080/api/login`

4. Use the secret key generated in step 3 to create HMAC-SHA256 signatures for login validation.

## Dependencies

- [Spring Boot](https://spring.io/projects/spring-boot): Spring Boot is used to create the web application and manage dependencies.

## Contributing

If you'd like to contribute to this project, please follow these guidelines:

1. Fork the repository.

2. Create a new branch for your feature or bug fix:

   ```bash
   git checkout -b feature/my-feature
   ```

3. Make your changes and commit them:

   ```bash
   git commit -m "Add my feature"
   ```

4. Push your changes to your fork:

   ```bash
   git push origin feature/my-feature
   ```

5. Create a pull request on the original repository.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.  

## Screenshots

![login succesfull](https://github.com/swapniltake1/hmacsha256demo/assets/61576958/8251a687-ab38-42fd-b824-615911936e8c)

![Key Generationn](https://github.com/swapniltake1/hmacsha256demo/assets/61576958/70ada3df-a586-44d7-a554-6f5f92354ad5)

