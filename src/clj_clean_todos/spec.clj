(ns clj-clean-todos.spec
  (:require
   [clj-clean-todos.usecase.get-todos :as get-todos]
   [clj-clean-todos.usecase.create-todo :as create-todo]
   [clj-clean-todos.usecase.delete-todo :as delete-todo]
   [clj-clean-todos.usecase.delete-todos :as delete-todos]
   [clj-clean-todos.repos.core :as repos :refer [repository?]]
   [clojure.spec.alpha :as s]))


;;; Entity

(s/def ::id pos-int?)
(s/def ::title string?)
(s/def ::todo (s/keys :req-un [::id ::title]))
(s/def ::todos (s/coll-of ::todo))


;;; Repository

(s/def ::repos repository?)

(s/fdef repos/store
  :args (s/cat :repos ::repos :title ::title)
  :ret ::todo)

(s/fdef repos/find-all
  :args (s/cat :repos ::repos)
  :ret ::todos)

(s/fdef repos/remove-by-id
  :args (s/cat :repos ::repos :id ::id)
  :ret boolean?)

(s/fdef repos/remove-all
  :args (s/cat :repos ::repos))


;;; Usecases

(s/fdef get-todos/handle
  :args (s/cat :repos ::repos)
  :ret ::todos)

(s/fdef create-todo/handle
  :args (s/cat :repos ::repos :title ::title)
  :ret ::todo)

(s/fdef delete-todo/handle
  :args (s/cat :repos ::repos :id ::id)
  :ret boolean?)

(s/fdef delete-todos/handle
  :args (s/cat :repos ::repos))
