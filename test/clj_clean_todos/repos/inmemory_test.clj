(ns clj-clean-todos.repos.inmemory-test
  (:require [clj-clean-todos.repos.core :refer [store find-all remove-by-id remove-all]]
            [clj-clean-todos.repos.inmemory :refer [create-inmemory]]
            [clj-clean-todos.spec :as spec]
            [clj-clean-todos.testdata :as td]
            [clojure.test :refer :all]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.alpha :as s]))

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

(deftest store-test
  (testing "returns created todo entity"
    (let [todo (store repos "send email to Joy")]
      (is (s/valid? ::spec/todo todo)))))

(deftest find-all-test
  (testing "returns all tests"
    (let [todos (find-all repos)]
      (is (s/valid? ::spec/todos todos)))))

(deftest remove-by-id-test
  (testing "returns true if deleted"
    (let [result (remove-by-id repos 1)]
      (is (= result true))))
  (testing "returns false if not exists"
    (let [result (remove-by-id repos 10)]
      (is (= result false)))))

(deftest remove-all-test
  (testing "makes repository to empty"
    (is (not= [] (find-all repos)))
    (remove-all repos)
    (is (= [] (find-all repos)))))
