(ns clj-clean-todos.repos.inmemory
  (:require [clj-clean-todos.repos.core :refer :all]))

(defn- allocate-next-id
  [{:keys [repository]}]
  (-> @repository
      keys
      last
      ((fnil inc 0))))

(defn- ->entity-todo
  [{:keys [id title] :as inmemory-todo}]
  {:id id
   :title title})

(defrecord InMemory [repository]
  IRepository
  (_store [{:keys [repository] :as this} title]
    (let [id (allocate-next-id this)
          todo {:id id :title title}]
      (swap! repository assoc id todo)
      todo))
  
  (_find-all [{:keys [repository] :as this}]
    (->> repository
         deref
         vals
         (mapv ->entity-todo)))
  
  (_remove-by-id [{:keys [repository] :as this} id]
    (let [found (contains? @repository id)]
      (swap! repository dissoc id)
      found))
  
  (_remove-all [{:keys [repository] :as this}]
    (reset! repository {})))

(defn create-inmemory
  []
  (let [repository (atom {})]
    (->InMemory repository)))
