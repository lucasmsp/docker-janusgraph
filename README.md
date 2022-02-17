# JanusGraph in Docker

This repository contains a minimum configuration to perform some experiments in JanusGraph.


## Quickstart

1. Start JanusGraph by using `docker-compose up`;
2. Enter the container (`docker exec -it janusgraph bash`), the started folder is the JanusGraph installation folder, it contains some binaries, configuration folders, and some data as examples.
3. Start the gremlin console: `./bin/gremlin.sh`
```bash
janusgraph@e0a49e7529be:/opt/janusgraph$ ./bin/gremlin.sh

         \,,,/
         (o o)
-----oOOo-(3)-oOOo-----
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/opt/janusgraph/lib/slf4j-log4j12-1.7.30.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/opt/janusgraph/lib/logback-classic-1.1.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
plugin activated: tinkerpop.server
plugin activated: tinkerpop.tinkergraph
12:50:34 WARN  org.apache.hadoop.util.NativeCodeLoader  - Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
plugin activated: tinkerpop.hadoop
plugin activated: tinkerpop.spark
plugin activated: tinkerpop.utilities
plugin activated: janusgraph.imports
gremlin> 
```
4. Establish the connection between Tinkerpop and JanusGraph.
```bash
gremlin> :remote connect tinkerpop.server conf/remote.yaml session
==>Configured localhost/127.0.0.1:8182-[37cd3387-55bf-4d76-94c6-a7de07c6d9fe]
```
5. Enable the console to remote execution (useful when you use more than one container);
```bash
gremlin> :remote console
==>All scripts will now be sent to Gremlin Server - [localhost/127.0.0.1:8182]-[37cd3387-55bf-4d76-94c6-a7de07c6d9fe] - type ':remote console' to return to local mode
```

6. Check the connection with the default graph. This result shows that you are using BerkeleyDB as storage backend.
```bash
gremlin> graph
==>standardjanusgraph[berkeleyje:/var/lib/janusgraph/data]
```

7. You can import the Air routes graph as an example, available in the data folder.
```bash
gremlin> graph.io(graphml()).readGraph('/opt/janusgraph/data/air-routes.graphml')
```
8. Some queries could be  `g.V().count()` to count the vertices number or list all vertices with specific label `g.V().hasLabel('airport').valueMap().toList()`. More examples available in [Tinkerpop Documentation](http://www.kelvinlawrence.net/book/PracticalGremlin.html).



# Interacting in Python

You can also interact with gremlin using a remote Python environment. First, install `gremlinpython` module using `pip install gremlinpython`. A basic example is shown below.

```python

from gremlin_python.process.anonymous_traversal import traversal
from gremlin_python.driver.driver_remote_connection import DriverRemoteConnection

g = traversal().withRemote(DriverRemoteConnection('ws://localhost:8182/gremlin','g'))

g.V().hasLabel('airport').valueMap().toList()
```

