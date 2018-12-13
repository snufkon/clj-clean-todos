(ns clj-clean-todos.usecase.get-todos
  (:require [clj-clean-todos.repos.core :refer [find-all]]))

(defn handle
  [repos]
  (find-all repos))
