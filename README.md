## Micronaut 3.7.3 Documentation

- [User Guide](https://docs.micronaut.io/3.7.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature rxjava3 documentation

- [Micronaut RxJava 3 documentation](https://micronaut-projects.github.io/micronaut-rxjava3/snapshot/guide/index.html)


## Feature security-jwt documentation

- [Micronaut Security JWT documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)


## Feature hibernate-validator documentation

- [Micronaut Hibernate Validator documentation](https://micronaut-projects.github.io/micronaut-hibernate-validator/latest/guide/index.html)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Testing login

curl -X "POST" "http://localhost:8123/login" -H 'Content-Type: application/json' -d $'{"username": "admin","password": "1234"}'


# mnlon


curl -X 'POST' \
  'http://localhost:8123/login' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{ 
  "username":"admin",
  "password": "1234"
}'


Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiZW1haWwiOiJhZG1AZ2cuY29tIiwidHlwZUxvbiI6IkFETSIsInBrZXkiOiJhZG1pbiIsImlkIjoxLCJpYXQiOjE2NzM1MzQ2NDUsImV4cCI6MTY3MzU0NTQ0NSwiaXNzIjoiTE9OVlgifQ.-mnGxrZY2byrdhmn3vPhoPQ57ZwtJ_fCfVtio6zuks8


curl -kv  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibmJmIjoxNjczNTM0NTI2LCJUWVBFTE9OIjoiQURNIiwicm9sZXMiOlsiQSIsIkIiXSwiaXNzIjoibW5sb24iLCJJRCI6MSwiZXhwIjoxNjczNTM4MTI2LCJpYXQiOjE2NzM1MzQ1MjZ9.0b9BrvPntw48vJTuyExdA3sraJgUsc7tu8niB7sR85A'  http://localhost:8123/pg/base

curl -X 'POST' \
  'http://localhost:8080/base' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibmJmIjoxNjczNTM0NTI2LCJUWVBFTE9OIjoiQURNIiwicm9sZXMiOlsiQSIsIkIiXSwiaXNzIjoibW5sb24iLCJJRCI6MSwiZXhwIjoxNjczNTM4MTI2LCJpYXQiOjE2NzM1MzQ1MjZ9.0b9BrvPntw48vJTuyExdA3sraJgUsc7tu8niB7sR85A' \
  -d '{
  "pkey": "string",
  "description": "string",
  "pname": "string",
  "type": "string"
}'





