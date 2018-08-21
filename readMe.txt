Running Acceptance Tests
First, make sure your MySQL container is up and running:

$ docker container ls
If it’s not, create a MySQL container:

$ docker container run -d --name mysql -e MYSQL_DATABASE=notepad -e MYSQL_ROOT_PASSWORD=r\
oot -p 3306:3306 mysql:5.7
As you have learned in the Selenium-Grid section, we need to set up the hub, a node to run acceptance tests on Firefox and a node to run acceptance tests on Chrome.

First, set up the Selenium hub:

$ docker container run -d --name selenium-hub -p 4444:4444 selenium/hub:3.4.0
Set up the Chrome node:

$ docker container run -d --name chrome -e HUB_PORT_4444_TCP_ADDR=selenium-hub -e HUB_POR\
T_4444_TCP_PORT=4444 -e DISPLAY=99.0 -e SE_OPTS="-port 5556" --link selenium-hub:selenium\
-hub selenium/node-chrome:3.4.0
Set up the Firefox node:

$ docker container run -d --name firefox -e HUB_PORT_4444_TCP_ADDR=selenium-hub -e HUB_PO\
RT_4444_TCP_PORT=4444 -e DISPLAY=98.0 -e SE_OPTS="-port 5557" --link selenium-hub:seleniu\
m-hub selenium/node-firefox:3.4.0

Go to your browser and navigate to http://localhost:4444/grid/console.

The Notepad stores the notes in a MySQL instance, so it expects the MySQL database to be up and running.
The bellow command starts a MySQL container with a newly created database notepad in it. It also sets up the
mysql root password as root.

(-p <host_port>:<container_port> (map container_port xx on host_port yy))
$ docker run -d --name mysql -e MYSQL_DATABASE=notepad -e MYSQL_ROOT_PASSWORD=root -p 3307:3306 mysql:5.7