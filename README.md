# Electrolux

###### DB snapshot
```
create table appliances
(
    id            bigserial   not null
        constraint appliances_pkey
            primary key,
    type          varchar(50) not null,
    state         varchar(50) not null,
    registry_date timestamp   not null,
    state_changed timestamp   not null,
    last_update   timestamp   not null,
    constraint state_changed_timestamp
        check (state_changed >= registry_date),
    constraint last_update_timestamp
        check (last_update >= state_changed)
);
```
###### Save STATE
URL http://localhost:8080/check/in
Method POST text/json

JSON example:
```
{
    "id": 1,
    "type": "WASHMACHINE",
    "state": "HOLD"
}
```
There is two Appliances WASHMACHINE and OVEN each has private and shared states; See this enum:
```
    WASHMACHINE(DRAIN, SPIN, DRYING, SHUTDOWN, WASH, QUICK_WASH, WASH_SHOES, RINSE, OUTDOOR_CARE, SOAK, HOLD),
    OVEN(GRILL, SHUTDOWN, WARM_UP, HOLD);
  ``` 
   ###### get Appliance by id
   URL http://localhost:8080/check/in?id={id}
Method GET
Make sure that the Appliance is exist in you DataBase

