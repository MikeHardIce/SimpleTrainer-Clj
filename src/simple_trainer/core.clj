(ns simple-trainer.core
  (:require [simple-trainer.guessing :as guess])
  (:gen-class))

(defn display-options []
  (println "Here are your options:")
  (println "1 - Guessing Numbers")
  (println "2 - Memorizing Words")
  (println "0 - Quit"))

(defn menu []
  (display-options)
  (let [selection (read-string (read-line))]
    (cond 
      (= selection 1) [(println "Starting \"Guessing Numbers\"...")
                       (guess/start 100)
                       (menu)]
      (= selection 2) [(println "Starting \"Memorizing Words\"...")
                       (menu)])))

(defn -main 
  "Starts the simple trainer"
  [& args]
  (println "Welcome to the Simple Trainer ...")
  (menu))


;;(require simple-trainer.guessing :as guess)


