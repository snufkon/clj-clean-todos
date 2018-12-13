(ns clj-clean-todos.ui.web.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as res]
            [clj-clean-todos.ui.web.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clj-clean-todos.usecase.index :as usecase]))

(defn- show-todos
  [repos req]
  (-> (usecase/get-todos repos)
      view/index))

(defn- create-todo
  [repos {:keys [params] :as req}]
  (let [title (:title params)]
    (usecase/create-todo repos title))
  (res/redirect "/todos"))

(defn- delete-todo
  [repos {:keys [params] :as req}]
  (let [id (-> (:id params) Integer/parseInt)]
    (usecase/delete-todo repos id))
  (res/redirect "/todos"))

(defn- create-routes
  [repos]
  (routes
   (GET  "/"                 req (res/redirect "/todos"))
   (GET  "/todos"            req (show-todos repos req))
   (POST "/todos"            req (create-todo repos req))
   (POST "/todos/:id/delete" req (delete-todo repos req))
   
   (route/not-found "Not Found")))

(defn create-handler
  [repos]
  (-> (create-routes repos)
      (wrap-defaults site-defaults)))
