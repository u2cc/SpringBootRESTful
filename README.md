# SpringBootRESTful

This is a practice project of implementing RESTful endpoints using SpringBoot.

Patch operation Json example:
[{
"op":"replace",
"path":"/color",
"value":"red"
}]

After adding Spring Security in cp, there is an immediate effect of requiring login credential when accessing all the 
exposed endpoints. The username is user and the password can be found in the console after "Using generated 
security password". To statically configure the password, use spring.security.user.password=Test12345_

Swagger UI url: http://localhost:9090/swagger-ui-diecastcars.html



