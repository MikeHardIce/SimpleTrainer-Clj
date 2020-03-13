(ns simple-trainer.memorize-words
  (:require [clojure.string :as cs]))

(defn read-words [out words]
  (out "Type a word to memorize (leave empty when you are done):")
  (let [word (do (flush) (read-line))]
    (if (empty? word) words 
        (read-words out (conj words word)))))

(defn black-out-word [word]
  (if (empty? word) "" 
      (let [sub (subs word 1 (dec (count word)))]
        (cs/join "" [(first word) (cs/replace sub #"\w" "*") (last word)]))))

(defn question-until-correct [out hidden-word word]
  (let [input (do (flush) (read-line))]
    (cond 
      (empty? input) (out "Getting out of here ...")
      (= input word) (out "Yes that is it!")
      :else (do (out "Nope ... try again")
                (question-until-correct out hidden-word word)))))

(defn start-questioning [out words]
  (let [word (rand-nth words)
        hidden-word (black-out-word word)]
    (out (str "Which word is in " hidden-word " ?:"))
    (question-until-correct out hidden-word word)
    (out "More? (Y/n)")
    (let [input (do (flush) (read-line))]
      (when (= (cs/lower-case input) "y") (start-questioning out words)))))

(defn start [out]
  (out "Welcome to the word memorizer")
  (let [words (read-words out [])]
    (start-questioning out words)))
