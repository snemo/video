# Bonus module

## Responsibility
Bonus module is responsible only for storing customer bonuses and calculating total amount of bonuses.

## Architecture
It is a very simple version of event sourcing based on SQL DB with no separate Query model.
In this case this is a very simple and good architecture, because we have history of all bonus events,
as well as we can use SQL mechanism to calculate current state. 