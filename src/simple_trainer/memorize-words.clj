(ns simple-trainer.memorize-words)

(defn attach [words]
  (println "Type a word to memorize (leave empty when you are done):")
  (let [word (read-string (read-line))]
    (when (> (count word) 0) [(conj word words)])))