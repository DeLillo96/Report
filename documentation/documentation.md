# Index #  
- 1 [Description](#1-description)
    - 1.1 [Goal](#11-goal)
    - 1.2 [Demands](#12-demands)
        - 1.2.1 [Employees](#121-employees)
        - 1.2.2 [Projects](#122-projects)
        - 1.2.3 [Customers](#123-customers)
        - 1.2.4 [Reports](#124-reports)
- 2 [Technology](#2-technology)
    - 2.1 [Languages](#21-languages)
    - 2.2 [Framework and Libraries](#22-framework-and-libraries)
    - 2.3 [Tools](#23-tools)
- 3 [Installation](#3-installation)
- 4 [Data](#4-data)
    - 4.1 [Management](#41-management)
    - 4.2 [ER Diagram](#42-er-diagram)
    - 4.3 [Location](#43-location)
    - 4.4 [Dump](#44-dump)
- 5 [Architecture](#5-architecture)
    - 5.1 [Description](#51-description)
    - 5.2 [Protocols](#52-protocols)
    - 5.3 [Communication](#53-communication)
- 6 [Maven](#6-maven)
    - 6.1 [JUnit Jupiter](#61-junit-jupiter)
    - 6.2 [Hibernate](#62-hibernate)
    - 6.3 [PostgreSQL](#63-postgresql)
    - 6.4 [json-simple](#64-json-simple)
    - 6.5 [Jackson](#65-jackson)
- 7 [Server](#7-server)
    - 7.1 [Result](#71-result)
    - 7.2 [SessionManager](#72-sessionmanager)
    - 7.3 [Entities](#73-entity)
        - 7.3.1 [Class Diagram](#731-class-diagram)
        - 7.3.2 [AbstractEntity](#732-abstractentity)
        - 7.3.3 [Attributes](#733-attributes)
        - 7.3.4 [Relations](#734-relations)
        - 7.3.5 [Filters](#735-filters)
        - 7.3.6 [Inheritance](#736-inheritance)
    - 7.4 [Repository](#74-repository)
        - 7.4.1 [Class Diagram](#741-class-diagram)
        - 7.4.2 [AbstractRepository](#742-abstractrepository)
        - 7.4.3 [Extensions](#743-extensions)
    - 7.5 [Remote](#75-remote)
        - 7.5.1 [Class Diagram](#751-class-diagram)
        - 7.5.2 [BaseService](#752-baseservice)
        - 7.5.3 [RelationService](#753-relationservice)
        - 7.5.4 [UserService](#754-userservice)
    - 7.6 [RemoteManager](#76-remotemanager)
- 8 [Client](#8-client)
    - 8.1 [Views](#81-views)
    - 8.2 [Controllers](#82-controllers)
        - 8.2.1 [Class Diagram](#821-class-diagram)
        - 8.2.2 [TableController](#822-tablecontroller)
    - 8.3 [Models](#83-models)
        - 8.3.1 [RowModel](#831-rowmodel)
    - 8.4 [Managers](#84-managers)
        - 8.4.1 [ControllerManager](#841-controllermanager)
        - 8.4.2 [RemoteManager](#842-remotemanager)
        - 8.4.3 [ViewsManager](#843-viewsmanager)

# 1 Description #  

## 1.1 Goal ##  
Reporting management system to make it easy the overview in the company project's and the employees related.  

## 1.2 Demands ##  

### 1.2.1 Employees ###  
Management personal data of **employees**, be sure to save the **name**, **surname** and **fiscal code**.  

### 1.2.2 Projects ###  
Management of **projects** with **code** and **expiry date**, including the related **types of tasks** and **customers** that compose them.  

### 1.2.3 Customers ###  
Management of **customers** with **code** of the company.  

### 1.2.4 Reports ###  
Every **employee** must have the possibility to insert his work **report** with the hours or cost, **project**, **customer** and **task** he has performed.  

# 2 Technology #  

## 2.1 Languages ##  
- Java  
- XML  
- CSS  

## 2.2 Framework and Libraries ##  
For the list of library and framework dependencies, look at the `pom.xml` file, managed by Maven.  

## 2.3 Tools ##  
- Docker  
- Postgres  
- Maven  
- Java FX (integrated in Java V.8)  

## 3 Installation ##
Follow the steps:  
1) Download or Clone the source code (https://github.com/DeLillo96/Report).  
2) On the machine that will host the database install one of the programs listed below  
    - Docker (https://www.docker.com/products) then run `up.sh` or run the command `docker-compose -p <container name> up -d` to start the database service  
    - Postgres (https://www.postgresql.org/download/) then:  
        1) Run the command `$sudo -u postgres psql` or just run the Postgres shell  
        2) Run the following commands:            
```SQL
CREATE DATABASE application;
CREATE USER admin WITH ENCRYPTED PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE application TO admin;
```
Obviously the name of database "**application**", the user "**admin**" and the password "**admin**" can be changed.  
If you change them remember it, because Hibernate needs that information to work.

3) Install Java SE (https://www.oracle.com/technetwork/java/javase/downloads) on the Client and Server machine, version 8 is required.  
4) On the Server run `Server.java`  
    If any of the database default data has been changed, remember to edit them in the `hibernate.cfg.xml` file.  

5) On the Clients run `Client.java`  

# 4 Data #  

## 4.1 Management ##  
For the creation of the database was used Hibernate, a distributed framework that provides ORM services, 
that allow the management of data persistence on a relational database, Postgres in this case.
  
## 4.2 ER Diagram ##  
![](resurces/images/er.jpg)  

## 4.3 Location ##  
If you use docker the database is located in a container that is turned on using the `up.sh` command and turns off using the `down.sh` command.  

## 4.4 DUMP ##  
To recover the database from a dump file is possible use `restore_db.sh` command, make sure the docker container is up.  

# 5 Architecture #

## 5.1 Description #
The project has been developed with a client-server architecture.  
The only common part between client and server is the Shared module that will be compiled by both actors.

## 5.2 Protocols ##
- RMI _(port 1099)_

## 5.3 Communication ##
The exchange of information and data between client and server of both protocols is done via JSON format.

# 6 Maven #

## 6.1 JUnit Jupiter ##
**JUnit** is used for unit tests, very convenient for the @BeforeAll and @AfterAll functions in order to prepare and clean the database before and after the relative tests.

## 6.2 Hibernate ##
**Hibernate** is a distributed framework that provides ORM services that allow the management of the persistence of data on the database itself through the representation and maintenance of a Java object system (called Entity) on a relational database.

## 6.3 PostgreSQL ##
**PostgreSQL** in this case is only the driver that interfaces the physical database to java, and is used only by **Hibernate**.

## 6.4 json-simple ##
**json-simple** is a library that is used for parsing classes in json and vice versa for sending data from server to client and vice versa.

## 6.5 Jackson ##
**Jackson-mapper** is a library that is used to transform a data in JSON format into a class.

# 7 Server #

## 7.1 Result ##
This class is used to communicate DB operations' results.
Contains the result (true or false), a list of messages linked to the result and a list of data used in operations if needed.
Is very useful for its function that transforms the data it contains into a readable JSON object.

## 7.2 SessionManager ##
Is used Singleton Design Pattern.
It is used to always have the hibernate session available to every entity for database operations.

## 7.3 Entity ##
Is used Abstract Factory Design Pattern.

### 7.3.1 Class Diagram ###
![](resurces/images/entitiesPattern.png)  

### 7.3.2 AbstractEntity ###
The class implements the interface's save and delete methods. 
Entities have methods of creating, updating and deleting from CRUD methods.
This class also has the methods of before and after the save and delete operations for modeling the data if necessary.

### 7.3.3 Attributes ###
Each class that extends the AbstractEntity will have attributes that are correlated by Hibernate to the columns of the database table which the class is connected.

### 7.3.4 Relations ###
They are attributes of type entity, so as to simulate the relation in the database also between the classes.
You can also specify cascadeType to perform the elimination of relation.
- **OneToOne:** Hibernate creates a column and a foreign key related to the primary key of the attribute OneToOne.  

Example class Person:  
```java
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Users user;
```
- **ManyToOne / OneToMany:** Hibernate creates a column and a foreign key related to the primary key of the attribute ManyToOne.  
It is very important to specify the class that will have the foreign key, it is not necessary that the related class also has the OneToMany attribute (but it is strongly recommended).  

Example class Project:  
```java
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer")
    private Customer customer;
```
Example class Customer:  
```java
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Project> projects = new HashSet<>();
```

- **ManyToMany:** Hibernate creates a table with the two columns which contains the IDs of the related classes.  

Example class Project: 
```java
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "have",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private Set<Task> tasks = new HashSet<>();  
```

Example class Task:  
```java
    @ManyToMany(mappedBy = "tasks", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Project> projects = new HashSet<>();
```
 
### 7.3.5 Filters ###
The filters are written in the entities because they have the interface with the database, but the Read operations are implemented by the Repositories.  
Example class Person:
```java
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "code", parameters = @ParamDef(name = "code", type = "string")),
        @FilterDef(name = "description", parameters = @ParamDef(name = "description", type = "string")),
        @FilterDef(name = "expireFrom", parameters = {@ParamDef(name = "expireFrom", type = "date")}),
        @FilterDef(name = "expireTo", parameters = {@ParamDef(name = "expireTo", type = "date")}),
        @FilterDef(name = "customer", parameters = {@ParamDef(name = "customer", type = "integer")}),
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "code", condition = "code like '%' || :code || '%'"),
        @Filter(name = "description", condition = "description like '%' || :description || '%'"),
        @Filter(name = "expireFrom", condition = "expire >= :expireFrom"),
        @Filter(name = "expireTo", condition = "expire <= :expireTo"),
        @Filter(name = "customer", condition = "customer = :customer")
})
```
### 7.3.6 Inheritance ###
Inheritance is an important feature of Generalization and Specialization. It allows lower-level entities to inherit the attributes of higher-level entities.  
For example, the attributes of a Person class such as name, fiscal code, and telephone can be inherited by lower-level entities such as Employee or CustomerContact.
Another example is in Task, which is differentiated by category Time or Cost.

## 7.4 Repository ##
Is used Abstract Factory Design Pattern.

### 7.4.1 Class Diagram ###
![](resurces/images/repositoryPattern.png)  

### 7.4.2 AbstractRepository ###
The class implements the interface's read methods. 
Repositories have methods of reading and searching from CRUD methods.
There is the possibility with this class to use a custom query instead of the query fixed only to the table of interest (query language is not SQL but HQL).

### 7.4.3 Extensions ###
Each class that extends the abstract class must have the name of the table related, it is also possible to write some researches if necessary.  
Example class UsersRepository:  
```java
public Users getUserByUsername(String username) {
    HashMap<String, Object> params = new HashMap<>();
    params.put("username", username);
    List users = read(params);

    return users != null && users.size() == 1 ? (Users) users.get(0) : null;
}
```

## 7.5 Remote ##
Is used Abstract Factory Design Pattern.

### 7.5.1 Class Diagram ###
![](resurces/images/remotePattern.png)  

### 7.5.2 BaseService ###
The BaseService is a class for communication between client and server, it receives data in json format and involves the repository, or the related entity for the various CRUD operations requested by the client.  

### 7.5.3 RelationService ###
The RelationService has the same principle as the BaseService but works only on readings, assignments and assignments of relation, not only on single tables.  

### 7.5.4 UserService ###
It is an extension of the base service, it is a special case because it implements the login and logout methods.  

## 7.6 RemoteManager ##
The RemoteManager is a class that initializes the implementations of the different services mentioned [above](#85-remote) and creates the bind between these objects and the RMI services.  
This operation is done at the beginning of the server execution.  

# 8 Client #
For the part of the GUI the Model View Controller Design Pattern was chosen.  
Each controller will have access to the service made available by the server through the RMI protocol.  
The Client has no logical part except the management of the GUI, everything is done inside the Server and the result is notified in the GUI itself.

## 8.1 Views ##
The views were built with the FXML format, in each view the controller is defined, which will manage events and objects GUI.  
A default style has been applied to all views of the project, defined in the `default.css` file.  

## 8.2 Controllers ##
Is used Abstract Factory Design Pattern.

### 8.2.1 Class Diagram ###
![](resurces/images/controllerPattern.png)  

### 8.2.2 TableController ###
The tableController is a particular controller, a table is managed with its own filters and populated through the specified service.  
Each implementation of the AbstractTableController must define the filters and the RowModel which manages the table row.  

## 8.3 Models ##
Is used Abstract Factory Design Pattern.
All the models in the project are of the RowModel type except for CalendarDay which manages the calendar cell.  

### 8.3.1 RowModel ###
The RowModel object is strictly related to its table, the raw data live within this model, so the Save and Delete methods are implemented by the RowModel and not in the TableController.  

## 8.4 Managers ##
For All manager is used Singleton Design Pattern.

### 8.4.1 ControllerManager ###
It is a Singleton class used to have the IDs of the employee who has accessed the platform.  

### 8.4.2 RemoteManager ###
It is a Singleton class used to connect to the RMI services provided by the Server.  

### 8.4.3 ViewsManager ###
Is a singleton class used to manege, render Views, Popups and Notifications.  