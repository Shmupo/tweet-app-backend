# Tweet App Backend Microservices
This is an example of a X/Twitter-like application comprising of several components communicating to each other. In other words, this is an example of microservice app.

The app itself allows all users to view posts, but only user profiles that are logged in and authenticated users can create, reply, or like posts.

The application was made using SPRING and MySQL.

## Components

1. [API Gateway](/api-gateway) Spring Cloud API Gateway, serves as a reverse proxy, providing a single entry point between clients and the backend microservices.
2. [Config Server](/config-server) Spring Cloud Config Server used to manage the properties among the microservices.
3. [Like Service](/like-service) SPRING REST microservice managing the likes from authenticated users.
4. [Post Service](/post-service) SPRING REST microservice managing the posts from authenticated users.
5. [Registry-Service](/registry-service) SPRING EUREKA SERVER microservice used to bind Spring Boot apps through autoconfiguration to the Spring Environment.
6. [Reply Service](/reply-service) SPRING REST microservice managing the post-replies of authenticated users.
7. [User Service](/user-service) SPRING REST microservice managing the account profiles, Spring security, and authentification for user accounts.

## Use cases

1. User Registration and Authentication

    Component Involved: User Service
    Description: Users can create new accounts by registering with their details. Authentication is handled via Spring Security to ensure that only registered users can access protected endpoints. Users log in and log out securely through JWT token-based authentication.

2. Creating and Managing Posts

    Component Involved: Post Service
    Description: Authenticated users can create, edit, and delete posts. Posts are stored, updated, and managed by the Post Service into a MySQL database. Each post can contain text content, and can optionally contain a tag. Users have full control over their own posts.

3. Liking Posts

    Component Involved: Like Service
    Description: Authenticated users can like posts made by other users. This service records the likes, ensuring that each user can only like a post once. The Like Service tracks likes across all posts in the system and updates like counts accordingly.

4. Replying to Posts

    Component Involved: Reply Service
    Description: Users can reply to posts made by other users, enabling a threaded conversation around specific posts. The Reply Service manages these replies, including creating, editing, and deleting replies while maintaining the relationship between posts and their replies.

5. Viewing and Managing User Profiles

    Component Involved: User Service
    Description: Users can view and update their profile picture. The User Service manages the data related to user accounts, including authentication credentials and profile information.

6. Centralized Configuration Management

    Component Involved: Config Server
    Description: All microservices rely on a centralized configuration managed by the Config Server. Properties related to databases, security, and other service configurations are managed centrally and can be dynamically updated without redeploying services.

7. Service Discovery

    Component Involved: Registry-Service
    Description: All microservices register themselves with the Eureka server, allowing dynamic discovery of services. This ensures that each service can communicate with others without hardcoding network locations, facilitating scalability and fault tolerance.

8. API Gateway for Unified Entry Point

    Component Involved: API Gateway
    Description: The API Gateway acts as a reverse proxy, providing a single entry point for all clients. It handles routing requests to the appropriate microservices based on URLs and ensures security by handling tasks like authentication, authorization, and rate-limiting.

## How to start

Only a few microservices need to be started in order that needs to be followed below, with the left being first:

Registry Service -> Config Server -> [Remaining Microservices in any order]

## Demo Images

1. View Posts While not Logged In
![ViewPosts, Not Logged In](https://github.com/Shmupo/tweet-app-backend/blob/main/images/PostsNotLoggedIn.png)

2. View Posts While Logged In
![ViewPosts, Not Logged In](https://github.com/Shmupo/tweet-app-backend/blob/main/images/PostsLoggedIn.png)

3. Logging In
![Login Page](https://github.com/Shmupo/tweet-app-backend/blob/main/images/LoginPage.png)

4. Logged In
![Logged In Page](https://github.com/Shmupo/tweet-app-backend/blob/main/images/LoginSuccess.png)

5. View Replies While Not Logged In
![Login Page](https://github.com/Shmupo/tweet-app-backend/blob/main/images/ViewPostLoggedOut.png)

6. View Replies While Logged In
![Login Page](https://github.com/Shmupo/tweet-app-backend/blob/main/images/ViewPostLoggedIn.png)

7. Edit Profile Page
![Login Page](https://github.com/Shmupo/tweet-app-backend/blob/main/images/EditProfile.png)

To See the Frontend Implementation, see
[Tweet App Frontend](https://github.com/Shmupo/tweet-app-frontend)


