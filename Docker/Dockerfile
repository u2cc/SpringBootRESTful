FROM openjdk:12
#8-jre-alpine alpine has minimum footprint therefore even certain shell cmd is n/a for example groupadd
# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY ./SpringBootRESTful-0.0.1.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variable


RUN groupadd -r app && useradd -r -g app -d /home/app -s /sbin/nologin -c "Docker image user" app

RUN chown -R app:app /app

USER app

# Run app.py when the container launches
CMD ["java", "-jar", "SpringBootRESTful-0.0.1.jar"]