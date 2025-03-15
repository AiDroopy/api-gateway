FROM quay.io/quarkus/ubi-quarkus-native-s2i:22.3-java17
COPY target/*-runner /application
RUN chmod +x /application
EXPOSE 8081
CMD ["./application", "-Dquarkus.http.port=8081"]