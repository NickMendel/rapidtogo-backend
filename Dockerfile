FROM openjdk:17-jdk-alpine

LABEL authors="Nick Mendel"

# Using a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# adding a volume to /tmp
VOLUME /tmp

# Set the working directory
WORKDIR /rapidtogo-backend

# Copying the jar file into the container
copy build/libs/*.jar app.jar

# Exposing the application port
EXPOSE 5000

# Using environment variables for flexibility
ENV JAVA_OPTS=""

# Adding Health Check to ensure the application is running
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/act/health || exit 1

# Running the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]