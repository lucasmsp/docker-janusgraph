# JanusGraph in Docker (Pseudo-distributed)

This repository contains a minimum configuration to perform some experiments in JanusGraph using a pseudo-distributed configuration.


## How to start:

```bash
docker-compose -f docker-compose.yml up
```

# Definitions

In this configuration, two graphs are defined in HBase, called *graph* and *graph2* (shortcuts for traversal queries respectively as *g1* and *g2*). To interact with these graphs, you can use the gremlin console with `docker exec -it jce-janusgraph bash ./bin/gremlin.sh` (see more details in the standalone folder) or via Python (see the notebooks provided in this repository).


# Data Persistence & Volumes

All important data (JanusGraph, HBase, Zookeeper) is persisted using Docker volumes.



## HBase usefull commands:

Below, I list some useful HBase commands that can be used to verify if the system is working as expected, or for other types of debugging:

1. Access HBase Shell: `docker exec -it jce-hbase hbase shell`
2. Check general HBase status: `status`
3. Check detailed status: `status 'detailed'`
4. List all tables: `list`
5. Create test table with column family: `create 'test', 'data'`
6. Insert some records:
```
put 'test', 'row1', 'data:name', 'John'
put 'test', 'row1', 'data:age', '30'
put 'test', 'row2', 'data:name', 'Maria'
put 'test', 'row2', 'data:age', '25'
```
7. Read a specific row: `get 'test', 'row1'`
8. Full table scan: `scan 'test'`
9. Count records: `count 'test'`


## Zookeeper usefull commands:

HBase depends on Zookeeper, and sometimes the tool can corrupt the HBase key between restarts. If HBase doesn't start correctly (for example, when it can't create tables or insert data), an alternative is to remove the HBase key using the following commands:

```
docker exec -it jce-zookeeper ./bin/zkCli.sh -server localhost:2181

ls /
ls /hbase
rmr /hbase
quit
```