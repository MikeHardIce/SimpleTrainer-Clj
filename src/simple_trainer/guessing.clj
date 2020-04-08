(ns simple-trainer.guessing
  (:require [simple-trainer.mode :as m]))

(def number-to-guess (atom 0))

(defmethod m/handle-input :guess [out input]
  (let [guess (if (> (count input) 0) (Integer/parseInt input) 0)]
    (cond
      (= guess @number-to-guess) (do (out "Yes thats it!")
                                     (m/switch-mode :menu)
                                     (out))
      (> guess @number-to-guess) (out "Choose a smaller number")
      :else (out "Choose a bigger number"))))

(defn start [out max-number]
    (swap! number-to-guess (fn [x] (rand-int max-number)))
    (m/switch-mode :guess)
    (out "Guess a number between 1 and " max-number))