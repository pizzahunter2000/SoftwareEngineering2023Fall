# SoftwareEngineering2023Fall

### Contents
1. Description
2. Requirements

## 1. Descripition
This Java app simulates a ticket webshop of a train company. The user can search train connections from one station to another and receives a selection of possible travel itinaries.

The user can choose from the routes and place an order to buy the specific ticket. They have the option to introduce a discount and the app calculates the ticket's price according to the length of the route (in distance) and the discount.

On the other hand, the admin can introduce new routes (and new stations) and new discount types.

## 2. Requirements

2.1. Use Cases

The application has 2 actors: user and admin. They can perform the following actions, shown on the __use case__ diagram:

![Alt text](usecase.png)

2.2. Data-Flow

The process of using the app is illustrated on the __data-flow diagram__ above:

![Alt text](dfd.png)

2.3. State Diagram

To demonstrate the transitions between different pages and states of the app, look at the following __state diagram__:

![Alt text](state.png)

## 3. Design

At the base of the application's design stands the following class diagram.

![Alt text](class.png)

At package level, we've used _MVC_, which stands for __Model, View, Controller__. This is a project structure principle that separates classes that represent models (shown on the class diagram, data structures in the background), views (pages of the app, used for display) and controller - a layer that connects model and view.

Due to the simplicity, there is no controller layer, that would be an important change that had to be made if the development of the app would advance.

The structure of the code is the shown in the following image, demonstrating the package structure descibed above:

![Alt text](structure.png)

#### 3.1. Model Design

In the _model_ package there are classes that represent real-life concrete objects, namely:
- Connection: containing 2 stations (departing and arriving), distance, train and date of departure and arrival.
- DiscountList: resembles an enumeration, however, each element has a name and a value between 0 and 100, representing the discount in percentage.
- Network: train network, consisting of connections.
- Point: position of a station on a map, its attributes are x and y (coordinates).
- Station: has a point and a name.
- Train: in this case has only an ID, and has no importance in this version of the app.

On the other hand, there are class for algorithms:
- Graph: contains all the functions working on the rail network. This means shortest path algorithm, convertion from network to adjacency list, adding connection, calculating distance and price between 2 stations that are not necessarily directly connected.
- Pair: value pair of Station and distance from the source station, used in the shortest path algorithm.

#### 3.2. View Design