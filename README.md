# ledger-transactions

## Pre-Req
- install restate-server in your local machine: https://docs.restate.dev/get_started/quickstart
- Java-21 [installation-guide](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)

### Steps for running project.
- for Building Clean solution: `./gradlew clean build`
- Run application with following command `./gradlew run`
- verify our application is running by hitting health check url status code need to be 200
    - http://localhost:7070/health -> response will be "OK"
    - http://localhost:9090/health -> response will be "success"
- makesure `restate-server` command is running in background or in different terminal
- then register your restate-service to your local restate-server. with `restate deployments register http://localhost:7070 --force`
- once registeration is done. you can verify by calling API service API for calculatingFee.


### API format: 

```
# @name MainService api response which was required
POST http://localhost:9000/transaction/fee HTTP/1.1
Content-Type: application/json

{
    "transaction_id": "txn_001",
    "amount": 98000,
    "asset": "USD",
    "asset_type": "FIAT",
    "type": "Mobile Top Up",
    "state": "SETTLED - PENDING FEE",
    "created_at": "2023-08-30T15:42:17.610059"
}

##########
# @name RestateApi Request
POST http://127.0.0.1:8080/FeeService/calculateFee HTTP/1.1
# Content-Type: application/x.restate.v1+json
Content-Type: application/json

{
    "amount": 98000,
    "type": "Mobile Top Up."
}

```
