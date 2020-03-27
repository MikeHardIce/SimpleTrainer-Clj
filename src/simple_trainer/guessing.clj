(ns simple-trainer.guessing)

(defn guess-loop-handler [selection {:keys [rnd-number read out continue-with end-with]} ]
  (let [guess (if (> (count selection) 0) (Integer/parseInt selection) 0)]
    (cond
      (= guess rnd-number) (do 
                              (out "Yes thats it!")
                              (end-with))
      (> guess rnd-number) (do  (out "Choose a smaller number")
                                (continue-with read out rnd-number end-with))
      :else (do (out "Choose a bigger number")
                (continue-with read out rnd-number end-with)))))

(defn guess-loop [read-and-call out rnd-number end-with]
  (read-and-call guess-loop-handler {:rnd-number rnd-number :read read-and-call :out out :continue-with guess-loop :end-with end-with}))
    
(defn start [read-and-call out max-number end-with]
  (let [rnd-number (rand-int max-number)]
    (println "Inside start guessing")
    (out "Guess a number between 1 and " max-number)
    (guess-loop read-and-call out rnd-number end-with)))