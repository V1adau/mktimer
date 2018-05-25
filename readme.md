# mktimer

This is a tiny web app you can use for timing things. For example for keeping
track of work hours.

## Building

```shell
$ mvn clean package docker:build
```

To also push the created tags to dockerhub (will create `:latest` and `:version`
every time) use:

```shell
$ mvn clean package docker:build -DpushImage
```

## To do

-   Add flyway support
-   Add a UUID field to all acitivies and categories
-   Add endpoint for editing an activity
-   Add JWT authentication for all endpoints (not the start activity endpoint due
    to tasker)
-   Add ics-endpoint which generates a calendar with activities for a given
    category
-   Build a frontend
