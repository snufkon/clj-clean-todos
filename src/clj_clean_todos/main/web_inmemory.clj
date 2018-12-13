(ns clj-clean-todos.main.web-inmemory
  (:require [clj-clean-todos.ui.web.core :refer [create-jetty start]]
            [clj-clean-todos.repos.inmemory :refer [create-inmemory]]))

(defn -main
  []
  (let [repos (create-inmemory)
        server (create-jetty repos)]
    (start server)))
