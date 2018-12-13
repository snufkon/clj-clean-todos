(ns clj-clean-todos.main.console-inmemory
  (:require [clj-clean-todos.ui.console.core :as console]
            [clj-clean-todos.repos.inmemory :refer [create-inmemory]]))

(defn -main
  []
  (let [repos (create-inmemory)]
    (console/start repos)))
