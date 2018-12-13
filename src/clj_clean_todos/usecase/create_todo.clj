(ns clj-clean-todos.usecase.create-todo
  (:require [clj-clean-todos.repos.core :refer [store]]))

(defn handle
  [repos todo]
  (store repos todo))
