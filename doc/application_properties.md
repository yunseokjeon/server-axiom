
resources/application.properties

```
spring.application.name=server-axiom

# Spring Batch Job enable
spring.batch.job.enabled=true

# H2
# http://localhost:8080/h2-console
spring.h2.console.enabled=true
#spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.url=jdbc:h2:mem:db;MODE=MYSQL;
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Hibernate
spring.jpa.hibernate.ddl-auto = create
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

# AWS
cloud.aws.credentials.access-key:
cloud.aws.credentials.secret-key:
cloud.aws.region.static:ap-southeast-2
cloud.aws.stack.auto:false
cloud.aws.s3.file-name:negative_review.csv
cloud.aws.s3.bucket-name:data-loom
```
