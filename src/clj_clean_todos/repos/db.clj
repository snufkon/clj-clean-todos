(ns clj-clean-todos.repos.db
  (:require [clj-clean-todos.repos.core :refer :all]
            [clojure.java.jdbc :as j]))

(defn- create-todo-table
  [db-spec]
  (let [todo-table-ddl (j/create-table-ddl
                        :todo
                        [[:id "INT NOT NULL AUTO_INCREMENT PRIMARY KEY"]
                         [:title "VARCHAR(32) NOT NULL"]
                         [:created_at "BIGINT NOT NULL"]]
                        {:conditional? true})]
    (j/db-do-commands db-spec [todo-table-ddl])))

(defn- ->entity-todo
  [{:keys [id title] :as db-todo}]
  {:id id
   :title title})

(defrecord Database [db-spec]
  IRepository
  (_store [this title]
    (let [db-todo {:title title
                   :created_at (System/currentTimeMillis)}
          result (j/insert! (:db-spec this) :todo db-todo)
          id (-> result first :generated_key long)]
      {:id id :title title}))
  
  (_find-all [this]
    (->> (j/query (:db-spec this) ["SELECT * FROM todo"])
         (mapv ->entity-todo)))
  
  (_remove-by-id [this id]
    (let [result (j/delete! (:db-spec this) :todo ["id = ?" id])]
      (if (= 0 (first result))
        false
        true)))
  
  (_remove-all [this]
    (j/execute! (:db-spec this) "TRUNCATE TABLE todo")))

(defn create-database
  [db-spec]
  (create-todo-table db-spec)
  (->Database db-spec))
