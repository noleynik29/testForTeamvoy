Test task application for Teamvoy

Fullstack online marketplace application.
Frontend: HTML, Thymeleaf
Backend: Java, Spring Boot, Spring Security
Database: H2, Hibernate

Clone application, launch, enter http://localhost:8080/ in browser.

![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/bc6c37c7-6068-4214-a572-6ea791173811)

For admin auth use 
login: admin
password: admin

![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/39fd29c9-c150-4d24-a95c-6540df71af19)
![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/e6868e67-8c13-40af-9f27-162ad816058c)

Admin can see products on /products page, and follow the /addProduct link to add new product.

For user auth use
login: user
password: user

![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/c0e4612e-676c-41f8-8347-de4c49104c28)

User can see products, buy them, set their quanntities in cart and order them. Total price in cart changes dynamicly if you change product quantity.
At the /checkout endpoint you can see 10 minute timer that will delete order if it's not "payed".

![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/23f8e614-168d-4359-aa0f-f951baf99a09)

To "pay" for the order just click Pay button - order will be payed and will not be deleted.

![image](https://github.com/noleynik29/testForTeamvoy/assets/71104368/42a9855c-11f7-4ab0-87b1-529e758a173a)
