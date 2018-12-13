(ns clj-clean-todos.ui.console.view)

(defn action-list
  []
  (println "actions ---------------------")
  (println "1. Create todo")
  (println "2. Delete todo")
  (println "3. List todos")
  (println "4. Delete all todos")
  (println "0. Exit")
  (println "-----------------------------"))

(defn action-prompt
  []
  (print "Which action? ")
  (flush))

(defn bye-message
  []
  (println "Bye."))

(defn retry-message
  []
  (println "Please enter a number from 0 to 4."))

(defn invalid-number-message
  []
  (println "Please enter a number"))

(defn new-line
  []
  (newline))


;;; create view

(defn create-prompt
  []
  (print "input todo title: ")
  (flush))

(defn created-result
  [todo]
  (println "created: " (:title todo)))


;;; delete view

(defn delete-prompt
  []
  (print "input todo id: ")
  (flush))

(defn delete-success
  [id]
  (-> (format "deleted: todo(id=%d)" id)
      println))

(defn delete-failure
  [id]
  (-> (format "failed to delete a todo which id is %s" id)
      println))


;;; list view

(defn todolist
  [todos]
  (println "todo items ------------------")
  (doseq [todo todos]
    (-> (format " %d: %s" (:id todo) (:title todo))
        println))
  (println "-----------------------------"))

(defn empty-message
  []
  (println "todo list is empty"))


;;; delete all view

(defn delete-all-message
  []
  (println "deleted: all todos"))
