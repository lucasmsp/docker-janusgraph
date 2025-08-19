#!/usr/bin/env bash

# Updated by Lucas Ponce, based on JanusGraph Authors entrypoint


/usr/bin/hbase master start &
sleep 10
/usr/bin/hbase regionserver start &
wait
