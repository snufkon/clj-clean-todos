(ns clj-clean-todos.ui.web.view
  (:require [hiccup.page :refer [html5]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [hiccup.form :refer [form-to]]))

(defn- layout
  [& contents]
  (html5
   [:head]
   [:body contents]))

(defn- todo-input
  []
  (form-to
   [:post "/todos"]
   (anti-forgery-field)
   [:input {:type "text"
            :name "title"}]))

(defn- todo-item
  [todo]
  (let [action (format "todos/%d/delete" (:id todo))]
    [:li (:title todo)
     [:input {:type "submit" :value "削除" :formaction action}]]))

(defn- todo-list
  [todos]
  (form-to
   [:post "/todos/:id/delete"]
   (anti-forgery-field)
   [:ul
    (for [todo todos]
      (todo-item todo))]))

(defn index
  [todos]
  (layout
   (todo-input)
   (todo-list todos)))
