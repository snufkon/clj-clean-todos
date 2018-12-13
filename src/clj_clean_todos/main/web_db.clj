(ns clj-clean-todos.main.web-db
  (:require [clj-clean-todos.ui.web.core :refer [create-jetty start]]
            [clj-clean-todos.repos.db :refer [create-database]]))

(defn -main
  []
  (let [mysql-db {:dbtype "mysql"
                  :dbname "clj_clean_todos"
                  :user "clj_dev"
                  :password "password"}
        repos (create-database mysql-db)
        server (create-jetty repos)]
    (start server)))
