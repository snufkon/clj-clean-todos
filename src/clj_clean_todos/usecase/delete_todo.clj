(ns clj-clean-todos.usecase.delete-todo
  (:require [clj-clean-todos.repos.core :refer [remove-by-id]]))

(defn handle
  [repos id]
  (remove-by-id repos id))
