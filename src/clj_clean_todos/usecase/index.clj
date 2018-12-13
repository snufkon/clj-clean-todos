(ns clj-clean-todos.usecase.index
  (:require [clj-clean-todos.usecase.get-todos]
            [clj-clean-todos.usecase.create-todo]
            [clj-clean-todos.usecase.delete-todo]
            [clj-clean-todos.usecase.delete-todos]))

(defn get-todos
  [repos]
  (clj-clean-todos.usecase.get-todos/handle repos))

(defn create-todo
  [repos todo]
  (clj-clean-todos.usecase.create-todo/handle repos todo))

(defn delete-todo
  [repos id]
  (clj-clean-todos.usecase.delete-todo/handle repos id))

(defn delete-todos
  [repos]
  (clj-clean-todos.usecase.delete-todos/handle repos))
