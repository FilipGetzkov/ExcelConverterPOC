# ExcelConverterPOC
This is simple spring project with apache poi integration. Its transfer data from excel to sql DB.

Prerequisite:
- Java 17
- Maven
- Spring
- Hibernate
- Apache POI
- Azure sql edge/ Ms sql server

**Building for the first time:**
1. There is a [create sql script](https://github.com/FilipGetzkov/ExcelConverterPOC/blob/master/createDBScript.sql) for the database. I personaly use Azure sql edge. You need to run the script in your database at first.
2. In project we have [application.properties](https://github.com/FilipGetzkov/ExcelConverterPOC/blob/master/src/main/resources/application.properties) file, you should change it with your credentials from your database.
3. Build and run
