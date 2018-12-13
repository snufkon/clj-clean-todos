(ns clj-clean-todos.usecase.delete-todo-test
  (:require [clj-clean-todos.usecase.delete-todo :as delete-todo]
            [clj-clean-todos.repos.core :refer :all]
            [clj-clean-todos.repos.inmemory :refer [create-inmemory]]
            [clj-clean-todos.spec :as spec]
            [clj-clean-todos.testdata :as td]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.alpha :as s]
            [clojure.test :refer :all]))

(def repos (create-inmemory))

(use-fixtures :once (fn [f]
                      (stest/instrument)
                      (f)
                      (stest/unstrument)))

(use-fixtures :each (fn [f]
                      (doseq [title td/titles]
                        (store repos title))
                      (f)
                      (remove-all repos)))


(deftest delete-todo
  (testing "returns 'true' if deleted"
    (is (= true (delete-todo/handle repos 1))))

  (testing "returns 'false' if not exists"
    (is (= false (delete-todo/handle repos 10)))))
