(ns simple-trainer.guessing)

(defn guess-loop [rnd-number]
  (let [guess (read-string (read-line))]
    (cond
      (= guess rnd-number) (println "Yes thats it!")
      (> guess rnd-number) [(println "Choose a smaller number")
                            (guess-loop rnd-number)]
      :else [(println "Choose a bigger number")
             (guess-loop rnd-number)])))

(defn start [max-number]
  (let [rnd-number (rand-int max-number)]
    (println "Guess a number between 1 and " max-number)
    (guess-loop rnd-number)))