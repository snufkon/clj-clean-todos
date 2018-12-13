(ns clj-clean-todos.usecase.get-todos-test
  (:require [clj-clean-todos.usecase.get-todos :as get-todos]
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


(deftest get-todos
  (testing "returns todos"
    (let [todos (get-todos/handle repos)]
      (is (s/valid? ::spec/todos todos))
      (is (= 3 (count todos))))))
