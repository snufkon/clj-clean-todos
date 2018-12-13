(ns clj-clean-todos.usecase.delete-todos-test
  (:require [clj-clean-todos.usecase.delete-todos :as delete-todos]
            [clj-clean-todos.repos.core :refer :all]
            [clj-clean-todos.repos.inmemory :refer [create-inmemory]]
            [clj-clean-todos.testdata :as td]
            [clojure.spec.test.alpha :as stest]
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


(deftest delete-todos
  (testing "makes repository to empty"
    (delete-todos/handle repos)
    (is (empty? (find-all repos)))))
