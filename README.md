# redis-demo

**NOTES**
1. Need to add serializers for redis cache key and value
2. Need to implement `java.io.Serializable` interface for every POJO
3. By default, redis server runs on port 6379
4. Basic redis commands:
   * To start redis server: `sudo service redis-server start`
   * To stop redis server: `sudo service redis-server stop`
   * To open redis-cli: `redis-cli`