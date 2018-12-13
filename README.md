# clj-clean-todos

Todo example based on Clean Architecture in Clojure.

## Prerequisites

- [Clojure CLI](https://clojure.org/guides/deps_and_cli) installed.
- MySQL installed (for db example and test).

## Setup

Create databases.

```
$ echo "create database clj_clean_todos" | mysql -u root -p
$ echo "create database clj_clean_todos_test" | mysql -u root -p
```


## Run

**Console UI and InMemory Repository**

```
$ clj -m clj-clean-todos.main.console-inmemory
```

**Console UI and Database Repository**


Change mysql user and password on `src/clj_clean_todos/console_db.clj`.

```
$ clj -m clj-clean-todos.main.console-db
```

**Web UI and InMemory Repository**

```
$ clj -m clj-clean-todos.main.web-inmemory
```

**Web UI and Database Repository**

Change mysql user and password on `src/clj_clean_todos/web_db.clj`.

```
$ clj -m clj-clean-todos.main.web-db
```


## Test

Change mysql user and password on `test/clj_clean_todos/db_test.clj`.

You can run test by following

```
$ clj -Atest
```


## License

Copyright © 2018 FIXME
