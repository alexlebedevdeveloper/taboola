Part II
Easy solution for the issue will be CQL or actually Redis ( which costs more than Casandra, however has it advantages on speed access to data).
In both this use cases usages we can enable straight connection to RMap or CQL and get data in no time.
This provide us almost always up to date data except corner cases when data will updated at the same time( which is anyway handled by syncing in Redis and in CQL).
The case when this can happen when we updated disc data and not yet cache data. However we also can invalidate straight ahead teh value in cache before we update to teh new value 
which solved this corner case.
Advantages of speed are visible, however it cost good amount of money ( Redis especially) 
Part I
Solution possible:
1) Remove TTL or increase this to the appropriate time for the frontend side.
2) On server side we can ask to update key whenever we update value in DB.
For this approach we have aud tables in order to fetch last updated values if we not sure that which values were
and which were not update with new values (in case of ETL processing of large amount of data we not always sure that data was updated).
For this approach i would be using kafka and insert all changes been done on DB into 1 topic.
On this topic we set rule to aggregate up to 5-10 minutes range or 10 000 entry messages and split to handling up to 5-10 minutes.
OFC everything needs to be handled accordingly to the expected heaviness of loading.