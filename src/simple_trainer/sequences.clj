(ns simple-trainer.sequences
    (:require [simple-trainer.mode :as m]))

(def q (atom 0))

(defn seq-to-guess [f q x]
    (cons x (lazy-seq (seq-to-guess f q (f x q)))))

(defmethod m/handle-input :sequence [out input]
    )

(defn start [out max-number]
    (swap! q (fn [x] (rand-int max-number)))
    (m/switch-mode :sequence)
    (out (str "What is the next element in the given sequence?\n"
            (apply str (map #(str % " ") (take 6 (seq-to-guess + @q 1)))))))
