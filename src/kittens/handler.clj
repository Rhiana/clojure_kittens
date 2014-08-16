(ns kittens.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(def last-result
  (atom 0))

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
    </form>
    <form action='/guessing'>
      <fieldset>
        <legend>What number am I thinking of?</legend>
        <label for='guess_number'>Guess a Number between 1 and 100</label>
        <input type='number' id='guess_number' name='guess_number'>
        <input type='submit' value='Guess!'>
      </fieldset>
    </form>")

  (GET "/calculate" [first_number second_number]
    (let  [result (+ (Integer/parseInt first_number)
          (Integer/parseInt second_number))
        last-num (deref last-result)]
      (reset! last-result result)
      (str "Last: " last-num
           "<br>New: " first_number " + " second_number " = " result
           "<br><a href='/'>Back</a>")))

  (GET "/guessing" [guess_number]
      ( let [winning_number 27]
        (if (= guess_number winning_number) (def message "You win!")
          (if (> guess_number winning_number) (def message "Too big try again") (def message "Too small try again"))
        )
        (str "Your number was " guess_number
        "<br> The winning number was " winning_number
        "Your number was " message
        "<br><a href='/'>Back</a>"))
    )


    (route/resources "/")
    (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
