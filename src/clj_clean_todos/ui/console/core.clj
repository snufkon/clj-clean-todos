(ns clj-clean-todos.ui.console.core
  (:require [clj-clean-todos.usecase.index :as usecase]
            [clj-clean-todos.ui.console.view :as view]
            [clojure.string :as cstr]))

(defn- input->number
  [s]
  (try
    (-> s
        cstr/trim
        Integer/parseInt)
    (catch NumberFormatException _
      nil)))

(defn- create-todo-action
  [repos]
  (view/create-prompt)
  (when-let [input (read-line)]
    (let [title (-> input cstr/trim)
          todo (usecase/create-todo repos title)]
      (view/created-result todo)
      true)))

(defn- delete-todo-action
  [repos]
  (view/delete-prompt)
  (when-let [input (read-line)]
    (if-let [id (input->number input)]
      (if-let [result (usecase/delete-todo repos id)]
        (view/delete-success id)
        (view/delete-failure id))
      (view/invalid-number-message))
    true))

(defn- list-todos-action
  [repos]
  (let [todos (usecase/get-todos repos)]
    (if (empty? todos)
      (view/empty-message)
      (view/todolist todos))
    true))

(defn- delete-all-todos-action
  [repos]
  (usecase/delete-todos repos)
  (view/delete-all-message)
  true)

(defn- exit-action
  [_]
  (view/bye-message)
  false)

(defn- retry-action
  [_]
  (view/retry-message)
  true)

(defn- action-loop
  [repos]
  (view/action-prompt)
  (when-let [input (read-line)]
    (let [action (case (input->number input)
                   0 exit-action
                   1 create-todo-action
                   2 delete-todo-action
                   3 list-todos-action
                   4 delete-all-todos-action
                   retry-action)]
      (when (action repos)
        (action-loop repos)))))

(defn start
  [repos]
  (view/action-list)
  (action-loop repos))
