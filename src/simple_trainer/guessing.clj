(ns simple-trainer.guessing)

(defn guess-loop [out rnd-number]
  (let [input (do (flush) (read-line))
        guess (if (> (count input) 0) (Integer/parseInt input) 0)]
    (cond
      (= guess rnd-number) (out "Yes thats it!")
      (> guess rnd-number) [(out "Choose a smaller number")
                            (guess-loop out rnd-number)]
      :else [(out "Choose a bigger number")
             (guess-loop out rnd-number)])))

(defn start [out max-number]
  (let [rnd-number (rand-int max-number)]
    (out "Guess a number between 1 and " max-number)
    (guess-loop out rnd-number)))