# JanusGraph in Docker

This repository provides ready-to-use Docker setups for running JanusGraph in two modes:

* Standalone: A single container for quick local development and testing (using a back-storage for test-only).
* Pseudo-Distributed: Separates JanusGraph, HBase, ElasticSearch and Zookeeper into distinct containers for a more realistic (but still local) multi-service architecture.

Example notebooks are provided to help you get started with Gremlin queries and graph traversal operations.


### Example Notebooks

See the notebooks/ folder for interactive Jupyter notebooks covering:

* Connecting to Gremlin Server
* Creating vertices and edges
* Running basic and advanced queries
* Gremlin traversal examples in Python and Groovy

References:
* [JanusGraph Documentation](https://docs.janusgraph.org/)
* [TinkerPop Gremlin](https://tinkerpop.apache.org/)
