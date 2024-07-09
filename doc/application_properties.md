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
```
