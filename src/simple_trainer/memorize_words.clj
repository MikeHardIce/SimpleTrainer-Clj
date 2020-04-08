(ns simple-trainer.memorize-words
  (:require [simple-trainer.mode :as m])
  (:require [clojure.string :as cs]))
  
(defn black-out-word [word]
  (if (empty? word) "" 
      (let [sub (subs word 1 (dec (count word)))]
        (cs/join "" [(first word) (cs/replace sub #"\w" "*") (last word)]))))

(def words (atom []))
(def word (atom ""))

(defn start-questioning
  ([out] (start-questioning out "")) 
  ([out info]
    (let [chosen-word (rand-nth @words)
          hidden-word (black-out-word chosen-word)]
            (swap! word (fn [x] chosen-word))
            (out (str info "Which word is in " hidden-word " ?:")))))

(defmethod m/handle-input :mem-read-words [out input]
  (cond 
      (= input 0) (do  (m/switch-mode :mem-ask)
                          (start-questioning out))
      :else (do (swap! words (fn [x] (conj x input)))
                (out "Word added! Type another word or submit empty when done."))))

(defmethod m/handle-input :mem-ask [out input]
  (cond 
    (= input 0) (do (m/switch-mode :menu)
              (out "Getting out of here ...")
              (out))
    (= input @word) (start-questioning out "Yes, thats it! ")
    :else (out (str "Nope ... try again. So which word hides behind " (black-out-word @word) " ?"))))

(defn start [out]
  (m/switch-mode :mem-read-words)
  (out "Welcome to the word memorizer, type in some words to memorize"))