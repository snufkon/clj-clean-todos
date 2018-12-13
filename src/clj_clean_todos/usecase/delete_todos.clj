(ns clj-clean-todos.usecase.delete-todos
  (:require [clj-clean-todos.repos.core :refer [remove-all]]))

(defn handle
  [repos]
  (remove-all repos))
