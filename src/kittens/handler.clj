(ns kittens.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "
    <form action='/calculate'>
      <fieldset>
        <legend>Addition!</legend>
        <label for='first_number'>First Number</label>
        <input type='number' id='first_number' name='first_number' step='any'>
        <label for='second_number'>Second Number</label>
        <input type='number' id='second_number' name='second_number' step='any'>
        <input type='submit' value='Add!'>
      </fieldset>
    </form>")
  (GET "/calculate" [first_number second_number]
    (str first_number " + " second_number " = "
      (+  (Integer/parseInt first_number)
          (Integer/parseInt second_number))))

    (route/resources "/")
    (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
