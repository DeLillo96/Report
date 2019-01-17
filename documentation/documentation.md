# Index #
1. [Technology](#1-technology)  
    1.1 [Languages](#11-languages)  
    1.2 [Framework and Libraries](#12-framework-and-libraries)  
    1.3 [Tools](#13-tools)
2. [Data](#2-data)  
    2.1 [Management](#21-management)  
    2.2 [ER Diagram](#22-er-diagram)  
3. [Maven](#3-maven)  
    3.1 [JUnit Jupiter](#31-junit-jupiter)  
    3.2 [Hibernate](#32-hibernate)  
    3.3 [PostgreSQL](#33-postgresql)  
    3.4 [json-simple](#34-json-simple)  
    3.5 [Jackson](#35-jackson)
4. [Server](#4-server)  
    4.1 [Result](#41-result)  
    4.2 [SessionManager](#42-sessionmanager)  
    4.3 [Entities](#43-entity)  
        4.3.1 [Class Diagram](#431-class-diagram)  
        4.3.2 [AbstractEntity](#432-abstractentity)  
        4.3.3 [Attributes](#433-attributes)  
        4.3.4 [Relations](#434-relations)  
        4.3.5 [Filters](#435-filters)  
        4.3.6 [Inheritance](#436-inheritance)  
    4.4 [Repository](#44-repository)  
        4.4.1 [Class Diagram](#441-class-diagram)  
        4.4.2 [AbstractRepository](#442-abstractrepository)  
        4.4.3 [Extensions](#443-extensions)  
    4.5 [Remote](#45-remote)  
        4.5.1 [Class Diagram](#451-class-diagram)  
        4.5.2 [BaseService](#452-baseservice)  
        4.5.3 [RelationService](#453-relationservice)  
        4.5.4 [UserService](#454-userservice)  
    4.6 [RemoteManager](#46-remotemanager)
5. [Client](#5-client)  
    5.1 [Views](#51-views)  
    5.2 [Controllers](#52-controllers)
        5.2.1 [Class Diagram](#521-class-diagram)
        5.2.2 [TableController](#522-tablecontroller)  
    5.3 [Models](#53-models)  
        5.3.1 [RowModel](#531-rowmodel)  
    5.4 [Managers](#54-managers)  
        5.4.1 [ControllerManager](#541-controllermanager)  
        5.4.2 [RemoteManager](#542-remotemanager)  
        5.4.3 [ViewsManager](#543-viewsmanager)  

---
# 1 Technology #
## 1.1 Languages ##  
- Java  
- HQL  
- XML  
- CSS  
## 1.2 Framework and Libraries ##  
For the list of library and framework dependencies, look at the `pom.xml` file, managed by Maven.
## 1.3 Tools ##  
- Docker  
- Postgres  
- Maven
---
# 2 Data #
## 2.1 Management ##  
For the creation of the database was used Hibernate, a distributed framework that provides ORM services, 
that allow the management of data persistence on a relational database, Postgres in this case.   
## 2.2 ER Diagram ##  
![Diagram](resurces/images/er.jpg)  

---
# 3 Maven #
## 3.1 JUnit Jupiter ##
**JUnit** is used for unit tests, very convenient for the @BeforeAll and @AfterAll functions in order to prepare and clean the database before and after the relative tests.
## 3.2 Hibernate ##
**Hibernate** is a distributed framework that provides ORM services that allow the management of the persistence of data on the database itself through the representation and maintenance of a Java object system (called Entity) on a relational database.
## 3.3 PostgreSQL ##
**PostgreSQL** in this case is only the driver that interfaces the physical database to java, and is used only by **Hibernate**.
## 3.4 json-simple ##
**json-simple** is a library that is used for parsing classes in json and vice versa for sending data from server to client and vice versa.
## 3.5 Jackson ##
**Jackson-mapper** is a library that is used to transform a data in JSON format into a class.

---
# 4 Server #
## 4.1 Result ##
This class is used to communicate DB operations' results.
Contains the result (true or false), a list of messages linked to the result and a list of data used in operations if needed.
Is very useful for its function that transforms the data it contains into a readable JSON object.
## 4.2 SessionManager ##
Singleton class, it is used to always have the hibernate session available to every entity for database operations.
## 4.3 Entity ##
### 4.3.1 Class Diagram ###
![Icon](resurces/images/entitiesPattern.png)  
### 4.3.2 AbstractEntity ###
The class implements the interface's save and delete methods. 
Entities have methods of creating, updating and deleting from CRUD methods.
This class also has the methods of before and after the save and delete operations for modeling the data if necessary.
### 4.3.3 Attributes ###
Each class that extends the AbstractEntity will have attributes that are correlated by Hibernate to the columns of the database table which the class is connected.
### 4.3.4 Relations ###
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
 
### 4.3.5 Filters ###
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
### 4.3.6 Inheritance ###
Inheritance is an important feature of Generalization and Specialization. It allows lower-level entities to inherit the attributes of higher-level entities.  
For example, the attributes of a Person class such as name, fiscal code, and telephone can be inherited by lower-level entities such as Employee or CustomerContact.
Another example is in Task, which is differentiated by category Time or Cost.

## 4.4 Repository ##
### 4.4.1 Class Diagram ###
![Icon](resurces/images/repositoryPattern.png)  
### 4.4.2 AbstractRepository ###
The class implements the interface's read methods. 
Repositories have methods of reading and searching from CRUD methods.
There is the possibility with this class to use a custom query instead of the query fixed only to the table of interest (query language is not SQL but HQL).
### 4.4.3 Extensions ###
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

## 4.5 Remote ##
### 4.5.1 Class Diagram ###
![Icon](resurces/images/remotePattern.png)  
### 4.5.2 BaseService ###
The BaseService is a class for communication between client and server, it receives data in json format and involves the repository, or the related entity for the various CRUD operations requested by the client.  
### 4.5.3 RelationService ###
The RelationService has the same principle as the BaseService but works only on readings, assignments and assignments of relation, not only on single tables.  
### 4.5.4 UserService ###
It is an extension of the base service, it is a special case because it implements the login and logout methods.  

## 4.6 RemoteManager ##
The RemoteManager is a class that initializes the implementations of the different services mentioned [above](#45-remote) and creates the bind between these objects and the RMI services.  
This operation is done at the beginning of the server execution.  

---
# 5 Client #
For the part of the GUI the Model View Controller Design Pattern was chosen.  
Each controller will have access to the service made available by the server through the RMI protocol.  
The Client has no logical part except the management of the GUI, everything is done inside the Server and the result is notified in the GUI itself.

## 5.1 Views ##
The views were built with the FXML format, in each view the controller is defined, which will manage events and objects GUI.  
A default style has been applied to all views of the project, defined in the `default.css` file.  

## 5.2 Controllers ##
### 5.2.1 Class Diagram ###
![Icon](resurces/images/controllerPattern.png)  
### 5.2.2 TableController ###
The tableController is a particular controller, a table is managed with its own filters and populated through the specified service.  
Each implementation of the AbstractTableController must define the filters and the RowModel which manages the table row.  

## 5.3 Models ##
All the models in the project are of the RowModel type except for CalendarDay which manages the calendar cell.  
### 5.3.1 RowModel ###
The RowModel object is strictly related to its table, the raw data live within this model, so the Save and Delete methods are implemented by the RowModel and not in the TableController.  

## 5.4 Managers ##
### 5.4.1 ControllerManager ###
It is a Singleton class used to have the IDs of the employee who has accessed the platform.  
### 5.4.2 RemoteManager ###
It is a Singleton class used to connect to the RMI services provided by the Server.  
### 5.4.3 ViewsManager ###
Is a singleton class used to manege, render Views, Popups and Notifications.  

---