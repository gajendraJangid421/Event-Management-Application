# DATABASE MIGRATION USING LIQUIBASE

## Step 1: Include following dependency

```xml

		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>

```

## Step 2: Create a liquibase.properties file in resources/liquibase.properties

```
	url=${jdbc:mysql://localhost:3306/event-management-database}
	username=${root}
	password=${adminGaj}
	changeLogFile=src/main/resources/Sql/changelog-master.xml
```


## Step 3: Create a folder in resources/Sql

###  Include changelog-master.xml in resources/Sql

```xml
	
	<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.2.xsd">

    	<include file="Sql/changelog-users-table.sql"/>
    
	</databaseChangeLog>
```
Note: Update all the changelog files in <include " "/> above

## Step 4: Now register all the changes you want to make to database in .sql files in resources/Sql/changelog-users-table.sql

```roomsql

	--liquibase formatted sql

	--changeset username:1

	CREATE TABLE IF NOT EXISTS `users` (
 	`id` varchar(255) NOT NULL,
  	`email` varchar(255) DEFAULT NULL,
 	`full_name` varchar(255) DEFAULT NULL,
  	`mobile_number` varchar(255) DEFAULT NULL,
  	`password` varchar(255) DEFAULT NULL,
  	`role` tinyint DEFAULT NULL,
  	`username` varchar(255) DEFAULT NULL,
  	PRIMARY KEY (`id`),
  	CONSTRAINT `users_chk_1` CHECK ((`role` between 0 and 1))
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```


## Step 5: Make changes in the application.properties

	spring.jpa.defer-datasource-initialization=false
	spring.liquibase.change-log=classpath:Sql/changelog-master.xml
	spring.liquibase.enabled=true

Make sure the auto ddl update is off
------------------------------------------------------------------------------------------------------------------------
	