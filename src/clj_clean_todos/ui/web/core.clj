(ns clj-clean-todos.ui.web.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clj-clean-todos.ui.web.handler :as handler]))

(defprotocol ILifecycle
  (start [this])
  (stop [this]))

(defrecord Jetty [repos port]
  ILifecycle
  (start [this]
    (when-not (:server this)
      (->> (run-jetty
            (handler/create-handler repos)
            {:port port :join? false})
           (assoc this :server))))
  (stop [this]
    (when-let [server (:server this)]
      (.stop server)
      (dissoc this :server))))

(defn create-jetty
  ([repos]
   (create-jetty repos 3000))
  ([repos port]
   (->Jetty repos port)))
