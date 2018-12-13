(ns clj-clean-todos.repos.core)

(defprotocol IRepository
  (_find-all [this])
  (_store [this title])
  (_remove-by-id [this id])
  (_remove-all [this]))

(defn repository?
  [repos]
  (satisfies? IRepository repos))


;;; wrapper functions to use spec test
;;; ref: https://dev.clojure.org/jira/browse/CLJ-2109

(defn find-all
  [repos]
  (_find-all repos))

(defn store
  [repos title]
  (_store repos title))

(defn remove-by-id
  [repos id]
  (_remove-by-id repos id))

(defn remove-all
  [repos]
  (_remove-all repos))
