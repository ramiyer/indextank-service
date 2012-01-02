<<<<<<< HEAD
About IndexTank Service
=======================

IndexTank Service (http://indextank.com) contains the source code for implementing the Search-as-a-Service platform. It contains the components that allow managing user accounts, server instances (worker) and index instances (deploy); it depends on IndexTank Engine (https://github.com/linkedin/indextank-engine) binary.
=======
About IndexTank Engine
======================

This project contains IndexTank (http://indextank.com) search engine implementation.
Includes features like variables (boosts), categories (facets), faceted search, snippeting, custom scoring functions, suggest, and autocomplete.
>>>>>>> upstream/master

### Homepage:

Find out more about at: TBD

### License:

Apache Public License (APL) 2.0

<<<<<<< HEAD
### Components:

1. API
2. Backoffice
3. Storefront
4. Nebu

### Dependencies:

* Django tested with 1.2.7
* Python tested with 2.6.6
* Java(TM) SE Runtime Environment tested with build 1.6.0_24-b07
* nginx
* uWSGI (http://projects.unbit.it/uwsgi/)
* MySQL
* daemontools (http://cr.yp.to/daemontools.html)
* Thrift library (generated sources with version 0.5.0 are provided)

### Getting started:

1. Create the database schema (python manage.py syncdb).
2. Create an account.
3. Start an index instance (IndexTank Engine).
4. Start API.

## API

Django application implementing the REST JSON API, enables index management per account, indexing functions and search. Interacts via Thrift with specific index instances (deploy).

## Backoffice

Django application that allows manual administration.

## Storefront

Django application with the service web, contains the user registration form (allows creating accounts).

## Nebu

Index, deploy & worker manager. A worker (server instance) may contain a deploy (index instance) or many. 
=======
### Artifacts:

1. indextank-engine-1.0.0.jar <-- core library

### Maven:

groupId: com.flaptor.indextank

artifactId: indextank-engine

version: 1.0.0

Package generation:
-------------------

Build a single jar containing all dependencies by:

    $ mvn package assembly:single

This will create a single file in:

    target/indextank-engine-1.0.0-jar-with-dependencies.jar

Quick start with the standalone REST API
----------------------------------------

You can try basic indexing and searching

Main class: com.flaptor.indextank.api.Launcher

After running the package generation:

    $ java -cp target/indextank-engine-1.0.0-jar-with-dependencies.jar com.flaptor.indextank.api.Launcher

This command starts an API server (http://www.indextank.com/documentation/api) at port 20220.
The indexing and searching can be done with any client or for example, via curl:

    $ curl -d "{\"docid\":\"post1\", \"fields\":{\"text\":\"I love Fallout\"}}" -v -X PUT http://localhost:20220/v1/indexes/idx/docs

    $ curl -d "{\"docid\":\"post2\", \"fields\":{\"text\":\"I love Planescape\"}}" -v -X PUT http://localhost:20220/v1/indexes/idx/docs

    $ curl http://localhost:20220/v1/indexes/idx/search?q=love

API Clients (compatible with the embedded API)
----------------------------------------------

Java: https://github.com/flaptor/indextank-java

Python: https://github.com/flaptor/indextank-py

Ruby: https://github.com/flaptor/indextank-rb

PHP: https://github.com/flaptor/indextank-php

Thrift API sample configuration:
--------------------------------

Main class: com.flaptor.indextank.index.IndexEngine

VM args:

    -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Dorg.apache.lucene.FSDirectory.class=org.apache.lucene.store.MMapDirectory -Xmx600M

Program args:

    --facets --rti-size 500 --conf-file sample-engine-config --port 20220 --environment-prefix TEST --recover --dir index --snippets --suggest documents --boosts 3 --index-code dgmqn --functions 0:-age

Sample engine configuration file contents:

    {
    "max_variables": 3, 
    "functions": {"0": "-age"}, 
    "index_code": "dgmqn", 
    "allows_facets": true, 
    "ram": 600, 
    "log_server_host": "index123.localhost", 
    "autocomplete": true,
    "log_server_port": 15100, 
    "autocomplete_type": "documents",
    "allows_snippets": true, 
    "rti_size": 500, 
    "facets_bits": 5, 
    "base_port": 20220, 
    "log_based_storage": false, 
    "xmx": 600
    }


Eclipse:
--------

Set up Eclipse for this project by executing the command below:

mvn eclipse:eclipse

Inside Eclipse, select Preferences > Java > Build Path > Classpath Variables. Define a new classpath variable M2_REPO and assign maven repository.

For more information, check out http://maven.apache.org/guides/mini/guide-ide-eclipse.html
>>>>>>> upstream/master

