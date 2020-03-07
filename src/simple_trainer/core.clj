(ns simple-trainer.core
  (:require [simple-trainer.guessing :as guess])
  (:require [simple-trainer.memorize-words :as memo])
  (:gen-class))

(defn display-options []
  (println "Here are your options:")
  (println "1 - Guessing Numbers")
  (println "2 - Memorizing Words")
  (println "0 - Quit"))

(defn out [& args]
  (println args))

(defn menu []
  (display-options)
  (let [selection (read-string (do (flush) (read-line)))]
    (cond 
      (= selection 1) [(println "Starting \"Guessing Numbers\"...")
                       (guess/start out 100)
                       (menu)]
      (= selection 2) [(println "Starting \"Memorizing Words\"...")
                       (memo/start out)
                       (menu)])))

(defn -main 
  "Starts the simple trainer"
  [& args]
  (println "Welcome to the Simple Trainer ...")
  (menu))



