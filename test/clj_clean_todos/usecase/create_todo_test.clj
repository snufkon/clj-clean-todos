(ns clj-clean-todos.usecase.create-todo-test
  (:require [clj-clean-todos.usecase.create-todo :as create-todo]
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


(deftest create-todo
  (testing "returns created todo"
    (let [title "send email to Mike"
          created (create-todo/handle repos title)]
      (is (s/valid? ::spec/todo created))
      (is (= {:id 4 :title title} created)))))
