(ns clj-clean-todos.main.console-db
  (:require [clj-clean-todos.ui.console.core :as console]
            [clj-clean-todos.repos.db :refer [create-database]]))

(defn -main
  []
  (let [mysql-db {:dbtype "mysql"
                  :dbname "clj_clean_todos"
                  :user "clj_dev"
                  :password "password"}
        repos (create-database mysql-db)]
    (console/start repos)))
